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


public class FeeExportVO implements Serializable {

	private static final long serialVersionUID = -1L;

	private int fee_export_stage_id;
	private String org_code;
	private String org_name;
	private String promoter_workstream;
	private String work_category;
	private String transaction_type;
	private BigDecimal discount_percentage;
	private String permit_reference;
	private String granted_date;
	private BigDecimal usrn;
	private String street;
	private String town;
	private String road_category;
	private String is_works_traffic_sensitive;
	private String is_street_traffic_sensitive;
	private String traffic_management_type;
	private String proposed_start_date;
	private String proposed_end_date;
	private String change_request_reference;
	
	private boolean org_code_validate;
	private boolean org_name_validate;
	private boolean promoter_workstream_validate;
	private boolean work_category_validate;
	private boolean transaction_type_validate;
	private boolean discount_percentage_validate;
	private boolean permit_reference_validate;
	private boolean granted_date_validate;
	private boolean usrn_validate;
	private boolean street_validate;
	private boolean town_validate;
	private boolean road_category_validate;
	private boolean is_works_traffic_sensitive_validate;
	private boolean is_street_traffic_sensitive_validate;
	private boolean traffic_management_type_validate;
	private boolean proposed_start_date_validate;
	private boolean proposed_end_date_validate;
	private boolean change_request_reference_validate;


	private Date created_at;
	private int created_by;
	private Date upload_date;
	private String file_name;
	private String status;
	private int office_code;
	private int year_month;
	
	
	
	public int getFee_export_stage_id() {
		return fee_export_stage_id;
	}
	public void setFee_export_stage_id(int fee_export_stage_id) {
		this.fee_export_stage_id = fee_export_stage_id;
	}
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getPromoter_workstream() {
		return promoter_workstream;
	}
	public void setPromoter_workstream(String promoter_workstream) {
		this.promoter_workstream = promoter_workstream;
	}
	public String getWork_category() {
		return work_category;
	}
	public void setWork_category(String work_category) {
		this.work_category = work_category;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public BigDecimal getDiscount_percentage() {
		return discount_percentage;
	}
	public void setDiscount_percentage(BigDecimal discount_percentage) {
		this.discount_percentage = discount_percentage;
	}
	public String getPermit_reference() {
		return permit_reference;
	}
	public void setPermit_reference(String permit_reference) {
		this.permit_reference = permit_reference;
	}
	public String getGranted_date() {
		return granted_date;
	}
	public void setGranted_date(String granted_date) {
		this.granted_date = granted_date;
	}
	public BigDecimal getUsrn() {
		return usrn;
	}
	public void setUsrn(BigDecimal usrn) {
		this.usrn = usrn;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getRoad_category() {
		return road_category;
	}
	public void setRoad_category(String road_category) {
		this.road_category = road_category;
	}
	public String getIs_works_traffic_sensitive() {
		return is_works_traffic_sensitive;
	}
	public void setIs_works_traffic_sensitive(String is_works_traffic_sensitive) {
		this.is_works_traffic_sensitive = is_works_traffic_sensitive;
	}
	public String getIs_street_traffic_sensitive() {
		return is_street_traffic_sensitive;
	}
	public void setIs_street_traffic_sensitive(String is_street_traffic_sensitive) {
		this.is_street_traffic_sensitive = is_street_traffic_sensitive;
	}
	
	public String getTraffic_management_type() {
		return traffic_management_type;
	}
	public void setTraffic_management_type(String traffic_management_type) {
		this.traffic_management_type = traffic_management_type;
	}
	public String getProposed_start_date() {
		return proposed_start_date;
	}
	public void setProposed_start_date(String proposed_start_date) {
		this.proposed_start_date = proposed_start_date;
	}
	public String getProposed_end_date() {
		return proposed_end_date;
	}
	public void setProposed_end_date(String proposed_end_date) {
		this.proposed_end_date = proposed_end_date;
	}
	
	public String getChange_request_reference() {
		return change_request_reference;
	}
	public void setChange_request_reference(String change_request_reference) {
		this.change_request_reference = change_request_reference;
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
	public boolean isOrg_code_validate() {
		return org_code_validate;
	}
	public void setOrg_code_validate(boolean org_code_validate) {
		this.org_code_validate = org_code_validate;
	}
	public boolean isOrg_name_validate() {
		return org_name_validate;
	}
	public void setOrg_name_validate(boolean org_name_validate) {
		this.org_name_validate = org_name_validate;
	}
	public boolean isPromoter_workstream_validate() {
		return promoter_workstream_validate;
	}
	public void setPromoter_workstream_validate(boolean promoter_workstream_validate) {
		this.promoter_workstream_validate = promoter_workstream_validate;
	}
	public boolean isWork_category_validate() {
		return work_category_validate;
	}
	public void setWork_category_validate(boolean work_category_validate) {
		this.work_category_validate = work_category_validate;
	}
	public boolean isTransaction_type_validate() {
		return transaction_type_validate;
	}
	public void setTransaction_type_validate(boolean transaction_type_validate) {
		this.transaction_type_validate = transaction_type_validate;
	}
	public boolean isDiscount_percentage_validate() {
		return discount_percentage_validate;
	}
	public void setDiscount_percentage_validate(boolean discount_percentage_validate) {
		this.discount_percentage_validate = discount_percentage_validate;
	}
	public boolean isPermit_reference_validate() {
		return permit_reference_validate;
	}
	public void setPermit_reference_validate(boolean permit_reference_validate) {
		this.permit_reference_validate = permit_reference_validate;
	}
	public boolean isGranted_date_validate() {
		return granted_date_validate;
	}
	public void setGranted_date_validate(boolean granted_date_validate) {
		this.granted_date_validate = granted_date_validate;
	}
	public boolean isUsrn_validate() {
		return usrn_validate;
	}
	public void setUsrn_validate(boolean usrn_validate) {
		this.usrn_validate = usrn_validate;
	}
	public boolean isStreet_validate() {
		return street_validate;
	}
	public void setStreet_validate(boolean street_validate) {
		this.street_validate = street_validate;
	}
	public boolean isTown_validate() {
		return town_validate;
	}
	public void setTown_validate(boolean town_validate) {
		this.town_validate = town_validate;
	}
	public boolean isRoad_category_validate() {
		return road_category_validate;
	}
	public void setRoad_category_validate(boolean road_category_validate) {
		this.road_category_validate = road_category_validate;
	}
	public boolean isIs_works_traffic_sensitive_validate() {
		return is_works_traffic_sensitive_validate;
	}
	public void setIs_works_traffic_sensitive_validate(boolean is_works_traffic_sensitive_validate) {
		this.is_works_traffic_sensitive_validate = is_works_traffic_sensitive_validate;
	}
	public boolean isIs_street_traffic_sensitive_validate() {
		return is_street_traffic_sensitive_validate;
	}
	public void setIs_street_traffic_sensitive_validate(boolean is_street_traffic_sensitive_validate) {
		this.is_street_traffic_sensitive_validate = is_street_traffic_sensitive_validate;
	}
	
	public boolean isTraffic_management_type_validate() {
		return traffic_management_type_validate;
	}
	public void setTraffic_management_type_validate(boolean traffic_management_type_validate) {
		this.traffic_management_type_validate = traffic_management_type_validate;
	}
	public boolean isProposed_start_date_validate() {
		return proposed_start_date_validate;
	}
	public void setProposed_start_date_validate(boolean proposed_start_date_validate) {
		this.proposed_start_date_validate = proposed_start_date_validate;
	}
	public boolean isProposed_end_date_validate() {
		return proposed_end_date_validate;
	}
	public void setProposed_end_date_validate(boolean proposed_end_date_validate) {
		this.proposed_end_date_validate = proposed_end_date_validate;
	}
	
	public boolean isChange_request_reference_validate() {
		return change_request_reference_validate;
	}
	public void setChange_request_reference_validate(boolean change_request_reference_validate) {
		this.change_request_reference_validate = change_request_reference_validate;
	}
	
	
	
	
	
}