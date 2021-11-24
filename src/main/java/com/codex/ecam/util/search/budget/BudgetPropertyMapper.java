package com.codex.ecam.util.search.budget;

import com.codex.ecam.util.search.BaseSearchPropertyMapper;

public class BudgetPropertyMapper extends BaseSearchPropertyMapper {


	private static BudgetPropertyMapper instance = null;

	private BudgetPropertyMapper() {
	}

	public static BudgetPropertyMapper getInstance() {
		if (instance == null) {
			instance = new BudgetPropertyMapper();
		}
		return instance;
	}

	@Override
	protected void mapSearchParamsToPropertyParams(String column) {

		switch (column) {
		case "budgetType":
			addColumns("budgetType");
			break;

		default:
			break;

		}

	}
}
