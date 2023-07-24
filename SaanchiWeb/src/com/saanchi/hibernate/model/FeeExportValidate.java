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
@Table(name = "fee_export_validate")
public class FeeExportValidate implements Serializable {

	private static final long serialVersionUID = -1L;

	private int fee_export_validate_id;
	private String org_code;
	private String org_name;
	private String promoter_workstream;
	private String work_category;
	private String transaction_type;
	private BigDecimal discount_percentage;
	private String permit_reference;
	private Date granted_date;
	private BigDecimal usrn;
	private String street;
	private String town;
	private String road_category;

	private String is_works_traffic_sensitive;
	private String is_street_traffic_sensitive;

	private BigDecimal workstream_prefix;
	private String area;
	private String is_ttro_required;
	private String traffic_management_type;

	private Date proposed_start_date;
	private Date proposed_end_date;
	private Date actual_start_date;
	private Date actual_end_date;
	private String change_request_reference;

	private Date created_at;
	private int created_by;
	private Date upload_date;
	private String file_name;
	private String status;
	private int office_code;
	private int year_month;
	private String comments_txt;
	
	
	@Id
	public int getFee_export_validate_id() {
		return fee_export_validate_id;
	}

	public void setFee_export_validate_id(int fee_export_validate_id) {
		this.fee_export_validate_id = fee_export_validate_id;
	}
	
	@Column(name = "org_code")
	public String getOrg_code() {
		return org_code;
	}
	
	

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	
	
	@Column(name = "org_name")
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	
	@Column(name = "promoter_workstream")
	public String getPromoter_workstream() {
		return promoter_workstream;
	}
	public void setPromoter_workstream(String promoter_workstream) {
		this.promoter_workstream = promoter_workstream;
	}
	
	@Column(name = "work_category")
	public String getWork_category() {
		return work_category;
	}
	public void setWork_category(String work_category) {
		this.work_category = work_category;
	}
	
	@Column(name = "transaction_type")
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	
	@Column(name = "discount_percentage")
	public BigDecimal getDiscount_percentage() {
		return discount_percentage;
	}
	public void setDiscount_percentage(BigDecimal discount_percentage) {
		this.discount_percentage = discount_percentage;
	}
	
	@Column(name = "permit_reference")
	public String getPermit_reference() {
		return permit_reference;
	}
	public void setPermit_reference(String permit_reference) {
		this.permit_reference = permit_reference;
	}
	
	@Column(name = "granted_date")
	public Date getGranted_date() {
		return granted_date;
	}
	public void setGranted_date(Date granted_date) {
		this.granted_date = granted_date;
	}
	
	
	@Column(name = "usrn")
	public BigDecimal getUsrn() {
		return usrn;
	}
	public void setUsrn(BigDecimal usrn) {
		this.usrn = usrn;
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
	
	
	@Column(name = "road_category")
	public String getRoad_category() {
		return road_category;
	}
	public void setRoad_category(String road_category) {
		this.road_category = road_category;
	}
	
	@Column(name = "is_works_traffic_sensitive")
	public String getIs_works_traffic_sensitive() {
		return is_works_traffic_sensitive;
	}
	public void setIs_works_traffic_sensitive(String is_works_traffic_sensitive) {
		this.is_works_traffic_sensitive = is_works_traffic_sensitive;
	}
	
	@Column(name = "is_street_traffic_sensitive")
	public String getIs_street_traffic_sensitive() {
		return is_street_traffic_sensitive;
	}
	public void setIs_street_traffic_sensitive(String is_street_traffic_sensitive) {
		this.is_street_traffic_sensitive = is_street_traffic_sensitive;
	}
	
	
	@Column(name = "workstream_prefix")
	public BigDecimal getWorkstream_prefix() {
		return workstream_prefix;
	}
	public void setWorkstream_prefix(BigDecimal workstream_prefix) {
		this.workstream_prefix = workstream_prefix;
	}
	
	
	@Column(name = "area")
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	
	@Column(name = "is_ttro_required")
	public String getIs_ttro_required() {
		return is_ttro_required;
	}
	public void setIs_ttro_required(String is_ttro_required) {
		this.is_ttro_required = is_ttro_required;
	}
	
	@Column(name = "traffic_management_type")
	public String getTraffic_management_type() {
		return traffic_management_type;
	}
	public void setTraffic_management_type(String traffic_management_type) {
		this.traffic_management_type = traffic_management_type;
	}
	
	
	@Column(name = "proposed_start_date")
	public Date getProposed_start_date() {
		return proposed_start_date;
	}
	public void setProposed_start_date(Date proposed_start_date) {
		this.proposed_start_date = proposed_start_date;
	}
	
	@Column(name = "proposed_end_date")
	public Date getProposed_end_date() {
		return proposed_end_date;
	}
	public void setProposed_end_date(Date proposed_end_date) {
		this.proposed_end_date = proposed_end_date;
	}
	
	@Column(name = "actual_start_date")
	public Date getActual_start_date() {
		return actual_start_date;
	}
	public void setActual_start_date(Date actual_start_date) {
		this.actual_start_date = actual_start_date;
	}
	
	
	@Column(name = "actual_end_date")
	public Date getActual_end_date() {
		return actual_end_date;
	}
	public void setActual_end_date(Date actual_end_date) {
		this.actual_end_date = actual_end_date;
	}
	
	
	@Column(name = "change_request_reference")
	public String getChange_request_reference() {
		return change_request_reference;
	}
	public void setChange_request_reference(String change_request_reference) {
		this.change_request_reference = change_request_reference;
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

	public String getComments_txt() {
		return comments_txt;
	}

	public void setComments_txt(String comments_txt) {
		this.comments_txt = comments_txt;
	}

	
	
}