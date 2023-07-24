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
import com.saanchi.hibernate.model.FeeExportProvisional;
import com.saanchi.hibernate.model.FileUploadDetail;
import com.saanchi.hibernate.model.PAExportProvisional;
import com.saanchi.hibernate.util.HibernateManager;

public class FileValidateSearch extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID = "";

	private ServletContext context;
	private HttpServletRequest request;

	
	
	private String officeCode;
	private String fileType;
	
	List<CommonOptionsVO> officeList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> fileTypeList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsNineVO> fileList = new ArrayList<CommonOptionsNineVO>();
	
	private static final Logger log = Logger.getLogger(FileValidateSearch.class);
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

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public List<CommonOptionsVO> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<CommonOptionsVO> officeList) {
		this.officeList = officeList;
	}

	public List<CommonOptionsVO> getFileTypeList() {
		return fileTypeList;
	}

	public void setFileTypeList(List<CommonOptionsVO> fileTypeList) {
		this.fileTypeList = fileTypeList;
	}

	public List<CommonOptionsNineVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<CommonOptionsNineVO> fileList) {
		this.fileList = fileList;
	}

	@SkipValidation
	public String execute() throws Exception {

		empID = (String) request.getSession().getAttribute("_EMPCODE");

		int returnMessage = 1;
		String returnValue = SUCCESS;

		try {
			fileTypeList = new ArrayList<CommonOptionsVO>();

			
			CommonOptionsVO optionVO = new CommonOptionsVO("FPN_Export", "FPN Export File");
			fileTypeList.add(optionVO);
			 optionVO = new CommonOptionsVO("PA_Export", "PA Export File");
			fileTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("Change_Request", "Change Request File");
			fileTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("Fee_Export", "Fee Export File");
			fileTypeList.add(optionVO);
			

			officeList = new ArrayList<CommonOptionsVO>();
			officeList = manager.searchOfficeList();
			fileList = new ArrayList<CommonOptionsNineVO>();

			
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

    public String searchFileForValidation() throws Exception {
		
		String returnMessage = "";
		boolean operationFlag = false;
		
		try {

			       fileList = new ArrayList<CommonOptionsNineVO>();
			       fileList = manager.SearchFileListForValidation(Integer.parseInt(officeCode), 
			    		   fileType);
				
				
				if (fileList.size() > 0) {
					
					operationFlag = true;
				}

				else {
					operationFlag = false;
					returnMessage = "No Data Found.";
				}
			

		} catch (Exception e) {
			this.addActionError(getText("global.system.error"));
			log.fatal(helper.writeLogInUpperCase(e));
		}

		if (operationFlag) {
			return "success";
		} else {
			this.addActionError(returnMessage);
			return "home";
		}

	
		
	}
	

	@SkipValidation
	public String exitFileValidateSearch() throws Exception {
		return "home";
	}

	public void validate() {
		try {

			this.execute();
			if ((empID == null) || (empID.trim().length() == 0)) {
				throw new Exception(getText("Invalid Session. Please Login"));
			}

			else if (!validator.requiredFiledValidate(fileType.trim())) {
				this.addFieldError("fileType", "Pl. Select File Type");
			} else if (!validator.requiredFiledValidate(officeCode.trim())) {
				this.addFieldError("officeCode", "Pl. Select Highways Authority");
			}

		} catch (Exception e) {
			this.addActionError(getText("global.system.error"));
			log.fatal(helper.writeLogInUpperCase(e));
		}
	}

}