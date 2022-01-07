package com.codex.ecam.audit.model.assest;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;
import org.springframework.context.ApplicationEvent;

import com.codex.ecam.event.RootAwareEvent;
import com.codex.ecam.model.BaseModel;
import com.codex.ecam.model.api.RootApplicationEventAware;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "tbl_asset_aud")
@Immutable
@Getter
@Setter
public class AssestAuditEntity extends BaseModel implements RootApplicationEventAware{
	
	    @Id
	    @Column(name = "rev")
	    private Long rev;
	    
	    @Column(name = "revtype")
	    private Long revtype;
	    
	    @Column(name="name")
		private String name;

		@Column(name="asset_url")
		private String assetUrl;

		@Column(name="description")
		private String description;

		@Column(name="code")
		private String code;

		@Column(name="image_location")
		private String imageLocation;

		@Column(name="serial_no")
		private String serialNo;

		@Column(name="notes")
		private String notes;

		@Column(name="is_online")
		private Boolean isOnline;

		@Column(name="address")
		private String address;

		@Column(name="city")
		private String city;

		@Override
		public ApplicationEvent getEvent() {
			return new RootAwareEvent(this);
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
