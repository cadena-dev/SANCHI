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
@Table(name = "fpn_export_provisional")
public class FPNExportProvisional implements Serializable {

	private static final long serialVersionUID = -1L;

	
	private int fpn_export_provisionall_id;
	private String works_reference_number;
	private String fpn_number;
	private String fpn_status;
	private String offence_code;
	private Date  offence_date;
	private String offence_details;
	private String authorised_officer_name;
	private String officer_contact_details;
	private String officer_address;
	private String representations_contact;
	private String representations_contact_address;
	private String payment_methods;
	private String works_promoter;
	private String issuing_authority;
	private Date  issue_date_time;
	private String street_name;
	private String area_name;
	private BigDecimal usrn;
	private String attach_photo_evidence;
	private BigDecimal workstream_prefix;
	private String town;
	private Date  status_changed_date;
	private Date created_at;
	private int created_by;
	private Date upload_date;
	private String file_name;
	private String status;
	private int office_code;
	private int year_month;
	
	
	@Id
	@Column(name = "fpn_export_provisionall_id")
	@SequenceGenerator(name = "fpn_export_provisional_seq", sequenceName = "fpn_export_provisional_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fpn_export_provisional_seq")
	public int getFpn_export_provisionall_id() {
		return fpn_export_provisionall_id;
	}
	public void setFpn_export_provisionall_id(int fpn_export_provisionall_id) {
		this.fpn_export_provisionall_id = fpn_export_provisionall_id;
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
	
	
	@Column(name = "authorised_officer_name")
	public String getAuthorised_officer_name() {
		return authorised_officer_name;
	}
	public void setAuthorised_officer_name(String authorised_officer_name) {
		this.authorised_officer_name = authorised_officer_name;
	}
	
	
	@Column(name = "officer_contact_details")
	public String getOfficer_contact_details() {
		return officer_contact_details;
	}
	public void setOfficer_contact_details(String officer_contact_details) {
		this.officer_contact_details = officer_contact_details;
	}
	
	
	@Column(name = "officer_address")
	public String getOfficer_address() {
		return officer_address;
	}
	public void setOfficer_address(String officer_address) {
		this.officer_address = officer_address;
	}
	
	@Column(name = "representations_contact")
	public String getRepresentations_contact() {
		return representations_contact;
	}
	public void setRepresentations_contact(String representations_contact) {
		this.representations_contact = representations_contact;
	}
	
	
	@Column(name = "representations_contact_address")
	public String getRepresentations_contact_address() {
		return representations_contact_address;
	}
	public void setRepresentations_contact_address(String representations_contact_address) {
		this.representations_contact_address = representations_contact_address;
	}
	
	@Column(name = "payment_methods")
	public String getPayment_methods() {
		return payment_methods;
	}
	public void setPayment_methods(String payment_methods) {
		this.payment_methods = payment_methods;
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
	
	
	@Column(name = "street_name")
	public String getStreet_name() {
		return street_name;
	}
	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}
	
	
	@Column(name = "area_name")
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	
	
	@Column(name = "usrn")
	public BigDecimal getUsrn() {
		return usrn;
	}
	public void setUsrn(BigDecimal usrn) {
		this.usrn = usrn;
	}
	
	
	@Column(name = "attach_photo_evidence")
	public String isAttach_photo_evidence() {
		return attach_photo_evidence;
	}
	public void setAttach_photo_evidence(String attach_photo_evidence) {
		this.attach_photo_evidence = attach_photo_evidence;
	}
	
	@Column(name = "workstream_prefix")
	public BigDecimal getWorkstream_prefix() {
		return workstream_prefix;
	}
	public void setWorkstream_prefix(BigDecimal workstream_prefix) {
		this.workstream_prefix = workstream_prefix;
	}
	
	
	@Column(name = "town")
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	
	@Column(name = "status_changed_date")
	public Date  getStatus_changed_date() {
		return status_changed_date;
	}
	public void setStatus_changed_date(Date  status_changed_date) {
		this.status_changed_date = status_changed_date;
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
	
	
	
	
}