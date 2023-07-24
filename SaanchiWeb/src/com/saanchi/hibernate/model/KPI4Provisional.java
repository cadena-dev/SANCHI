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
@Table(name = "kpi4_provisional")
public class KPI4Provisional implements Serializable {

	private static final long serialVersionUID = -1L;

	private int kpi4_id;
	private BigDecimal site_code;
	private String  site_name;
	private BigDecimal locality_id;
	private String  street_work_code;
	private String  street_work_name;
	private String  locality_name;
	private BigDecimal town_id;
	private String  town_name;
	private BigDecimal county_id;
	private String  county_name;
	private BigDecimal area_code;
	private String  area_name;
	private String  ward_code;
	private String  ward_name;
	private BigDecimal work_header_no;
	private Date notice_issue_time;
	private BigDecimal app_seq_no;
	private Date permit_date;
	private String  notice_type_code;
	private BigDecimal ext_version_no;
	private BigDecimal early_start_agree;
	private BigDecimal reduced_period;
	private Date work_start_date;
	private String  works_ref;
	private Date period_end_date;
	private String  notice_type_name;
	private String  external_reference;
	private BigDecimal org_code;
	private String  org_name;
	private BigDecimal org_group_code;
	private String  org_group_name;
	private BigDecimal auth_org_code;
	private String  auth_org_name;
	private BigDecimal auth_org_group_code;
	private String  auth_org_group_name;
	private Date created_at;
	private int created_by;
	private Date upload_date;
	private String  file_name;
	private String  status;
	private int office_code;
	private int year_month;
	
	@Id
	@Column(name = "kpi4_id")
	@SequenceGenerator(name = "kpi4_seq", sequenceName = "kpi4_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kpi4_seq")
	public int getKpi4_id() {
		return kpi4_id;
	}
	public void setKpi4_id(int kpi4_id) {
		this.kpi4_id = kpi4_id;
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
	
	
	@Column(name = "street_work_name")
	public String getStreet_work_name() {
		return street_work_name;
	}
	public void setStreet_work_name(String street_work_name) {
		this.street_work_name = street_work_name;
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
	
	
	@Column(name = "early_start_agree")
	public BigDecimal getEarly_start_agree() {
		return early_start_agree;
	}
	public void setEarly_start_agree(BigDecimal early_start_agree) {
		this.early_start_agree = early_start_agree;
	}
	
	
	@Column(name = "reduced_period")
	public BigDecimal getReduced_period() {
		return reduced_period;
	}
	public void setReduced_period(BigDecimal reduced_period) {
		this.reduced_period = reduced_period;
	}
	
	
	@Column(name = "work_start_date")
	public Date getWork_start_date() {
		return work_start_date;
	}
	public void setWork_start_date(Date work_start_date) {
		this.work_start_date = work_start_date;
	}
	
	@Column(name = "works_ref")
	public String getWorks_ref() {
		return works_ref;
	}
	public void setWorks_ref(String works_ref) {
		this.works_ref = works_ref;
	}
	
	
	@Column(name = "period_end_date")
	public Date getPeriod_end_date() {
		return period_end_date;
	}
	public void setPeriod_end_date(Date period_end_date) {
		this.period_end_date = period_end_date;
	}
	
	
	@Column(name = "notice_type_name")
	public String getNotice_type_name() {
		return notice_type_name;
	}
	public void setNotice_type_name(String notice_type_name) {
		this.notice_type_name = notice_type_name;
	}
	
	
	@Column(name = "external_reference")
	public String getExternal_reference() {
		return external_reference;
	}
	public void setExternal_reference(String external_reference) {
		this.external_reference = external_reference;
	}
	
	
	@Column(name = "org_code")
	public BigDecimal getOrg_code() {
		return org_code;
	}
	public void setOrg_code(BigDecimal org_code) {
		this.org_code = org_code;
	}
	
	
	@Column(name = "org_name")
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	
	
	@Column(name = "org_group_code")
	public BigDecimal getOrg_group_code() {
		return org_group_code;
	}
	public void setOrg_group_code(BigDecimal org_group_code) {
		this.org_group_code = org_group_code;
	}
	
	
	@Column(name = "org_group_name")
	public String getOrg_group_name() {
		return org_group_name;
	}
	public void setOrg_group_name(String org_group_name) {
		this.org_group_name = org_group_name;
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