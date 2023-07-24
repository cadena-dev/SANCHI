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
import com.saanchi.hibernate.model.FPNExport_Infringements_Validated;
import com.saanchi.hibernate.model.FeeExportProvisional;
import com.saanchi.hibernate.model.FeeExportStage;
import com.saanchi.hibernate.model.FileUploadDetail;
import com.saanchi.hibernate.model.HolidayMasterModel;
import com.saanchi.hibernate.model.InvoiceDetail;
import com.saanchi.hibernate.model.OfficeCreateModel;
import com.saanchi.hibernate.model.PAExportProvisional;
import com.saanchi.hibernate.model.PAExportStage;
import com.saanchi.hibernate.model.PermitFeeMasterModel;
import com.saanchi.hibernate.model.UserCreateModel;
import com.saanchi.hibernate.util.HibernateManager;
import com.saanchi.optionVO.FPNExportInfringementsVO;

public class UpdateFPNDetail extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID = "";

	private ServletContext context;
	private HttpServletRequest request;

	private String office_location;
	private String yearCode;
	private String monthCode;
	private String fileType;
	private String rowNumber;

	List<CommonOptionsVO> officeList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> yearList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> monthList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> fileTypeList = new ArrayList<CommonOptionsVO>();
	List<FPNExportInfringementsVO> fpnInfringementsList = new ArrayList<FPNExportInfringementsVO>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static final Logger log = Logger.getLogger(UpdateFPNDetail.class);
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

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public List<CommonOptionsVO> getFileTypeList() {
		return fileTypeList;
	}

	public void setFileTypeList(List<CommonOptionsVO> fileTypeList) {
		this.fileTypeList = fileTypeList;
	}


	public List<FPNExportInfringementsVO> getFpnInfringementsList() {
		return fpnInfringementsList;
	}

	public void setFpnInfringementsList(List<FPNExportInfringementsVO> fpnInfringementsList) {
		this.fpnInfringementsList = fpnInfringementsList;
	}

	public String getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
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
			fileTypeList = new ArrayList<CommonOptionsVO>();
			fpnInfringementsList = new ArrayList<FPNExportInfringementsVO>();

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

			optionVO = new CommonOptionsVO("Immediate", "Immediate File");
			fileTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("Start", "Start File");
			fileTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("Stop", "Stop File");
			fileTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("Registration", "Registration File");
			fileTypeList.add(optionVO);

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

	public String searchFPNDetail() throws Exception {

		boolean operationFlag = false;

		try {

			fpnInfringementsList = new ArrayList<FPNExportInfringementsVO>();

			fpnInfringementsList = manager.getFPNInfringementsDetailToUpdate(Integer.parseInt(office_location),
					Integer.parseInt(String.valueOf(yearCode) + String.valueOf(monthCode)), this.fileType);

			//System.out.println("Permit Fee List Size " + fpnInfringementsList.size());

			this.rowNumber = String.valueOf(fpnInfringementsList.size());

			if (fpnInfringementsList.size() <= 0) {
				operationFlag = false;
				this.addActionError("No Data Found");
				return "home";
			} else {
				operationFlag = true;
			}

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

	public String saveFPNDetails() throws Exception {

		String returnMessage = "System Error Occured.";
		boolean operationFlag = false;

		try {

			System.out.println("row number " + this.rowNumber);

			List<FPNExport_Infringements_Validated> alledited = helper.getAllDataForFPNUpdate(request,
					rowNumber);

			System.out.println("alledited SIZE " + alledited.size());
			operationFlag = manager.UpdateFPNInfringement(alledited);

		} catch (Exception e) {
			this.addActionError(getText("global.system.error"));
			log.fatal(helper.writeLogInUpperCase(e));
		}

		if (operationFlag) {
			returnMessage = "Data Updated Successfully.";
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
			} else if (!validator.requiredFiledValidate(office_location.trim())) {
				this.addFieldError("officeCode", "Pl. Select Highways Authority");
			} else if (!validator.requiredFiledValidate(yearCode.trim())) {
				this.addFieldError("yearCode", "Pl. Select Year");
			} else if (!validator.requiredFiledValidate(monthCode.trim())) {
				this.addFieldError("monthCode", "Pl. Select Month");
			} else if (!validator.requiredFiledValidate(fileType.trim())) {
				this.addFieldError("fileType", "Pl. Select File Type");
			}
		} catch (Exception e) {
			this.addActionError(getText("global.system.error"));
			log.fatal(helper.writeLogInUpperCase(e));
		}
	}

	@SkipValidation
	public String exitUpdateFPN() throws Exception {
		return "home";
	}

	@SkipValidation
	public void emptyField() throws Exception {

		try {

			this.setMonthCode("");
			this.setFileType("");

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

	}

}