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
@Table(name = "pa_export_provisional")
public class PAExportProvisional implements Serializable {

	private static final long serialVersionUID = -1L;

	private int pa_export_provisional_id;
	private String work_reference_number;
	private String street_name;
	private String area_name;
	private BigDecimal usrn;
	private String highway_authority;
	private String road_category;
	private String works_location;
	private String works_location_description;
	private String works_location_coordinates;
	private String designations_applicable_to_works;
	private String promoter_organisation;
	private String promoter_contact_details;
	private String approved_contractor;
	private String contractor_contact_details;
	private String additional_contact;
	private String additional_contact_number;
	private String additional_contact_email;
	private Date proposed_start_date;
	private Date proposed_start_time;
	private Date proposed_end_date;
	private Date proposed_end_time;
	private String total_works_duration;
	private String activity_type;
	private String description_of_work;
	private String is_an_excavation_required;
	private String works_category;
	private String project_reference_number;
	private String traffic_management_type;
	private String assessment_status;
	private String is_deemed;
	private String footway_closure;
	private String is_lane_rental_applicable;
	private String is_traffic_management_plan_required;
	private String is_environmental_health_notifiable;
	private String is_there_collaborative_working;
	private String permit_conditions;
	private String work_status;
	private Date deadline_date;
	private String permit_status;
	private String reasons_for_refusal;
	private String assessment_comments;
	private Date status_changed_date;
	private String lane_rental_assessment_outcome_id;
	private String lane_rental_assessment_charges_agreed;
	private String lane_rental_assessment_charge_band;
	private String lane_rental_assessment_chargeable_days;
	private String lane_rental_assessment_additional_details;
	private String permit_reference_number;
	private BigDecimal assessment_discount;
	private String assessment_discount_reason;
	private BigDecimal workstream_prefix;
	private String hs2_in_act_limits;
	private Date hs2_consultation_requested_response_date;
	private String hs2_highway_exemption;
	private String hs2_is_consent;
	private String hs2_is_consultation;
	private String hs2_acknowledged;
	private Date hs2_acknowledged_date_time;
	private String ever_modification_requested;
	private String is_duration_challenged;
	private Date actual_start_date;
	private Date actual_end_date;
	private String revoke_reason;
	private Date reasonable_period_end_date;
	private String town;
	private String is_covid_19_response;
	private String hs2_permit_additional_usrns;
	private String excavation_carried_out;
	private String linked_section_81;
	private String modification_request_details;
	private String has_attachments;
	
	private Date created_at;
	private int created_by;
	private Date upload_date;
	private String file_name;
	private String status;
	private int office_code;
	private int year_month;
	
	
	
	@Id
	@Column(name = "pa_export_provisional_id")
	@SequenceGenerator(name = "pa_export_provisional_seq", sequenceName = "pa_export_provisional_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pa_export_provisional_seq")
	public int getPa_export_provisional_id() {
		return pa_export_provisional_id;
	}
	public void setPa_export_provisional_id(int pa_export_provisional_id) {
		this.pa_export_provisional_id = pa_export_provisional_id;
	}
	
	
	@Column(name = "work_reference_number")
	public String getWork_reference_number() {
		return work_reference_number;
	}
	public void setWork_reference_number(String work_reference_number) {
		this.work_reference_number = work_reference_number;
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
	
	
	@Column(name = "usrn")
	public BigDecimal getUsrn() {
		return usrn;
	}
	public void setUsrn(BigDecimal usrn) {
		this.usrn = usrn;
	}
	
	
	@Column(name = "highway_authority")
	public String getHighway_authority() {
		return highway_authority;
	}
	
	public void setHighway_authority(String highway_authority) {
		this.highway_authority = highway_authority;
	}
	
	@Column(name = "road_category")
	public String getRoad_category() {
		return road_category;
	}
	public void setRoad_category(String road_category) {
		this.road_category = road_category;
	}
	
	
	@Column(name = "works_location")
	public String getWorks_location() {
		return works_location;
	}
	public void setWorks_location(String works_location) {
		this.works_location = works_location;
	}
	
	@Column(name = "works_location_description")
	public String getWorks_location_description() {
		return works_location_description;
	}
	public void setWorks_location_description(String works_location_description) {
		this.works_location_description = works_location_description;
	}
	
	
	@Column(name = "works_location_coordinates")
	public String getWorks_location_coordinates() {
		return works_location_coordinates;
	}
	public void setWorks_location_coordinates(String works_location_coordinates) {
		this.works_location_coordinates = works_location_coordinates;
	}
	
	
	@Column(name = "designations_applicable_to_works")
	public String getDesignations_applicable_to_works() {
		return designations_applicable_to_works;
	}
	public void setDesignations_applicable_to_works(String designations_applicable_to_works) {
		this.designations_applicable_to_works = designations_applicable_to_works;
	}
	
	
	@Column(name = "promoter_organisation")
	public String getPromoter_organisation() {
		return promoter_organisation;
	}
	public void setPromoter_organisation(String promoter_organisation) {
		this.promoter_organisation = promoter_organisation;
	}
	
	
	@Column(name = "promoter_contact_details")
	public String getPromoter_contact_details() {
		return promoter_contact_details;
	}
	public void setPromoter_contact_details(String promoter_contact_details) {
		this.promoter_contact_details = promoter_contact_details;
	}
	
	
	@Column(name = "approved_contractor")
	public String getApproved_contractor() {
		return approved_contractor;
	}
	public void setApproved_contractor(String approved_contractor) {
		this.approved_contractor = approved_contractor;
	}
	
	
	
	@Column(name = "contractor_contact_details")
	public String getContractor_contact_details() {
		return contractor_contact_details;
	}
	public void setContractor_contact_details(String contractor_contact_details) {
		this.contractor_contact_details = contractor_contact_details;
	}
	
	
	@Column(name = "additional_contact")
	public String getAdditional_contact() {
		return additional_contact;
	}
	public void setAdditional_contact(String additional_contact) {
		this.additional_contact = additional_contact;
	}
	
	
	@Column(name = "additional_contact_number")
	public String getAdditional_contact_number() {
		return additional_contact_number;
	}
	public void setAdditional_contact_number(String additional_contact_number) {
		this.additional_contact_number = additional_contact_number;
	}
	
	
	@Column(name = "additional_contact_email")
	public String getAdditional_contact_email() {
		return additional_contact_email;
	}
	public void setAdditional_contact_email(String additional_contact_email) {
		this.additional_contact_email = additional_contact_email;
	}
	
	
	@Column(name = "proposed_start_date")
	public Date getProposed_start_date() {
		return proposed_start_date;
	}
	public void setProposed_start_date(Date proposed_start_date) {
		this.proposed_start_date = proposed_start_date;
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
	
	
	@Column(name = "total_works_duration")
	public String getTotal_works_duration() {
		return total_works_duration;
	}
	public void setTotal_works_duration(String total_works_duration) {
		this.total_works_duration = total_works_duration;
	}
	
	
	@Column(name = "activity_type")
	public String getActivity_type() {
		return activity_type;
	}
	public void setActivity_type(String activity_type) {
		this.activity_type = activity_type;
	}
	
	
	@Column(name = "description_of_work")
	public String getDescription_of_work() {
		return description_of_work;
	}
	public void setDescription_of_work(String description_of_work) {
		this.description_of_work = description_of_work;
	}
	
	
	@Column(name = "is_an_excavation_required")
	public String getIs_an_excavation_required() {
		return is_an_excavation_required;
	}
	public void setIs_an_excavation_required(String is_an_excavation_required) {
		this.is_an_excavation_required = is_an_excavation_required;
	}
	
	
	@Column(name = "works_category")
	public String getWorks_category() {
		return works_category;
	}
	
	public void setWorks_category(String works_category) {
		this.works_category = works_category;
	}
	
	
	@Column(name = "project_reference_number")
	public String getProject_reference_number() {
		return project_reference_number;
	}
	public void setProject_reference_number(String project_reference_number) {
		this.project_reference_number = project_reference_number;
	}
	
	
	@Column(name = "traffic_management_type")
	public String getTraffic_management_type() {
		return traffic_management_type;
	}
	public void setTraffic_management_type(String traffic_management_type) {
		this.traffic_management_type = traffic_management_type;
	}
	
	
	@Column(name = "assessment_status")
	public String getAssessment_status() {
		return assessment_status;
	}
	public void setAssessment_status(String assessment_status) {
		this.assessment_status = assessment_status;
	}
	
	
	@Column(name = "is_deemed")
	public String getIs_deemed() {
		return is_deemed;
	}
	public void setIs_deemed(String is_deemed) {
		this.is_deemed = is_deemed;
	}
	
	
	@Column(name = "footway_closure")
	public String getFootway_closure() {
		return footway_closure;
	}
	public void setFootway_closure(String footway_closure) {
		this.footway_closure = footway_closure;
	}
	
	
	@Column(name = "is_lane_rental_applicable")
	public String getIs_lane_rental_applicable() {
		return is_lane_rental_applicable;
	}
	public void setIs_lane_rental_applicable(String is_lane_rental_applicable) {
		this.is_lane_rental_applicable = is_lane_rental_applicable;
	}
	
	
	@Column(name = "is_traffic_management_plan_required")
	public String getIs_traffic_management_plan_required() {
		return is_traffic_management_plan_required;
	}
	public void setIs_traffic_management_plan_required(String is_traffic_management_plan_required) {
		this.is_traffic_management_plan_required = is_traffic_management_plan_required;
	}
	
	
	@Column(name = "is_environmental_health_notifiable")
	public String getIs_environmental_health_notifiable() {
		return is_environmental_health_notifiable;
	}
	public void setIs_environmental_health_notifiable(String is_environmental_health_notifiable) {
		this.is_environmental_health_notifiable = is_environmental_health_notifiable;
	}
	
	
	@Column(name = "is_there_collaborative_working")
	public String getIs_there_collaborative_working() {
		return is_there_collaborative_working;
	}
	
	public void setIs_there_collaborative_working(String is_there_collaborative_working) {
		this.is_there_collaborative_working = is_there_collaborative_working;
	}
	
	@Column(name = "permit_conditions")
	public String getPermit_conditions() {
		return permit_conditions;
	}
	public void setPermit_conditions(String permit_conditions) {
		this.permit_conditions = permit_conditions;
	}
	
	
	@Column(name = "work_status")
	public String getWork_status() {
		return work_status;
	}
	public void setWork_status(String work_status) {
		this.work_status = work_status;
	}
	
	
	@Column(name = "deadline_date")
	public Date getDeadline_date() {
		return deadline_date;
	}
	public void setDeadline_date(Date deadline_date) {
		this.deadline_date = deadline_date;
	}
	
	
	@Column(name = "permit_status")
	public String getPermit_status() {
		return permit_status;
	}
	public void setPermit_status(String permit_status) {
		this.permit_status = permit_status;
	}
	
	
	@Column(name = "reasons_for_refusal")
	public String getReasons_for_refusal() {
		return reasons_for_refusal;
	}
	public void setReasons_for_refusal(String reasons_for_refusal) {
		this.reasons_for_refusal = reasons_for_refusal;
	}
	
	
	@Column(name = "assessment_comments")
	public String getAssessment_comments() {
		return assessment_comments;
	}
	public void setAssessment_comments(String assessment_comments) {
		this.assessment_comments = assessment_comments;
	}
	
	
	@Column(name = "status_changed_date")
	public Date getStatus_changed_date() {
		return status_changed_date;
	}
	public void setStatus_changed_date(Date status_changed_date) {
		this.status_changed_date = status_changed_date;
	}
	
	
	@Column(name = "lane_rental_assessment_outcome_id")
	public String getLane_rental_assessment_outcome_id() {
		return lane_rental_assessment_outcome_id;
	}
	public void setLane_rental_assessment_outcome_id(String lane_rental_assessment_outcome_id) {
		this.lane_rental_assessment_outcome_id = lane_rental_assessment_outcome_id;
	}
	
	@Column(name = "lane_rental_assessment_charges_agreed")
	public String getLane_rental_assessment_charges_agreed() {
		return lane_rental_assessment_charges_agreed;
	}
	public void setLane_rental_assessment_charges_agreed(String lane_rental_assessment_charges_agreed) {
		this.lane_rental_assessment_charges_agreed = lane_rental_assessment_charges_agreed;
	}
	
	
	@Column(name = "lane_rental_assessment_charge_band")
	public String getLane_rental_assessment_charge_band() {
		return lane_rental_assessment_charge_band;
	}
	public void setLane_rental_assessment_charge_band(String lane_rental_assessment_charge_band) {
		this.lane_rental_assessment_charge_band = lane_rental_assessment_charge_band;
	}
	
	
	@Column(name = "lane_rental_assessment_chargeable_days")
	public String getLane_rental_assessment_chargeable_days() {
		return lane_rental_assessment_chargeable_days;
	}
	public void setLane_rental_assessment_chargeable_days(String lane_rental_assessment_chargeable_days) {
		this.lane_rental_assessment_chargeable_days = lane_rental_assessment_chargeable_days;
	}
	
	
	@Column(name = "lane_rental_assessment_additional_details")
	public String getLane_rental_assessment_additional_details() {
		return lane_rental_assessment_additional_details;
	}
	public void setLane_rental_assessment_additional_details(String lane_rental_assessment_additional_details) {
		this.lane_rental_assessment_additional_details = lane_rental_assessment_additional_details;
	}
	
	
	@Column(name = "permit_reference_number")
	public String getPermit_reference_number() {
		return permit_reference_number;
	}
	public void setPermit_reference_number(String permit_reference_number) {
		this.permit_reference_number = permit_reference_number;
	}
	
	
	@Column(name = "assessment_discount")
	public BigDecimal getAssessment_discount() {
		return assessment_discount;
	}
	public void setAssessment_discount(BigDecimal assessment_discount) {
		this.assessment_discount = assessment_discount;
	}
	
	
	@Column(name = "assessment_discount_reason")
	public String getAssessment_discount_reason() {
		return assessment_discount_reason;
	}
	public void setAssessment_discount_reason(String assessment_discount_reason) {
		this.assessment_discount_reason = assessment_discount_reason;
	}
	
	
	@Column(name = "workstream_prefix")
	public BigDecimal getWorkstream_prefix() {
		return workstream_prefix;
	}
	public void setWorkstream_prefix(BigDecimal workstream_prefix) {
		this.workstream_prefix = workstream_prefix;
	}
	
	
	@Column(name = "hs2_in_act_limits")
	public String getHs2_in_act_limits() {
		return hs2_in_act_limits;
	}
	
	public void setHs2_in_act_limits(String hs2_in_act_limits) {
		this.hs2_in_act_limits = hs2_in_act_limits;
	}
	
	
	@Column(name = "hs2_consultation_requested_response_date")
	public Date getHs2_consultation_requested_response_date() {
		return hs2_consultation_requested_response_date;
	}
	public void setHs2_consultation_requested_response_date(Date hs2_consultation_requested_response_date) {
		this.hs2_consultation_requested_response_date = hs2_consultation_requested_response_date;
	}
	
	
	@Column(name = "hs2_highway_exemption")
	public String getHs2_highway_exemption() {
		return hs2_highway_exemption;
	}
	public void setHs2_highway_exemption(String hs2_highway_exemption) {
		this.hs2_highway_exemption = hs2_highway_exemption;
	}
	
	
	@Column(name = "hs2_is_consent")
	public String getHs2_is_consent() {
		return hs2_is_consent;
	}
	public void setHs2_is_consent(String hs2_is_consent) {
		this.hs2_is_consent = hs2_is_consent;
	}
	
	
	@Column(name = "hs2_is_consultation")
	public String getHs2_is_consultation() {
		return hs2_is_consultation;
	}
	public void setHs2_is_consultation(String hs2_is_consultation) {
		this.hs2_is_consultation = hs2_is_consultation;
	}
	
	
	@Column(name = "hs2_acknowledged")
	public String getHs2_acknowledged() {
		return hs2_acknowledged;
	}
	public void setHs2_acknowledged(String hs2_acknowledged) {
		this.hs2_acknowledged = hs2_acknowledged;
	}
	
	
	@Column(name = "hs2_acknowledged_date_time")
	public Date getHs2_acknowledged_date_time() {
		return hs2_acknowledged_date_time;
	}
	public void setHs2_acknowledged_date_time(Date hs2_acknowledged_date_time) {
		this.hs2_acknowledged_date_time = hs2_acknowledged_date_time;
	}
	
	
	@Column(name = "ever_modification_requested")
	public String getEver_modification_requested() {
		return ever_modification_requested;
	}
	public void setEver_modification_requested(String ever_modification_requested) {
		this.ever_modification_requested = ever_modification_requested;
	}
	
	
	@Column(name = "is_duration_challenged")
	public String getIs_duration_challenged() {
		return is_duration_challenged;
	}
	public void setIs_duration_challenged(String is_duration_challenged) {
		this.is_duration_challenged = is_duration_challenged;
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
	
	
	@Column(name = "revoke_reason")
	public String getRevoke_reason() {
		return revoke_reason;
	}
	public void setRevoke_reason(String revoke_reason) {
		this.revoke_reason = revoke_reason;
	}
	
	
	@Column(name = "reasonable_period_end_date")
	public Date getReasonable_period_end_date() {
		return reasonable_period_end_date;
	}
	public void setReasonable_period_end_date(Date reasonable_period_end_date) {
		this.reasonable_period_end_date = reasonable_period_end_date;
	}
	
	
	@Column(name = "town")
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	
	
	@Column(name = "is_covid_19_response")
	public String getIs_covid_19_response() {
		return is_covid_19_response;
	}
	public void setIs_covid_19_response(String is_covid_19_response) {
		this.is_covid_19_response = is_covid_19_response;
	}
	
	
	@Column(name = "hs2_permit_additional_usrns")
	public String getHs2_permit_additional_usrns() {
		return hs2_permit_additional_usrns;
	}
	
	public void setHs2_permit_additional_usrns(String hs2_permit_additional_usrns) {
		this.hs2_permit_additional_usrns = hs2_permit_additional_usrns;
	}
	
	
	@Column(name = "excavation_carried_out")
	public String getExcavation_carried_out() {
		return excavation_carried_out;
	}
	public void setExcavation_carried_out(String excavation_carried_out) {
		this.excavation_carried_out = excavation_carried_out;
	}
	
	
	@Column(name = "linked_section_81")
	public String getLinked_section_81() {
		return linked_section_81;
	}
	
	public void setLinked_section_81(String linked_section_81) {
		this.linked_section_81 = linked_section_81;
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
	
	
	@Column(name = "modification_request_details")
	public String getModification_request_details() {
		return modification_request_details;
	}
	public void setModification_request_details(String modification_request_details) {
		this.modification_request_details = modification_request_details;
	}
	
	@Column(name = "has_attachments")
	public String getHas_attachments() {
		return has_attachments;
	}
	public void setHas_attachments(String has_attachments) {
		this.has_attachments = has_attachments;
	}

	
	

}