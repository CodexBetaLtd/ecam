package com.codex.ecam.dao.budget;

import org.springframework.stereotype.Repository;

import com.codex.ecam.model.budget.Budget;
import com.codex.ecam.repository.FocusDataTableRepository;

@Repository
public interface BudgetDao extends FocusDataTableRepository<Budget, Integer> {

}
