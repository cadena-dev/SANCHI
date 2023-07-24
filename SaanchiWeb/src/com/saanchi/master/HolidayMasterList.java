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
import com.saanchi.commomUtility.CommonOptionsNineVO;
import com.saanchi.commomUtility.CommonOptionsVO;
import com.saanchi.commomUtility.CommonValidation;
import com.saanchi.hibernate.model.ChangeRequestProvisional;
import com.saanchi.hibernate.model.ChangeRequestStage;
import com.saanchi.hibernate.model.FPNExportProvisional;
import com.saanchi.hibernate.model.FPNExportStage;
import com.saanchi.hibernate.model.FeeExportProvisional;
import com.saanchi.hibernate.model.FileUploadDetail;
import com.saanchi.hibernate.model.HolidayMasterModel;
import com.saanchi.hibernate.model.OfficeCreateModel;
import com.saanchi.hibernate.model.PAExportProvisional;
import com.saanchi.hibernate.model.PAExportStage;
import com.saanchi.hibernate.model.PromoterMasterModel;
import com.saanchi.hibernate.model.RegulationMasterModel;
import com.saanchi.hibernate.model.UserCreateModel;
import com.saanchi.hibernate.util.HibernateManager;
import com.saanchi.optionVO.ChangeRequestVO;
import com.saanchi.optionVO.FPNValidationVO;
import com.saanchi.optionVO.PAValidateVO;

public class HolidayMasterList extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID;

	private ServletContext context;
	private HttpServletRequest request;
	private String holidaye_master_id;
	private String message;

	
	List<HolidayMasterModel> holiadyMasterList = new ArrayList<HolidayMasterModel>();
	
	private static final Logger log = Logger.getLogger(HolidayMasterList.class);
	HibernateManager manager = new HibernateManager();
	CommonHelper helper = new CommonHelper();
	private static CommonValidation validator = new CommonValidation();

	public void setServletContext(ServletContext arg0) {
		this.context = arg0;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	



	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public List<HolidayMasterModel> getHoliadyMasterList() {
		return holiadyMasterList;
	}

	public void setHoliadyMasterList(List<HolidayMasterModel> holiadyMasterList) {
		this.holiadyMasterList = holiadyMasterList;
	}
	
	
	

	public String getHolidaye_master_id() {
		return holidaye_master_id;
	}

	public void setHolidaye_master_id(String holidaye_master_id) {
		this.holidaye_master_id = holidaye_master_id;
	}

	public HibernateManager getManager() {
		return manager;
	}

	public void setManager(HibernateManager manager) {
		this.manager = manager;
	}
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@SkipValidation
	public String execute() throws Exception {

		empID = (String) request.getSession().getAttribute("_EMPCODE");

		int returnMessage = 1;
		String returnValue = SUCCESS;
		String returnText = "System Error Occured.";

		try {
			

			holiadyMasterList = new ArrayList<HolidayMasterModel>();
			

			holiadyMasterList = manager.searchHolidayMaster(holiadyMasterList);
			
			log.fatal("Holiday List Size "+holiadyMasterList.size());

			if (holiadyMasterList.size() > 0) {

				returnMessage = 1;
				
			}

			else {
				returnMessage = 0;
				returnText = "No Data Found.";
			}

		} catch (Exception e) {
			returnMessage = 0;
			log.fatal(helper.writeLogInUpperCase(e));

		}

		if ((empID == null) || (empID.length() == 0)) {
			throw new Exception(getText("Invalid Session. Please Login"));
		}

		if (returnMessage == 1) {
			returnValue = SUCCESS;
			
		}

		else if (returnMessage == 0) {
			this.addActionError(returnText);
			return SUCCESS;
		}
		return returnValue;
	}
	
	
	@SkipValidation
	public String deleteHoliday() throws Exception {

		try {
			
			if ((this.holidaye_master_id != null) && (this.holidaye_master_id.length() > 0)) {

				
				Date currentDate= new Date();
				HolidayMasterModel holidayModel = new HolidayMasterModel();
			
				
				holidayModel.setHolidaye_master_id(Integer.parseInt(holidaye_master_id));
				holidayModel.setUpdated_by(Integer.parseInt(empID));
				holidayModel.setUpdated_datetime(currentDate);
				
					
				boolean operationFlag = manager.updateHolidayMaster(holidayModel,"DELETE");
				
				if(operationFlag)
				this.message="Holiday deleted successfully.";
				else 
					this.message="System Error Occured";
			}
		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

		return SUCCESS;

	}

	@SkipValidation
	public String exitHolidayMasterList() throws Exception {
		return "home";
	}


}