package com.saanchi.master;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;
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
import com.saanchi.hibernate.model.OfficeCreateModel;
import com.saanchi.hibernate.model.PAExportProvisional;
import com.saanchi.hibernate.model.PAExportStage;
import com.saanchi.hibernate.model.PromoterMasterModel;
import com.saanchi.hibernate.model.UserCreateModel;
import com.saanchi.hibernate.util.HibernateManager;

public class PromoterMasterEntry extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID = "";

	private ServletContext context;
	private HttpServletRequest request;

	private String promoter_name;
	private String promoter_short_name;
	private String org_reference_number;
	private String prefix;
	private String promoter_mail;
	private String promoter_contact_no;
	private String promoter_mobile_no;

	private static final Logger log = Logger.getLogger(PromoterMasterEntry.class);
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

	public String getPromoter_name() {
		return promoter_name;
	}

	public void setPromoter_name(String promoter_name) {
		this.promoter_name = promoter_name;
	}

	public String getPromoter_short_name() {
		return promoter_short_name;
	}

	public void setPromoter_short_name(String promoter_short_name) {
		this.promoter_short_name = promoter_short_name;
	}

	public String getOrg_reference_number() {
		return org_reference_number;
	}

	public void setOrg_reference_number(String org_reference_number) {
		this.org_reference_number = org_reference_number;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getPromoter_mail() {
		return promoter_mail;
	}

	public void setPromoter_mail(String promoter_mail) {
		this.promoter_mail = promoter_mail;
	}

	public String getPromoter_contact_no() {
		return promoter_contact_no;
	}

	public void setPromoter_contact_no(String promoter_contact_no) {
		this.promoter_contact_no = promoter_contact_no;
	}

	public String getPromoter_mobile_no() {
		return promoter_mobile_no;
	}

	public void setPromoter_mobile_no(String promoter_mobile_no) {
		this.promoter_mobile_no = promoter_mobile_no;
	}

	@SkipValidation
	public String execute() throws Exception {

		empID = (String) request.getSession().getAttribute("_EMPCODE");

		int returnMessage = 1;
		String returnValue = SUCCESS;

		try {

			log.fatal("At Promoter master Entry");

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

	public String addPromoterMaster() throws Exception {

		boolean operationFlag = false;

		try {

			Date currentDate = new Date();

			PromoterMasterModel promoterModel = new PromoterMasterModel();

			promoterModel.setCreated_by(Integer.parseInt(empID));
			promoterModel.setCreated_datetime(currentDate);
			promoterModel.setIsactive(true);
			promoterModel.setPromoter_name(promoter_name);
			promoterModel.setPromoter_short_name(promoter_short_name);
			promoterModel.setOrg_reference_number(org_reference_number);
			promoterModel.setPrefix(prefix);
			promoterModel.setPromoter_mail(promoter_mail);
			promoterModel.setPromoter_contact_no(promoter_contact_no);
			promoterModel.setPromoter_mobile_no(promoter_mobile_no);
			promoterModel.setUpdated_by(Integer.parseInt(empID));
			promoterModel.setUpdated_datetime(currentDate);

			operationFlag = manager.insertPromoterMaster(promoterModel);

		} catch (Exception e) {
			e.printStackTrace();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

		if (operationFlag) {
			emptyField();
			this.addActionMessage("Data Uploaded Successfully");

			return SUCCESS;
		} else {
			this.addActionError("System Error Occured");
			return SUCCESS;
		}

	}

	@SkipValidation
	public String exitPromoterMaster() throws Exception {
		return "home";
	}

	public void validate() {
		try {

			this.execute();
			if ((empID == null) || (empID.trim().length() == 0)) {
				throw new Exception(getText("Invalid Session. Please Login"));
			}

			else if (!validator.requiredFiledValidate(promoter_name.trim())) {
				this.addFieldError("promoter_name", "Promoter Name is Required.");
			} else if (!validator.requiredFiledValidate(promoter_short_name.trim())) {
				this.addFieldError("promoter_short_name", "promoter Short Name is Required.");
			} else if (!validator.requiredFiledValidate(promoter_mail.trim())) {
				this.addFieldError("promoter_mail", "Email is Required.");

			} else if (!validator.requiredFiledValidate(org_reference_number.trim())) {
				this.addFieldError("org_reference_number", "Org Reference Number is Required.");
			} else if (!validator.requiredFiledValidate(promoter_contact_no.trim())) {
				this.addFieldError("promoter_contact_no", "Phone Number is Required.");
			} else if (!validator.requiredFiledValidate(prefix.trim())) {
				this.addFieldError("prefix", "Prefix is Required.");
			} else if (!validator.requiredFiledValidate(promoter_mobile_no.trim())) {
				this.addFieldError("promoter_mobile_no", "Mobile Number is Required.");
			} else if (!validator.validateNameField(promoter_name.trim(), "1")) {
				this.addFieldError("promoter_name", "Name is not valid.");
			} else if (!validator.validateNameField(promoter_short_name.trim(), "1")) {
				this.addFieldError("promoter_short_name", "Short Name is not valid.");
			}  else if (!validator.neumericFiledLengthValidate(promoter_mobile_no.trim(), 10)) {
				this.addFieldError("promoter_mobile_no", "Mobile Number is not valid.");
			} else if (!validator.mailFiledValidate(promoter_mail.trim())) {
				this.addFieldError("promoter_mail", "Email is not valid.");
			}

		} catch (Exception e) {
			this.addActionError(getText("global.system.error"));
			log.fatal(helper.writeLogInUpperCase(e));
		}
	}

	@SkipValidation
	public void emptyField() throws Exception {

		try {

			this.setPromoter_name("");
			this.setPromoter_short_name("");
			this.setOrg_reference_number("");
			this.setPrefix("");
			this.setPromoter_mail("");
			this.setPromoter_contact_no("");
			this.setPromoter_mobile_no("");

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

	}

}