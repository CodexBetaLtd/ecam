package com.codex.ecam.controller.budget;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(BudgetValidationController.REQUEST_MAPPING_URL)
public class BudgetValidationController {

	public static final String REQUEST_MAPPING_URL = "/validate/budget";

}