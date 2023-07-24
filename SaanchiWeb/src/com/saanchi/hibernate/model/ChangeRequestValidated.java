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
@Table(name = "change_request_validated")
public class ChangeRequestValidated implements Serializable {

	private static final long serialVersionUID = -1L;

	
	
	private String change_request_reference_number;
	private String permit_reference_number;
	private String change_request_type;
	private String application_type;
	private String  type_of_work;
	private String change_status;
	private Date change_submission_date;
	private BigDecimal assessment_discount;
	private Date pa_proposed_start_date;
	private Date pa_proposed_end_date;
	private BigDecimal duration;
	private Date pa_actual_start_date;
	private Date pa_actual_end_date;
	private BigDecimal permit_fee;
	private BigDecimal discount;
	private Date created_at;
	private int created_by;
	private Date upload_date;
	private String file_name;
	private String status;
	private int office_code;
	private int year_month;
	private String comments_txt;
	private String promoter_name;
	private String coordinator;
	
	
	
	
	
	
	@Id
	@Column(name = "change_request_reference_number")
	public String getChange_request_reference_number() {
		return change_request_reference_number;
	}
	public void setChange_request_reference_number(String change_request_reference_number) {
		this.change_request_reference_number = change_request_reference_number;
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
	
	@Column(name = "application_type")
	public String getApplication_type() {
		return application_type;
	}
	public void setApplication_type(String application_type) {
		this.application_type = application_type;
	}
	
	
	
	@Column(name = "type_of_work")
	public String getType_of_work() {
		return type_of_work;
	}
	public void setType_of_work(String type_of_work) {
		this.type_of_work = type_of_work;
	}
	
	
	@Column(name = "change_status")
	public String getChange_status() {
		return change_status;
	}
	public void setChange_status(String change_status) {
		this.change_status = change_status;
	}
	
	
	@Column(name = "change_submission_date")
	public Date getChange_submission_date() {
		return change_submission_date;
	}
	public void setChange_submission_date(Date change_submission_date) {
		this.change_submission_date = change_submission_date;
	}
	
	
	@Column(name = "assessment_discount")
	public BigDecimal getAssessment_discount() {
		return assessment_discount;
	}
	public void setAssessment_discount(BigDecimal assessment_discount) {
		this.assessment_discount = assessment_discount;
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
	
	
	@Column(name = "duration")
	public BigDecimal getDuration() {
		return duration;
	}
	public void setDuration(BigDecimal duration) {
		this.duration = duration;
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
	
	
	@Column(name = "permit_fee")
	public BigDecimal getPermit_fee() {
		return permit_fee;
	}
	public void setPermit_fee(BigDecimal permit_fee) {
		this.permit_fee = permit_fee;
	}
	
	
	@Column(name = "discount")
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
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
	
	
	
	@Column(name = "comments_txt")
	public String getComments_txt() {
		return comments_txt;
	}
	public void setComments_txt(String comments_txt) {
		this.comments_txt = comments_txt;
	}
	
	
	@Column(name = "promoter_name")
	public String getPromoter_name() {
		return promoter_name;
	}
	public void setPromoter_name(String promoter_name) {
		this.promoter_name = promoter_name;
	}
	
	
	@Column(name = "coordinator")
	public String getCoordinator() {
		return coordinator;
	}
	public void setCoordinator(String coordinator) {
		this.coordinator = coordinator;
	}
	
	
	
	
	
	
	
	
	
}