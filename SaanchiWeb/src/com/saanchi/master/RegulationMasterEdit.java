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
import com.saanchi.hibernate.model.RegulationMasterModel;
import com.saanchi.hibernate.model.UserCreateModel;
import com.saanchi.hibernate.util.HibernateManager;

public class RegulationMasterEdit extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID = "";

	private ServletContext context;
	private HttpServletRequest request;

	private String regulation_master_id;
	private String regulation_id;
	private String regulation_code;
	private String description;
	private String isactive;
	
	private static final Logger log = Logger.getLogger(RegulationMasterEdit.class);
	HibernateManager manager = new HibernateManager();
	CommonHelper helper = new CommonHelper();
	private static CommonValidation validator = new CommonValidation();
	List<CommonOptionsVO> activityList = new ArrayList<CommonOptionsVO>();

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

	
	
	public String getRegulation_id() {
		return regulation_id;
	}

	public void setRegulation_id(String regulation_id) {
		this.regulation_id = regulation_id;
	}

	public String getRegulation_code() {
		return regulation_code;
	}

	public void setRegulation_code(String regulation_code) {
		this.regulation_code = regulation_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

	public String getRegulation_master_id() {
		return regulation_master_id;
	}

	public void setRegulation_master_id(String regulation_master_id) {
		this.regulation_master_id = regulation_master_id;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public List<CommonOptionsVO> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<CommonOptionsVO> activityList) {
		this.activityList = activityList;
	}

	@SkipValidation
	public String execute() throws Exception {

		empID = (String) request.getSession().getAttribute("_EMPCODE");

		int returnMessage = 1;
		String returnValue = SUCCESS;

		try {

			log.fatal("At Regulation master Edit");
			
activityList = new ArrayList<CommonOptionsVO>();
			
			CommonOptionsVO optionVO = new CommonOptionsVO("true", "true");
			 activityList.add(optionVO);
				 optionVO = new CommonOptionsVO("false", "false");
				 activityList.add(optionVO);


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

	public String updateRegulationrMaster() throws Exception {

		boolean operationFlag = false;

		try {

			Date currentDate = new Date();

			RegulationMasterModel regulationModel = new RegulationMasterModel();

			regulationModel.setRegulation_master_id(Integer.parseInt(regulation_master_id));
			regulationModel.setIsactive(isactive.equalsIgnoreCase("true")?true:false);
			regulationModel.setRegulation_id(regulation_id);
			regulationModel.setRegulation_code(regulation_code);
			regulationModel.setDescription(description);
			regulationModel.setUpdated_by(Integer.parseInt(empID));
			regulationModel.setUpdated_datetime(currentDate);

			operationFlag = manager.updateRegulationMaster(regulationModel,"UPDATE");

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
	
public String deleteRegulationMaster() throws Exception {

		
		boolean operationFlag = false;
		
		try {

			Date currentDate= new Date();
			RegulationMasterModel regulationModel = new RegulationMasterModel();
		
			
			
			
			regulationModel.setRegulation_master_id(Integer.parseInt(regulation_master_id));
			regulationModel.setUpdated_by(Integer.parseInt(empID));
			regulationModel.setUpdated_datetime(currentDate);
			operationFlag = manager.updateRegulationMaster(regulationModel,"DELETE");
				
				

		} catch (Exception e) {
			e.printStackTrace();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		} 

		if (operationFlag) {
			emptyField();
				this.addActionMessage("Data Deleted Successfully");
			
			return SUCCESS;
		} else {
			 this.addActionError("System Error Occured");
			return SUCCESS;
		}

	}


	@SkipValidation
	public String exitRegulationMasterEdit() throws Exception {
		return "home";
	}

	public void validate() {
		try {

			this.execute();
			if ((empID == null) || (empID.trim().length() == 0)) {
				throw new Exception(getText("Invalid Session. Please Login"));
			}

			else if (!validator.requiredFiledValidate(regulation_id.trim())) {
				this.addFieldError("regulation_id", "Regulation ID is Required.");
			} else if (!validator.requiredFiledValidate(regulation_code.trim())) {
				this.addFieldError("regulation_code", "Regulation Code is Required.");
			} else if (!validator.requiredFiledValidate(description.trim())) {
				this.addFieldError("description", "Description is Required.");
			} else if (!validator.requiredFiledValidate(isactive.trim())) {
				this.addFieldError("isactive", "Activity Status is Required.");
			} 

		} catch (Exception e) {
			this.addActionError(getText("global.system.error"));
			log.fatal(helper.writeLogInUpperCase(e));
		}
	}

	@SkipValidation
	public void emptyField() throws Exception {

		try {

			this.setRegulation_id("");
			this.setRegulation_code("");
			this.setDescription("");
			this.setIsactive("");

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

	}

}