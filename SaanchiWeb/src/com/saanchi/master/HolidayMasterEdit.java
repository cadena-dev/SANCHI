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
import com.saanchi.hibernate.model.HolidayMasterModel;
import com.saanchi.hibernate.model.OfficeCreateModel;
import com.saanchi.hibernate.model.PAExportProvisional;
import com.saanchi.hibernate.model.PAExportStage;
import com.saanchi.hibernate.model.PromoterMasterModel;
import com.saanchi.hibernate.model.RegulationMasterModel;
import com.saanchi.hibernate.model.UserCreateModel;
import com.saanchi.hibernate.util.HibernateManager;

public class HolidayMasterEdit extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID = "";

	private ServletContext context;
	private HttpServletRequest request;

	private String holidaye_master_id;
	
	private String holidate_date;
	private String description;
	private String isactive;
	private String holiday;
	
	
	private static final Logger log = Logger.getLogger(HolidayMasterEdit.class);
	HibernateManager manager = new HibernateManager();
	CommonHelper helper = new CommonHelper();
	private static CommonValidation validator = new CommonValidation();
	List<CommonOptionsVO> activityList = new ArrayList<CommonOptionsVO>();
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yyyy");
	
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

	
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getHolidaye_master_id() {
		return holidaye_master_id;
	}

	public void setHolidaye_master_id(String holidaye_master_id) {
		this.holidaye_master_id = holidaye_master_id;
	}

	
	public String getHolidate_date() {
		return holidate_date;
	}

	public void setHolidate_date(String holidate_date) {
		this.holidate_date = holidate_date;
	}
	
	

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	@SkipValidation
	public String execute() throws Exception {

		empID = (String) request.getSession().getAttribute("_EMPCODE");

		int returnMessage = 1;
		String returnValue = SUCCESS;

		try {
			System.out.println("Holiday "+holidate_date);
			
			if(holidate_date.contains("/")) {
			holidate_date=sdf3.format(sdf2.parse(holidate_date));
			}
			System.out.println("Holiday Date "+holidate_date);
			
			
			
		System.out.println("holiday "+holiday);
		
			
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

	public String updateHolidayMaster() throws Exception {

		boolean operationFlag = false;

		try {
			
			System.out.println(" Holiday Date is "+holidate_date +" and ID= "+holidaye_master_id);

			Date currentDate = new Date();

			HolidayMasterModel holidayModel = new HolidayMasterModel();
			
			if(!manager.checkExistingHolidayDateForUpdate(sdf3.parse(holidate_date),Integer.parseInt(holidaye_master_id))){
				
				this.addActionError("Holiday Date "+holidate_date+" already exists.");
				return SUCCESS;
			}

			else {
				
				System.out.println(" holidate_date at save "+holidate_date);
			holidayModel.setHolidaye_master_id(Integer.parseInt(holidaye_master_id));
			holidayModel.setIsactive(isactive.equalsIgnoreCase("true") ?true:false );
			holidayModel.setHolidate_date(sdf1.parse(holidate_date));
			holidayModel.setDescription(description);
			holidayModel.setUpdated_by(Integer.parseInt(empID));
			holidayModel.setUpdated_datetime(currentDate);

			operationFlag = manager.updateHolidayMaster(holidayModel,"UPDATE");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

		if (operationFlag) {
			emptyField();
			this.addActionMessage("Holiday updated successfully");

			return SUCCESS;
		} else {
			this.addActionError("System Error Occured");
			return SUCCESS;
		}

	}
	
public String deleteHolidayMaster() throws Exception {

		
		boolean operationFlag = false;
		
		try {

			Date currentDate= new Date();
			HolidayMasterModel holidayModel = new HolidayMasterModel();
		
			
			
			
			holidayModel.setHolidaye_master_id(Integer.parseInt(holidaye_master_id));
			holidayModel.setUpdated_by(Integer.parseInt(empID));
			holidayModel.setUpdated_datetime(currentDate);
			operationFlag = manager.updateHolidayMaster(holidayModel,"DELETE");
				
				

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
	public String exitHolidayEdit() throws Exception {
		return "home";
	}

	public void validate() {
		try {

			this.execute();
			
			System.out.println("holidate_date "+holidate_date);
			if ((empID == null) || (empID.trim().length() == 0)) {
				throw new Exception(getText("Invalid Session. Please Login"));
			}

			 else if (!validator.requiredFiledValidate(holidate_date.trim())) {
				this.addFieldError("holidate_date", "Holiday Date is Required.");
			} else if (!validator.requiredFiledValidate(description.trim())) {
				this.addFieldError("description", "Description is Required.");
			
			} 
				  else if (!validator.requiredFiledValidate(isactive.trim())) {
				  this.addFieldError("isactive", "Activity Status is Required."); }
				  else if (validator.checkSpecialCharacter(description.trim())) {
						this.addFieldError("description", "Special Character Not Allowed.");
					}else if (!validator.dateFormate(holidate_date.trim())) {
						this.addFieldError("holidate_date", "Holiday Date should be in dd-MM-yyyy Format.");
					} 
				 

		} catch (Exception e) {
			this.addActionError(getText("global.system.error"));
			log.fatal(helper.writeLogInUpperCase(e));
		}
	}

	@SkipValidation
	public void emptyField() throws Exception {

		try {

			
			this.setHolidate_date("");
			this.setDescription("");
			this.setIsactive("");
			this.setHoliday("");

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

	}
	
	
	@SkipValidation
	public String holidate_get() throws Exception {

		try {

					System.out.println(" In ajax "+holiday);
					if((holiday!=null)&&(holiday.length()>0)) {
					holidate_date=sdf1.format(sdf2.parse(holiday));
					System.out.println(" In ajax "+holidate_date);
					}
					

		} catch (Exception e) {
		e.printStackTrace();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

		return SUCCESS;

	}

}