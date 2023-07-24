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
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

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
import com.saanchi.hibernate.model.UserCreateModel;
import com.saanchi.hibernate.util.HibernateManager;

public class UserMasterEntry extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID = "";

	private ServletContext context;
	private HttpServletRequest request;

	private String office_location;
	private String user_type;
	private String user_name;
	private String user_password;
	private String user_mail;
	private String user_contact_no;
	private String user_address;
	private String emp_name;

	List<CommonOptionsVO> userTypeList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> officeList = new ArrayList<CommonOptionsVO>();

	private static final Logger log = Logger.getLogger(UserMasterEntry.class);
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

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public List<CommonOptionsVO> getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(List<CommonOptionsVO> userTypeList) {
		this.userTypeList = userTypeList;
	}

	public List<CommonOptionsVO> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<CommonOptionsVO> officeList) {
		this.officeList = officeList;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	@SkipValidation
	public String execute() throws Exception {

		empID = (String) request.getSession().getAttribute("_EMPCODE");

		int returnMessage = 1;
		String returnValue = SUCCESS;

		try {

			log.fatal("At User master Entry");

			officeList = new ArrayList<CommonOptionsVO>();
			userTypeList = new ArrayList<CommonOptionsVO>();

		
	
			CommonOptionsVO  optionVO = new CommonOptionsVO("2", "Admin user");
			userTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("3", "Normal user");
			userTypeList.add(optionVO);

			officeList = manager.searchOfficeListForUser();

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

	public String addUserMaster() throws Exception {

		boolean operationFlag = false;

		try {

			Date currentDate = new Date();
			
			
			if(manager.checkExistingUser(user_name)) {
			
			UserCreateModel userModel = new UserCreateModel();

			userModel.setCreated_by(Integer.parseInt(empID));
			userModel.setCreated_datetime(currentDate);
			userModel.setIsactive(true);
			userModel.setUser_name(user_name);
			userModel.setEmp_name(emp_name);
			userModel.setUser_mail(user_mail);
			userModel.setUser_password(user_password);
			userModel.setUser_contact_no(user_contact_no);
			userModel.setUser_address(user_address);
			userModel.setOffice_location(Integer.parseInt(office_location));
			userModel.setUser_type(Integer.parseInt(user_type));
			userModel.setUpdated_by(Integer.parseInt(empID));
			userModel.setUpdated_datetime(currentDate);

			operationFlag = manager.insertUserMaster(userModel);
			}
			
			else {
				this.addActionError("User Name -"+user_name+"- already exists.");
				return SUCCESS;
			}

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
	public String exitUserMaster() throws Exception {
		return "home";
	}

	public void validate() {
		try {

			this.execute();
			if ((empID == null) || (empID.trim().length() == 0)) {
				throw new Exception(getText("Invalid Session. Please Login"));
			}

			
			else if (!validator.requiredFiledValidate(user_name.trim())) {
				this.addFieldError("user_name", "User Name is Required.");
			} else if (!validator.requiredFiledValidate(emp_name.trim())) {
				this.addFieldError("emp_name", "Name is Required.");
			} else if (!validator.requiredFiledValidate(user_mail.trim())) {
				this.addFieldError("user_mail", "Email is Required.");
				
			}else if (!validator.requiredFiledValidate(user_password.trim())) {
				this.addFieldError("user_password", "Password is Required.");
			}else if (user_password.trim().length()<6) {
				this.addFieldError("user_password", "Password Should be atleast 6 Character.");
			} 
			
			
			
			else if (!validator.requiredFiledValidate(user_contact_no.trim())) {
				this.addFieldError("user_contact_no", "Contact Number is Required.");
			} else if (!validator.requiredFiledValidate(user_mail.trim())) {
				this.addFieldError("user_mail", "Email is Required.");
			}else if (!validator.requiredFiledValidate(user_address.trim())) {
			this.addFieldError("user_address", "Address is Required.");
		} else if (!validator.requiredFiledValidate(office_location.trim())) {
			this.addFieldError("office_location", "Office is Required.");
		} else if (!validator.requiredFiledValidate(user_type.trim())) {
			this.addFieldError("user_type", "User Type is Required.");
		}else if (!validator.validateNameField(emp_name.trim(),"1")) {
			this.addFieldError("emp_name", "Name is not valid.");
		}else if (!validator.neumericFiledLengthValidate(user_contact_no.trim(),10)) {
			this.addFieldError("user_contact_no", "Contact Number is not valid.");
		}else if (!validator.mailFiledValidate(user_mail.trim())) {
			this.addFieldError("user_mail", "Email is not valid.");
		}
		else if ((user_type.equalsIgnoreCase("1") )&&(!office_location.equalsIgnoreCase("6"))) {
			this.addFieldError("user_type", "Supper User is allowed on for SAANCHI office.");
		}

		else if(user_name.trim().contains("#")) {
			this.addFieldError("user_name", "# is not allowed.");
		}
		else if(emp_name.trim().contains("#")) {
			this.addFieldError("emp_name", "# is not allowed.");
		}	
		else if(user_address.trim().contains("#")) {
			this.addFieldError("user_address", "# is not allowed.");
		}		
		else if(user_password.trim().contains("#")) {
			this.addFieldError("user_password", "# is not allowed.");
		}		
				
			
			
		} catch (Exception e) {
			this.addActionError(getText("global.system.error"));
			log.fatal(helper.writeLogInUpperCase(e));
		}
	}

	@SkipValidation
	public void emptyField() throws Exception {

		try {

			
			this.setUser_name("");
			this.setEmp_name("");
			this.setUser_mail("");
			this.setUser_password("");
			this.setUser_contact_no("");
			this.setUser_address("");
			this.setOffice_location("");
			this.setUser_type("");
			
		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

	}

}