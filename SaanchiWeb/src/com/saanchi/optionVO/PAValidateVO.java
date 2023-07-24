package com.saanchi.optionVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
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


public class PAValidateVO implements Serializable {

	private static final long serialVersionUID = -1L;

	private int pa_export_stage_id;
	private String date_of_permits;
	private boolean date_of_permits_validate;
	private String promoter_name;
	private boolean promoter_name_validate;
	private String work_reference_number;
	private boolean work_reference_number_validate;
	private String permit_reference_number;
	private boolean permit_reference_number_validate;
	private String application_type;
	private boolean application_type_validate;
	private String type_of_work;
	private boolean type_of_work_validate;
	private String location;
	private boolean location_validate;
	private String street;
	private boolean street_validate;
	private String town;
	private boolean town_validate;
	private BigDecimal usrn;
	private boolean usrn_validate;
	private String activity_type;
	private boolean activity_type_validate;
	private String work_description;
	private boolean work_description_validate;
	private String works_with_excavation;
	private boolean works_with_excavation_validate;
	private String reinstatement_category;
	private boolean reinstatement_category_validate;
	private String traffic_management_method;
	private boolean traffic_management_method_validate;
	private String collaborative_working;
	private boolean collaborative_working_validate;
	private String eastimated_start_date;
	private boolean eastimated_start_date_validate;
	private String proposed_start_time;
	private boolean proposed_start_time_validate;
	private String proposed_end_date;
	private boolean proposed_end_date_validate;
	private String proposed_end_time;
	private boolean proposed_end_time_validate;
	private String duration_of_work;
	private boolean duration_of_work_validate;
	private String permit_status;
	private boolean permit_status_validate;
	private String permit_deeming_date;
	private boolean permit_deeming_date_validate;
	private BigDecimal discount;
	private boolean discount_validate;
	private Date created_at;
	private int created_by;
	private Date upload_date;
	private String file_name;
	private String status;
	private int office_code;
	private int year_month;
	
	
	
	public int getPa_export_stage_id() {
		return pa_export_stage_id;
	}
	public void setPa_export_stage_id(int pa_export_stage_id) {
		this.pa_export_stage_id = pa_export_stage_id;
	}
	public String getDate_of_permits() {
		return date_of_permits;
	}
	public void setDate_of_permits(String date_of_permits) {
		this.date_of_permits = date_of_permits;
	}
	public boolean isDate_of_permits_validate() {
		return date_of_permits_validate;
	}
	public void setDate_of_permits_validate(boolean date_of_permits_validate) {
		this.date_of_permits_validate = date_of_permits_validate;
	}
	public String getPromoter_name() {
		return promoter_name;
	}
	public void setPromoter_name(String promoter_name) {
		this.promoter_name = promoter_name;
	}
	public boolean isPromoter_name_validate() {
		return promoter_name_validate;
	}
	public void setPromoter_name_validate(boolean promoter_name_validate) {
		this.promoter_name_validate = promoter_name_validate;
	}
	public String getWork_reference_number() {
		return work_reference_number;
	}
	public void setWork_reference_number(String work_reference_number) {
		this.work_reference_number = work_reference_number;
	}
	public boolean isWork_reference_number_validate() {
		return work_reference_number_validate;
	}
	public void setWork_reference_number_validate(boolean work_reference_number_validate) {
		this.work_reference_number_validate = work_reference_number_validate;
	}
	public String getPermit_reference_number() {
		return permit_reference_number;
	}
	public void setPermit_reference_number(String permit_reference_number) {
		this.permit_reference_number = permit_reference_number;
	}
	public boolean isPermit_reference_number_validate() {
		return permit_reference_number_validate;
	}
	public void setPermit_reference_number_validate(boolean permit_reference_number_validate) {
		this.permit_reference_number_validate = permit_reference_number_validate;
	}
	public String getApplication_type() {
		return application_type;
	}
	public void setApplication_type(String application_type) {
		this.application_type = application_type;
	}
	public boolean isApplication_type_validate() {
		return application_type_validate;
	}
	public void setApplication_type_validate(boolean application_type_validate) {
		this.application_type_validate = application_type_validate;
	}
	public String getType_of_work() {
		return type_of_work;
	}
	public void setType_of_work(String type_of_work) {
		this.type_of_work = type_of_work;
	}
	public boolean isType_of_work_validate() {
		return type_of_work_validate;
	}
	public void setType_of_work_validate(boolean type_of_work_validate) {
		this.type_of_work_validate = type_of_work_validate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isLocation_validate() {
		return location_validate;
	}
	public void setLocation_validate(boolean location_validate) {
		this.location_validate = location_validate;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public boolean isStreet_validate() {
		return street_validate;
	}
	public void setStreet_validate(boolean street_validate) {
		this.street_validate = street_validate;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public boolean isTown_validate() {
		return town_validate;
	}
	public void setTown_validate(boolean town_validate) {
		this.town_validate = town_validate;
	}
	public BigDecimal getUsrn() {
		return usrn;
	}
	public void setUsrn(BigDecimal usrn) {
		this.usrn = usrn;
	}
	public boolean isUsrn_validate() {
		return usrn_validate;
	}
	public void setUsrn_validate(boolean usrn_validate) {
		this.usrn_validate = usrn_validate;
	}
	public String getActivity_type() {
		return activity_type;
	}
	public void setActivity_type(String activity_type) {
		this.activity_type = activity_type;
	}
	public boolean isActivity_type_validate() {
		return activity_type_validate;
	}
	public void setActivity_type_validate(boolean activity_type_validate) {
		this.activity_type_validate = activity_type_validate;
	}
	public String getWork_description() {
		return work_description;
	}
	public void setWork_description(String work_description) {
		this.work_description = work_description;
	}
	public boolean isWork_description_validate() {
		return work_description_validate;
	}
	public void setWork_description_validate(boolean work_description_validate) {
		this.work_description_validate = work_description_validate;
	}
	public String getWorks_with_excavation() {
		return works_with_excavation;
	}
	public void setWorks_with_excavation(String works_with_excavation) {
		this.works_with_excavation = works_with_excavation;
	}
	public boolean isWorks_with_excavation_validate() {
		return works_with_excavation_validate;
	}
	public void setWorks_with_excavation_validate(boolean works_with_excavation_validate) {
		this.works_with_excavation_validate = works_with_excavation_validate;
	}
	public String getReinstatement_category() {
		return reinstatement_category;
	}
	public void setReinstatement_category(String reinstatement_category) {
		this.reinstatement_category = reinstatement_category;
	}
	public boolean isReinstatement_category_validate() {
		return reinstatement_category_validate;
	}
	public void setReinstatement_category_validate(boolean reinstatement_category_validate) {
		this.reinstatement_category_validate = reinstatement_category_validate;
	}
	public String getTraffic_management_method() {
		return traffic_management_method;
	}
	public void setTraffic_management_method(String traffic_management_method) {
		this.traffic_management_method = traffic_management_method;
	}
	public boolean isTraffic_management_method_validate() {
		return traffic_management_method_validate;
	}
	public void setTraffic_management_method_validate(boolean traffic_management_method_validate) {
		this.traffic_management_method_validate = traffic_management_method_validate;
	}
	public String getCollaborative_working() {
		return collaborative_working;
	}
	public void setCollaborative_working(String collaborative_working) {
		this.collaborative_working = collaborative_working;
	}
	public boolean isCollaborative_working_validate() {
		return collaborative_working_validate;
	}
	public void setCollaborative_working_validate(boolean collaborative_working_validate) {
		this.collaborative_working_validate = collaborative_working_validate;
	}
	public String getEastimated_start_date() {
		return eastimated_start_date;
	}
	public void setEastimated_start_date(String eastimated_start_date) {
		this.eastimated_start_date = eastimated_start_date;
	}
	public boolean isEastimated_start_date_validate() {
		return eastimated_start_date_validate;
	}
	public void setEastimated_start_date_validate(boolean eastimated_start_date_validate) {
		this.eastimated_start_date_validate = eastimated_start_date_validate;
	}
	public String getProposed_start_time() {
		return proposed_start_time;
	}
	public void setProposed_start_time(String proposed_start_time) {
		this.proposed_start_time = proposed_start_time;
	}
	public boolean isProposed_start_time_validate() {
		return proposed_start_time_validate;
	}
	public void setProposed_start_time_validate(boolean proposed_start_time_validate) {
		this.proposed_start_time_validate = proposed_start_time_validate;
	}
	public String getProposed_end_date() {
		return proposed_end_date;
	}
	public void setProposed_end_date(String proposed_end_date) {
		this.proposed_end_date = proposed_end_date;
	}
	public boolean isProposed_end_date_validate() {
		return proposed_end_date_validate;
	}
	public void setProposed_end_date_validate(boolean proposed_end_date_validate) {
		this.proposed_end_date_validate = proposed_end_date_validate;
	}
	public String getProposed_end_time() {
		return proposed_end_time;
	}
	public void setProposed_end_time(String proposed_end_time) {
		this.proposed_end_time = proposed_end_time;
	}
	public boolean isProposed_end_time_validate() {
		return proposed_end_time_validate;
	}
	public void setProposed_end_time_validate(boolean proposed_end_time_validate) {
		this.proposed_end_time_validate = proposed_end_time_validate;
	}
	public String getDuration_of_work() {
		return duration_of_work;
	}
	public void setDuration_of_work(String duration_of_work) {
		this.duration_of_work = duration_of_work;
	}
	public boolean isDuration_of_work_validate() {
		return duration_of_work_validate;
	}
	public void setDuration_of_work_validate(boolean duration_of_work_validate) {
		this.duration_of_work_validate = duration_of_work_validate;
	}
	public String getPermit_status() {
		return permit_status;
	}
	public void setPermit_status(String permit_status) {
		this.permit_status = permit_status;
	}
	public boolean isPermit_status_validate() {
		return permit_status_validate;
	}
	public void setPermit_status_validate(boolean permit_status_validate) {
		this.permit_status_validate = permit_status_validate;
	}
	public String getPermit_deeming_date() {
		return permit_deeming_date;
	}
	public void setPermit_deeming_date(String permit_deeming_date) {
		this.permit_deeming_date = permit_deeming_date;
	}
	public boolean isPermit_deeming_date_validate() {
		return permit_deeming_date_validate;
	}
	public void setPermit_deeming_date_validate(boolean permit_deeming_date_validate) {
		this.permit_deeming_date_validate = permit_deeming_date_validate;
	}
	
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public boolean isDiscount_validate() {
		return discount_validate;
	}
	public void setDiscount_validate(boolean discount_validate) {
		this.discount_validate = discount_validate;
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