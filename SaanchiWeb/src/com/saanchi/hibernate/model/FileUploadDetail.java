package com.saanchi.hibernate.model;

import java.io.Serializable;
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
@Table(name = "file_upload_detail")
public class FileUploadDetail implements Serializable {

	private static final long serialVersionUID = -1L;

	
	private int upload_id;
	private Date created_at;
	private int created_by;
	private String upload_type;
	private String filename;
	private int no_of_records;
	private int successful_records;
	private int error_records;
	
	
	
	@Id
	@Column(name = "upload_id")
	@SequenceGenerator(name = "file_upload_detail_seq", sequenceName = "file_upload_detail_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_upload_detail_seq")
	public int getUpload_id() {
		return upload_id;
	}
	public void setUpload_id(int upload_id) {
		this.upload_id = upload_id;
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
	
	
	@Column(name = "upload_type")
	public String getUpload_type() {
		return upload_type;
	}
	public void setUpload_type(String upload_type) {
		this.upload_type = upload_type;
	}
	
	@Column(name = "filename")
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	@Column(name = "no_of_records")
	public int getNo_of_records() {
		return no_of_records;
	}
	public void setNo_of_records(int no_of_records) {
		this.no_of_records = no_of_records;
	}
	
	
	@Column(name = "successful_records")
	public int getSuccessful_records() {
		return successful_records;
	}
	public void setSuccessful_records(int successful_records) {
		this.successful_records = successful_records;
	}
	
	
	@Column(name = "error_records")
	public int getError_records() {
		return error_records;
	}
	public void setError_records(int error_records) {
		this.error_records = error_records;
	
	}
	
}