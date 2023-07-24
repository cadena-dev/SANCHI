package com.saanchi.hibernate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "regulation_master")
public class RegulationMasterModel implements Serializable {

	private static final long serialVersionUID = -1L;

	  private int regulation_master_id;			
	  private String regulation_id;		
	  private String regulation_code;	
	  private String description;		
	  private Boolean isactive;		
	  private int created_by;		
	  private Date created_datetime	;
	  private int updated_by;
	  private Date updated_datetime;
	  
	  
	  @Id
		@Column(name = "regulation_master_id")
		@SequenceGenerator(name="regulation_master_seq", sequenceName="regulation_master_seq",allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regulation_master_seq")  
	public int getRegulation_master_id() {
		return regulation_master_id;
	}
	public void setRegulation_master_id(int regulation_master_id) {
		this.regulation_master_id = regulation_master_id;
	}
	
	
	@Column(name = "regulation_id")
	public String getRegulation_id() {
		return regulation_id;
	}
	public void setRegulation_id(String regulation_id) {
		this.regulation_id = regulation_id;
	}
	
	@Column(name = "regulation_code")
	public String getRegulation_code() {
		return regulation_code;
	}
	public void setRegulation_code(String regulation_code) {
		this.regulation_code = regulation_code;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "isactive")
	public Boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	
	@Column(name = "created_by")
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	
	@Column(name = "created_datetime")
	public Date getCreated_datetime() {
		return created_datetime;
	}
	public void setCreated_datetime(Date created_datetime) {
		this.created_datetime = created_datetime;
	}
	
	@Column(name = "updated_by")
	public int getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
	
	@Column(name = "updated_datetime")
	public Date getUpdated_datetime() {
		return updated_datetime;
	}
	public void setUpdated_datetime(Date updated_datetime) {
		this.updated_datetime = updated_datetime;
	}
	  
	  
	  
	 
	
	
	  
	  
	  
	
	
}