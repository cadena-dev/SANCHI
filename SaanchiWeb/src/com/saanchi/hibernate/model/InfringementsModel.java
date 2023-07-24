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
@Table(name = "infringements_data")
public class InfringementsModel implements Serializable {

	private static final long serialVersionUID = -1L;

	private int infringements_data_id;
	private String work_reference_number;
	private Date stop_notice_recorded_date;
	private Date stop_notice_recorded_time;
	private Date actual_completed_date;
	private Date actual_completed_time;
	private String notice_type;
	private boolean holiday_flag;
	private boolean weekend_flag;
	private int date_difference;
	private Date time_differ;
	private String fpn_comments;
	private Date offence_date;
	private String offence_code;
	private Date created_at;
	private int created_by;
	private Date upload_date;
	private String file_name;
	private String status;
	private int office_code;
	private int year_month;
	private String promoter_name;
	
	
	

	@Id
	@Column(name = "infringements_data_id")
	@SequenceGenerator(name = "infringements_export_seq", sequenceName = "infringements_export_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "infringements_export_seq")
	public int getInfringements_data_id() {
		return infringements_data_id;
	}
	public void setInfringements_data_id(int infringements_data_id) {
		this.infringements_data_id = infringements_data_id;
	}
	
	
	@Column(name = "work_reference_number")
	public String getWork_reference_number() {
		return work_reference_number;
	}
	public void setWork_reference_number(String work_reference_number) {
		this.work_reference_number = work_reference_number;
	}
	
	
	@Column(name = "stop_notice_recorded_date")
	public Date getStop_notice_recorded_date() {
		return stop_notice_recorded_date;
	}
	public void setStop_notice_recorded_date(Date stop_notice_recorded_date) {
		this.stop_notice_recorded_date = stop_notice_recorded_date;
	}
	
	
	@Column(name = "stop_notice_recorded_time")
	public Date getStop_notice_recorded_time() {
		return stop_notice_recorded_time;
	}
	public void setStop_notice_recorded_time(Date stop_notice_recorded_time) {
		this.stop_notice_recorded_time = stop_notice_recorded_time;
	}
	
	
	@Column(name = "actual_completed_date")
	public Date getActual_completed_date() {
		return actual_completed_date;
	}
	public void setActual_completed_date(Date actual_completed_date) {
		this.actual_completed_date = actual_completed_date;
	}
	
	@Column(name = "actual_completed_time")
	public Date getActual_completed_time() {
		return actual_completed_time;
	}
	public void setActual_completed_time(Date actual_completed_time) {
		this.actual_completed_time = actual_completed_time;
	}
	
	@Column(name = "notice_type")
	public String getNotice_type() {
		return notice_type;
	}
	public void setNotice_type(String notice_type) {
		this.notice_type = notice_type;
	}
	
	@Column(name = "holiday_flag")
	public boolean isHoliday_flag() {
		return holiday_flag;
	}
	public void setHoliday_flag(boolean holiday_flag) {
		this.holiday_flag = holiday_flag;
	}
	
	@Column(name = "weekend_flag")
	public boolean isWeekend_flag() {
		return weekend_flag;
	}
	public void setWeekend_flag(boolean weekend_flag) {
		this.weekend_flag = weekend_flag;
	}
	
	
	
	
	@Column(name = "date_difference")
	public int getDate_difference() {
		return date_difference;
	}
	public void setDate_difference(int date_difference) {
		this.date_difference = date_difference;
	}
	
	
	@Column(name = "time_differ")
	public Date getTime_differ() {
		return time_differ;
	}
	public void setTime_differ(Date time_differ) {
		this.time_differ = time_differ;
	}
	
	@Column(name = "fpn_comments")
	public String getFpn_comments() {
		return fpn_comments;
	}
	public void setFpn_comments(String fpn_comments) {
		this.fpn_comments = fpn_comments;
	}
	
	
	@Column(name = "offence_date")
	public Date getOffence_date() {
		return offence_date;
	}
	public void setOffence_date(Date offence_date) {
		this.offence_date = offence_date;
	}
	
	@Column(name = "offence_code")
	public String getOffence_code() {
		return offence_code;
	}
	public void setOffence_code(String offence_code) {
		this.offence_code = offence_code;
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
	
	
	
	@Column(name = "promoter_name")
	public String getPromoter_name() {
		return promoter_name;
	}
	public void setPromoter_name(String promoter_name) {
		this.promoter_name = promoter_name;
	}
	

}