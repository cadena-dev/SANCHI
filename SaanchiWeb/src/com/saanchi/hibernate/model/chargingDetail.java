package com.saanchi.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "charging_detail")
public class chargingDetail implements Serializable {

	private static final long serialVersionUID = -1L;

	private int charging_detail_id;
	private int office_code;
	private int year_month;
	private String promoter_name;
	private String works_reference;
	private String permit_reference;
	private Date date_of_permits;
	private String works_type;
	private String permit_type;
	private String applicable_charge;
	private BigDecimal standard_charges;
	private BigDecimal adjustment_charges;
	private BigDecimal invoice_amount;
	private String work_location;
	private String street;
	
	
	
	@Id
	@Column(name = "charging_detail_id")
	@SequenceGenerator(name = "charging_detail_seq", sequenceName = "charging_detail_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "charging_detail_seq")
	public int getCharging_detail_id() {
		return charging_detail_id;
	}
	public void setCharging_detail_id(int charging_detail_id) {
		this.charging_detail_id = charging_detail_id;
	}
	
	@Column(name = "office_code")
	public int getOffice_code() {
		return office_code;
	}
	public void setOffice_code(int office_code) {
		this.office_code = office_code;
	}
	
	@Column(name = "year_month")
	public int getYear_month() {
		return year_month;
	}
	public void setYear_month(int year_month) {
		this.year_month = year_month;
	}
	
	@Column(name = "promoter_name")
	public String getPromoter_name() {
		return promoter_name;
	}
	public void setPromoter_name(String promoter_name) {
		this.promoter_name = promoter_name;
	}
	
	@Column(name = "works_reference")
	public String getWorks_reference() {
		return works_reference;
	}
	public void setWorks_reference(String works_reference) {
		this.works_reference = works_reference;
	}
	
	@Column(name = "permit_reference")
	public String getPermit_reference() {
		return permit_reference;
	}
	public void setPermit_reference(String permit_reference) {
		this.permit_reference = permit_reference;
	}
	
	@Column(name = "date_of_permits")
	public Date getDate_of_permits() {
		return date_of_permits;
	}
	public void setDate_of_permits(Date date_of_permits) {
		this.date_of_permits = date_of_permits;
	}
	
	@Column(name = "works_type")
	public String getWorks_type() {
		return works_type;
	}
	public void setWorks_type(String works_type) {
		this.works_type = works_type;
	}
	
	@Column(name = "permit_type")
	public String getPermit_type() {
		return permit_type;
	}
	public void setPermit_type(String permit_type) {
		this.permit_type = permit_type;
	}
	
	@Column(name = "applicable_charge")
	public String getApplicable_charge() {
		return applicable_charge;
	}
	public void setApplicable_charge(String applicable_charge) {
		this.applicable_charge = applicable_charge;
	}
	
	@Column(name = "standard_charges")
	public BigDecimal getStandard_charges() {
		return standard_charges;
	}
	public void setStandard_charges(BigDecimal standard_charges) {
		this.standard_charges = standard_charges;
	}
	
	@Column(name = "adjustment_charges")
	public BigDecimal getAdjustment_charges() {
		return adjustment_charges;
	}
	public void setAdjustment_charges(BigDecimal adjustment_charges) {
		this.adjustment_charges = adjustment_charges;
	}
	
	@Column(name = "invoice_amount")
	public BigDecimal getInvoice_amount() {
		return invoice_amount;
	}
	public void setInvoice_amount(BigDecimal invoice_amount) {
		this.invoice_amount = invoice_amount;
	}
	
	@Column(name = "work_location")
	public String getWork_location() {
		return work_location;
	}
	public void setWork_location(String work_location) {
		this.work_location = work_location;
	}
	
	
	@Column(name = "street")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	
	
	
	
	

	
	
	
	

}