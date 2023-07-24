package com.saanchi.hibernate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserCreateModel implements Serializable {

	private static final long serialVersionUID = -1L;

	  private int emp_no;			
	  private int office_location;		
	  private String user_name;		
	  private String user_password;	
	  private String user_mail;		
	  private String user_contact_no;	
	  private String user_address;		
	  private Boolean isactive;		
	  private int user_type;      
	  private String emp_name;		
	  private int created_by;		
	  private Date created_datetime	;
	  private int updated_by;
	  private Date updated_datetime;
	  
	  @Id
		@Column(name = "emp_no")
		@SequenceGenerator(name="emp_seq", sequenceName="emp_seq",allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	
	
	@Column(name = "office_location")
	public int getOffice_location() {
		return office_location;
	}
	public void setOffice_location(int office_location) {
		this.office_location = office_location;
	}
	
	
	@Column(name = "user_name")
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	@Column(name = "user_password")
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
	@Column(name = "user_mail")
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}
	
	@Column(name = "user_contact_no")
	public String getUser_contact_no() {
		return user_contact_no;
	}
	public void setUser_contact_no(String user_contact_no) {
		this.user_contact_no = user_contact_no;
	}
	
	@Column(name = "user_address")
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	
	@Column(name = "isactive")
	public Boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	
	
	@Column(name = "user_type")
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	
	@Column(name = "emp_name")
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	
	
	@Column(name = "created_by")
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	
	@Column(name = "created_datetime")
	public Date getCreated_datetime() {
		return created_datetime;
	}
	public void setCreated_datetime(Date created_datetime) {
		this.created_datetime = created_datetime;
	}
	
	
	@Column(name = "updated_by")
	public int getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
	
	@Column(name = "updated_datetime")
	public Date getUpdated_datetime() {
		return updated_datetime;
	}
	public void setUpdated_datetime(Date updated_datetime) {
		this.updated_datetime = updated_datetime;
	}

	
	
	
	
	
	
	
	
	
	
	
}