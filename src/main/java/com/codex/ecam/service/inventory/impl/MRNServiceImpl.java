package com.codex.ecam.service.inventory.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codex.ecam.constants.inventory.AODStatus;
import com.codex.ecam.constants.inventory.MRNStatus;
import com.codex.ecam.dao.admin.UserDao;
import com.codex.ecam.dao.asset.AssetDao;
import com.codex.ecam.dao.biz.BusinessDao;
import com.codex.ecam.dao.inventory.MRNDao;
import com.codex.ecam.dao.maintenance.WorkOrderDao;
import com.codex.ecam.dto.inventory.mrn.MRNDTO;
import com.codex.ecam.dto.inventory.mrn.MRNItemDTO;
import com.codex.ecam.mappers.inventory.mrn.MRNMapper;
import com.codex.ecam.model.inventory.mrn.MRN;
import com.codex.ecam.model.inventory.mrn.MRNItem;
import com.codex.ecam.repository.FocusDataTablesInput;
import com.codex.ecam.result.inventory.MRNResult;
import com.codex.ecam.service.inventory.api.MRNService;
import com.codex.ecam.util.AuthenticationUtil;

@Service
public class MRNServiceImpl implements MRNService {

	@Autowired
	MRNDao mrnDao;
	
	@Autowired
	private AssetDao assetDao;

	@Autowired
	private BusinessDao businessDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WorkOrderDao workOrderDao;
	@Override
	public MRNResult newMRN() {
		// TODO Auto-generated method stub
		
		MRNResult result=new MRNResult(new MRN(), new MRNDTO());
		return result;
	}

	@Override
	public MRNResult save(MRNDTO dto) throws Exception {
		MRNResult result = new MRNResult(new MRN(), dto);
		try {
			saveOrUpdate(result);
			result.setResultStatusSuccess();
			result.addToMessageList("MRN Added Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultStatusError();
			result.addToMessageList("Receipt order save Unsuccessful. ".concat(e.getMessage()));
		}
		return result;
	}

	@Override
	public MRNResult update(MRNDTO dto) throws Exception {
		MRNResult result = new MRNResult(null, dto);
		try {
			MRN domain = findEntityById(dto.getId());
			result.setDomainEntity(domain);
			saveOrUpdate(result);
			result.addToMessageList("MRN Updated Successfully.");
		} catch (ObjectOptimisticLockingFailureException ex) {
			result.setResultStatusError();
			result.addToErrorList("MRN Already updated. Please Reload MRN.");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultStatusError();
			result.addToErrorList(e.getMessage());
		}
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void saveOrUpdate(MRNResult result) throws Exception {
		MRNMapper.getInstance().dtoToDomain(result.getDtoEntity(), result.getDomainEntity());
		setMRNData(result);
		mrnDao.save(result.getDomainEntity()); 
		result.setDtoEntity(findDTOById(result.getDomainEntity().getId())); 
	}
	
	private void setMRNData(MRNResult result) throws Exception {
		setMRNCustomer(result);
		setMRNRequestUser(result);
		setWorkOrder(result);
		setMRNStatus(result);
		setMRNItem(result);
		setBusinessSite(result);
		//setNextMRNNo(result);
	}
	
	private void setMRNCustomer(MRNResult result) {
		if ((result.getDtoEntity() != null) && (result.getDtoEntity().getMrnCustomerId() != null)) {
			result.getDomainEntity().setCustomer(businessDao.findOne(result.getDtoEntity().getMrnCustomerId()));
		}
	}

	private void setMRNRequestUser(MRNResult result) {
		if ((result.getDtoEntity() != null) && (result.getDtoEntity().getRequestedUserId() != null)) {
			result.getDomainEntity().setRequestedBy(userDao.findOne(result.getDtoEntity().getRequestedUserId()));
		}
	}

	private void setMRNStatus(MRNResult result) {
		if ((result.getDtoEntity() != null) && (result.getDtoEntity().getMrnStatus() != null)) {
			result.getDomainEntity().setMrnStatus(result.getDtoEntity().getMrnStatus());
		}
	}
	
	private void setWorkOrder(MRNResult result) {
		if (result.getDtoEntity() != null && result.getDtoEntity().getWoId() != null) {
			result.getDomainEntity().setWorkOrder(workOrderDao.findOne(result.getDtoEntity().getWoId()));
		}
	}
	
	private void setBusinessSite(MRNResult result) {
		if ((result.getDtoEntity().getBusinessId() != null) && (result.getDtoEntity().getBusinessId() > 0)) {
			result.getDomainEntity().setBusiness(businessDao.findOne(result.getDtoEntity().getBusinessId()));
		} else {
			result.getDomainEntity().setBusiness(AuthenticationUtil.getLoginUserBusiness());
		}
		if ((result.getDtoEntity().getSiteId() != null) && (result.getDtoEntity().getSiteId() > 0)) {
			result.getDomainEntity().setSite(assetDao.findOne(result.getDtoEntity().getSiteId()));
		} else if (!AuthenticationUtil.isAuthUserAdminLevel()) {
			result.getDomainEntity().setSite(AuthenticationUtil.getLoginSite().getSite());
		}
	}

	private void setMRNItem(MRNResult result) {
		
		Set<MRNItem> aodItems = new HashSet<>();
		List<MRNItemDTO> aodItemDTOs = result.getDtoEntity().getMrnItemDTOs();
		
		if (aodItemDTOs != null && aodItemDTOs.size() > 0) {
			
			Set<MRNItem> currentMRNItems = result.getDomainEntity().getMrnItems();
			
			for ( MRNItemDTO aodItemDTO : aodItemDTOs ) {
				
				MRNItem aodItem = new MRNItem();
				
				if ((currentMRNItems != null) && (currentMRNItems.size() > 0)) {
					MRNItem optional = currentMRNItems.stream().filter((x) -> x.getId().equals(aodItemDTO.getId())).findAny().orElseGet(MRNItem :: new);
					aodItem = optional; 
				} else {
					aodItem = new MRNItem();
				}
				
			//	createMRNItem(result, aodItemDTO, aodItem);
				aodItems.add(aodItem);
			}
		}
		result.getDomainEntity().setMrnItems(aodItems);
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private MRN findEntityById(Integer id) throws Exception {
		return mrnDao.findOne(id);
	}
	
	private MRNDTO findDTOById(Integer id) throws Exception {
		return MRNMapper.getInstance().domainToDto(findEntityById(id));
	}
	@Override
	public MRNResult delete(Integer id) throws Exception {
		MRNResult result = new MRNResult(null, null);
		try {
			deleteEntityById(id);
			result.setResultStatusSuccess();
			result.addToMessageList("MRN Deleted Successfully.");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.setResultStatusError();
			result.addToMessageList("MRN Deleted Unsuccessful. ".concat(ex.getMessage()));
		}
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void deleteEntityById(Integer id) throws Exception {
		mrnDao.delete(id);
	} 


	@Override
	public MRNResult findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public MRNResult statusChange(Integer id, MRNStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MRNDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTablesOutput<MRNDTO> findAll(FocusDataTablesInput input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTablesOutput<MRNItemDTO> findAll(FocusDataTablesInput input, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MRNResult statusChange(Integer id, AODStatus status) {
		// TODO Auto-generated method stub
		return null;
	}




}
