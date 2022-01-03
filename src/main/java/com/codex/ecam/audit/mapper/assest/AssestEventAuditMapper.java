package com.codex.ecam.audit.mapper.assest;

import org.springframework.beans.BeanUtils;

import com.codex.ecam.audit.model.assest.AssestEventAuditEntity;
import com.codex.ecam.dto.asset.AssetEventDTO;
import com.codex.ecam.mappers.GenericMapper;

public class AssestEventAuditMapper extends GenericMapper<AssestEventAuditEntity, AssetEventDTO> {

	private static AssestEventAuditMapper instance = null;

	private AssestEventAuditMapper() {
	}

	public static AssestEventAuditMapper getInstance() {
		if (instance == null) {
			instance = new AssestEventAuditMapper();
		}
		return instance;
	}

	@Override
	public AssetEventDTO domainToDto(AssestEventAuditEntity domain) throws Exception {
		final AssetEventDTO dto = new AssetEventDTO();
	    BeanUtils.copyProperties(domain, dto);
		return dto;
	}

	@Override
	public AssetEventDTO domainToDtoForDataTable(AssestEventAuditEntity domain) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dtoToDomain(AssetEventDTO dto, AssestEventAuditEntity domain) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
