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
@Table(name = "promoter_master")
public class PromoterMasterModel implements Serializable {

	private static final long serialVersionUID = -1L;

	  private int promoter_master_id;			
	  private String promoter_name;		
	  private String promoter_short_name;	
	  private String org_reference_number;		
	  private String prefix;	
	  private String promoter_mail;		
	  private String promoter_contact_no;
	  private String promoter_mobile_no;
	  private Boolean isactive;		
	  private int created_by;		
	  private Date created_datetime	;
	  private int updated_by;
	  private Date updated_datetime;
	  
	  
	  
	  @Id
		@Column(name = "promoter_master_id")
		@SequenceGenerator(name="promoter_master_seq", sequenceName="promoter_master_seq",allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promoter_master_seq")  
	public int getPromoter_master_id() {
		return promoter_master_id;
	}
	public void setPromoter_master_id(int promoter_master_id) {
		this.promoter_master_id = promoter_master_id;
	}
	
	
	@Column(name = "promoter_name")
	public String getPromoter_name() {
		return promoter_name;
	}
	public void setPromoter_name(String promoter_name) {
		this.promoter_name = promoter_name;
	}
	
	
	@Column(name = "promoter_short_name")
	public String getPromoter_short_name() {
		return promoter_short_name;
	}
	public void setPromoter_short_name(String promoter_short_name) {
		this.promoter_short_name = promoter_short_name;
	}
	
	
	@Column(name = "org_reference_number")
	public String getOrg_reference_number() {
		return org_reference_number;
	}
	public void setOrg_reference_number(String org_reference_number) {
		this.org_reference_number = org_reference_number;
	}
	
	
	@Column(name = "prefix")
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	
	@Column(name = "promoter_mail")
	public String getPromoter_mail() {
		return promoter_mail;
	}
	public void setPromoter_mail(String promoter_mail) {
		this.promoter_mail = promoter_mail;
	}
	
	
	@Column(name = "promoter_contact_no")
	public String getPromoter_contact_no() {
		return promoter_contact_no;
	}
	public void setPromoter_contact_no(String promoter_contact_no) {
		this.promoter_contact_no = promoter_contact_no;
	}
	
	@Column(name = "promoter_mobile_no")
	public String getPromoter_mobile_no() {
		return promoter_mobile_no;
	}
	public void setPromoter_mobile_no(String promoter_mobile_no) {
		this.promoter_mobile_no = promoter_mobile_no;
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