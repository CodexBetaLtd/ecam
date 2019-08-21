package com.neolith.focus.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException; 
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.neolith.focus.dao.admin.UserJobTitleDao;
import com.neolith.focus.dao.biz.BusinessDao;
import com.neolith.focus.dto.admin.UserJobTitleDTO;
import com.neolith.focus.mappers.admin.UserJobTitleMapper;
import com.neolith.focus.model.admin.UserJobTitle;
import com.neolith.focus.repository.FocusDataTablesInput;
import com.neolith.focus.result.admin.UserJobTitleResult;
import com.neolith.focus.service.admin.api.UserJobTitleService;
import com.neolith.focus.util.AuthenticationUtil;
import com.neolith.focus.util.search.admin.UserJobTitleSearchPropertyMapper;

@Service
public class UserJobTitleServiceImpl implements UserJobTitleService {

	@Autowired
	UserJobTitleDao  userJobTitleDao;

	@Autowired
	private BusinessDao businessDao;

	@Override
	public DataTablesOutput<UserJobTitleDTO> findAll(FocusDataTablesInput input) throws Exception {
		DataTablesOutput<UserJobTitle> domainOut;

		UserJobTitleSearchPropertyMapper.getInstance().generateDataTableInput(input);

		if(AuthenticationUtil.isAuthUserAdminLevel()){
			domainOut = userJobTitleDao.findAll(input);
		} else {
			Specification<UserJobTitle> specification = (root, query, cb) -> cb.equal(root.get("business"), AuthenticationUtil.getLoginUserBusiness());
			domainOut = userJobTitleDao.findAll(input, specification);
		}

		DataTablesOutput<UserJobTitleDTO> out = UserJobTitleMapper.getInstance().domainToDTODataTablesOutput(domainOut);

		return out;
	}

	@Override
	public UserJobTitleDTO findById(Integer id) throws Exception {
		UserJobTitle  domain = findEntityById(id);
		if (domain != null) {
			return UserJobTitleMapper.getInstance().domainToDto(domain);
		}
		return null;
	}

	private UserJobTitle findEntityById(Integer id) {
		return userJobTitleDao.findById(id);
	}

	@Override
	public UserJobTitleResult delete(Integer id) {
		UserJobTitleResult result = new UserJobTitleResult(null, null);
		try {
			userJobTitleDao.delete(id);
			result.setResultStatusSuccess();
			result.addToMessageList("User Job Title Deleted Successfully.");
		} catch (DataIntegrityViolationException e) {
			result.setResultStatusError();
			result.addToErrorList("User Job Title Already Assigned. Please Remove from Assigned User Job Title and try again.");
		} catch (Exception ex) {
			result.setResultStatusError();
			result.addToErrorList(ex.getMessage());
		}
		return result;
	}

	@Override
	public UserJobTitleResult save(UserJobTitleDTO dto) throws Exception {
		UserJobTitleResult result = createPriorityResult(dto);
		try{
			saveOrUpdate(result);
			result.addToMessageList(getMessageByAction(dto));
		} catch (ObjectOptimisticLockingFailureException e) {
			result.setResultStatusError();
			result.addToErrorList("User Job Title Already updated. Please Reload User Job Title.");
		} catch (Exception ex) {
			result.setResultStatusError();
			result.addToErrorList(ex.getMessage());
			ex.printStackTrace();
		}

		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void saveOrUpdate(UserJobTitleResult result) throws Exception {
		UserJobTitleMapper.getInstance().dtoToDomain(result.getDtoEntity(), result.getDomainEntity());
		setBusiness(result);
		userJobTitleDao.save(result.getDomainEntity());
		result.updateDtoIdAndVersion();
	}

	private String getMessageByAction(UserJobTitleDTO dto) {
		if (dto.getId() == null) {
			return "User Job Title Added Successfully.";
		} else {
			return "User Job Title Updated Successfully.";
		}
	}
	
	private UserJobTitleResult createPriorityResult(UserJobTitleDTO dto) {
		UserJobTitleResult result;
		if ((dto.getId() != null) && (dto.getId() > 0)) {
			result = new UserJobTitleResult(userJobTitleDao.findOne(dto.getId()), dto);
		} else {
			result = new UserJobTitleResult(new UserJobTitle(), dto);
		}
		
		return result;
	}

	private void setBusiness(UserJobTitleResult result) {
		if ((result.getDtoEntity() != null) && (result.getDtoEntity().getBusinessId() != null)) {
			result.getDomainEntity().setBusiness(businessDao.findOne(result.getDtoEntity().getBusinessId()));
		}
	}

	@Override
	public List<UserJobTitleDTO> findAll() {
		Iterable<UserJobTitle> domainList = userJobTitleDao.findAll();
		try {
			return UserJobTitleMapper.getInstance().domainToDTOList(domainList);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
