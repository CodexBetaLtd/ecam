package com.codex.ecam.model.budget;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.codex.ecam.constants.budget.BudgetType;
import com.codex.ecam.model.BaseModel;
import com.codex.ecam.model.asset.Asset;
import com.codex.ecam.model.biz.business.Business;

@Entity
@Table(name = "tbl_budget")
public class Budget extends BaseModel {

	private static final long serialVersionUID = -5566712823688332116L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "budget_s")
	@SequenceGenerator(name = "budget_s", sequenceName = "budget_s", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@JoinColumn(name = "business_id")
	@ManyToOne(targetEntity = Business.class, fetch = FetchType.LAZY)
	private Business business;

	@JoinColumn(name = "site_id")
	@ManyToOne(targetEntity = Asset.class, fetch = FetchType.LAZY)
	private Asset site;

	@Column(name = "budget_type_id")
	private BudgetType budgetType;

	@Column(name = "description")
	private String budgetDescription;

	@Column(name = "from_date")
	private Date budgetFromDate;

	@Column(name = "to_date")
	private Date budgetToDate;

	@Column(name = "amount")
	private Date budgetAmount;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public Asset getSite() {
		return site;
	}

	public void setSite(Asset site) {
		this.site = site;
	}

	public BudgetType getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(BudgetType budgetType) {
		this.budgetType = budgetType;
	}

	public String getBudgetDescription() {
		return budgetDescription;
	}

	public void setBudgetDescription(String budgetDescription) {
		this.budgetDescription = budgetDescription;
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

	public Date getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(Date budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

}
