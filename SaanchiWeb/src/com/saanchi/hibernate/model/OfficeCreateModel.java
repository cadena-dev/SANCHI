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
@Table(name = "office_master")
public class OfficeCreateModel implements Serializable {

	private static final long serialVersionUID = -1L;

	  private int office_master_id;			
	  private String office_desc;		
	  private boolean isactive;	
	  private int created_by;		
	  private Date created_datetime;	
	  private String office_address;		
	  private int updated_by;		
	  private Date updated_datetime;
	  private String office_type;
	  
	  
	    @Id
		@Column(name = "office_master_id")
		@SequenceGenerator(name="office_master_seq", sequenceName="office_master_seq",allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "office_master_seq")  
	public int getOffice_master_id() {
		return office_master_id;
	}
	public void setOffice_master_id(int office_master_id) {
		this.office_master_id = office_master_id;
	}
	
	@Column(name = "office_desc")
	public String getOffice_desc() {
		return office_desc;
	}
	public void setOffice_desc(String office_desc) {
		this.office_desc = office_desc;
	}
	
	@Column(name = "isactive")
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
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
	
	@Column(name = "office_address")
	public String getOffice_address() {
		return office_address;
	}
	public void setOffice_address(String office_address) {
		this.office_address = office_address;
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
	
	@Column(name = "office_type")
	public String getOffice_type() {
		return office_type;
	}
	public void setOffice_type(String office_type) {
		this.office_type = office_type;
	}    
	  
	 
	
	
	
	
	
	
	
	
	
	
	
	
}