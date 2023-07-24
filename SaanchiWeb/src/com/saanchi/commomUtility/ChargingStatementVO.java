/**
 * Class Name  : OptionsVO
 * Description : Can Store the Options in a Drop down List
 * Created by  : TCS/Sabyasachi Bhattacharya
 * Created on  : 7-SEP-04
 * Modified    : [Who , When , What]
 */
package com.saanchi.commomUtility;
import java.io.Serializable;


public class ChargingStatementVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String works_reference ;
	private String permit_reference;
	private String date_of_permits;
	private String works_type;
	private String permit_type;
	private String applicable_charge;
	private String standard_charges;
	private String adjustment_charges;
	private String invoice_amount;
	private String work_location;
	private String street;
	
	
	public String getWorks_reference() {
		return works_reference;
	}
	public void setWorks_reference(String works_reference) {
		this.works_reference = works_reference;
	}
	public String getPermit_reference() {
		return permit_reference;
	}
	public void setPermit_reference(String permit_reference) {
		this.permit_reference = permit_reference;
	}
	public String getDate_of_permits() {
		return date_of_permits;
	}
	public void setDate_of_permits(String date_of_permits) {
		this.date_of_permits = date_of_permits;
	}
	public String getWorks_type() {
		return works_type;
	}
	public void setWorks_type(String works_type) {
		this.works_type = works_type;
	}
	public String getPermit_type() {
		return permit_type;
	}
	public void setPermit_type(String permit_type) {
		this.permit_type = permit_type;
	}
	public String getApplicable_charge() {
		return applicable_charge;
	}
	public void setApplicable_charge(String applicable_charge) {
		this.applicable_charge = applicable_charge;
	}
	public String getStandard_charges() {
		return standard_charges;
	}
	public void setStandard_charges(String standard_charges) {
		this.standard_charges = standard_charges;
	}
	public String getAdjustment_charges() {
		return adjustment_charges;
	}
	public void setAdjustment_charges(String adjustment_charges) {
		this.adjustment_charges = adjustment_charges;
	}
	public String getInvoice_amount() {
		return invoice_amount;
	}
	public void setInvoice_amount(String invoice_amount) {
		this.invoice_amount = invoice_amount;
	}
	public String getWork_location() {
		return work_location;
	}
	public void setWork_location(String work_location) {
		this.work_location = work_location;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	
	
	
	

	

}
