package com.codex.ecam.audit.service.asset.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.codex.ecam.audit.dao.assest.AssestHistoryDAO;
import com.codex.ecam.audit.mapper.assest.AssestAuditMapper;
import com.codex.ecam.audit.model.assest.AssestAuditEntity;
import com.codex.ecam.audit.service.asset.api.AssestAuditService;
import com.codex.ecam.dto.asset.AssetDTO;
import com.codex.ecam.dto.asset.AssetMeterReadingValueDTO;
import com.codex.ecam.mappers.asset.AssetMapper;
import com.codex.ecam.mappers.asset.AssetMeterReadingValueMapper;
import com.codex.ecam.model.asset.AssetMeterReadingValue;
import com.codex.ecam.repository.FocusDataTablesInput;


@Service
public class AssestAuditServiceImpl implements AssestAuditService {

	@Autowired
	AssestHistoryDAO assestHistoryDAO;

	
	public DataTablesOutput<AssetDTO> findAssesttHistory(FocusDataTablesInput input, Integer assetId) throws Exception {
		Specification<AssestAuditEntity> specification = (root, query, cb) -> cb.equal(root.get("assestAuditEntity").get("id"), assetId);

		DataTablesOutput<AssestAuditEntity> domainOut = assestHistoryDAO.findAll(input, specification);
		DataTablesOutput<AssetDTO> out = AssestAuditMapper.getInstance().domainToDTODataTablesOutput(domainOut);

		return out;
	}

}
