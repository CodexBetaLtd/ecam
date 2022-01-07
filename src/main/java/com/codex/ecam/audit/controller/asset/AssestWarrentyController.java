package com.codex.ecam.audit.controller.asset;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codex.ecam.audit.service.asset.api.AssestEventAuditService;
import com.codex.ecam.dto.asset.AssetEventDTO;
import com.codex.ecam.repository.FocusDataTablesInput;

@Controller
@RequestMapping(AssestEventAuditController.REQUEST_MAPPING_URL)
public class AssestWarrentyController {
	public static final String REQUEST_MAPPING_URL = "/asset-audit";
	@Autowired
	AssestEventAuditService assestEventAuditService;
	
	@RequestMapping(value = "/warrenty-historybyAssestEventId/{assestEventId}", method = RequestMethod.GET)
	public DataTablesOutput<AssetEventDTO> assetMtrReadingValuesByMtrReadingId(@Valid FocusDataTablesInput input, @PathVariable("assestEventId") Integer assestId) {
		try {
			return assestEventAuditService.findAssesttHistory(input, assestId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
