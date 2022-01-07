package com.codex.ecam.audit.dao.assest;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codex.ecam.audit.model.assest.AssestEventAuditEntity;
import com.codex.ecam.repository.FocusDataTableRepository;

public interface AssestEventHistoryDAO extends FocusDataTableRepository<AssestEventAuditEntity, Long>{
	
	
}
