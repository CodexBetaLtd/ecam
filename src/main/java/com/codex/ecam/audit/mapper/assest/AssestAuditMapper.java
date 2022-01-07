package com.codex.ecam.audit.mapper.assest;


import org.springframework.beans.BeanUtils;

import com.codex.ecam.audit.model.assest.AssestAuditEntity;
import com.codex.ecam.dto.asset.AssetDTO;
import com.codex.ecam.mappers.GenericMapper;

public class AssestAuditMapper extends GenericMapper<AssestAuditEntity, AssetDTO> {

	private static AssestAuditMapper instance = null;

	private AssestAuditMapper() {
	}

	public static AssestAuditMapper getInstance() {
		if (instance == null) {
			instance = new AssestAuditMapper();
		}
		return instance;
	}

	@Override
	public AssetDTO domainToDto(AssestAuditEntity domain) throws Exception {
		final AssetDTO dto = new AssetDTO();
	    BeanUtils.copyProperties(domain, dto);
		return dto;
	}

	@Override
	public AssetDTO domainToDtoForDataTable(AssestAuditEntity domain) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dtoToDomain(AssetDTO dto, AssestAuditEntity domain) throws Exception {
		

	}

}
