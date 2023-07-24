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
@Table(name = "holidaye_master")
public class HolidayMasterModel implements Serializable {

	private static final long serialVersionUID = -1L;

	  private int holidaye_master_id;
	 
	
	  private Date holidate_date;		
	  private String description;	
	  private Boolean isactive;		
	  private int created_by;		
	  private Date created_datetime	;
	  private int updated_by;
	  private Date updated_datetime;
	  
	  
	  @Id
		@Column(name = "holidaye_master_id")
		@SequenceGenerator(name="holiday_master_seq", sequenceName="holiday_master_seq",allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "holiday_master_seq")  
	public int getHolidaye_master_id() {
		return holidaye_master_id;
	}
	public void setHolidaye_master_id(int holidaye_master_id) {
		this.holidaye_master_id = holidaye_master_id;
	}
	
	
	
	@Column(name = "holidate_date")
	public Date getHolidate_date() {
		return holidate_date;
	}
	public void setHolidate_date(Date holidate_date) {
		this.holidate_date = holidate_date;
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