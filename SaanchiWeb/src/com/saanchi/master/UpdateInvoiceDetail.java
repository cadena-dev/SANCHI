package com.saanchi.master;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;
import com.saanchi.commomUtility.ChargingStatementVO;
import com.saanchi.commomUtility.CommonHelper;
import com.saanchi.commomUtility.CommonOptionsVO;
import com.saanchi.commomUtility.CommonValidation;
import com.saanchi.hibernate.model.ChangeRequestProvisional;
import com.saanchi.hibernate.model.ChangeRequestStage;
import com.saanchi.hibernate.model.FPNExportProvisional;
import com.saanchi.hibernate.model.FPNExportStage;
import com.saanchi.hibernate.model.FeeExportProvisional;
import com.saanchi.hibernate.model.FeeExportStage;
import com.saanchi.hibernate.model.FileUploadDetail;
import com.saanchi.hibernate.model.HolidayMasterModel;
import com.saanchi.hibernate.model.InvoiceDetail;
import com.saanchi.hibernate.model.OfficeCreateModel;
import com.saanchi.hibernate.model.PAExportProvisional;
import com.saanchi.hibernate.model.PAExportStage;
import com.saanchi.hibernate.model.UserCreateModel;
import com.saanchi.hibernate.util.HibernateManager;

public class UpdateInvoiceDetail extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID = "";

	private ServletContext context;
	private HttpServletRequest request;

	private String office_location;
	private String yearCode;
	private String monthCode;
	private String promoterName;

	private String invoice_detail_id;
	private String permit_fee_amount;
	private String discount_applied;
	private String invoiceable_amount;
	private String statement_issued;
	private String statement_issued_date;
	private String promoter_response;
	private String challenged_amount;
	private String invoiced_amount;
	private String invoiced_date;
	private String order_number;
	private String invoice_no;
	private String paid_amount;
	private String paid_date;
	private String comments;
	private String status;
	private String status_final;

	List<CommonOptionsVO> officeList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> yearList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> monthList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> promoterList = new ArrayList<CommonOptionsVO>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	List<CommonOptionsVO> statementIssuedList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> promoterResponseList = new ArrayList<CommonOptionsVO>();

	private static final Logger log = Logger.getLogger(UpdateInvoiceDetail.class);
	HibernateManager manager = new HibernateManager();
	CommonHelper helper = new CommonHelper();
	private static CommonValidation validator = new CommonValidation();

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public void setServletContext(ServletContext arg0) {
		this.context = arg0;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public String getOffice_location() {
		return office_location;
	}

	public void setOffice_location(String office_location) {
		this.office_location = office_location;
	}

	public List<CommonOptionsVO> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<CommonOptionsVO> officeList) {
		this.officeList = officeList;
	}

	public String getYearCode() {
		return yearCode;
	}

	public void setYearCode(String yearCode) {
		this.yearCode = yearCode;
	}

	public String getMonthCode() {
		return monthCode;
	}

	public void setMonthCode(String monthCode) {
		this.monthCode = monthCode;
	}

	public List<CommonOptionsVO> getYearList() {
		return yearList;
	}

	public void setYearList(List<CommonOptionsVO> yearList) {
		this.yearList = yearList;
	}

	public List<CommonOptionsVO> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<CommonOptionsVO> monthList) {
		this.monthList = monthList;
	}

	public String getPromoterName() {
		return promoterName;
	}

	public void setPromoterName(String promoterName) {
		this.promoterName = promoterName;
	}

	public List<CommonOptionsVO> getPromoterList() {
		return promoterList;
	}

	public void setPromoterList(List<CommonOptionsVO> promoterList) {
		this.promoterList = promoterList;
	}

	public String getInvoice_detail_id() {
		return invoice_detail_id;
	}

	public void setInvoice_detail_id(String invoice_detail_id) {
		this.invoice_detail_id = invoice_detail_id;
	}

	public String getPermit_fee_amount() {
		return permit_fee_amount;
	}

	public void setPermit_fee_amount(String permit_fee_amount) {
		this.permit_fee_amount = permit_fee_amount;
	}

	public String getDiscount_applied() {
		return discount_applied;
	}

	public void setDiscount_applied(String discount_applied) {
		this.discount_applied = discount_applied;
	}

	public String getInvoiceable_amount() {
		return invoiceable_amount;
	}

	public void setInvoiceable_amount(String invoiceable_amount) {
		this.invoiceable_amount = invoiceable_amount;
	}

	public String getStatement_issued() {
		return statement_issued;
	}

	public void setStatement_issued(String statement_issued) {
		this.statement_issued = statement_issued;
	}

	public String getStatement_issued_date() {
		return statement_issued_date;
	}

	public void setStatement_issued_date(String statement_issued_date) {
		this.statement_issued_date = statement_issued_date;
	}

	public String getPromoter_response() {
		return promoter_response;
	}

	public void setPromoter_response(String promoter_response) {
		this.promoter_response = promoter_response;
	}

	public String getChallenged_amount() {
		return challenged_amount;
	}

	public void setChallenged_amount(String challenged_amount) {
		this.challenged_amount = challenged_amount;
	}

	public String getInvoiced_amount() {
		return invoiced_amount;
	}

	public void setInvoiced_amount(String invoiced_amount) {
		this.invoiced_amount = invoiced_amount;
	}

	public String getInvoiced_date() {
		return invoiced_date;
	}

	public void setInvoiced_date(String invoiced_date) {
		this.invoiced_date = invoiced_date;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public String getPaid_amount() {
		return paid_amount;
	}

	public void setPaid_amount(String paid_amount) {
		this.paid_amount = paid_amount;
	}

	public String getPaid_date() {
		return paid_date;
	}

	public void setPaid_date(String paid_date) {
		this.paid_date = paid_date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_final() {
		return status_final;
	}

	public void setStatus_final(String status_final) {
		this.status_final = status_final;
	}

	public List<CommonOptionsVO> getStatementIssuedList() {
		return statementIssuedList;
	}

	public void setStatementIssuedList(List<CommonOptionsVO> statementIssuedList) {
		this.statementIssuedList = statementIssuedList;
	}

	public List<CommonOptionsVO> getPromoterResponseList() {
		return promoterResponseList;
	}

	public void setPromoterResponseList(List<CommonOptionsVO> promoterResponseList) {
		this.promoterResponseList = promoterResponseList;
	}

	@SkipValidation
	public String execute() throws Exception {

		empID = (String) request.getSession().getAttribute("_EMPCODE");

		int returnMessage = 1;
		String returnValue = SUCCESS;

		try {

			log.fatal("At User master Entry");

			officeList = new ArrayList<CommonOptionsVO>();
			officeList = manager.searchOfficeList();
			yearList = new ArrayList<CommonOptionsVO>();
			monthList = new ArrayList<CommonOptionsVO>();
			promoterList = new ArrayList<CommonOptionsVO>();
			statementIssuedList = new ArrayList<CommonOptionsVO>();
			promoterResponseList = new ArrayList<CommonOptionsVO>();

			CommonOptionsVO optionVO = new CommonOptionsVO("", "");

			int year = Calendar.getInstance().get(Calendar.YEAR) - 1;
			optionVO = new CommonOptionsVO(String.valueOf(year), String.valueOf(year));
			yearList.add(optionVO);

			for (int i = 1; i < 10; i++) {
				year = year + 1;
				optionVO = new CommonOptionsVO(String.valueOf(year), String.valueOf(year));
				yearList.add(optionVO);
			}
			monthList = new ArrayList<CommonOptionsVO>();
			optionVO = new CommonOptionsVO("01", "JAN");
			monthList.add(optionVO);
			optionVO = new CommonOptionsVO("02", "FEB");
			monthList.add(optionVO);
			optionVO = new CommonOptionsVO("03", "MAR");
			monthList.add(optionVO);
			optionVO = new CommonOptionsVO("04", "APR");
			monthList.add(optionVO);
			optionVO = new CommonOptionsVO("05", "MAY");
			monthList.add(optionVO);
			optionVO = new CommonOptionsVO("06", "JUN");
			monthList.add(optionVO);
			optionVO = new CommonOptionsVO("07", "JUL");
			monthList.add(optionVO);
			optionVO = new CommonOptionsVO("08", "AUG");
			monthList.add(optionVO);
			optionVO = new CommonOptionsVO("09", "SEP");
			monthList.add(optionVO);
			optionVO = new CommonOptionsVO("10", "OCT");
			monthList.add(optionVO);
			optionVO = new CommonOptionsVO("11", "NOV");
			monthList.add(optionVO);
			optionVO = new CommonOptionsVO("12", "DEC");
			monthList.add(optionVO);

			optionVO = new CommonOptionsVO("Yes", "Yes");
			statementIssuedList.add(optionVO);
			optionVO = new CommonOptionsVO("No", "No");
			statementIssuedList.add(optionVO);

			optionVO = new CommonOptionsVO("Approved", "Approved");
			promoterResponseList.add(optionVO);
			optionVO = new CommonOptionsVO("Deemed", "Deemed");
			promoterResponseList.add(optionVO);
			optionVO = new CommonOptionsVO("Challenged", "Challenged");
			promoterResponseList.add(optionVO);
			optionVO = new CommonOptionsVO("Not Applicable", "Not Applicable");
			promoterResponseList.add(optionVO);

			
			
			loadPromoterName();

		} catch (Exception e) {
			returnMessage = 0;
			log.fatal(helper.writeLogInUpperCase(e));

		}

		if ((empID == null) || (empID.length() == 0)) {
			throw new Exception(getText("Invalid Session. Please Login"));
		}

		if (returnMessage == 1)
			returnValue = SUCCESS;

		else if (returnMessage == 0) {
			this.addActionError(getText("global.system.error"));
			return "home";
		}
		return returnValue;
	}

	public String searchInvoiceDetail() throws Exception {

		boolean operationFlag = false;

		try {

			InvoiceDetail invoiceDetailVO = manager.getInvoiceDetailToUpdate(Integer.parseInt(office_location),
					Integer.parseInt(String.valueOf(yearCode) + String.valueOf(monthCode)), promoterName);

			this.setInvoice_detail_id(String.valueOf(invoiceDetailVO.getInvoice_detail_id()));
			this.setPermit_fee_amount(String.valueOf(invoiceDetailVO.getPermit_fee_amount()));
			this.setDiscount_applied(String.valueOf(invoiceDetailVO.getDiscount_applied()));
			this.setInvoiceable_amount(String.valueOf(invoiceDetailVO.getInvoiceable_amount()));
			this.setStatement_issued(invoiceDetailVO.getStatement_issued());
			this.setStatement_issued_date(invoiceDetailVO.getStatement_issued_date() == null ? ""
					: sdf.format(invoiceDetailVO.getStatement_issued_date()));
			this.setPromoter_response(invoiceDetailVO.getPromoter_response());
			this.setChallenged_amount(String.valueOf(invoiceDetailVO.getChallenged_amount()));
			this.setInvoiced_amount(String.valueOf(invoiceDetailVO.getInvoiced_amount()));
			this.setInvoiced_date(
					invoiceDetailVO.getInvoiced_date() == null ? "" : sdf.format(invoiceDetailVO.getInvoiced_date()));
			this.setOrder_number(invoiceDetailVO.getOrder_number());
			this.setInvoice_no(invoiceDetailVO.getInvoice_no());
			this.setPaid_amount(invoiceDetailVO.getPaid_amount()==null?"0":String.valueOf(invoiceDetailVO.getPaid_amount()));
			this.setPaid_date(invoiceDetailVO.getPaid_date() == null ? "" : sdf.format(invoiceDetailVO.getPaid_date()));
			this.setComments(invoiceDetailVO.getComments());

			if (invoiceDetailVO.getStatus() == null) {
				this.setStatus("false");
				this.setStatus_final("true");
			} else if (invoiceDetailVO.getStatus() != null && invoiceDetailVO.getStatus().length() > 0) {
				this.setStatus("true");
				if (invoiceDetailVO.getStatus_final() == null)
					this.setStatus_final("false");
				else if (invoiceDetailVO.getStatus_final() != null && invoiceDetailVO.getStatus_final().length() > 0)
					this.setStatus_final("true");
			}

			operationFlag = true;

		} catch (Exception e) {
			e.printStackTrace();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

		if (operationFlag) {
			return SUCCESS;
		} else {
			this.addActionError("System Error Occured");
			return SUCCESS;
		}

	}
	
	public String invoiceDetailFirstUpdate() throws Exception {

		boolean operationFlag = false;

		try {


			log.fatal("NB AT FIRST UPDATE "+this.status+" "+this.status_final+" "+this.invoice_detail_id);
			
			InvoiceDetail invoiceDetailVO= new InvoiceDetail();
			
			invoiceDetailVO.setInvoice_detail_id(Integer.parseInt(this.invoice_detail_id));
			invoiceDetailVO.setStatement_issued(this.statement_issued);
			invoiceDetailVO.setStatement_issued_date(sdf.parse(this.statement_issued_date));
			invoiceDetailVO.setPromoter_response(this.promoter_response);
			invoiceDetailVO.setChallenged_amount(new BigDecimal(this.challenged_amount));
			invoiceDetailVO.setInvoiced_amount(new BigDecimal(this.invoiced_amount));
			invoiceDetailVO.setInvoiced_date(sdf.parse(this.invoiced_date));
			invoiceDetailVO.setOrder_number(this.order_number);
			invoiceDetailVO.setInvoice_no(this.invoice_no);
			invoiceDetailVO.setStatus("Yes");
			
			operationFlag = manager.updateInvoiceDetail(invoiceDetailVO,"First");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

		if (operationFlag) {
			emptyField();
			this.addActionMessage("Data Updated Successfully");

			return SUCCESS;
		} else {
			this.addActionError("System Error Occured");
			return SUCCESS;
		}

	}
	
	
	
	public String invoiceDetailSecondUpdate() throws Exception {

		boolean operationFlag = false;

		try {


			log.fatal("NB AT SECOND UPDATE "+this.status+" "+this.status_final+" "+this.invoice_detail_id);
			
			InvoiceDetail invoiceDetailVO= new InvoiceDetail();
			
			invoiceDetailVO.setInvoice_detail_id(Integer.parseInt(this.invoice_detail_id));
			invoiceDetailVO.setPaid_amount(new BigDecimal(this.paid_amount));
			invoiceDetailVO.setPaid_date(sdf.parse(this.paid_date));
			invoiceDetailVO.setComments(this.comments);
			invoiceDetailVO.setStatus_final("Yes");
			
			operationFlag = manager.updateInvoiceDetail(invoiceDetailVO,"Second");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

		if (operationFlag) {
			emptyField();
			this.addActionMessage("Data Updated Successfully");

			return SUCCESS;
		} else {
			this.addActionError("System Error Occured");
			return SUCCESS;
		}

	}


	@SkipValidation
	public String exitUpdateInvoice() throws Exception {
		return "home";
	}

	public void validate() {
		try {

			log.fatal("NB AT VALIDATION "+this.status+" "+this.status_final+" "+this.invoice_detail_id);
			
			this.execute();
			if ((empID == null) || (empID.trim().length() == 0)) {
				throw new Exception(getText("Invalid Session. Please Login"));
			} else if (!validator.requiredFiledValidate(office_location.trim())) {
				this.addFieldError("officeCode", "Pl. Select Highways Authority");
			} else if (!validator.requiredFiledValidate(yearCode.trim())) {
				this.addFieldError("yearCode", "Pl. Select Year");
			} else if (!validator.requiredFiledValidate(monthCode.trim())) {
				this.addFieldError("monthCode", "Pl. Select Month");
			} else if (!validator.requiredFiledValidate(promoterName.trim())) {
				this.addFieldError("promoterName", "Pl. Select Promoter");
			}else if (this.status.equals("false")&&(!validator.requiredFiledValidate(permit_fee_amount.trim()))) {
				this.addFieldError("permit_fee_amount", "Permit Fee can't empty.");
			}else if (this.status.equals("false")&&(!validator.requiredFiledValidate(discount_applied.trim()))) {
				this.addFieldError("discount_applied", "Discount can't empty.");
			}else if (this.status.equals("false")&&(!validator.requiredFiledValidate(invoiceable_amount.trim()))) {
				this.addFieldError("invoiceable_amount", "Invoiceable Amount can't empty.");
			}else if (this.status.equals("false")&&(!validator.requiredFiledValidate(statement_issued.trim()))) {
				this.addFieldError("statement_issued", "Please Select Statement Issue Status.");
			}else if (this.status.equals("false")&&(!validator.requiredFiledValidate(statement_issued_date.trim()))) {
				this.addFieldError("statement_issued_date", "Statement Issue Date Can't be Empty");
			}else if (this.status.equals("false")&&(!validator.requiredFiledValidate(promoter_response.trim()))) {
				this.addFieldError("promoter_response", "Pl. Select Promoter Response");
			}else if (this.status.equals("false")&&(!validator.requiredFiledValidate(challenged_amount.trim()))) {
				this.addFieldError("challenged_amount", "Challenged Amount can't be Empty");
			}else if (this.status.equals("false")&&(!validator.requiredFiledValidate(invoiced_amount.trim()))) {
				this.addFieldError("invoiced_amount", "Invoiced Amount can't be Empty");
			}else if (this.status.equals("false")&&(!validator.requiredFiledValidate(invoiced_date.trim()))) {
				this.addFieldError("invoiced_date", "Invoiced Date can't be Empty");
			}else if (this.status.equals("false")&&(!validator.requiredFiledValidate(order_number.trim()))) {
				this.addFieldError("order_number", "Order Number can't be Empty");
			}else if (this.status.equals("false")&&(!validator.requiredFiledValidate(invoice_no.trim()))) {
				this.addFieldError("invoice_no", "Invoice Number can't be Empty");
			}
			
			else if (this.status_final.equals("false")&&(!validator.requiredFiledValidate(paid_amount.trim()))) {
				this.addFieldError("paid_amount", "Paid Amount can't be Empty");
			}else if (this.status_final.equals("false")&&(!validator.requiredFiledValidate(paid_date.trim()))) {
				this.addFieldError("order_number", "Paid Date can't be Empty");
			}else if (this.status_final.equals("false")&&(!validator.requiredFiledValidate(comments.trim()))) {
				this.addFieldError("comments", "Comments can't be Empty");
			}

		} catch (Exception e) {
			this.addActionError(getText("global.system.error"));
			log.fatal(helper.writeLogInUpperCase(e));
		}
	}

	@SkipValidation
	public void emptyField() throws Exception {

		try {
			
			this.setStatus("");
			this.setStatus_final("");
			this.setMonthCode("");
			this.setPromoterName("");

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

	}

	@SkipValidation
	public String loadPromoterName() throws Exception {

		try {
			promoterList = new ArrayList<CommonOptionsVO>();

			if ((this.monthCode != null) && (this.monthCode.length() > 0) && (this.yearCode != null)
					&& (this.yearCode.length() > 0) && (this.office_location != null)
					&& (this.office_location.trim().length() > 0)) {

				promoterList = manager.searchPromoterForInvoiceUpdateList(Integer.parseInt(office_location),
						Integer.parseInt(String.valueOf(yearCode) + String.valueOf(monthCode)));
			}
		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

		return SUCCESS;

	}
}