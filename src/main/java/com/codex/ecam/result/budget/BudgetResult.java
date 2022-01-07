package com.codex.ecam.result.budget;

import com.codex.ecam.dto.budget.BudgetDTO;
import com.codex.ecam.model.budget.Budget;
import com.codex.ecam.result.BaseResult;

public class BudgetResult extends BaseResult<Budget, BudgetDTO> {

	public BudgetResult(Budget domain, BudgetDTO dto) {
		super(domain, dto);
	}

	@Override
	public void updateDtoIdAndVersion() {
		getDtoEntity().setBudgetId(getDomainEntity().getId());
		getDtoEntity().setVersion(getDomainEntity().getVersion());
	}

}
