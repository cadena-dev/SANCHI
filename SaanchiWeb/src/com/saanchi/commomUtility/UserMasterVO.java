/**
 * Class Name  : OptionsVO
 * Description : Can Store the Options in a Drop down List
 * Created by  : TCS/Sabyasachi Bhattacharya
 * Created on  : 7-SEP-04
 * Modified    : [Who , When , What]
 */
package com.saanchi.commomUtility;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserMasterVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String emp_no;
	private String office_location;
	private String user_name;
	private String user_password;
	private String user_mail;
	private String user_contact_no;
	private String user_address;
	private String isactive;
	private String user_activity;
	private String user_type;
	private String emp_name;
	private String office_desc;
	private String user_type_desc;

	public UserMasterVO(String emp_no, String office_location, String user_name, String user_password, String user_mail,
			String user_contact_no, String user_address, String isactive,String user_activity ,String user_type, String emp_name,
			String office_desc, String user_type_desc) {

		this.emp_no = emp_no;
		this.office_location = office_location;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_mail = user_mail;
		this.user_contact_no = user_contact_no;
		this.user_address = user_address;
		this.isactive = isactive;
		this.user_activity=user_activity;
		this.user_type = user_type;
		this.emp_name = emp_name;
		this.office_desc = office_desc;
		this.user_type_desc = user_type_desc;

	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public String getOffice_location() {
		return office_location;
	}

	public void setOffice_location(String office_location) {
		this.office_location = office_location;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String getUser_contact_no() {
		return user_contact_no;
	}

	public void setUser_contact_no(String user_contact_no) {
		this.user_contact_no = user_contact_no;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getUser_activity() {
		return user_activity;
	}

	public void setUser_activity(String user_activity) {
		this.user_activity = user_activity;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getOffice_desc() {
		return office_desc;
	}

	public void setOffice_desc(String office_desc) {
		this.office_desc = office_desc;
	}

	public String getUser_type_desc() {
		return user_type_desc;
	}

	public void setUser_type_desc(String user_type_desc) {
		this.user_type_desc = user_type_desc;
	}

}
