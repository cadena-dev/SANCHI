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
@Table(name = "kpi3_provisional")
public class KPI3Provisional implements Serializable {

	private static final long serialVersionUID = -1L;

	private int kpi3_id;
	private BigDecimal site_code;
	private String site_name;
	private BigDecimal locality_id;
	private String street_work_code;
	private String locality_name;
	private BigDecimal town_id;
	private String town_name;
	private BigDecimal county_id;
	private String county_name;
	private BigDecimal area_code;
	private String area_name;
	private String ward_code;
	private String ward_name;
	private BigDecimal work_header_no;
	private Date notice_issue_time;
	private BigDecimal app_seq_no;
	private Date permit_date;
	private String notice_type_code;
	private BigDecimal ext_version_no;
	private BigDecimal promoter_org_code;
	private String promoter_org_name;
	private BigDecimal promoter_org_group_code;
	private String promoter_org_group_name;
	private BigDecimal auth_org_code;
	private String auth_org_name;
	private BigDecimal auth_org_group_code;
	private String auth_org_group_name;
	private String restrict_org_code;
	private String restrict_works_ref;
	private String customer_connect;
	private String street_work_name;
	private String external_reference;
	private String urgent_flag;
	private String works_ref;
	private Date work_start_date;
	private Date work_est_end_date;
	private String permit_application_type;
	private BigDecimal permit_granted;
	private BigDecimal permit_duration_extension;
	private BigDecimal permit_duration_extension_appr;

	private Date created_at;
	private int created_by;
	private Date upload_date;
	private String file_name;
	private String status;
	private int office_code;
	private int year_month;

	@Id
	@Column(name = "kpi3_id")
	@SequenceGenerator(name = "kpi3_seq", sequenceName = "kpi3_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kpi3_seq")
	public int getKpi3_id() {
		return kpi3_id;
	}

	public void setKpi3_id(int kpi3_id) {
		this.kpi3_id = kpi3_id;
	}

	@Column(name = "site_code")
	public BigDecimal getSite_code() {
		return site_code;
	}

	public void setSite_code(BigDecimal site_code) {
		this.site_code = site_code;
	}

	@Column(name = "site_name")
	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	@Column(name = "locality_id")
	public BigDecimal getLocality_id() {
		return locality_id;
	}

	public void setLocality_id(BigDecimal locality_id) {
		this.locality_id = locality_id;
	}

	@Column(name = "street_work_code")
	public String getStreet_work_code() {
		return street_work_code;
	}

	public void setStreet_work_code(String street_work_code) {
		this.street_work_code = street_work_code;
	}

	@Column(name = "locality_name")
	public String getLocality_name() {
		return locality_name;
	}

	public void setLocality_name(String locality_name) {
		this.locality_name = locality_name;
	}

	@Column(name = "town_id")
	public BigDecimal getTown_id() {
		return town_id;
	}

	public void setTown_id(BigDecimal town_id) {
		this.town_id = town_id;
	}

	@Column(name = "town_name")
	public String getTown_name() {
		return town_name;
	}

	public void setTown_name(String town_name) {
		this.town_name = town_name;
	}

	@Column(name = "county_id")
	public BigDecimal getCounty_id() {
		return county_id;
	}

	public void setCounty_id(BigDecimal county_id) {
		this.county_id = county_id;
	}

	@Column(name = "county_name")
	public String getCounty_name() {
		return county_name;
	}

	public void setCounty_name(String county_name) {
		this.county_name = county_name;
	}

	@Column(name = "area_code")
	public BigDecimal getArea_code() {
		return area_code;
	}

	public void setArea_code(BigDecimal area_code) {
		this.area_code = area_code;
	}

	@Column(name = "area_name")
	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	@Column(name = "ward_code")
	public String getWard_code() {
		return ward_code;
	}

	public void setWard_code(String ward_code) {
		this.ward_code = ward_code;
	}

	@Column(name = "ward_name")
	public String getWard_name() {
		return ward_name;
	}

	public void setWard_name(String ward_name) {
		this.ward_name = ward_name;
	}

	@Column(name = "work_header_no")
	public BigDecimal getWork_header_no() {
		return work_header_no;
	}

	public void setWork_header_no(BigDecimal work_header_no) {
		this.work_header_no = work_header_no;
	}

	@Column(name = "notice_issue_time")
	public Date getNotice_issue_time() {
		return notice_issue_time;
	}

	public void setNotice_issue_time(Date notice_issue_time) {
		this.notice_issue_time = notice_issue_time;
	}

	@Column(name = "app_seq_no")
	public BigDecimal getApp_seq_no() {
		return app_seq_no;
	}

	public void setApp_seq_no(BigDecimal app_seq_no) {
		this.app_seq_no = app_seq_no;
	}

	@Column(name = "permit_date")
	public Date getPermit_date() {
		return permit_date;
	}

	public void setPermit_date(Date permit_date) {
		this.permit_date = permit_date;
	}

	@Column(name = "notice_type_code")
	public String getNotice_type_code() {
		return notice_type_code;
	}

	public void setNotice_type_code(String notice_type_code) {
		this.notice_type_code = notice_type_code;
	}

	@Column(name = "ext_version_no")
	public BigDecimal getExt_version_no() {
		return ext_version_no;
	}

	public void setExt_version_no(BigDecimal ext_version_no) {
		this.ext_version_no = ext_version_no;
	}

	@Column(name = "promoter_org_code")
	public BigDecimal getPromoter_org_code() {
		return promoter_org_code;
	}

	public void setPromoter_org_code(BigDecimal promoter_org_code) {
		this.promoter_org_code = promoter_org_code;
	}

	@Column(name = "promoter_org_name")
	public String getPromoter_org_name() {
		return promoter_org_name;
	}

	public void setPromoter_org_name(String promoter_org_name) {
		this.promoter_org_name = promoter_org_name;
	}

	@Column(name = "promoter_org_group_code")
	public BigDecimal getPromoter_org_group_code() {
		return promoter_org_group_code;
	}

	public void setPromoter_org_group_code(BigDecimal promoter_org_group_code) {
		this.promoter_org_group_code = promoter_org_group_code;
	}

	@Column(name = "promoter_org_group_name")
	public String getPromoter_org_group_name() {
		return promoter_org_group_name;
	}

	public void setPromoter_org_group_name(String promoter_org_group_name) {
		this.promoter_org_group_name = promoter_org_group_name;
	}

	@Column(name = "auth_org_code")
	public BigDecimal getAuth_org_code() {
		return auth_org_code;
	}

	public void setAuth_org_code(BigDecimal auth_org_code) {
		this.auth_org_code = auth_org_code;
	}

	@Column(name = "auth_org_name")
	public String getAuth_org_name() {
		return auth_org_name;
	}

	public void setAuth_org_name(String auth_org_name) {
		this.auth_org_name = auth_org_name;
	}

	@Column(name = "auth_org_group_code")
	public BigDecimal getAuth_org_group_code() {
		return auth_org_group_code;
	}

	public void setAuth_org_group_code(BigDecimal auth_org_group_code) {
		this.auth_org_group_code = auth_org_group_code;
	}

	@Column(name = "auth_org_group_name")
	public String getAuth_org_group_name() {
		return auth_org_group_name;
	}

	public void setAuth_org_group_name(String auth_org_group_name) {
		this.auth_org_group_name = auth_org_group_name;
	}

	@Column(name = "restrict_org_code")
	public String getRestrict_org_code() {
		return restrict_org_code;
	}

	public void setRestrict_org_code(String restrict_org_code) {
		this.restrict_org_code = restrict_org_code;
	}

	@Column(name = "restrict_works_ref")
	public String getRestrict_works_ref() {
		return restrict_works_ref;
	}

	public void setRestrict_works_ref(String restrict_works_ref) {
		this.restrict_works_ref = restrict_works_ref;
	}

	@Column(name = "customer_connect")
	public String getCustomer_connect() {
		return customer_connect;
	}

	public void setCustomer_connect(String customer_connect) {
		this.customer_connect = customer_connect;
	}

	@Column(name = "street_work_name")
	public String getStreet_work_name() {
		return street_work_name;
	}

	public void setStreet_work_name(String street_work_name) {
		this.street_work_name = street_work_name;
	}

	@Column(name = "external_reference")
	public String getExternal_reference() {
		return external_reference;
	}

	public void setExternal_reference(String external_reference) {
		this.external_reference = external_reference;
	}

	@Column(name = "urgent_flag")
	public String getUrgent_flag() {
		return urgent_flag;
	}

	public void setUrgent_flag(String urgent_flag) {
		this.urgent_flag = urgent_flag;
	}

	@Column(name = "permit_granted")
	public BigDecimal getPermit_granted() {
		return permit_granted;
	}

	public void setPermit_granted(BigDecimal permit_granted) {
		this.permit_granted = permit_granted;
	}

	@Column(name = "work_start_date")
	public Date getWork_start_date() {
		return work_start_date;
	}

	public void setWork_start_date(Date work_start_date) {
		this.work_start_date = work_start_date;
	}

	@Column(name = "work_est_end_date")
	public Date getWork_est_end_date() {
		return work_est_end_date;
	}

	public void setWork_est_end_date(Date work_est_end_date) {
		this.work_est_end_date = work_est_end_date;
	}

	@Column(name = "permit_duration_extension")
	public BigDecimal getPermit_duration_extension() {
		return permit_duration_extension;
	}

	public void setPermit_duration_extension(BigDecimal permit_duration_extension) {
		this.permit_duration_extension = permit_duration_extension;
	}

	@Column(name = "permit_duration_extension_appr")
	public BigDecimal getPermit_duration_extension_appr() {
		return permit_duration_extension_appr;
	}

	public void setPermit_duration_extension_appr(BigDecimal permit_duration_extension_appr) {
		this.permit_duration_extension_appr = permit_duration_extension_appr;
	}

	@Column(name = "permit_application_type")
	public String getPermit_application_type() {
		return permit_application_type;
	}

	public void setPermit_application_type(String permit_application_type) {
		this.permit_application_type = permit_application_type;
	}

	@Column(name = "works_ref")
	public String getWorks_ref() {
		return works_ref;
	}

	public void setWorks_ref(String works_ref) {
		this.works_ref = works_ref;
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