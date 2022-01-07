package com.codex.ecam.audit.dao.assest;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codex.ecam.audit.model.assest.AssestAuditEntity;
import com.codex.ecam.repository.FocusDataTableRepository;

public interface AssestHistoryDAO extends FocusDataTableRepository<AssestAuditEntity, Long>{
	
	@Query("select v from AssestAuditEntity m where  m.asset.id = :assetId ORDER BY v.createdDate DESC")
    List<AssestAuditEntity> findLAllAssetMetereReadingsByAsset(@Param("assetId") Integer id);

}
