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
@Table(name = "fpn_export_infringements")
public class FPNExport_Infringements_Validated implements Serializable {

	private static final long serialVersionUID = -1L;

	
	private int fpn_export_infringements_id;
	private String works_reference_number;
	private String fpn_number;
	private String fpn_status;
	private String offence_code;
	private Date  offence_date;
	private String offence_details;
	private Date  issue_date_time;
	private String works_promoter;
	private String issuing_authority;
	private BigDecimal amount_paid;
	private Date created_at;
	private int created_by;
	private Date upload_date;
	private String file_name;
	private String status;
	private int office_code;
	private int year_month;
	private String first_update_status;
	private String second_update_status;
	private String file_type;
	private Date received_date;
	
	
	
	
	
	@Id
	@Column(name = "fpn_export_infringements_id")
	@SequenceGenerator(name = "fpn_export_infringements_seq", sequenceName = "fpn_export_infringements_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fpn_export_infringements_seq")
	public int getFpn_export_infringements_id() {
		return fpn_export_infringements_id;
	}

	public void setFpn_export_infringements_id(int fpn_export_infringements_id) {
		this.fpn_export_infringements_id = fpn_export_infringements_id;
	}

	@Column(name = "works_reference_number")
	public String getWorks_reference_number() {
		return works_reference_number;
	}
	
	public void setWorks_reference_number(String works_reference_number) {
		this.works_reference_number = works_reference_number;
	}
	

	@Column(name = "fpn_number")
	public String getFpn_number() {
		return fpn_number;
	}
	public void setFpn_number(String fpn_number) {
		this.fpn_number = fpn_number;
	}
	
	@Column(name = "fpn_status")
	public String getFpn_status() {
		return fpn_status;
	}
	public void setFpn_status(String fpn_status) {
		this.fpn_status = fpn_status;
	}
	
	
	@Column(name = "offence_code")
	public String getOffence_code() {
		return offence_code;
	}
	public void setOffence_code(String offence_code) {
		this.offence_code = offence_code;
	}
	
	@Column(name = "offence_date")
	public Date  getOffence_date() {
		return offence_date;
	}
	public void setOffence_date(Date  offence_date) {
		this.offence_date = offence_date;
	}
	
	@Column(name = "offence_details")
	public String getOffence_details() {
		return offence_details;
	}
	public void setOffence_details(String offence_details) {
		this.offence_details = offence_details;
	}
	
	
	@Column(name = "works_promoter")
	public String getWorks_promoter() {
		return works_promoter;
	}
	public void setWorks_promoter(String works_promoter) {
		this.works_promoter = works_promoter;
	}
	
	@Column(name = "issuing_authority")
	public String getIssuing_authority() {
		return issuing_authority;
	}
	public void setIssuing_authority(String issuing_authority) {
		this.issuing_authority = issuing_authority;
	}
	
	
	@Column(name = "issue_date_time")
	public Date  getIssue_date_time() {
		return issue_date_time;
	}
	public void setIssue_date_time(Date  issue_date_time) {
		this.issue_date_time = issue_date_time;
	}
	
	
	
	
	
	@Column(name = "created_at")
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	
	@Column(name = "created_by")
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	
	@Column(name = "upload_date")
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	
	@Column(name = "file_name")
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	
	@Column(name = "status")
	public String isStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	
	
	@Column(name = "amount_paid")
	public BigDecimal getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(BigDecimal amount_paid) {
		this.amount_paid = amount_paid;
	}
	
	

	
	@Column(name = "first_update_status")
	public String getFirst_update_status() {
		return first_update_status;
	}

	public void setFirst_update_status(String first_update_status) {
		this.first_update_status = first_update_status;
	}

	
	@Column(name = "second_update_status")
	public String getSecond_update_status() {
		return second_update_status;
	}

	public void setSecond_update_status(String second_update_status) {
		this.second_update_status = second_update_status;
	}

	
	@Column(name = "file_type")
	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	
	@Column(name = "received_date")
	public Date getReceived_date() {
		return received_date;
	}

	public void setReceived_date(Date received_date) {
		this.received_date = received_date;
	}
	
	
	
	
	
	
	
}