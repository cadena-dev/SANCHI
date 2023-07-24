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
@Table(name = "change_request_provisional")
public class ChangeRequestProvisional implements Serializable {

	private static final long serialVersionUID = -1L;

	
	private int change_request_provisional_id;
	private String change_request_reference_number;
	private String highway_authority_name;
	private String promoter_name;
	private String permit_reference_number;
	private String  change_request_type;
	private String change_status;
	private Date status_change_date;
	private Date reasonable_period_end_date;
	private Date pa_proposed_start_date;
	private Date pa_proposed_end_date;
	private Date pa_actual_start_date;
	private Date pa_actual_end_date;
	private String works_status;
	private String assessment_comment;
	private BigDecimal assessment_discount;
	private String change_reason;
	private String works_location;
	private String street_name;
	private String area_name;
	private String works_category;
	private Date submission_date;
	private Date deem_by_date;
	private BigDecimal workstream_prefix;
	private String town;
	private Date created_at;
	private int created_by;
	private Date upload_date;
	private String file_name;
	private String status;
	private int office_code;
	private int year_month;
	
	
	
	@Id
	@Column(name = "change_request_provisional_id")
	@SequenceGenerator(name = "change_request_provisional_seq", sequenceName = "change_request_provisional_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "change_request_provisional_seq")
	public int getChange_request_provisional_id() {
		return change_request_provisional_id;
	}
	public void setChange_request_provisional_id(int change_request_provisional_id) {
		this.change_request_provisional_id = change_request_provisional_id;
	}
	
	
	
	@Column(name = "change_request_reference_number")
	public String getChange_request_reference_number() {
		return change_request_reference_number;
	}
	public void setChange_request_reference_number(String change_request_reference_number) {
		this.change_request_reference_number = change_request_reference_number;
	}
	
	
	
	@Column(name = "highway_authority_name")
	public String getHighway_authority_name() {
		return highway_authority_name;
	}
	public void setHighway_authority_name(String highway_authority_name) {
		this.highway_authority_name = highway_authority_name;
	}
	
	
	@Column(name = "promoter_name")
	public String getPromoter_name() {
		return promoter_name;
	}
	public void setPromoter_name(String promoter_name) {
		this.promoter_name = promoter_name;
	}
	
	
	@Column(name = "permit_reference_number")
	public String getPermit_reference_number() {
		return permit_reference_number;
	}
	public void setPermit_reference_number(String permit_reference_number) {
		this.permit_reference_number = permit_reference_number;
	}
	
	
	@Column(name = "change_request_type")
	public String getChange_request_type() {
		return change_request_type;
	}
	public void setChange_request_type(String change_request_type) {
		this.change_request_type = change_request_type;
	}
	
	
	@Column(name = "change_status")
	public String getChange_status() {
		return change_status;
	}
	public void setChange_status(String change_status) {
		this.change_status = change_status;
	}
	
	
	@Column(name = "status_change_date")
	public Date getStatus_change_date() {
		return status_change_date;
	}
	public void setStatus_change_date(Date status_change_date) {
		this.status_change_date = status_change_date;
	}
	
	
	@Column(name = "reasonable_period_end_date")
	public Date getReasonable_period_end_date() {
		return reasonable_period_end_date;
	}
	public void setReasonable_period_end_date(Date reasonable_period_end_date) {
		this.reasonable_period_end_date = reasonable_period_end_date;
	}
	
	
	@Column(name = "pa_proposed_start_date")
	public Date getPa_proposed_start_date() {
		return pa_proposed_start_date;
	}
	public void setPa_proposed_start_date(Date pa_proposed_start_date) {
		this.pa_proposed_start_date = pa_proposed_start_date;
	}
	
	
	@Column(name = "pa_proposed_end_date")
	public Date getPa_proposed_end_date() {
		return pa_proposed_end_date;
	}
	public void setPa_proposed_end_date(Date pa_proposed_end_date) {
		this.pa_proposed_end_date = pa_proposed_end_date;
	}
	
	
	@Column(name = "pa_actual_start_date")
	public Date getPa_actual_start_date() {
		return pa_actual_start_date;
	}
	public void setPa_actual_start_date(Date pa_actual_start_date) {
		this.pa_actual_start_date = pa_actual_start_date;
	}
	
	
	
	@Column(name = "pa_actual_end_date")
	public Date getPa_actual_end_date() {
		return pa_actual_end_date;
	}
	public void setPa_actual_end_date(Date pa_actual_end_date) {
		this.pa_actual_end_date = pa_actual_end_date;
	}
	
	
	@Column(name = "works_status")
	public String getWorks_status() {
		return works_status;
	}
	public void setWorks_status(String works_status) {
		this.works_status = works_status;
	}
	
	
	@Column(name = "assessment_comment")
	public String getAssessment_comment() {
		return assessment_comment;
	}
	public void setAssessment_comment(String assessment_comment) {
		this.assessment_comment = assessment_comment;
	}
	
	
	@Column(name = "assessment_discount")
	public BigDecimal getAssessment_discount() {
		return assessment_discount;
	}
	public void setAssessment_discount(BigDecimal assessment_discount) {
		this.assessment_discount = assessment_discount;
	}
	
	
	@Column(name = "change_reason")
	public String getChange_reason() {
		return change_reason;
	}
	public void setChange_reason(String change_reason) {
		this.change_reason = change_reason;
	}
	
	
	@Column(name = "works_location")
	public String getWorks_location() {
		return works_location;
	}
	public void setWorks_location(String works_location) {
		this.works_location = works_location;
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
	
	
	@Column(name = "works_category")
	public String getWorks_category() {
		return works_category;
	}
	public void setWorks_category(String works_category) {
		this.works_category = works_category;
	}
	
	
	
	@Column(name = "submission_date")
	public Date getSubmission_date() {
		return submission_date;
	}
	public void setSubmission_date(Date submission_date) {
		this.submission_date = submission_date;
	}
	
	
	
	@Column(name = "deem_by_date")
	public Date getDeem_by_date() {
		return deem_by_date;
	}
	public void setDeem_by_date(Date deem_by_date) {
		this.deem_by_date = deem_by_date;
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
	public String getStatus() {
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