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
import com.saanchi.hibernate.util.HibernateManager;

public class OfficeMasterEdit extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID = "";

	private ServletContext context;
	private HttpServletRequest request;

	private String office_master_id;
	private String office_desc;
	private String office_address;
	private String office_type;
	private String isactive;
	List<CommonOptionsVO> officeTypeList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> activityList = new ArrayList<CommonOptionsVO>();

	private static final Logger log = Logger.getLogger(OfficeMasterEdit.class);
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

	public String getOffice_desc() {
		return office_desc;
	}

	public void setOffice_desc(String office_desc) {
		this.office_desc = office_desc;
	}

	public String getOffice_address() {
		return office_address;
	}

	public void setOffice_address(String office_address) {
		this.office_address = office_address;
	}


	public String getOffice_type() {
		return office_type;
	}

	public void setOffice_type(String office_type) {
		this.office_type = office_type;
	}
	
	

	public List<CommonOptionsVO> getOfficeTypeList() {
		return officeTypeList;
	}

	public void setOfficeTypeList(List<CommonOptionsVO> officeTypeList) {
		this.officeTypeList = officeTypeList;
	}

	
	
	
	public String getOffice_master_id() {
		return office_master_id;
	}

	public void setOffice_master_id(String office_master_id) {
		this.office_master_id = office_master_id;
	}

	public String isIsactive() {
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
			
			log.fatal("At Office master Edit");
			
			System.out.println("office_master_id "+office_master_id);
			
			officeTypeList = new ArrayList<CommonOptionsVO>();
			activityList= new ArrayList<CommonOptionsVO>();
			
			CommonOptionsVO optionVO = new CommonOptionsVO("Highway Authority", "Highway Authority");
			officeTypeList.add(optionVO);
			 optionVO = new CommonOptionsVO("Promoter", "Promoter");
			 officeTypeList.add(optionVO);
			 
			 
			 optionVO = new CommonOptionsVO("true", "true");
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


	public String updateOfficeMaster() throws Exception {

		
		boolean operationFlag = false;
		
		try {

			Date currentDate= new Date();
			
			
if(!manager.checkExistingOfficeForUpdate(office_desc,Integer.parseInt(office_master_id))){
				
				this.addActionError("Office "+office_desc+" already exists.");
				return SUCCESS;
			}

			else {
			
			OfficeCreateModel officeModel= new OfficeCreateModel();
		
			
			System.out.println("office_master_id "+office_master_id);
			
			officeModel.setOffice_master_id(Integer.parseInt(office_master_id));
			officeModel.setIsactive( isactive.equalsIgnoreCase("true") ?true:false );
			officeModel.setOffice_address(office_address);
			officeModel.setOffice_desc(office_desc); 
			officeModel.setOffice_type(office_type);
			officeModel.setUpdated_by(Integer.parseInt(empID));
			officeModel.setUpdated_datetime(currentDate);
					

					
					operationFlag = manager.updateOfficeMaster(officeModel,"UPDATE");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		} 

		if (operationFlag) {
			emptyField();
				this.addActionMessage("Organization updated successfully");
			
			return SUCCESS;
		} else {
			 this.addActionError("System Error Occured");
			return SUCCESS;
		}

	}
	
	
public String deleteOfficeMaster() throws Exception {

		
		boolean operationFlag = false;
		
		try {

			Date currentDate= new Date();
			
			OfficeCreateModel officeModel= new OfficeCreateModel();
		
			
			System.out.println("office_master_id "+office_master_id);
			
			officeModel.setOffice_master_id(Integer.parseInt(office_master_id));
			officeModel.setUpdated_by(Integer.parseInt(empID));
			officeModel.setUpdated_datetime(currentDate);
					

					
					operationFlag = manager.updateOfficeMaster(officeModel,"DELETE");
				
				

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
	public String exitOfficeMasterEdit() throws Exception {
		return "home";
	}

	public void validate() {
		try {

			this.execute();
			if ((empID == null) || (empID.trim().length() == 0)) {
				throw new Exception(getText("Invalid Session. Please Login"));
			}

			else if (!validator.requiredFiledValidate(office_desc.trim())) {
				this.addFieldError("office_desc", "Highways Authority Name is Required.");
			} else if (!validator.requiredFiledValidate(office_address.trim())) {
				this.addFieldError("office_address", "Highways Authority Address is Required.");
			}else if (!validator.requiredFiledValidate(office_type.trim())) {
				this.addFieldError("office_type", "Highways Authority Type is Required.");
			} 
				  else if (!validator.requiredFiledValidate(isactive.trim())) {
				  this.addFieldError("isactive", "Active or not is Required."); }
				  else if (validator.checkSpecialCharacter(office_desc.trim())) {
						this.addFieldError("office_desc", "Special Character Not Allowed.");
					}else if (validator.checkSpecialCharacter(office_address.trim())) {
						this.addFieldError("office_address", "Special Character Not Allowed.");
					}
				 

			
			
			
		} catch (Exception e) {
			this.addActionError(getText("global.system.error"));
			log.fatal(helper.writeLogInUpperCase(e));
		}
	}
	
	
	@SkipValidation
	public void emptyField() throws Exception {

		try {

			
			this.setOffice_desc("");
			this.setOffice_address("");
			this.setOffice_type("");
			this.setIsactive("");
		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

	}

}