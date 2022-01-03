package com.codex.ecam.audit.service.asset.api;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.codex.ecam.dto.asset.AssetEventDTO;
import com.codex.ecam.repository.FocusDataTablesInput;

public interface AssestEventAuditService {

	
	DataTablesOutput<AssetEventDTO> findAssesttHistory(FocusDataTablesInput input, Integer id) throws Exception;
}
