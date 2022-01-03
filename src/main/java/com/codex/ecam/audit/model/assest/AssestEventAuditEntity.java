package com.codex.ecam.audit.model.assest;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.springframework.context.ApplicationEvent;

import com.codex.ecam.model.BaseModel;
import com.codex.ecam.model.api.RootApplicationEventAware;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_asset_event_aud")
@Immutable
@Getter
@Setter
public class AssestEventAuditEntity extends BaseModel implements RootApplicationEventAware {

	@Id
	@Column(name = "rev")
	private Long rev;

	@Column(name = "revtype")
	private Long revtype;
	
	private Integer assetEventId;
	private Integer assetEventIndex;
	private Integer assetEventTypeAssetId;
	private Integer assetEventTypeId;
	private String assetEventTypeName;
	private String assetEventDescription;
	private Long assetEventOccuredDate;
	private String assetEventOccuredDateStr;
	@Column(name = "occured_date")
    private Date occuredDate;

	@Override
	public ApplicationEvent getEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub

	}

}
