/**
 * Class Name  : OptionsVO
 * Description : Can Store the Options in a Drop down List
 * Created by  : TCS/Sabyasachi Bhattacharya
 * Created on  : 7-SEP-04
 * Modified    : [Who , When , What]
 */
package com.saanchi.optionVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class FPNValidationVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int fpn_export_stage_id;
	private boolean works_reference_number_validate;
	private String works_reference_number;
	private boolean fpn_number_validate;
	private String fpn_number;
	private boolean fpn_status_validate;
	private String fpn_status;
	private boolean offence_code_validate;
	private String offence_code;
	private boolean  offence_date_validate;
	private String  offence_date;
	private boolean offence_details_validate;
	private String offence_details;
	private boolean  issue_date_time_validate;
	private String  issue_date_time;
	private boolean street_name_validate;
	private String street_name;
	private boolean uern_validate;
	private BigDecimal usrn;	
	private boolean works_promoter_validate;
	private String works_promoter;
	private boolean issuing_authority_validate;
	private String issuing_authority;
	private boolean status_changed_date_validate;
	private String  status_changed_date;
	private boolean amount_paid_validate;
	private BigDecimal amount_paid;
	private String comments_txt;
	private Date created_at;
	private int created_by;
	private Date upload_date;
	private String file_name;
	private String status;
	private int office_code;
	private int year_month;
	
	
	
	
	public int getFpn_export_stage_id() {
		return fpn_export_stage_id;
	}
	public void setFpn_export_stage_id(int fpn_export_stage_id) {
		this.fpn_export_stage_id = fpn_export_stage_id;
	}
	public boolean isWorks_reference_number_validate() {
		return works_reference_number_validate;
	}
	public void setWorks_reference_number_validate(boolean works_reference_number_validate) {
		this.works_reference_number_validate = works_reference_number_validate;
	}
	public String getWorks_reference_number() {
		return works_reference_number;
	}
	public void setWorks_reference_number(String works_reference_number) {
		this.works_reference_number = works_reference_number;
	}
	public boolean isFpn_number_validate() {
		return fpn_number_validate;
	}
	public void setFpn_number_validate(boolean fpn_number_validate) {
		this.fpn_number_validate = fpn_number_validate;
	}
	public String getFpn_number() {
		return fpn_number;
	}
	public void setFpn_number(String fpn_number) {
		this.fpn_number = fpn_number;
	}
	public boolean isFpn_status_validate() {
		return fpn_status_validate;
	}
	public void setFpn_status_validate(boolean fpn_status_validate) {
		this.fpn_status_validate = fpn_status_validate;
	}
	public String getFpn_status() {
		return fpn_status;
	}
	public void setFpn_status(String fpn_status) {
		this.fpn_status = fpn_status;
	}
	public boolean isOffence_code_validate() {
		return offence_code_validate;
	}
	public void setOffence_code_validate(boolean offence_code_validate) {
		this.offence_code_validate = offence_code_validate;
	}
	public String getOffence_code() {
		return offence_code;
	}
	public void setOffence_code(String offence_code) {
		this.offence_code = offence_code;
	}
	public boolean isOffence_date_validate() {
		return offence_date_validate;
	}
	public void setOffence_date_validate(boolean offence_date_validate) {
		this.offence_date_validate = offence_date_validate;
	}
	public String getOffence_date() {
		return offence_date;
	}
	public void setOffence_date(String offence_date) {
		this.offence_date = offence_date;
	}
	public boolean isOffence_details_validate() {
		return offence_details_validate;
	}
	public void setOffence_details_validate(boolean offence_details_validate) {
		this.offence_details_validate = offence_details_validate;
	}
	public String getOffence_details() {
		return offence_details;
	}
	public void setOffence_details(String offence_details) {
		this.offence_details = offence_details;
	}
	public boolean isIssue_date_time_validate() {
		return issue_date_time_validate;
	}
	public void setIssue_date_time_validate(boolean issue_date_time_validate) {
		this.issue_date_time_validate = issue_date_time_validate;
	}
	public String getIssue_date_time() {
		return issue_date_time;
	}
	public void setIssue_date_time(String issue_date_time) {
		this.issue_date_time = issue_date_time;
	}
	public boolean isStreet_name_validate() {
		return street_name_validate;
	}
	public void setStreet_name_validate(boolean street_name_validate) {
		this.street_name_validate = street_name_validate;
	}
	public String getStreet_name() {
		return street_name;
	}
	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}
	public boolean isUern_validate() {
		return uern_validate;
	}
	public void setUern_validate(boolean uern_validate) {
		this.uern_validate = uern_validate;
	}
	public BigDecimal getUsrn() {
		return usrn;
	}
	public void setUsrn(BigDecimal usrn) {
		this.usrn = usrn;
	}
	public boolean isWorks_promoter_validate() {
		return works_promoter_validate;
	}
	public void setWorks_promoter_validate(boolean works_promoter_validate) {
		this.works_promoter_validate = works_promoter_validate;
	}
	public String getWorks_promoter() {
		return works_promoter;
	}
	public void setWorks_promoter(String works_promoter) {
		this.works_promoter = works_promoter;
	}
	public boolean isIssuing_authority_validate() {
		return issuing_authority_validate;
	}
	public void setIssuing_authority_validate(boolean issuing_authority_validate) {
		this.issuing_authority_validate = issuing_authority_validate;
	}
	public String getIssuing_authority() {
		return issuing_authority;
	}
	public void setIssuing_authority(String issuing_authority) {
		this.issuing_authority = issuing_authority;
	}
	public boolean isStatus_changed_date_validate() {
		return status_changed_date_validate;
	}
	public void setStatus_changed_date_validate(boolean status_changed_date_validate) {
		this.status_changed_date_validate = status_changed_date_validate;
	}
	public String getStatus_changed_date() {
		return status_changed_date;
	}
	public void setStatus_changed_date(String status_changed_date) {
		this.status_changed_date = status_changed_date;
	}
	public boolean isAmount_paid_validate() {
		return amount_paid_validate;
	}
	public void setAmount_paid_validate(boolean amount_paid_validate) {
		this.amount_paid_validate = amount_paid_validate;
	}
	public BigDecimal getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(BigDecimal amount_paid) {
		this.amount_paid = amount_paid;
	}
	public String getComments_txt() {
		return comments_txt;
	}
	public void setComments_txt(String comments_txt) {
		this.comments_txt = comments_txt;
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
