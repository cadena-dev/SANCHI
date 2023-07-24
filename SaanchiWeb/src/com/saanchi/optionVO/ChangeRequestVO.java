package com.saanchi.optionVO;

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
public class ChangeRequestVO implements Serializable {

	private static final long serialVersionUID = -1L;

	
	private int change_request_stage_id;
	private String change_request_reference_number;
	private boolean change_request_reference_number_validated;
	private String permit_reference_number;
	private boolean permit_reference_number_validated;
	private String change_request_type;
	private boolean change_request_type_validated;
	private String application_type;
	private boolean application_type_validated;
	private String  type_of_work;
	private boolean  type_of_work_validated;
	private String change_status;
	private boolean change_status_validated;
	private String change_submission_date;
	private boolean change_submission_date_validated;
	private BigDecimal assessment_discount;
	private boolean assessment_discount_validated;
	private String pa_proposed_start_date;
	private boolean pa_proposed_start_date_validated;
	private String pa_proposed_end_date;
	private boolean pa_proposed_end_date_validated;
	private BigDecimal duration;
	private boolean duration_validated;
	private Date created_at;
	private int created_by;
	private Date upload_date;
	private String file_name;
	private String status;
	private int office_code;
	private int year_month;
	
	
	
	public int getChange_request_stage_id() {
		return change_request_stage_id;
	}
	public void setChange_request_stage_id(int change_request_stage_id) {
		this.change_request_stage_id = change_request_stage_id;
	}
	public String getChange_request_reference_number() {
		return change_request_reference_number;
	}
	public void setChange_request_reference_number(String change_request_reference_number) {
		this.change_request_reference_number = change_request_reference_number;
	}
	public boolean isChange_request_reference_number_validated() {
		return change_request_reference_number_validated;
	}
	public void setChange_request_reference_number_validated(boolean change_request_reference_number_validated) {
		this.change_request_reference_number_validated = change_request_reference_number_validated;
	}
	public String getPermit_reference_number() {
		return permit_reference_number;
	}
	public void setPermit_reference_number(String permit_reference_number) {
		this.permit_reference_number = permit_reference_number;
	}
	public boolean isPermit_reference_number_validated() {
		return permit_reference_number_validated;
	}
	public void setPermit_reference_number_validated(boolean permit_reference_number_validated) {
		this.permit_reference_number_validated = permit_reference_number_validated;
	}
	public String getChange_request_type() {
		return change_request_type;
	}
	public void setChange_request_type(String change_request_type) {
		this.change_request_type = change_request_type;
	}
	public boolean isChange_request_type_validated() {
		return change_request_type_validated;
	}
	public void setChange_request_type_validated(boolean change_request_type_validated) {
		this.change_request_type_validated = change_request_type_validated;
	}
	public String getApplication_type() {
		return application_type;
	}
	public void setApplication_type(String application_type) {
		this.application_type = application_type;
	}
	public boolean isApplication_type_validated() {
		return application_type_validated;
	}
	public void setApplication_type_validated(boolean application_type_validated) {
		this.application_type_validated = application_type_validated;
	}
	public String getType_of_work() {
		return type_of_work;
	}
	public void setType_of_work(String type_of_work) {
		this.type_of_work = type_of_work;
	}
	public boolean isType_of_work_validated() {
		return type_of_work_validated;
	}
	public void setType_of_work_validated(boolean type_of_work_validated) {
		this.type_of_work_validated = type_of_work_validated;
	}
	public String getChange_status() {
		return change_status;
	}
	public void setChange_status(String change_status) {
		this.change_status = change_status;
	}
	public boolean isChange_status_validated() {
		return change_status_validated;
	}
	public void setChange_status_validated(boolean change_status_validated) {
		this.change_status_validated = change_status_validated;
	}
	public String getChange_submission_date() {
		return change_submission_date;
	}
	public void setChange_submission_date(String change_submission_date) {
		this.change_submission_date = change_submission_date;
	}
	public boolean isChange_submission_date_validated() {
		return change_submission_date_validated;
	}
	public void setChange_submission_date_validated(boolean change_submission_date_validated) {
		this.change_submission_date_validated = change_submission_date_validated;
	}
	public BigDecimal getAssessment_discount() {
		return assessment_discount;
	}
	public void setAssessment_discount(BigDecimal assessment_discount) {
		this.assessment_discount = assessment_discount;
	}
	public boolean isAssessment_discount_validated() {
		return assessment_discount_validated;
	}
	public void setAssessment_discount_validated(boolean assessment_discount_validated) {
		this.assessment_discount_validated = assessment_discount_validated;
	}
	public String getPa_proposed_start_date() {
		return pa_proposed_start_date;
	}
	public void setPa_proposed_start_date(String pa_proposed_start_date) {
		this.pa_proposed_start_date = pa_proposed_start_date;
	}
	public boolean isPa_proposed_start_date_validated() {
		return pa_proposed_start_date_validated;
	}
	public void setPa_proposed_start_date_validated(boolean pa_proposed_start_date_validated) {
		this.pa_proposed_start_date_validated = pa_proposed_start_date_validated;
	}
	public String getPa_proposed_end_date() {
		return pa_proposed_end_date;
	}
	public void setPa_proposed_end_date(String pa_proposed_end_date) {
		this.pa_proposed_end_date = pa_proposed_end_date;
	}
	public boolean isPa_proposed_end_date_validated() {
		return pa_proposed_end_date_validated;
	}
	public void setPa_proposed_end_date_validated(boolean pa_proposed_end_date_validated) {
		this.pa_proposed_end_date_validated = pa_proposed_end_date_validated;
	}
	public BigDecimal getDuration() {
		return duration;
	}
	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}
	public boolean isDuration_validated() {
		return duration_validated;
	}
	public void setDuration_validated(boolean duration_validated) {
		this.duration_validated = duration_validated;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getOffice_code() {
		return office_code;
	}
	public void setOffice_code(int office_code) {
		this.office_code = office_code;
	}
	public int getYear_month() {
		return year_month;
	}
	public void setYear_month(int year_month) {
		this.year_month = year_month;
	}
	
	
	
	
	
	
	
	
	
	
	
}