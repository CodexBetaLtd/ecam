package com.codex.ecam.audit.controller.asset;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codex.ecam.audit.service.asset.api.AssestAuditService;
import com.codex.ecam.controller.asset.AssetController;
import com.codex.ecam.dto.asset.AssetDTO;
import com.codex.ecam.dto.asset.AssetMeterReadingValueDTO;
import com.codex.ecam.repository.FocusDataTablesInput;

@Controller
@RequestMapping(AssestAuditController.REQUEST_MAPPING_URL)
public class AssestAuditController {
	public static final String REQUEST_MAPPING_URL = "/asset-audit";
	
	@Autowired
	AssestAuditService assestAuditService;
	
	
	@RequestMapping(value = "/historybyAssestId/{assestId}", method = RequestMethod.GET)
	public DataTablesOutput<AssetDTO> assetMtrReadingValuesByMtrReadingId(@Valid FocusDataTablesInput input, @PathVariable("assestId") Integer assestId) {
		try {
			return assestAuditService.findAssesttHistory(input, assestId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
