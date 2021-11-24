package com.codex.ecam.service.budget.api;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.codex.ecam.constants.budget.BudgetStatus;
import com.codex.ecam.dto.budget.BudgetDTO;
import com.codex.ecam.repository.FocusDataTablesInput;
import com.codex.ecam.result.budget.BudgetResult;


public interface BudgetService {

	BudgetResult save(BudgetDTO aodDTO) throws Exception;

	BudgetResult update(BudgetDTO aodDTO) throws Exception;

	BudgetResult delete(Integer id) throws Exception;

	BudgetResult deleteMultiple(Integer[] ids) throws Exception;

	BudgetResult findById(Integer id) throws Exception;

	BudgetResult statusChange(Integer id, BudgetStatus status);

	List<BudgetDTO> findAll();

	DataTablesOutput<BudgetDTO> findAll(FocusDataTablesInput input) throws Exception;


}
