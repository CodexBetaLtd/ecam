package com.codex.ecam.service.budget.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codex.ecam.constants.budget.BudgetStatus;
import com.codex.ecam.dao.budget.BudgetDao;
import com.codex.ecam.dto.budget.BudgetDTO;
import com.codex.ecam.exception.inventory.stock.StockQuantityExceedException;
import com.codex.ecam.mappers.budget.BudgetMapper;
import com.codex.ecam.model.budget.Budget;
import com.codex.ecam.repository.FocusDataTablesInput;
import com.codex.ecam.result.budget.BudgetResult;
import com.codex.ecam.service.budget.api.BudgetService;
import com.codex.ecam.util.AuthenticationUtil;
import com.codex.ecam.util.search.budget.BudgetPropertyMapper;

@Service
public class BudgetServiceImpl implements BudgetService {

	@Autowired
	private BudgetDao budgetDao;

	private BudgetDTO findDTOById(Integer id) throws Exception {
		return BudgetMapper.getInstance().domainToDto(findEntityById(id));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private Budget findEntityById(Integer id) throws Exception {
		return budgetDao.findOne(id);
	}

	@Override
	public BudgetResult save(BudgetDTO dto) throws Exception {
		final BudgetResult result = new BudgetResult(new Budget(), dto);
		saveOrUpdate(result);
		result.addToMessageList("Budget Added Successfully.");
		return result;

	}

	@Override
	public BudgetResult update(BudgetDTO dto) throws Exception {
		final BudgetResult result = new BudgetResult(null, dto);
		try {
			final Budget domain = findEntityById(dto.getBudgetId());
			result.setDomainEntity(domain);
			saveOrUpdate(result);
			result.addToMessageList("Budget Updated Successfully.");
		} catch (final ObjectOptimisticLockingFailureException ex) {
			result.setResultStatusError();
			result.addToErrorList("Budget Already updated. Please Reload Budget.");
		} catch (final Exception e) {
			e.printStackTrace();
			result.setResultStatusError();
			result.addToErrorList(e.getMessage());
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void saveOrUpdate(BudgetResult result) throws Exception {
		BudgetMapper.getInstance().dtoToDomain(result.getDtoEntity(), result.getDomainEntity());
		setBudgetData(result);
		budgetDao.save(result.getDomainEntity());
		result.updateDtoIdAndVersion();
	}

	private void setBudgetData(BudgetResult result) throws Exception {

	}

	@Override
	public BudgetResult delete(Integer id) throws Exception {
		final BudgetResult result = new BudgetResult(null, null);
		try {
			deleteEntityById(id);
			result.setResultStatusSuccess();
			result.addToMessageList("Budget Deleted Successfully.");
		} catch (final Exception ex) {
			ex.printStackTrace();
			result.setResultStatusError();
			result.addToMessageList("Budget Deleted Unsuccessful. ".concat(ex.getMessage()));
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BudgetResult deleteMultiple(Integer[] ids) throws Exception {
		final BudgetResult result = new BudgetResult(null, null);
		try {
			for (final Integer id : ids) {
				budgetDao.delete(id);
			}
			result.setResultStatusSuccess();
			result.addToMessageList("Budget(s) Deleted Successfully.");
		} catch (final DataIntegrityViolationException e) {
			result.setResultStatusError();
			result.addToErrorList("Budget(s) Already Used. Cannot delete.");
		} catch (final Exception ex) {
			ex.printStackTrace();
			result.setResultStatusError();
			result.addToErrorList(ex.getMessage());
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void deleteEntityById(Integer id) throws Exception {
		budgetDao.delete(id);
	}

	@Override
	public BudgetResult findById(Integer id) throws Exception {
		final BudgetResult result = new BudgetResult(null, null);
		try {
			result.setDtoEntity(findDTOById(id));
			result.setResultStatusSuccess();
			result.addToMessageList("Budget Found.");
		} catch (final Exception ex) {
			ex.printStackTrace();
			result.setResultStatusError();
			result.addToMessageList("Error Occurred! Budget NOT Found.".concat(ex.getMessage()));
		}
		return result;
	}

	@Override
	public BudgetResult statusChange(Integer id, BudgetStatus status) {
		final BudgetResult result = new BudgetResult(null, null);
		try {
			result.setDomainEntity(findEntityById(id));
			budgetStatusChange(result);
			result.setResultStatusSuccess();
			result.addToMessageList("Budget status change successful.");
		} catch (final StockQuantityExceedException e) {
			e.printStackTrace();
			result.setResultStatusError();
			result.addToErrorList("Error Occurred! Budget Status Cannot change. ".concat(e.getMessage()));
		}
		catch (final Exception e) {
			e.printStackTrace();
			result.setResultStatusError();
			result.addToErrorList("Error Occurred! Budget Status Cannot change. ".concat(e.getMessage()));
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void budgetStatusChange(BudgetResult result) throws Exception {

	}

	@Override
	public List<BudgetDTO> findAll() {
		try {
			return BudgetMapper.getInstance().domainToDTOList(budgetDao.findAll());
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DataTablesOutput<BudgetDTO> findAll(FocusDataTablesInput input) throws Exception {
		BudgetPropertyMapper.getInstance().generateDataTableInput(input);

		Specification<Budget> specification = (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (AuthenticationUtil.isAuthUserGeneralLevel()) {
				predicates.add(cb.equal(root.get("business"), AuthenticationUtil.getLoginUserBusiness()));
				predicates.add(cb.equal(root.get("site"), AuthenticationUtil.getLoginSite().getSite()));
			} else  if (AuthenticationUtil.isAuthUserSystemLevel()) {
				predicates.add(cb.equal(root.get("business"), AuthenticationUtil.getLoginUserBusiness()));
			}
			return cb.and(predicates.toArray(new Predicate[0]));
		};

		DataTablesOutput<Budget> domainOut = budgetDao.findAll(input, specification);
		final DataTablesOutput<BudgetDTO> out = BudgetMapper.getInstance().domainToDTODataTablesOutput(domainOut);
		return out;
	}

}
