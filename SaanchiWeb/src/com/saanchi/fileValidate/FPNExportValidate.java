package com.saanchi.fileValidate;

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
import com.saanchi.hibernate.model.FPNExportProvisional;
import com.saanchi.hibernate.model.FPNExportStage;
import com.saanchi.hibernate.model.FeeExportProvisional;
import com.saanchi.hibernate.model.FileUploadDetail;
import com.saanchi.hibernate.model.PAExportProvisional;
import com.saanchi.hibernate.util.HibernateManager;
import com.saanchi.optionVO.FPNValidationVO;

public class FPNExportValidate extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID ;

	

	private ServletContext context;
	private HttpServletRequest request;

	private String file_name;
	private String comment;
	
	
	List<FPNExportStage> fpnStageList = new ArrayList<FPNExportStage>();
	List<FPNValidationVO> fpnValidationLogicList = new ArrayList<FPNValidationVO>();

	private static final Logger log = Logger.getLogger(FPNExportValidate.class);
	HibernateManager manager = new HibernateManager();
	CommonHelper helper = new CommonHelper();
	private static CommonValidation validator = new CommonValidation();

	public void setServletContext(ServletContext arg0) {
		this.context = arg0;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public List<FPNExportStage> getFpnStageList() {
		return fpnStageList;
	}

	public void setFpnStageList(List<FPNExportStage> fpnStageList) {
		this.fpnStageList = fpnStageList;
	}

	public List<FPNValidationVO> getFpnValidationLogicList() {
		return fpnValidationLogicList;
	}

	public void setFpnValidationLogicList(List<FPNValidationVO> fpnValidationLogicList) {
		this.fpnValidationLogicList = fpnValidationLogicList;
	}
	
	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	@SkipValidation
	public String execute() throws Exception {

		empID = (String) request.getSession().getAttribute("_EMPCODE");

		int returnMessage = 1;
		String returnValue = SUCCESS;
		String returnText = "System Error Occured.";

		try {
			System.out.println("My File Name= " + file_name);

			fpnStageList = new ArrayList<FPNExportStage>();
			fpnValidationLogicList = new ArrayList<FPNValidationVO>();

			fpnStageList = manager.searchFPNFileDataFromStage(file_name, fpnStageList);

			if (fpnStageList.size() > 0) {

				returnMessage = 1;
				fpnValidationLogicList = helper.FPNExportValidation(fpnStageList);

				returnText = "Total Records in File (" + file_name + " ) =" + fpnStageList.size()
						+ ", System Validated Record/s = " + (fpnStageList.size() - fpnValidationLogicList.size())
						+ " Error Records =" + fpnValidationLogicList.size();
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
			this.addActionMessage(returnText);
		}

		else if (returnMessage == 0) {
			this.addActionError(returnText);
			return "home";
		}
		return returnValue;
	}

	@SkipValidation
	public String exitFPNExportValidate() throws Exception {
		return "home";
	}
	
	
	
	  public String saveDataToStage() throws Exception {
			
			String returnMessage = "System Error Occured.";
			boolean operationFlag = false;
			
			try {

				      
			System.out.println(" "+file_name+" "+fpnValidationLogicList.size());	
			
			List<FPNExportStage> alledited=helper.getAllDataForFPNStage(request, String.valueOf(fpnValidationLogicList.size()));
			
			operationFlag  = manager.UpdateFPNExportStage(alledited,file_name);
					
			} catch (Exception e) {
				this.addActionError(getText("global.system.error"));
				log.fatal(helper.writeLogInUpperCase(e));
			}

			if (operationFlag) {
				returnMessage="Data Updated Successfully in Temporary Table. Please Revalidate again.";
				this.clearMessages();
				this.addActionMessage(returnMessage);
				return "home";
			} else {
				this.addActionError(returnMessage);
				return "home";
			}
	  }
	  
	  
	  
	  public String confirmToValidation() throws Exception {
			
			String returnMessage = "System Error Occured.";
			boolean operationFlag = false;
			
			try {

				      
			System.out.println(" "+file_name+" "+fpnValidationLogicList.size());	
			
			
			
			operationFlag  = manager.saveToValidate(file_name,empID,comment);
					
			} catch (Exception e) {
				this.addActionError(getText("global.system.error"));
				log.fatal(helper.writeLogInUpperCase(e));
			}

			if (operationFlag) {
				returnMessage="Data Updated Successfully in Final Table.";
				this.clearMessages();
				this.addActionMessage(returnMessage);
				return "home";
			} else {
				this.addActionError(returnMessage);
				return "home";
			}
	  }

	public void validate() {
		try {

			this.execute();
			if ((empID == null) || (empID.trim().length() == 0)) {
				throw new Exception(getText("Invalid Session. Please Login"));
			}

		} catch (Exception e) {
			this.addActionError(getText("global.system.error"));
			log.fatal(helper.writeLogInUpperCase(e));
		}
	}

}