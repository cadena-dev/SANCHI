package com.saanchi.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "permit_fee_master")
public class PermitFeeMasterModel implements Serializable {

	private static final long serialVersionUID = -1L;

	  private int permit_fee_master_id;	
	  private int highway_authority_id;
	  private String highway_authority_name;	
	  private String application_type;		
	  private String work_category;	
	  private String road_category;		
	  private String traffic_sensitivity;
	  private BigDecimal fee_amount;
	  private Boolean isactive;		
	  private int created_by;		
	  private Date created_datetime	;
	  private int updated_by;
	  private Date updated_datetime;
	  
	  
	  
	  @Id
		@Column(name = "permit_fee_master_id")
		@SequenceGenerator(name="permit_fee_master_seq", sequenceName="permit_fee_master_seq",allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permit_fee_master_seq")
	public int getPermit_fee_master_id() {
		return permit_fee_master_id;
	}
	public void setPermit_fee_master_id(int permit_fee_master_id) {
		this.permit_fee_master_id = permit_fee_master_id;
	}
	
	
	
	@Column(name = "highway_authority_id")
	public int getHighway_authority_id() {
		return highway_authority_id;
	}
	public void setHighway_authority_id(int highway_authority_id) {
		this.highway_authority_id = highway_authority_id;
	}
	@Column(name = "highway_authority_name")
	public String getHighway_authority_name() {
		return highway_authority_name;
	}
	public void setHighway_authority_name(String highway_authority_name) {
		this.highway_authority_name = highway_authority_name;
	}
	
	
	@Column(name = "application_type")
	public String getApplication_type() {
		return application_type;
	}
	public void setApplication_type(String application_type) {
		this.application_type = application_type;
	}
	
	
	@Column(name = "work_category")
	public String getWork_category() {
		return work_category;
	}
	public void setWork_category(String work_category) {
		this.work_category = work_category;
	}
	
	@Column(name = "road_category")
	public String getRoad_category() {
		return road_category;
	}
	public void setRoad_category(String road_category) {
		this.road_category = road_category;
	}
	
	
	@Column(name = "traffic_sensitivity")
	public String getTraffic_sensitivity() {
		return traffic_sensitivity;
	}
	public void setTraffic_sensitivity(String traffic_sensitivity) {
		this.traffic_sensitivity = traffic_sensitivity;
	}
	
	@Column(name = "fee_amount")
	public BigDecimal getFee_amount() {
		return fee_amount;
	}
	public void setFee_amount(BigDecimal fee_amount) {
		this.fee_amount = fee_amount;
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