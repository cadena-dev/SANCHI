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
import com.saanchi.hibernate.model.chargingDetail;
import com.saanchi.hibernate.util.HibernateManager;

public class ChargingStatementGeneration extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID = "";

	private ServletContext context;
	private HttpServletRequest request;

	private String office_location;
	private String yearCode;
	private String monthCode;
	private String fileName;

	List<CommonOptionsVO> officeList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> yearList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> monthList = new ArrayList<CommonOptionsVO>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

	private static final Logger log = Logger.getLogger(ChargingStatementGeneration.class);
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String generateFile() throws Exception {

		String officeName = "";
		String officeCode = "";
		boolean operationFlag = false;
		Date currentDate = new Date();
		FileOutputStream outputStream = null;
		BigDecimal invoiceSum = new BigDecimal(0);
		BigDecimal discountSum = new BigDecimal(0);
		BigDecimal permitFeeSum = new BigDecimal(0);

		System.out.println("Office " + office_location);
		if (office_location.contains("~")) {
			String[] office = office_location.split("~");
			officeCode = office[0].trim();
			officeName = office[1].trim();

		}

		String fileLocation = "/home/ec2-user/PERMIT_CHARGES/" + String.valueOf(officeName) + "/"
				+ (String.valueOf(yearCode) + String.valueOf(monthCode)) + "/";

		/*
		 * String fileLocation = "E://SANCHI//" + String.valueOf(officeName) + "//" +
		 * (String.valueOf(yearCode) + String.valueOf(monthCode)) + "//";
		 */

		String monthName = "";

		try {

			boolean alreadyUpdatedInvoice = manager.checkInvoiceDetails(
					Integer.parseInt(String.valueOf(yearCode) + String.valueOf(monthCode)),
					Integer.parseInt(officeCode));

			if (alreadyUpdatedInvoice) {
				System.out.println(officeCode + " " + String.valueOf(yearCode) + String.valueOf(monthCode));
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

				String firstDateOfMonth = "01-" + monthName + "-" + String.valueOf(yearCode);
				Date convertedDate = sdf.parse(firstDateOfMonth);
				Calendar c = Calendar.getInstance();
				c.setTime(convertedDate);
				c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
				Date lastDayOfMonth = c.getTime();
				String lastDateOfMonth = sdf.format(lastDayOfMonth);

				List<CommonOptionsVO> promoterNameList = manager.promoterNameList(Integer.parseInt(officeCode),
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
					List<chargingDetail> chargingDetailList = new ArrayList<chargingDetail>();
					ListIterator<CommonOptionsVO> listItr = promoterNameList.listIterator();
					while (listItr.hasNext()) {
						invoiceSum = new BigDecimal(0);
						discountSum = new BigDecimal(0);
						permitFeeSum = new BigDecimal(0);
						InvoiceDetail invoiceDetail = new InvoiceDetail();

						CommonOptionsVO optionVO = (CommonOptionsVO) listItr.next();
						int bodyRow = 6;
						String fileName = fileLocation + String.valueOf(officeName) + " Charge Statement- "
								+ optionVO.getTypeCode() + "- " + (String.valueOf(yearCode) + String.valueOf(monthCode))
								+ ".xlsx";

						List<ChargingStatementVO> chargingStatementList = manager.chargingStatementList(
								optionVO.getTypeCode(), Integer.parseInt(officeCode),
								Integer.parseInt(String.valueOf(yearCode) + String.valueOf(monthCode)));

						invoiceDetail.setOffice_code(Integer.parseInt(officeCode));
						invoiceDetail
								.setYear_month(Integer.parseInt(String.valueOf(yearCode) + String.valueOf(monthCode)));
						invoiceDetail.setPromoter_name(optionVO.getTypeCode());

						XSSFWorkbook workbook = new XSSFWorkbook();
						XSSFSheet sheet = workbook
								.createSheet("Permit Charges-" + monthName + " " + String.valueOf(yearCode));
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

						Row row = sheet.createRow(0);
						Cell cell = row.createCell(1);
						cell.setCellValue("About  ");
						cell.setCellStyle(headCellStyle);
						cell = row.createCell(2);
						cell.setCellValue(officeName + "- Charge Statement-" + optionVO.getTypeCode());
						cell.setCellStyle(bodyCellStyle);

						row = sheet.createRow(1);
						cell = row.createCell(1);
						cell.setCellValue("Published on   ");
						cell.setCellStyle(headCellStyle);
						cell = row.createCell(2);
						cell.setCellValue(sdf.format(currentDate));
						cell.setCellStyle(bodyCellStyle);

						row = sheet.createRow(2);
						cell = row.createCell(1);
						cell.setCellValue("Published by   ");
						cell.setCellStyle(headCellStyle);
						cell = row.createCell(2);
						cell.setCellValue(String.valueOf(officeName));
						cell.setCellStyle(bodyCellStyle);

						row = sheet.createRow(3);
						cell = row.createCell(1);
						cell.setCellValue("Period Covered   ");
						cell.setCellStyle(headCellStyle);
						cell = row.createCell(2);
						cell.setCellValue(firstDateOfMonth + "  to  " + lastDateOfMonth);
						cell.setCellStyle(bodyCellStyle);

						row = sheet.createRow(5);
						cell = row.createCell(1);
						cell.setCellValue("Works Reference");
						cell.setCellStyle(headingCellStyle);
						cell = row.createCell(2);
						cell.setCellValue("Permit Reference");
						cell.setCellStyle(headingCellStyle);
						cell = row.createCell(3);
						cell.setCellValue("Date of Permits");
						cell.setCellStyle(headingCellStyle);

						cell = row.createCell(4);
						cell.setCellValue("Works Type");
						cell.setCellStyle(headingCellStyle);

						cell = row.createCell(5);
						cell.setCellValue("Permit Type");
						cell.setCellStyle(headingCellStyle);

						cell = row.createCell(6);
						cell.setCellValue("Applicable Charge");
						cell.setCellStyle(headingCellStyle);

						cell = row.createCell(7);
						cell.setCellValue("Standard Charge");
						cell.setCellStyle(headingCellStyle);

						cell = row.createCell(8);
						cell.setCellValue("Adjustment Charge");
						cell.setCellStyle(headingCellStyle);

						cell = row.createCell(9);
						cell.setCellValue("Invoice Amount");
						cell.setCellStyle(headingCellStyle);

						cell = row.createCell(10);
						cell.setCellValue("Location");
						cell.setCellStyle(headingCellStyle);

						cell = row.createCell(11);
						cell.setCellValue("Street");
						cell.setCellStyle(headingCellStyle);

						ListIterator<ChargingStatementVO> statementItr = chargingStatementList.listIterator();
						while (statementItr.hasNext()) {

							ChargingStatementVO statementVO = (ChargingStatementVO) statementItr.next();

							if ((statementVO.getPermit_type().equalsIgnoreCase("Major"))
									&& (statementVO.getWorks_type().equalsIgnoreCase("Permit"))) {
								System.out.println("Checking PAA");
								ChargingStatementVO paaVO = manager.getPAA(Integer.parseInt(officeCode),
										statementVO.getWorks_reference(), statementVO.getApplicable_charge());
								if (paaVO != null && paaVO.getWorks_reference() != null) {

									chargingDetail chargingDtl = new chargingDetail();
									chargingDtl.setOffice_code(Integer.parseInt(officeCode));
									chargingDtl.setYear_month(
											Integer.parseInt(String.valueOf(yearCode) + String.valueOf(monthCode)));
									chargingDtl.setPromoter_name(optionVO.getTypeCode());

									chargingDtl.setWorks_reference(paaVO.getWorks_reference());
									chargingDtl.setPermit_reference(paaVO.getPermit_reference());
									chargingDtl.setDate_of_permits(sdf1.parse(paaVO.getDate_of_permits()));
									chargingDtl.setWorks_type(paaVO.getWorks_type());
									chargingDtl.setPermit_type(paaVO.getPermit_type());
									chargingDtl.setApplicable_charge(paaVO.getApplicable_charge());
									chargingDtl.setStandard_charges(new BigDecimal(paaVO.getStandard_charges()));
									chargingDtl.setAdjustment_charges(new BigDecimal(paaVO.getAdjustment_charges()));
									chargingDtl.setInvoice_amount(new BigDecimal(paaVO.getInvoice_amount()));

									row = sheet.createRow(bodyRow);
									cell = row.createCell(1);
									cell.setCellValue(paaVO.getWorks_reference());
									cell.setCellStyle(bodyCellStyle);
									cell = row.createCell(2);
									cell.setCellValue(paaVO.getPermit_reference());
									cell.setCellStyle(bodyCellStyle);
									cell = row.createCell(3);
									cell.setCellValue(paaVO.getDate_of_permits());
									cell.setCellStyle(bodyCellStyle);
									cell = row.createCell(4);
									cell.setCellValue(paaVO.getWorks_type());
									cell.setCellStyle(bodyCellStyle);
									cell = row.createCell(5);
									cell.setCellValue(paaVO.getPermit_type());
									cell.setCellStyle(bodyCellStyle);

									cell = row.createCell(6);
									cell.setCellValue(paaVO.getApplicable_charge());
									cell.setCellStyle(bodyCellStyle);
									cell = row.createCell(7);
									if (!paaVO.getStandard_charges().contains("."))
										cell.setCellValue("£" + paaVO.getStandard_charges() + ".00");
									else
										cell.setCellValue("£" + paaVO.getStandard_charges());
									cell.setCellStyle(bodyCellStyle);

									cell = row.createCell(8);
									if (!paaVO.getAdjustment_charges().contains("."))
										cell.setCellValue("£" + paaVO.getAdjustment_charges() + ".00");
									else
										cell.setCellValue("£" + paaVO.getAdjustment_charges());
									cell.setCellStyle(bodyCellStyle);

									cell = row.createCell(9);
									if (!paaVO.getInvoice_amount().contains("."))
										cell.setCellValue("£" + paaVO.getInvoice_amount() + ".00");
									else
										cell.setCellValue("£" + paaVO.getInvoice_amount());
									cell.setCellStyle(bodyCellStyle);

									cell = row.createCell(10);
									cell.setCellValue(paaVO.getWork_location());
									cell.setCellStyle(bodyCellStyle);

									cell = row.createCell(11);
									cell.setCellValue(paaVO.getStreet());
									cell.setCellStyle(bodyCellStyle);

									chargingDetailList.add(chargingDtl);
									invoiceSum = invoiceSum.add(new BigDecimal(paaVO.getInvoice_amount()));
									discountSum = discountSum.add(new BigDecimal(paaVO.getAdjustment_charges()));
									permitFeeSum = permitFeeSum.add(new BigDecimal(paaVO.getStandard_charges()));
									bodyRow = bodyRow + 1;

								}
							}

							chargingDetail chargingDtl = new chargingDetail();
							chargingDtl.setOffice_code(Integer.parseInt(officeCode));
							chargingDtl.setYear_month(
									Integer.parseInt(String.valueOf(yearCode) + String.valueOf(monthCode)));
							chargingDtl.setPromoter_name(optionVO.getTypeCode());

							chargingDtl.setWorks_reference(statementVO.getWorks_reference());
							chargingDtl.setPermit_reference(statementVO.getPermit_reference());
							chargingDtl.setDate_of_permits(sdf1.parse(statementVO.getDate_of_permits()));
							chargingDtl.setWorks_type(statementVO.getPermit_type());
							chargingDtl.setPermit_type(statementVO.getWorks_type());
							chargingDtl.setApplicable_charge(statementVO.getApplicable_charge());
							chargingDtl.setStandard_charges(new BigDecimal(statementVO.getStandard_charges()));
							chargingDtl.setAdjustment_charges(new BigDecimal(statementVO.getAdjustment_charges()));
							chargingDtl.setInvoice_amount(new BigDecimal(statementVO.getInvoice_amount()));

							row = sheet.createRow(bodyRow);
							cell = row.createCell(1);
							cell.setCellValue(statementVO.getWorks_reference());
							cell.setCellStyle(bodyCellStyle);
							cell = row.createCell(2);
							cell.setCellValue(statementVO.getPermit_reference());
							cell.setCellStyle(bodyCellStyle);
							cell = row.createCell(3);
							cell.setCellValue(statementVO.getDate_of_permits());
							cell.setCellStyle(bodyCellStyle);
							cell = row.createCell(4);
							cell.setCellValue(statementVO.getPermit_type());
							cell.setCellStyle(bodyCellStyle);
							cell = row.createCell(5);
							cell.setCellValue(statementVO.getWorks_type());
							cell.setCellStyle(bodyCellStyle);

							cell = row.createCell(6);
							cell.setCellValue(statementVO.getApplicable_charge());
							cell.setCellStyle(bodyCellStyle);
							cell = row.createCell(7);
							if (!statementVO.getStandard_charges().contains("."))
								cell.setCellValue("£" + statementVO.getStandard_charges() + ".00");
							else
								cell.setCellValue("£" + statementVO.getStandard_charges());
							cell.setCellStyle(bodyCellStyle);

							cell = row.createCell(8);
							if (!statementVO.getAdjustment_charges().contains("."))
								cell.setCellValue("£" + statementVO.getAdjustment_charges() + ".00");
							else
								cell.setCellValue("£" + statementVO.getAdjustment_charges());
							cell.setCellStyle(bodyCellStyle);

							cell = row.createCell(9);
							if (!statementVO.getInvoice_amount().contains("."))
								cell.setCellValue("£" + statementVO.getInvoice_amount() + ".00");
							else
								cell.setCellValue("£" + statementVO.getInvoice_amount());
							cell.setCellStyle(bodyCellStyle);

							if (!statementVO.getWork_location().equalsIgnoreCase("*")
									&& !statementVO.getStreet().equalsIgnoreCase("*")) {
								cell = row.createCell(10);
								cell.setCellValue(statementVO.getWork_location() == null ? "*"
										: statementVO.getWork_location().trim());
								cell.setCellStyle(bodyCellStyle);

								cell = row.createCell(11);
								cell.setCellValue(
										statementVO.getStreet() == null ? "*" : statementVO.getStreet().trim());
								cell.setCellStyle(bodyCellStyle);

								chargingDtl.setWork_location(statementVO.getWork_location() == null ? "*"
										: statementVO.getWork_location().trim());

								chargingDtl.setStreet(
										statementVO.getStreet() == null ? "*" : statementVO.getStreet().trim());

							} else {

								String locationStreetArray[] = manager
										.getLocationAndStreetForChangeRequest(statementVO.getPermit_reference())
										.split("~");

								cell = row.createCell(10);
								cell.setCellValue(locationStreetArray[0].trim());
								cell.setCellStyle(bodyCellStyle);

								cell = row.createCell(11);
								cell.setCellValue(locationStreetArray[1].trim());
								cell.setCellStyle(bodyCellStyle);

								chargingDtl.setWork_location(locationStreetArray[0].trim());

								chargingDtl.setStreet(locationStreetArray[1].trim());

							}
							chargingDetailList.add(chargingDtl);
							invoiceSum = invoiceSum.add(new BigDecimal(statementVO.getInvoice_amount()));
							discountSum = discountSum.add(new BigDecimal(statementVO.getAdjustment_charges()));
							permitFeeSum = permitFeeSum.add(new BigDecimal(statementVO.getStandard_charges()));
							bodyRow = bodyRow + 1;

						}
						invoiceDetail.setPermit_fee_amount(permitFeeSum);
						invoiceDetail.setDiscount_applied(discountSum);
						invoiceDetail.setInvoiceable_amount(permitFeeSum.subtract(discountSum));
						invoiceDetail.setChallenged_amount(new BigDecimal(0));
						invoiceDetail.setInvoiced_amount(permitFeeSum.subtract(discountSum));

						row = sheet.createRow(bodyRow);
						cell = row.createCell(9);
						if (!String.valueOf(invoiceSum).contains("."))
							cell.setCellValue("£" + String.valueOf(invoiceSum) + ".00");
						else
							cell.setCellValue("£" + String.valueOf(invoiceSum));
						cell.setCellStyle(footerCellStyle);

						int currentCellValue = 12;

						for (int i = 0; i < currentCellValue; i++)
							sheet.autoSizeColumn(i);
						outputStream = new FileOutputStream(fileName);
						workbook.write(outputStream);
						invoiceDetailList.add(invoiceDetail);

					}

					boolean invoiceSaveValue = manager.saveInvoiceList(invoiceDetailList, Integer.parseInt(officeCode),
							Integer.parseInt(String.valueOf(yearCode) + String.valueOf(monthCode)), chargingDetailList);

					if (invoiceSaveValue) {

						String zipDirName = "/home/ec2-user/PERMIT_CHARGES/" + String.valueOf(officeName) + "/"
								+ (String.valueOf(yearCode) + String.valueOf(monthCode)) + "/"
								+ String.valueOf(officeName) + "-"
								+ (String.valueOf(yearCode) + String.valueOf(monthCode)) + ".zip";

						/*
						 * String zipDirName = "E://SANCHI//" + String.valueOf(officeName) + "/" +
						 * (String.valueOf(yearCode) + String.valueOf(monthCode)) + "/" +
						 * String.valueOf(officeName) + "-" + (String.valueOf(yearCode) +
						 * String.valueOf(monthCode)) + ".zip";
						 */

						if (helper.zipDirectory(physicalFile, zipDirName)) {
							operationFlag = true;
							this.setFileName(zipDirName);
						}

						else
							operationFlag = false;

						operationFlag = true;
					}
				} else
					operationFlag = false;

			}

			else {
				this.addActionError("Invoice Details Already Updated.");
				return "home";

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
	public String exitChargingStatement() throws Exception {
		return "home";
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