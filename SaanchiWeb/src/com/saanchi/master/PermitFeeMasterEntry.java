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
import com.saanchi.hibernate.model.PermitFeeMasterModel;
import com.saanchi.hibernate.model.PromoterMasterModel;
import com.saanchi.hibernate.model.RegulationMasterModel;
import com.saanchi.hibernate.model.UserCreateModel;
import com.saanchi.hibernate.util.HibernateManager;

public class PermitFeeMasterEntry extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID = "";

	private ServletContext context;
	private HttpServletRequest request;

	private String highway_authority_name;
	private String application_type;
	private String work_category;
	private String road_category;
	private String traffic_sensitivity;
	private String fee_amount;

	private static final Logger log = Logger.getLogger(PermitFeeMasterEntry.class);
	HibernateManager manager = new HibernateManager();
	CommonHelper helper = new CommonHelper();
	private static CommonValidation validator = new CommonValidation();
	List<CommonOptionsVO> traffic_sensitivityList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> applicationTypeList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> workCategoryList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> roadTypeList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> officeList = new ArrayList<CommonOptionsVO>();

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

	public String getHighway_authority_name() {
		return highway_authority_name;
	}

	public void setHighway_authority_name(String highway_authority_name) {
		this.highway_authority_name = highway_authority_name;
	}

	public String getWork_category() {
		return work_category;
	}

	public void setWork_category(String work_category) {
		this.work_category = work_category;
	}

	public String getRoad_category() {
		return road_category;
	}

	public void setRoad_category(String road_category) {
		this.road_category = road_category;
	}

	public String getTraffic_sensitivity() {
		return traffic_sensitivity;
	}

	public void setTraffic_sensitivity(String traffic_sensitivity) {
		this.traffic_sensitivity = traffic_sensitivity;
	}

	public String getApplication_type() {
		return application_type;
	}

	public void setApplication_type(String application_type) {
		this.application_type = application_type;
	}

	public String getFee_amount() {
		return fee_amount;
	}

	public void setFee_amount(String fee_amount) {
		this.fee_amount = fee_amount;
	}

	public List<CommonOptionsVO> getTraffic_sensitivityList() {
		return traffic_sensitivityList;
	}

	public void setTraffic_sensitivityList(List<CommonOptionsVO> traffic_sensitivityList) {
		this.traffic_sensitivityList = traffic_sensitivityList;
	}

	public List<CommonOptionsVO> getApplicationTypeList() {
		return applicationTypeList;
	}

	public void setApplicationTypeList(List<CommonOptionsVO> applicationTypeList) {
		this.applicationTypeList = applicationTypeList;
	}

	public List<CommonOptionsVO> getWorkCategoryList() {
		return workCategoryList;
	}

	public void setWorkCategoryList(List<CommonOptionsVO> workCategoryList) {
		this.workCategoryList = workCategoryList;
	}

	public List<CommonOptionsVO> getRoadTypeList() {
		return roadTypeList;
	}

	public void setRoadTypeList(List<CommonOptionsVO> roadTypeList) {
		this.roadTypeList = roadTypeList;
	}

	public List<CommonOptionsVO> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<CommonOptionsVO> officeList) {
		this.officeList = officeList;
	}

	@SkipValidation
	public String execute() throws Exception {

		empID = (String) request.getSession().getAttribute("_EMPCODE");

		int returnMessage = 1;
		String returnValue = SUCCESS;

		try {

			log.fatal("At Permi Fee master Entry");

			traffic_sensitivityList = new ArrayList<CommonOptionsVO>();
			applicationTypeList = new ArrayList<CommonOptionsVO>();
			workCategoryList = new ArrayList<CommonOptionsVO>();
			roadTypeList = new ArrayList<CommonOptionsVO>();
			officeList = new ArrayList<CommonOptionsVO>();

			CommonOptionsVO optionVO = new CommonOptionsVO("Yes", "Yes");
			traffic_sensitivityList.add(optionVO);
			optionVO = new CommonOptionsVO("No", "No");
			traffic_sensitivityList.add(optionVO);

			optionVO = new CommonOptionsVO("PAA", "PAA");
			applicationTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("Permit", "Permit");
			applicationTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("Variation", "Variation");
			applicationTypeList.add(optionVO);
			
if ((application_type != null) && (application_type.length() > 0)) {

				
				workCategoryList = manager.searchWorkcategory(application_type);

			}

			/*
			 * optionVO = new CommonOptionsVO("Major more than 10 days",
			 * "Major more than 10 days"); workCategoryList.add(optionVO); optionVO = new
			 * CommonOptionsVO("Major 4-10 days", "Major 4-10 days");
			 * workCategoryList.add(optionVO); optionVO = new
			 * CommonOptionsVO("Major works with TTRO", "Major works with TTRO");
			 * workCategoryList.add(optionVO); optionVO = new
			 * CommonOptionsVO("Major works up to 3 days", "Major works up to 3 days");
			 * workCategoryList.add(optionVO); optionVO = new CommonOptionsVO("Standard",
			 * "Standard"); workCategoryList.add(optionVO); optionVO = new
			 * CommonOptionsVO("Minor", "Minor"); workCategoryList.add(optionVO); optionVO =
			 * new CommonOptionsVO("Immediate", "Immediate");
			 * workCategoryList.add(optionVO); optionVO = new
			 * CommonOptionsVO("All works categories", "All works categories");
			 * workCategoryList.add(optionVO); optionVO = new
			 * CommonOptionsVO("All Major Works", "All Major Works");
			 * workCategoryList.add(optionVO);
			 */

			optionVO = new CommonOptionsVO("CAT 0-2", "CAT 0-2");
			roadTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("CAT 3-4", "CAT 3-4");
			roadTypeList.add(optionVO);
			officeList = manager.searchOfficeList();

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

	public String addFeePermitMaster() throws Exception {

		boolean operationFlag = false;

		try {

			Date currentDate = new Date();

			PermitFeeMasterModel permitFeeModel = new PermitFeeMasterModel();

			String highWay[] = highway_authority_name.split("~");

			if (!manager.checkExistingPermitFee(Integer.parseInt(highWay[0].trim()), application_type, work_category,
					road_category, traffic_sensitivity)) {

				this.addActionError("Permit Fee already exists.");
				return SUCCESS;
			}

			else {
				permitFeeModel.setCreated_by(Integer.parseInt(empID));
				permitFeeModel.setCreated_datetime(currentDate);
				permitFeeModel.setIsactive(true);
				permitFeeModel.setHighway_authority_id(Integer.parseInt(highWay[0].trim()));
				permitFeeModel.setHighway_authority_name(highWay[1].toUpperCase());
				permitFeeModel.setApplication_type(application_type);
				permitFeeModel.setWork_category(work_category);
				permitFeeModel.setRoad_category(road_category);
				permitFeeModel.setTraffic_sensitivity(traffic_sensitivity);
				permitFeeModel.setFee_amount(new BigDecimal(fee_amount));

				permitFeeModel.setUpdated_by(Integer.parseInt(empID));
				permitFeeModel.setUpdated_datetime(currentDate);

				operationFlag = manager.insertPermitFeeMaster(permitFeeModel);
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
	public String exitFeePermitMaster() throws Exception {
		return "home";
	}

	public void validate() {
		try {

			this.execute();
			if ((empID == null) || (empID.trim().length() == 0)) {
				throw new Exception(getText("Invalid Session. Please Login"));
			}

			else if (!validator.requiredFiledValidate(highway_authority_name.trim())) {
				this.addFieldError("highway_authority_name", "Authority Name is Required.");
			} else if (!validator.requiredFiledValidate(application_type.trim())) {
				this.addFieldError("application_type", "Application Type is Required.");
			} else if (!validator.requiredFiledValidate(work_category.trim())) {
				this.addFieldError("work_category", "Work Category is Required.");
			} else if (!validator.requiredFiledValidate(road_category.trim())) {
				this.addFieldError("road_category", "Road Category is Required.");
			} else if (!validator.requiredFiledValidate(traffic_sensitivity.trim())) {
				this.addFieldError("traffic_sensitivity", "Traffic Sensitivity is Required.");
			} else if (!validator.requiredFiledValidate(fee_amount.trim())) {
				this.addFieldError("fee_amount", "Fee is Required.");
			} else if (!validator.amountFiledValidate(fee_amount.trim())) {
				this.addFieldError("fee_amount", "Fee is not Valid.");
			}

		} catch (Exception e) {
			this.addActionError(getText("global.system.error"));
			log.fatal(helper.writeLogInUpperCase(e));
		}
	}

	@SkipValidation
	public void emptyField() throws Exception {

		try {

			this.setHighway_authority_name("");
			this.setApplication_type("");
			this.setWork_category("");
			this.setRoad_category("");
			this.setTraffic_sensitivity("");
			this.setFee_amount("");

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

	}

	@SkipValidation
	public String workcategory_get() throws Exception {

		try {

			System.out.println(" In ajax " + application_type);
			workCategoryList = new ArrayList<CommonOptionsVO>();
			if ((application_type != null) && (application_type.length() > 0)) {

				
				workCategoryList = manager.searchWorkcategory(application_type);

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

		return SUCCESS;

	}

}