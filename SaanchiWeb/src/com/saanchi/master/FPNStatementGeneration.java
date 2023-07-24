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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
import com.saanchi.commomUtility.FPNStatementVO;
import com.saanchi.hibernate.model.ChangeRequestProvisional;
import com.saanchi.hibernate.model.ChangeRequestStage;
import com.saanchi.hibernate.model.FPNExportProvisional;
import com.saanchi.hibernate.model.FPNExportStage;
import com.saanchi.hibernate.model.FeeExportProvisional;
import com.saanchi.hibernate.model.FeeExportStage;
import com.saanchi.hibernate.model.FileUploadDetail;
import com.saanchi.hibernate.model.InvoiceDetail;
import com.saanchi.hibernate.model.OfficeCreateModel;
import com.saanchi.hibernate.model.PAExportProvisional;
import com.saanchi.hibernate.model.PAExportStage;
import com.saanchi.hibernate.model.UserCreateModel;
import com.saanchi.hibernate.util.HibernateManager;

public class FPNStatementGeneration extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID = "";

	private ServletContext context;
	private HttpServletRequest request;

	private String office_location;
	private String yearCode;
	private String monthCode;
	private String fileName;

	List<CommonOptionsVO> yearList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> monthList = new ArrayList<CommonOptionsVO>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	List<CommonOptionsVO> officeList = new ArrayList<CommonOptionsVO>();

	private static final Logger log = Logger.getLogger(FPNStatementGeneration.class);
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	@SkipValidation
	public String execute() throws Exception {

		empID = (String) request.getSession().getAttribute("_EMPCODE");

		int returnMessage = 1;
		String returnValue = SUCCESS;

		try {

			officeList = new ArrayList<CommonOptionsVO>();
			officeList = manager.searchOfficeList();
			yearList = new ArrayList<CommonOptionsVO>();
			monthList = new ArrayList<CommonOptionsVO>();
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

	public String generateFPNFile() throws Exception {

		boolean operationFlag = false;
		FileOutputStream outputStream = null;
		String officeName = "";
		String officeCode = "";

		 String fileLocation = "/home/ec2-user/FPN/" + String.valueOf(officeName) + "/"+ (String.valueOf(yearCode) +
		 String.valueOf(monthCode)) + "/";

		System.out.println("Office " + office_location);
		if (office_location.contains("~")) {
			String[] office = office_location.split("~");
			officeCode = office[0].trim();
			officeName = office[1].trim();

		}

		/*
		 * String fileLocation = "E://SANCHI//FPN//" + String.valueOf(officeName) + "//"
		 * + (String.valueOf(yearCode) + String.valueOf(monthCode)) + "//";
		 */

		String monthName = "";

		try {

			if (monthCode.equalsIgnoreCase("01"))
				monthName = "January";
			else if (monthCode.equalsIgnoreCase("02"))
				monthName = "February";
			else if (monthCode.equalsIgnoreCase("03"))
				monthName = "March";
			else if (monthCode.equalsIgnoreCase("04"))
				monthName = "April";
			else if (monthCode.equalsIgnoreCase("05"))
				monthName = "May";
			else if (monthCode.equalsIgnoreCase("06"))
				monthName = "June";
			else if (monthCode.equalsIgnoreCase("07"))
				monthName = "July";
			else if (monthCode.equalsIgnoreCase("08"))
				monthName = "August";
			else if (monthCode.equalsIgnoreCase("09"))
				monthName = "September";
			else if (monthCode.equalsIgnoreCase("10"))
				monthName = "October";
			else if (monthCode.equalsIgnoreCase("11"))
				monthName = "November";
			else if (monthCode.equalsIgnoreCase("12"))
				monthName = "December";

			List<CommonOptionsVO> promoterNameList = manager.promoterNameListForFPN(Integer.parseInt(officeCode),
					Integer.parseInt(String.valueOf(yearCode) + String.valueOf(monthCode)));

			if (promoterNameList.size() <= 0) {
				operationFlag = false;
				this.addActionError("No Data Found");
				return "home";
			} else if (promoterNameList.size() > 0) {

				File physicalFile = new File(fileLocation);
				if (!physicalFile.exists()) {
					physicalFile.mkdirs();
				}
				if (physicalFile.exists()) {
					FileUtils.cleanDirectory(physicalFile);
				}

				List<InvoiceDetail> invoiceDetailList = new ArrayList<InvoiceDetail>();
				ListIterator<CommonOptionsVO> listItr = promoterNameList.listIterator();
				while (listItr.hasNext()) {

					CommonOptionsVO optionVO = (CommonOptionsVO) listItr.next();
					int bodyRow = 2;
					String fileName = fileLocation + " FPN Export- " + optionVO.getTypeCode() + "- "
							+ (String.valueOf(yearCode) + String.valueOf(monthCode)) + ".xlsx";

					List<FPNStatementVO> chargingStatementList = manager.FPNStatementList(optionVO.getTypeCode(),
							Integer.parseInt(String.valueOf(yearCode) + String.valueOf(monthCode)));

					XSSFWorkbook workbook = new XSSFWorkbook();
					XSSFSheet sheet = workbook.createSheet("FPN Export-" + monthName + " " + String.valueOf(yearCode));
					XSSFCellStyle headCellStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();
					headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					headCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					headCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					headCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					Font headFont = sheet.getWorkbook().createFont();
					headFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
					headFont.setFontHeightInPoints((short) 12);
					headFont.setColor(HSSFColor.WHITE.index);
					headCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
					headCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
					headCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					headCellStyle.setFont(headFont);

					XSSFCellStyle bodyCellStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();

					bodyCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					bodyCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					bodyCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					bodyCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					Font bodyFont = sheet.getWorkbook().createFont();
					bodyFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
					bodyFont.setFontHeightInPoints((short) 12);
					bodyFont.setColor(HSSFColor.BLACK.index);
					bodyCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
					bodyCellStyle.setFont(bodyFont);

					XSSFCellStyle headingCellStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();

					headingCellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
					headingCellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
					headingCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
					headingCellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
					Font headingFont = sheet.getWorkbook().createFont();
					headingFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
					headingFont.setFontHeightInPoints((short) 12);
					headingFont.setColor(HSSFColor.BLACK.index);
					headingCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
					headingCellStyle.setFont(headingFont);

					XSSFCellStyle footerCellStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();

					footerCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
					footerCellStyle.setFont(headingFont);

					Row row = sheet.createRow(1);
					Cell cell = row.createCell(1);
					cell.setCellValue("Promoter Name");
					cell.setCellStyle(headingCellStyle);
					cell = row.createCell(2);
					cell.setCellValue("Works Reference");
					cell.setCellStyle(headingCellStyle);
					cell = row.createCell(3);
					cell.setCellValue("Notice Date");
					cell.setCellStyle(headingCellStyle);

					cell = row.createCell(4);
					cell.setCellValue("Notice Time");
					cell.setCellStyle(headingCellStyle);

					cell = row.createCell(5);
					cell.setCellValue("Actual Date");
					cell.setCellStyle(headingCellStyle);

					cell = row.createCell(6);
					cell.setCellValue("Actual Time");
					cell.setCellStyle(headingCellStyle);

					cell = row.createCell(7);
					cell.setCellValue("FPN Comment");
					cell.setCellStyle(headingCellStyle);

					ListIterator<FPNStatementVO> statementItr = chargingStatementList.listIterator();
					while (statementItr.hasNext()) {

						FPNStatementVO statementVO = (FPNStatementVO) statementItr.next();

						row = sheet.createRow(bodyRow);
						cell = row.createCell(1);
						cell.setCellValue(statementVO.getPromoter_name());
						cell.setCellStyle(bodyCellStyle);
						cell = row.createCell(2);
						cell.setCellValue(statementVO.getWork_reference_number());
						cell.setCellStyle(bodyCellStyle);
						cell = row.createCell(3);
						cell.setCellValue(statementVO.getNotice_date());
						cell.setCellStyle(bodyCellStyle);
						cell = row.createCell(4);
						cell.setCellValue(statementVO.getNotice_time());
						cell.setCellStyle(bodyCellStyle);
						cell = row.createCell(5);
						cell.setCellValue(statementVO.getActual_date());
						cell.setCellStyle(bodyCellStyle);

						cell = row.createCell(6);
						cell.setCellValue(statementVO.getActual_time());
						cell.setCellStyle(bodyCellStyle);

						cell = row.createCell(7);
						cell.setCellValue(statementVO.getFpn_comments());
						cell.setCellStyle(bodyCellStyle);
						bodyRow = bodyRow + 1;

					}

					for (int i = 0; i <= 7; i++)
						sheet.autoSizeColumn(i);
					outputStream = new FileOutputStream(fileName);
					workbook.write(outputStream);

					
					  String zipDirName = "/home/ec2-user/FPN/" + String.valueOf(officeName) + "/" + (String.valueOf(yearCode) +
					  String.valueOf(monthCode)) + "/" +"FPN_EXPORT" + "-" + String.valueOf(officeName) + "-" + 
					  (String.valueOf(yearCode) + String.valueOf(monthCode)) + ".zip";
					 

						/*
						 * String zipDirName = "E://SANCHI//FPN//" + String.valueOf(officeName) + "/"+
						 * (String.valueOf(yearCode) + String.valueOf(monthCode)) + "/" + "FPN_EXPORT"+
						 * "-" + String.valueOf(officeName) + "-" + "-" + (String.valueOf(yearCode) +
						 * String.valueOf(monthCode)) + ".zip";
						 */

					if (helper.zipDirectory(physicalFile, zipDirName)) {
						operationFlag = true;
						this.setFileName(zipDirName);
					}

					else
						operationFlag = false;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		} finally {
			try {
				outputStream.flush();
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
				log.fatal(helper.writeLogInUpperCase(e));
			}
		}
		if (operationFlag) {

			this.addActionMessage("File Generated Successfully");
			return SUCCESS;
		} else {
			this.addActionError("System Error Occured");
			return SUCCESS;
		}

	}

	@SkipValidation
	public String exitFPNStatement() throws Exception {
		return "home";
	}

	public void validate() {
		try {

			this.execute();
			if ((empID == null) || (empID.trim().length() == 0)) {
				throw new Exception(getText("Invalid Session. Please Login"));
			} else if (!validator.requiredFiledValidate(yearCode.trim())) {
				this.addFieldError("yearCode", "Pl. Select Year");
			} else if (!validator.requiredFiledValidate(monthCode.trim())) {
				this.addFieldError("monthCode", "Pl. Select Month");
			}

		} catch (Exception e) {
			this.addActionError(getText("global.system.error"));
			log.fatal(helper.writeLogInUpperCase(e));
		}
	}

	@SkipValidation
	public void emptyField() throws Exception {

		try {

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		}

	}

}