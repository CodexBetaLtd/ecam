package com.neolith.focus.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.neolith.focus.dao.admin.AccountDao;
import com.neolith.focus.dao.biz.BusinessDao;
import com.neolith.focus.dto.admin.AccountDTO;
import com.neolith.focus.mappers.admin.AccountMapper;
import com.neolith.focus.model.maintenance.Account;
import com.neolith.focus.repository.FocusDataTablesInput;
import com.neolith.focus.result.admin.AccountResult;
import com.neolith.focus.service.admin.api.AccountService;
import com.neolith.focus.util.AuthenticationUtil;
import com.neolith.focus.util.search.admin.AccountSearchPropertyMapper;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private BusinessDao businessDao;

	@Override
	public DataTablesOutput<AccountDTO> findAll(FocusDataTablesInput input) throws Exception {
		DataTablesOutput<Account> domainOut;

		AccountSearchPropertyMapper.getInstance().generateDataTableInput(input);

		if(AuthenticationUtil.isAuthUserAdminLevel()){
			domainOut = accountDao.findAll(input);
		} else {
			Specification<Account> specification = (root, query, cb) -> cb.equal(root.get("business"), AuthenticationUtil.getLoginUserBusiness());
			domainOut = accountDao.findAll(input, specification);
		}

		DataTablesOutput<AccountDTO> out = AccountMapper.getInstance().domainToDTODataTablesOutput(domainOut);

		return out;
	}

	@Override
	public AccountDTO findById(Integer id) throws Exception {
		Account domain = findEntityById(id);
		if (domain != null) {
			return AccountMapper.getInstance().domainToDto(domain);
		}
		return null;
	}

	@Override
	public Account findEntityById(Integer id) throws Exception {
		return accountDao.findById(id);
	}

	@Override
	public AccountResult delete(Integer id) {
		AccountResult result = new AccountResult(null, null);
		try {
			accountDao.delete(id);
			result.setResultStatusSuccess();
			result.addToMessageList("Account Deleted Successfully.");
		} catch (DataIntegrityViolationException e) {
			result.setResultStatusError();
			result.addToErrorList("Account Already Assigned. Please Remove from Assigned Account and try again.");
		} catch (Exception ex) {
			result.setResultStatusError();
			result.addToErrorList(ex.getMessage());
		}
		return result;
	}

	@Override
	public AccountResult save(AccountDTO dto) throws Exception {
		AccountResult result = createAccountResult(dto);
		try{
			saveOrUpdate(result);
			result.addToMessageList(getMessageByAction(dto));
		} catch (ObjectOptimisticLockingFailureException e) {
			result.setResultStatusError();
			result.addToErrorList("Account Already updated. Please Reload Account.");
		} catch (Exception ex) {
			result.setResultStatusError();
			result.addToErrorList(ex.getMessage());
		}

		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void saveOrUpdate(AccountResult result) throws Exception {
		AccountMapper.getInstance().dtoToDomain(result.getDtoEntity(), result.getDomainEntity());
		setBusiness(result);
		accountDao.save(result.getDomainEntity());
		result.updateDtoIdAndVersion();
	}

	private AccountResult createAccountResult(AccountDTO dto) {
		AccountResult result;
		if ((dto.getId() != null) && (dto.getId() > 0)) {
			result = new AccountResult(accountDao.findOne(dto.getId()), dto);
		} else {
			result = new AccountResult(new Account(), dto);
		}

		return result;
	}

	private String getMessageByAction(AccountDTO dto) {
		if (dto.getId() == null) {
			return "Account Added Successfully.";
		} else {
			return "Account Updated Successfully.";
		}
	}

	private void setBusiness(AccountResult result) {
		if ((result.getDtoEntity() != null) && (result.getDtoEntity().getBusinessId() != null)) {
			result.getDomainEntity().setBusiness(businessDao.findOne(result.getDtoEntity().getBusinessId()));
		}
	}

	@Override
	public List<AccountDTO> findAll() {
		try {
			Iterable<Account> domainList;
			if(AuthenticationUtil.isAuthUserAdminLevel()){
				domainList = accountDao.findAll();
			} else {
				Specification<Account> specification = (root, query, cb) -> cb.equal(root.get("business"), AuthenticationUtil.getLoginUserBusiness());
				domainList = accountDao.findAll(specification);
			}
			return AccountMapper.getInstance().domainToDTOList(domainList);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<AccountDTO> findAllByBusiness(Integer id) {
		try {
			List<AccountDTO> dtoOut =new ArrayList<>();
			Specification<Account> specification = (root, query, cb) -> cb.equal(root.get("business").get("id"), id);

			if( specification != null){
				List<Account> domainList = accountDao.findAll(specification);
				dtoOut = AccountMapper.getInstance().domainToDTOList(domainList);
			}
			return dtoOut;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void saveAll(List<AccountDTO> list) {
		for (AccountDTO dto : list) {
			try {
				save(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteAll() {
		accountDao.deleteAll();
	}

}
