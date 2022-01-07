package com.codex.ecam.audit.service.asset.api;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.codex.ecam.dto.asset.AssetDTO;
import com.codex.ecam.dto.asset.AssetMeterReadingValueDTO;
import com.codex.ecam.repository.FocusDataTablesInput;

public interface AssestAuditService {
	
	DataTablesOutput<AssetDTO> findAssesttHistory(FocusDataTablesInput input, Integer id) throws Exception;

}
