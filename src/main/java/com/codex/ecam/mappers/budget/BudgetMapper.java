package com.codex.ecam.mappers.budget;

import com.codex.ecam.dto.budget.BudgetDTO;
import com.codex.ecam.mappers.GenericMapper;
import com.codex.ecam.model.budget.Budget;

public class BudgetMapper extends GenericMapper<Budget, BudgetDTO> {

	private static BudgetMapper instance = null;

	private BudgetMapper() { }

	public static BudgetMapper getInstance() {
		if (instance == null) {
			instance = new BudgetMapper();
		}
		return instance;
	}

	@Override
	public BudgetDTO domainToDto(Budget domain) throws Exception {
		BudgetDTO dto = new BudgetDTO();
		dto.setBudgetId(domain.getId());
		dto.setBudgetDescription(domain.getBudgetDescription());
		dto.setBudgetFromDate(domain.getBudgetFromDate());
		dto.setBudgetToDate(domain.getBudgetToDate());
		dto.setBudgetType(domain.getBudgetType());

		setBudgetBusiness(domain, dto);
		setBudgetSite(domain, dto);
		setBudgetType(domain, dto);

		setCommanDTOFields(dto, domain);
		return dto;
	}

	private void setBudgetBusiness(Budget domain, BudgetDTO dto) {
		if (domain.getBusiness() != null && domain.getBusiness().getId() > 0) {
			dto.setBudgetBusinessId(domain.getBusiness().getId());
			dto.setBudgetBusinessName(domain.getBusiness().getName());
		}
	}

	private void setBudgetSite(Budget domain, BudgetDTO dto) {
		if (domain.getSite() != null) {
			dto.setBudgetSiteId(domain.getSite().getId());
			dto.setBudgetSiteName(domain.getSite().getName());
		}
	}

	private void setBudgetType(Budget domain, BudgetDTO dto) {
		if (domain.getBudgetType() != null) {
			dto.setBudgetType(domain.getBudgetType());
		}
	}

	@Override
	public void dtoToDomain(BudgetDTO dto, Budget domain) throws Exception {
		domain.setId(dto.getBudgetId());
		domain.setBudgetDescription(dto.getBudgetDescription());
		domain.setBudgetFromDate(dto.getBudgetFromDate());
		domain.setBudgetToDate(dto.getBudgetToDate());
		domain.setBudgetType(dto.getBudgetType());

		setCommanDomainFields(dto, domain);
	}

	@Override
	public BudgetDTO domainToDtoForDataTable(Budget domain) throws Exception {
		return domainToDto(domain);
	}

}
