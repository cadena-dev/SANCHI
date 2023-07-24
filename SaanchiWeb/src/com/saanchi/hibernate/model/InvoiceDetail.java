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
@Table(name = "invoice_detail")
public class InvoiceDetail implements Serializable {

	private static final long serialVersionUID = -1L;

	private int invoice_detail_id;
	private int office_code;
	private int year_month;
	private String promoter_name;
	private BigDecimal permit_fee_amount;
	private BigDecimal discount_applied;
	private BigDecimal invoiceable_amount;
	private String statement_issued;
	private Date statement_issued_date;
	private String promoter_response;
	private BigDecimal challenged_amount;
	private BigDecimal invoiced_amount;
	private Date invoiced_date;
	private String order_number;
	private String invoice_no;
	private BigDecimal paid_amount;
	private Date paid_date;
	private String comments;
	private String status;
	private String status_final;
	
	
	@Id
	@Column(name = "invoice_detail_id")
	@SequenceGenerator(name = "invoive_detail_seq", sequenceName = "invoive_detail_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoive_detail_seq")
	public int getInvoice_detail_id() {
		return invoice_detail_id;
	}
	public void setInvoice_detail_id(int invoice_detail_id) {
		this.invoice_detail_id = invoice_detail_id;
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
	
	@Column(name = "promoter_name")
	public String getPromoter_name() {
		return promoter_name;
	}
	public void setPromoter_name(String promoter_name) {
		this.promoter_name = promoter_name;
	}
	
	@Column(name = "permit_fee_amount")
	public BigDecimal getPermit_fee_amount() {
		return permit_fee_amount;
	}
	public void setPermit_fee_amount(BigDecimal permit_fee_amount) {
		this.permit_fee_amount = permit_fee_amount;
	}
	
	@Column(name = "discount_applied")
	public BigDecimal getDiscount_applied() {
		return discount_applied;
	}
	public void setDiscount_applied(BigDecimal discount_applied) {
		this.discount_applied = discount_applied;
	}
	
	@Column(name = "invoiceable_amount")
	public BigDecimal getInvoiceable_amount() {
		return invoiceable_amount;
	}
	public void setInvoiceable_amount(BigDecimal invoiceable_amount) {
		this.invoiceable_amount = invoiceable_amount;
	}
	
	@Column(name = "statement_issued")
	public String getStatement_issued() {
		return statement_issued;
	}
	public void setStatement_issued(String statement_issued) {
		this.statement_issued = statement_issued;
	}
	
	
	@Column(name = "statement_issued_date")
	public Date getStatement_issued_date() {
		return statement_issued_date;
	}
	public void setStatement_issued_date(Date statement_issued_date) {
		this.statement_issued_date = statement_issued_date;
	}
	
	@Column(name = "promoter_response")
	public String getPromoter_response() {
		return promoter_response;
	}
	public void setPromoter_response(String promoter_response) {
		this.promoter_response = promoter_response;
	}
	
	@Column(name = "challenged_amount")
	public BigDecimal getChallenged_amount() {
		return challenged_amount;
	}
	public void setChallenged_amount(BigDecimal challenged_amount) {
		this.challenged_amount = challenged_amount;
	}
	
	@Column(name = "invoiced_amount")
	public BigDecimal getInvoiced_amount() {
		return invoiced_amount;
	}
	public void setInvoiced_amount(BigDecimal invoiced_amount) {
		this.invoiced_amount = invoiced_amount;
	}
	
	@Column(name = "invoiced_date")
	public Date getInvoiced_date() {
		return invoiced_date;
	}
	public void setInvoiced_date(Date invoiced_date) {
		this.invoiced_date = invoiced_date;
	}
	
	@Column(name = "order_number")
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	
	@Column(name = "invoice_no")
	public String getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}
	
	
	@Column(name = "paid_amount")
	public BigDecimal getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(BigDecimal paid_amount) {
		this.paid_amount = paid_amount;
	}
	
	
	@Column(name = "paid_date")
	public Date getPaid_date() {
		return paid_date;
	}
	public void setPaid_date(Date paid_date) {
		this.paid_date = paid_date;
	}
	
	@Column(name = "comments")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Column(name = "status_final")
	public String getStatus_final() {
		return status_final;
	}
	public void setStatus_final(String status_final) {
		this.status_final = status_final;
	}
	
	
	
	
	

	
	
	
	

}