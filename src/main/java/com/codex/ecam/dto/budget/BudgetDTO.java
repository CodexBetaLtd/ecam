package com.codex.ecam.dto.budget;

import java.math.BigDecimal;
import java.util.Date;

import com.codex.ecam.constants.budget.BudgetType;
import com.codex.ecam.dto.BaseDTO;

public class BudgetDTO extends BaseDTO {

	private Integer budgetId;
	private Integer budgetSiteId;
	private Integer budgetBusinessId;

	private String budgetBusinessName;
	private String budgetSiteName;
	private String budgetDescription;

	private BigDecimal budgetAmount;

	private BudgetType budgetType;

	private Date budgetFromDate = new Date();
	private Date budgetToDate = new Date();

	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}

	public Integer getBudgetSiteId() {
		return budgetSiteId;
	}

	public void setBudgetSiteId(Integer budgetSiteId) {
		this.budgetSiteId = budgetSiteId;
	}

	public Integer getBudgetBusinessId() {
		return budgetBusinessId;
	}

	public void setBudgetBusinessId(Integer budgetBusinessId) {
		this.budgetBusinessId = budgetBusinessId;
	}

	public String getBudgetBusinessName() {
		return budgetBusinessName;
	}

	public void setBudgetBusinessName(String budgetBusinessName) {
		this.budgetBusinessName = budgetBusinessName;
	}

	public String getBudgetSiteName() {
		return budgetSiteName;
	}

	public void setBudgetSiteName(String budgetSiteName) {
		this.budgetSiteName = budgetSiteName;
	}

	public String getBudgetDescription() {
		return budgetDescription;
	}

	public void setBudgetDescription(String budgetDescription) {
		this.budgetDescription = budgetDescription;
	}

	public BigDecimal getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(BigDecimal budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public BudgetType getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(BudgetType budgetType) {
		this.budgetType = budgetType;
	}

	public Date getBudgetFromDate() {
		return budgetFromDate;
	}

	public void setBudgetFromDate(Date budgetFromDate) {
		this.budgetFromDate = budgetFromDate;
	}

	public Date getBudgetToDate() {
		return budgetToDate;
	}

	public void setBudgetToDate(Date budgetToDate) {
		this.budgetToDate = budgetToDate;
	}

}
