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
@Table(name = "pa_export_validated")
public class PAExportValidated implements Serializable {

	private static final long serialVersionUID = -1L;

	
	private Date date_of_permits;
	private String promoter_name;
	private String work_reference_number;
	private String permit_reference_number;
	private String application_type;
	private String type_of_work;
	private String location;
	private String street;
	private String town;
	private BigDecimal usrn;
	private String activity_type;
	private String work_description;
	private String works_with_excavation;
	private String reinstatement_category;
	private String traffic_management_method;
	private String collaborative_working;
	private Date eastimated_start_date;
	private Date proposed_start_time;
	private Date proposed_end_date;
	private Date proposed_end_time;
	private String duration_of_work;
	private String permit_status;
	private Date permit_deeming_date;
	private BigDecimal discount;
	private Date created_at;
	private int created_by;
	private Date upload_date;
	private String file_name;
	private String status;
	private int office_code;
	private int year_month;
	private String comments_txt;
	private String traffic_sensitive;
	private String out_of_working_hours;
	private Date grant_permit_date;
	private String coordinator;
	private BigDecimal permit_fee;


	
	

	

	

	@Column(name = "date_of_permits")
	public Date getDate_of_permits() {
		return date_of_permits;
	}

	public void setDate_of_permits(Date date_of_permits) {
		this.date_of_permits = date_of_permits;
	}

	@Column(name = "promoter_name")
	public String getPromoter_name() {
		return promoter_name;
	}

	public void setPromoter_name(String promoter_name) {
		this.promoter_name = promoter_name;
	}

	@Column(name = "work_reference_number")
	public String getWork_reference_number() {
		return work_reference_number;
	}

	public void setWork_reference_number(String work_reference_number) {
		this.work_reference_number = work_reference_number;
	}

	@Id
	@Column(name = "permit_reference_number")
	public String getPermit_reference_number() {
		return permit_reference_number;
	}

	public void setPermit_reference_number(String permit_reference_number) {
		this.permit_reference_number = permit_reference_number;
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

	@Column(name = "location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "street")
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "town")
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Column(name = "usrn")
	public BigDecimal getUsrn() {
		return usrn;
	}

	public void setUsrn(BigDecimal usrn) {
		this.usrn = usrn;
	}

	@Column(name = "activity_type")
	public String getActivity_type() {
		return activity_type;
	}

	public void setActivity_type(String activity_type) {
		this.activity_type = activity_type;
	}

	@Column(name = "work_description")
	public String getWork_description() {
		return work_description;
	}

	public void setWork_description(String work_description) {
		this.work_description = work_description;
	}

	@Column(name = "works_with_excavation")
	public String getWorks_with_excavation() {
		return works_with_excavation;
	}

	public void setWorks_with_excavation(String works_with_excavation) {
		this.works_with_excavation = works_with_excavation;
	}

	@Column(name = "reinstatement_category")
	public String getReinstatement_category() {
		return reinstatement_category;
	}

	public void setReinstatement_category(String reinstatement_category) {
		this.reinstatement_category = reinstatement_category;
	}

	
	@Column(name = "traffic_management_method")
	public String getTraffic_management_method() {
		return traffic_management_method;
	}

	public void setTraffic_management_method(String traffic_management_method) {
		this.traffic_management_method = traffic_management_method;
	}

	
	
	@Column(name = "collaborative_working")
	public String getCollaborative_working() {
		return collaborative_working;
	}

	public void setCollaborative_working(String collaborative_working) {
		this.collaborative_working = collaborative_working;
	}

	@Column(name = "eastimated_start_date")
	public Date getEastimated_start_date() {
		return eastimated_start_date;
	}

	public void setEastimated_start_date(Date eastimated_start_date) {
		this.eastimated_start_date = eastimated_start_date;
	}

	
	@Column(name = "proposed_start_time")
	public Date getProposed_start_time() {
		return proposed_start_time;
	}

	public void setProposed_start_time(Date proposed_start_time) {
		this.proposed_start_time = proposed_start_time;
	}

	
	@Column(name = "proposed_end_date")
	public Date getProposed_end_date() {
		return proposed_end_date;
	}

	public void setProposed_end_date(Date proposed_end_date) {
		this.proposed_end_date = proposed_end_date;
	}

	
	@Column(name = "proposed_end_time")
	public Date getProposed_end_time() {
		return proposed_end_time;
	}

	public void setProposed_end_time(Date proposed_end_time) {
		this.proposed_end_time = proposed_end_time;
	}

	
	@Column(name = "duration_of_work")
	public String getDuration_of_work() {
		return duration_of_work;
	}

	public void setDuration_of_work(String duration_of_work) {
		this.duration_of_work = duration_of_work;
	}

	
	@Column(name = "permit_status")
	public String getPermit_status() {
		return permit_status;
	}

	public void setPermit_status(String permit_status) {
		this.permit_status = permit_status;
	}

	
	@Column(name = "permit_deeming_date")
	public Date getPermit_deeming_date() {
		return permit_deeming_date;
	}

	public void setPermit_deeming_date(Date permit_deeming_date) {
		this.permit_deeming_date = permit_deeming_date;
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
	
	
	
	@Column(name = "traffic_sensitive")
	public String getTraffic_sensitive() {
		return traffic_sensitive;
	}

	public void setTraffic_sensitive(String traffic_sensitive) {
		this.traffic_sensitive = traffic_sensitive;
	}

	@Column(name = "out_of_working_hours")
	public String getOut_of_working_hours() {
		return out_of_working_hours;
	}

	public void setOut_of_working_hours(String out_of_working_hours) {
		this.out_of_working_hours = out_of_working_hours;
	}
	
	
	@Column(name = "grant_permit_date")
	public Date getGrant_permit_date() {
		return grant_permit_date;
	}

	public void setGrant_permit_date(Date grant_permit_date) {
		this.grant_permit_date = grant_permit_date;
	}

	@Column(name = "coordinator")
	public String getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(String coordinator) {
		this.coordinator = coordinator;
	}

	
	@Column(name = "permit_fee")
	public BigDecimal getPermit_fee() {
		return permit_fee;
	}

	public void setPermit_fee(BigDecimal permit_fee) {
		this.permit_fee = permit_fee;
	}
	
	
	
	

}