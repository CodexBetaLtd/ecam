package com.codex.ecam.audit.service.asset.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;

import com.codex.ecam.audit.dao.assest.AssestEventHistoryDAO;
import com.codex.ecam.audit.mapper.assest.AssestEventAuditMapper;
import com.codex.ecam.audit.model.assest.AssestEventAuditEntity;
import com.codex.ecam.audit.service.asset.api.AssestEventAuditService;
import com.codex.ecam.dto.asset.AssetEventDTO;
import com.codex.ecam.repository.FocusDataTablesInput;

public class AssestEventAuditImpl implements AssestEventAuditService{
	
	@Autowired
	AssestEventHistoryDAO assestEventHistoryDAO;

	@Override
	public DataTablesOutput<AssetEventDTO> findAssesttHistory(FocusDataTablesInput input, Integer assetEventId) throws Exception {
		Specification<AssestEventAuditEntity> specification = (root, query, cb) -> cb.equal(root.get("assestEventAuditEntity").get("id"), assetEventId);

		DataTablesOutput<AssestEventAuditEntity> domainOut = assestEventHistoryDAO.findAll(input, specification);
		DataTablesOutput<AssetEventDTO> out = AssestEventAuditMapper.getInstance().domainToDTODataTablesOutput(domainOut);

		return out;
	}

}
