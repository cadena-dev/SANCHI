package com.saanchi.commomUtility;
import java.io.Serializable;


public class FPNStatementVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String promoter_name ;
	private String work_reference_number;
	private String notice_date;
	private String notice_time;
	private String actual_date;
	private String actual_time;
	private String fpn_comments;
	
	
	
	public String getPromoter_name() {
		return promoter_name;
	}
	public void setPromoter_name(String promoter_name) {
		this.promoter_name = promoter_name;
	}
	public String getWork_reference_number() {
		return work_reference_number;
	}
	public void setWork_reference_number(String work_reference_number) {
		this.work_reference_number = work_reference_number;
	}
	public String getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}
	public String getNotice_time() {
		return notice_time;
	}
	public void setNotice_time(String notice_time) {
		this.notice_time = notice_time;
	}
	public String getActual_date() {
		return actual_date;
	}
	public void setActual_date(String actual_date) {
		this.actual_date = actual_date;
	}
	public String getActual_time() {
		return actual_time;
	}
	public void setActual_time(String actual_time) {
		this.actual_time = actual_time;
	}
	public String getFpn_comments() {
		return fpn_comments;
	}
	public void setFpn_comments(String fpn_comments) {
		this.fpn_comments = fpn_comments;
	}
	
	
	
	
	

	

}
