package com.codex.ecam.controller.budget;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codex.ecam.dto.budget.BudgetDTO;
import com.codex.ecam.repository.FocusDataTablesInput;
import com.codex.ecam.service.budget.api.BudgetService;

@RestController
@RequestMapping(BudgetRestController.REQUEST_MAPPING_URL)
public class BudgetRestController {

	public static final String REQUEST_MAPPING_URL = "restapi/budget";

	@Autowired
	private BudgetService budgetService;

	@RequestMapping(value = "/tabledata", method = RequestMethod.GET)
	public DataTablesOutput<BudgetDTO> getAssetList(@Valid FocusDataTablesInput input) {
		try {
			return budgetService.findAll(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new DataTablesOutput<BudgetDTO>();
	}

}
