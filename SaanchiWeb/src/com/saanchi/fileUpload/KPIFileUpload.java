package com.saanchi.fileUpload;

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
import com.saanchi.hibernate.model.KPI1Provisional;
import com.saanchi.hibernate.model.KPI2Provisional;
import com.saanchi.hibernate.model.KPI3Provisional;
import com.saanchi.hibernate.model.KPI4Provisional;
import com.saanchi.hibernate.model.PAExportProvisional;
import com.saanchi.hibernate.model.PAExportStage;
import com.saanchi.hibernate.util.HibernateManager;

public class KPIFileUpload extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID = "";

	private ServletContext context;
	private HttpServletRequest request;

	private File fileUpload;
	private String fileUploadFileName;
	private String officeCode;
	private String fileType;
	private String yearCode;
	private String monthCode;
	List<CommonOptionsVO> officeList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> fileTypeList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> yearList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> monthList = new ArrayList<CommonOptionsVO>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");

	private static final Logger log = Logger.getLogger(KPIFileUpload.class);
	HibernateManager manager = new HibernateManager();
	CommonHelper helper = new CommonHelper();
	private static CommonValidation validator = new CommonValidation();

	public File getFileUpload() {
		return fileUpload;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
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

	@SkipValidation
	public String execute() throws Exception {

		empID = (String) request.getSession().getAttribute("_EMPCODE");

		int returnMessage = 1;
		String returnValue = SUCCESS;

		try {
			fileTypeList = new ArrayList<CommonOptionsVO>();

			CommonOptionsVO optionVO = new CommonOptionsVO("KPI-1", "KPI-1 File");
			fileTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("KPI-2", "KPI-2 File");
			fileTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("KPI-3", "KPI-3 File");
			fileTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("KPI-4", "KPI-4 File");
			fileTypeList.add(optionVO);

			officeList = new ArrayList<CommonOptionsVO>();
			officeList = manager.searchOfficeList();
			yearList = new ArrayList<CommonOptionsVO>();

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

	public String uploadKPIFile() throws Exception {

		FileInputStream fis = new FileInputStream(fileUpload);
		List<KPI1Provisional> kpi1ProvisionalList = new ArrayList<KPI1Provisional>();
		List<KPI2Provisional> kpi2ProvisionalList = new ArrayList<KPI2Provisional>();
		List<KPI3Provisional> kpi3ProvisionalList = new ArrayList<KPI3Provisional>();
		List<KPI4Provisional> kpi4ProvisionalList = new ArrayList<KPI4Provisional>();
		
		
		boolean operationFlag = false;
		int uploadFileId = 0;
		try {

			int fileID = manager.getFileUploadID(fileUploadFileName);
			Date currentDate = new Date();

			if (fileID == 0) {

				uploadFileId = manager.uploadFile(fileUploadFileName, this.getFileType());
				log.fatal("File Name= " + fileUploadFileName);

				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet mySheet = workbook.getSheetAt(0);
				Iterator<Row> iterator = mySheet.iterator();

				KPI1Provisional kpi1ProvisionalModel;
				KPI2Provisional kpi2Provisional;
				KPI3Provisional kpi3Provisional;
				KPI4Provisional kpi4Provisional;

				if (this.fileType.equalsIgnoreCase("KPI-1")) {

					while (iterator.hasNext()) {
						Row nextRow = iterator.next();
						Iterator<Cell> cellIterator = nextRow.cellIterator();
						kpi1ProvisionalModel = new KPI1Provisional();

						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();

							if (nextRow.getRowNum() >= 1) {

								switch (cell.getCellType()) {

								case Cell.CELL_TYPE_NUMERIC:
									
									

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 0)) {

										kpi1ProvisionalModel.setSite_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 2)) {
										kpi1ProvisionalModel.setLocality_id(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 5)) {
										kpi1ProvisionalModel.setTown_id(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 7)) {
										kpi1ProvisionalModel.setCounty_id(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 9)) {
										kpi1ProvisionalModel.setArea_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 13)) {
										kpi1ProvisionalModel
												.setWork_header_no(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 16)) {
										kpi1ProvisionalModel.setApp_seq_no(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 19)) {
										kpi1ProvisionalModel
												.setExt_version_no(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 20)) {
										kpi1ProvisionalModel
												.setPromoter_org_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 22)) {
										kpi1ProvisionalModel
												.setAuth_org_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 26)) {
										kpi1ProvisionalModel
												.setAuth_org_group_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 24)) {
										kpi1ProvisionalModel
												.setPromoter_org_group_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 34)) {
										kpi1ProvisionalModel
												.setPermit_granted(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 35)) {
										kpi1ProvisionalModel
												.setPermit_refused(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 36)) {
										kpi1ProvisionalModel
												.setPermit_deemed(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 37)) {
										kpi1ProvisionalModel
												.setPermit_superseded(new BigDecimal(cell.getNumericCellValue()));
									}

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(15))) {

										kpi1ProvisionalModel.setNotice_issue_time(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(17))) {

										kpi1ProvisionalModel.setPermit_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									break;

								case Cell.CELL_TYPE_STRING:

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 1)) {
										kpi1ProvisionalModel.setSite_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 3)) {
										kpi1ProvisionalModel
												.setStreet_work_code(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 4)) {
										kpi1ProvisionalModel.setLocality_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									} else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 6)) {
										kpi1ProvisionalModel.setTown_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									} else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 8)) {
										kpi1ProvisionalModel.setCounty_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 10)) {
										kpi1ProvisionalModel.setArea_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 11)) {
										kpi1ProvisionalModel.setWard_code(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 12)) {
										kpi1ProvisionalModel.setWard_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 18)) {
										kpi1ProvisionalModel
												.setNotice_type_code(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 21)) {
										kpi1ProvisionalModel
												.setPromoter_org_name(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 23)) {
										kpi1ProvisionalModel
												.setPromoter_org_group_name(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 25)) {
										kpi1ProvisionalModel.setAuth_org_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 27)) {
										kpi1ProvisionalModel
												.setAuth_org_group_name(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 28)) {
										kpi1ProvisionalModel
												.setRestrict_org_code(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 29)) {
										kpi1ProvisionalModel
												.setRestrict_works_ref(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 30)) {
										kpi1ProvisionalModel
												.setCustomer_connect(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 31)) {
										kpi1ProvisionalModel
												.setStreet_work_name(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
									} else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 32)) {
										kpi1ProvisionalModel
												.setExternal_reference(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 33)) {
										kpi1ProvisionalModel.setUrgent_flag(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 38)) {
										kpi1ProvisionalModel
												.setPermit_application_type(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 39)) {
										kpi1ProvisionalModel.setWorks_ref(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(15))) {
										kpi1ProvisionalModel
												.setNotice_issue_time(cell.getStringCellValue() == null ? null
														: sdf.parse(cell.getStringCellValue().trim()));
									}

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(17))) {
										kpi1ProvisionalModel.setPermit_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}

									break;

								}

							}

							if (nextRow.getRowNum() >= 1) {

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(15))) {

									kpi1ProvisionalModel.setNotice_issue_time(nextRow.getCell(15) == null ? null
											: nextRow.getCell(15).getDateCellValue());

								}

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(17))) {

									kpi1ProvisionalModel.setPermit_date(nextRow.getCell(17) == null ? null
											: nextRow.getCell(17).getDateCellValue());

								}

							}

						}

						if (nextRow.getRowNum() >= 1) {
							kpi1ProvisionalModel.setCreated_at(currentDate);
							kpi1ProvisionalModel.setCreated_by(Integer.parseInt(empID));
							kpi1ProvisionalModel.setUpload_date(currentDate);
							kpi1ProvisionalModel.setFile_name(fileUploadFileName);
							kpi1ProvisionalModel.setOffice_code(Integer.parseInt(this.officeCode));
							int yearMonth = Integer.parseInt(yearCode + monthCode);
							kpi1ProvisionalModel.setYear_month(yearMonth);

							kpi1ProvisionalList.add(kpi1ProvisionalModel);
						}

					}

					System.out.println("File Read Done");
					log.fatal("File Read Done");
					operationFlag = manager.uploadKPI1Provisional(kpi1ProvisionalList, uploadFileId, empID);
					operationFlag = true;
				}

				else if (this.fileType.equalsIgnoreCase("KPI-2")) {

					while (iterator.hasNext()) {
						Row nextRow = iterator.next();
						Iterator<Cell> cellIterator = nextRow.cellIterator();
						kpi2Provisional = new KPI2Provisional();

						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();

							if (nextRow.getRowNum() >= 1) {

								switch (cell.getCellType()) {

								case Cell.CELL_TYPE_NUMERIC:

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 0)) {

										kpi2Provisional.setSite_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 2)) {
										kpi2Provisional.setLocality_id(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 6)) {
										kpi2Provisional.setTown_id(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 8)) {
										kpi2Provisional.setCounty_id(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 10)) {
										kpi2Provisional.setArea_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 14)) {
										kpi2Provisional.setWork_header_no(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 16)) {
										kpi2Provisional.setApp_seq_no(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 18)) {
										kpi2Provisional.setNotice_type_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 19)) {
										kpi2Provisional.setExt_version_no(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 20)) {
										kpi2Provisional.setConditions_1(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 21)) {
										kpi2Provisional.setConditions_2(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 22)) {
										kpi2Provisional.setConditions_3(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 23)) {
										kpi2Provisional.setConditions_4(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 24)) {
										kpi2Provisional.setConditions_5(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 25)) {
										kpi2Provisional.setConditions_6(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 26)) {
										kpi2Provisional.setConditions_7(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 27)) {
										kpi2Provisional.setConditions_8(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 28)) {
										kpi2Provisional.setConditions_9(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 29)) {
										kpi2Provisional.setConditions_10(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 30)) {
										kpi2Provisional.setConditions_11(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 31)) {
										kpi2Provisional.setConditions_12(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 32)) {
										kpi2Provisional.setConditions_13(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 36)) {
										kpi2Provisional.setOrg_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 38)) {
										kpi2Provisional.setOrg_group_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 40)) {
										kpi2Provisional.setAuth_org_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 42)) {
										kpi2Provisional
												.setAuth_org_group_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(15))) {

										kpi2Provisional.setNotice_issue_time(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(17))) {

										kpi2Provisional.setPermit_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									break;

								case Cell.CELL_TYPE_STRING:

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 1)) {
										kpi2Provisional.setSite_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 3)) {
										kpi2Provisional.setStreet_work_code(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 4)) {
										kpi2Provisional.setStreet_work_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									} else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 5)) {
										kpi2Provisional.setLocality_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 7)) {
										kpi2Provisional.setTown_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									} else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 9)) {
										kpi2Provisional.setCounty_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 11)) {
										kpi2Provisional.setArea_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 12)) {
										kpi2Provisional.setWard_code(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 13)) {
										kpi2Provisional.setWard_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 33)) {
										kpi2Provisional.setWorks_ref(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 34)) {
										kpi2Provisional.setNotice_type_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 35)) {
										kpi2Provisional.setExternal_reference(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 37)) {
										kpi2Provisional.setOrg_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 39)) {
										kpi2Provisional.setOrg_group_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 41)) {
										kpi2Provisional.setAuth_org_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 43)) {
										kpi2Provisional.setAuth_org_group_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(15))) {
										kpi2Provisional.setNotice_issue_time(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(17))) {
										kpi2Provisional.setPermit_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}

									break;

								}

							}

							if (nextRow.getRowNum() >= 1) {

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(15))) {

									kpi2Provisional.setNotice_issue_time(nextRow.getCell(15) == null ? null
											: nextRow.getCell(15).getDateCellValue());

								}

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(17))) {

									kpi2Provisional.setPermit_date(nextRow.getCell(17) == null ? null
											: nextRow.getCell(17).getDateCellValue());

								}

							}

						}

						if (nextRow.getRowNum() >= 1) {
							kpi2Provisional.setCreated_at(currentDate);
							kpi2Provisional.setCreated_by(Integer.parseInt(empID));
							kpi2Provisional.setUpload_date(currentDate);
							kpi2Provisional.setFile_name(fileUploadFileName);
							kpi2Provisional.setOffice_code(Integer.parseInt(this.officeCode));
							int yearMonth = Integer.parseInt(yearCode + monthCode);
							kpi2Provisional.setYear_month(yearMonth);

							kpi2ProvisionalList.add(kpi2Provisional);
						}

					}

					System.out.println("File Read Done");
					log.fatal("File Read Done");
					operationFlag = manager.uploadKPI2Provisional(kpi2ProvisionalList, uploadFileId, empID);
					operationFlag = true;
				}

				else if (this.fileType.equalsIgnoreCase("KPI-3")) {

					while (iterator.hasNext()) {
						Row nextRow = iterator.next();
						Iterator<Cell> cellIterator = nextRow.cellIterator();
						kpi3Provisional = new KPI3Provisional();

						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();

							if (nextRow.getRowNum() >= 1) {

								switch (cell.getCellType()) {

								case Cell.CELL_TYPE_NUMERIC:

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 0)) {
										
									

										kpi3Provisional.setSite_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 2)) {
										kpi3Provisional.setLocality_id(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 5)) {
										kpi3Provisional.setTown_id(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 7)) {
										kpi3Provisional.setCounty_id(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 9)) {
										kpi3Provisional.setArea_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 13)) {
										kpi3Provisional.setWork_header_no(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 15)) {
										kpi3Provisional.setApp_seq_no(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 18)) {
										kpi3Provisional.setExt_version_no(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 19)) {
										kpi3Provisional
												.setPromoter_org_code(new BigDecimal(cell.getNumericCellValue()));
									}

									

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 21)) {
										kpi3Provisional
												.setPromoter_org_group_code(new BigDecimal(cell.getNumericCellValue()));
									}

									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 23)) {
										kpi3Provisional.setAuth_org_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 25)) {
										kpi3Provisional
												.setAuth_org_group_code(new BigDecimal(cell.getNumericCellValue()));
									}
									
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 37)) {
										kpi3Provisional.setPermit_granted(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 38)) {
										kpi3Provisional.setPermit_duration_extension(
												new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 39)) {
										kpi3Provisional.setPermit_duration_extension_appr(
												new BigDecimal(cell.getNumericCellValue()));
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 17)) {
										kpi3Provisional.setNotice_type_code(String.valueOf(cell.getNumericCellValue()));
									}

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(14))) {

										kpi3Provisional.setNotice_issue_time(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(16))) {

										kpi3Provisional.setPermit_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										
									} 
									
									
									
									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(34))) {

										kpi3Provisional.setWork_start_date (
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}
									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(35))) {

										kpi3Provisional.setWork_est_end_date (
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									break;

								case Cell.CELL_TYPE_STRING:

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 1)) {
										kpi3Provisional.setSite_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 3)) {
										kpi3Provisional.setStreet_work_code(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 4)) {
										kpi3Provisional.setLocality_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									} else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 6)) {
										kpi3Provisional.setTown_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									} else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 8)) {
										kpi3Provisional.setCounty_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 10)) {
										kpi3Provisional.setArea_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 11)) {
										kpi3Provisional.setWard_code(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 12)) {
										kpi3Provisional.setWard_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 17)) {
										kpi3Provisional.setNotice_type_code(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 20)) {
										kpi3Provisional.setPromoter_org_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 22)) {
										kpi3Provisional
												.setPromoter_org_group_name(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 24)) {
										kpi3Provisional.setAuth_org_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 26)) {
										kpi3Provisional.setAuth_org_group_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 27)) {
										kpi3Provisional.setRestrict_org_code(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 28)) {
										kpi3Provisional.setRestrict_works_ref(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 29)) {
										kpi3Provisional.setCustomer_connect(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 30)) {
										kpi3Provisional.setStreet_work_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									} else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 31)) {
										kpi3Provisional.setExternal_reference(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 32)) {
										kpi3Provisional.setUrgent_flag(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 33)) {
										kpi3Provisional.setWorks_ref (cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 36)) {
										kpi3Provisional
												.setPermit_application_type(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
									}

									
									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(14))) {
										kpi3Provisional.setNotice_issue_time(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(16))) {
										kpi3Provisional.setPermit_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}
									
									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(34))) {

										kpi3Provisional.setWork_start_date (cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}
									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(35))) {

										kpi3Provisional.setWork_est_end_date (cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}

									break;

								}

							}

							if (nextRow.getRowNum() >= 1) {

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(14))) {

									kpi3Provisional.setNotice_issue_time(nextRow.getCell(14) == null ? null
											: nextRow.getCell(14).getDateCellValue());

								}

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(16))) {

									kpi3Provisional.setPermit_date(nextRow.getCell(16) == null ? null
											: nextRow.getCell(16).getDateCellValue());

								}
								
								 if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(34))) {

									kpi3Provisional.setWork_start_date (nextRow.getCell(34) == null ? null
											: nextRow.getCell(34).getDateCellValue());
								}
								 if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(35))) {

									kpi3Provisional.setWork_est_end_date (nextRow.getCell(35) == null ? null
											: nextRow.getCell(35).getDateCellValue());
								}

							}

						}

						if (nextRow.getRowNum() >= 1) {
							kpi3Provisional.setCreated_at(currentDate);
							kpi3Provisional.setCreated_by(Integer.parseInt(empID));
							kpi3Provisional.setUpload_date(currentDate);
							kpi3Provisional.setFile_name(fileUploadFileName);
							kpi3Provisional.setOffice_code(Integer.parseInt(this.officeCode));
							int yearMonth = Integer.parseInt(yearCode + monthCode);
							kpi3Provisional.setYear_month(yearMonth);

							kpi3ProvisionalList.add(kpi3Provisional);
						}

					}

					System.out.println("File Read Done");
					log.fatal("File Read Done");
					operationFlag = manager.uploadKPI3Provisional(kpi3ProvisionalList, uploadFileId, empID);
					operationFlag = true;
				}
				
				
				
				
				
				
				
				
				else if (this.fileType.equalsIgnoreCase("KPI-4")) {

					while (iterator.hasNext()) {
						Row nextRow = iterator.next();
						Iterator<Cell> cellIterator = nextRow.cellIterator();
						kpi4Provisional = new KPI4Provisional();

						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();

							if (nextRow.getRowNum() >= 1) {

								switch (cell.getCellType()) {

								case Cell.CELL_TYPE_NUMERIC:

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 0)) {

										kpi4Provisional.setSite_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 2)) {
										kpi4Provisional.setLocality_id(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 6)) {
										kpi4Provisional.setTown_id(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 8)) {
										kpi4Provisional.setCounty_id(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 10)) {
										kpi4Provisional.setArea_code(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 14)) {
										kpi4Provisional.setWork_header_no(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 16)) {
										kpi4Provisional.setApp_seq_no(new BigDecimal(cell.getNumericCellValue()));
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 18)) {
										kpi4Provisional.setNotice_type_code(String.valueOf(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 19)) {
										kpi4Provisional.setExt_version_no(new BigDecimal(cell.getNumericCellValue()));
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 20)) {
										kpi4Provisional.setEarly_start_agree(new BigDecimal(cell.getNumericCellValue()));
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 21)) {
										kpi4Provisional.setReduced_period(new BigDecimal(cell.getNumericCellValue()));
									}
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 27)) {
										kpi4Provisional.setOrg_code(new BigDecimal(cell.getNumericCellValue()));
									}
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 29)) {
										kpi4Provisional.setOrg_group_code (new BigDecimal(cell.getNumericCellValue()));
									}
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 31)) {
										kpi4Provisional.setAuth_org_code(new BigDecimal(cell.getNumericCellValue()));
									}
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 33)) {
										kpi4Provisional.setAuth_org_group_code(new BigDecimal(cell.getNumericCellValue()));
									}
									
																		
									

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(15))) {

										kpi4Provisional.setNotice_issue_time(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(17))) {

										kpi4Provisional.setPermit_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										
									} 
									
									
									
									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(22))) {

										kpi4Provisional.setWork_start_date (
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}
									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(24))) {

										kpi4Provisional.setPeriod_end_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									break;

								case Cell.CELL_TYPE_STRING:

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 1)) {
										kpi4Provisional.setSite_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 3)) {
										kpi4Provisional.setStreet_work_code(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 4)) {
										kpi4Provisional.setStreet_work_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 5)) {
										kpi4Provisional.setLocality_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									} 
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 7)) {
										kpi4Provisional.setTown_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									} else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 9)) {
										kpi4Provisional.setCounty_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 11)) {
										kpi4Provisional.setArea_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 12)) {
										kpi4Provisional.setWard_code(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 13)) {
										kpi4Provisional.setWard_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 18)) {
										kpi4Provisional.setNotice_type_code(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 23)) {
										kpi4Provisional.setWorks_ref(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 25)) {
										kpi4Provisional.setNotice_type_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 26)) {
										kpi4Provisional.setExternal_reference(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 28)) {
										kpi4Provisional.setOrg_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 30)) {
										kpi4Provisional.setOrg_group_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 32)) {
										kpi4Provisional.setAuth_org_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 34)) {
										kpi4Provisional.setAuth_org_group_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									
									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(15))) {
										kpi4Provisional.setNotice_issue_time(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(17))) {
										kpi4Provisional.setPermit_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}
									
									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(22))) {

										kpi4Provisional.setWork_start_date (cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}
									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(24))) {

										kpi4Provisional.setPeriod_end_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}

									break;

								}

							}

							if (nextRow.getRowNum() >= 1) {

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(15))) {

									kpi4Provisional.setNotice_issue_time(nextRow.getCell(15) == null ? null
											: nextRow.getCell(15).getDateCellValue());

								}

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(17))) {

									kpi4Provisional.setPermit_date(nextRow.getCell(17) == null ? null
											: nextRow.getCell(17).getDateCellValue());

								}
								
								 if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(22))) {

									 kpi4Provisional.setWork_start_date (nextRow.getCell(22) == null ? null
											: nextRow.getCell(22).getDateCellValue());
								}
								 if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(24))) {

									 kpi4Provisional.setPeriod_end_date(nextRow.getCell(24) == null ? null
											: nextRow.getCell(24).getDateCellValue());
								}

							}

						}

						if (nextRow.getRowNum() >= 1) {
							kpi4Provisional.setCreated_at(currentDate);
							kpi4Provisional.setCreated_by(Integer.parseInt(empID));
							kpi4Provisional.setUpload_date(currentDate);
							kpi4Provisional.setFile_name(fileUploadFileName);
							kpi4Provisional.setOffice_code(Integer.parseInt(this.officeCode));
							int yearMonth = Integer.parseInt(yearCode + monthCode);
							kpi4Provisional.setYear_month(yearMonth);

							kpi4ProvisionalList.add(kpi4Provisional);
						}

					}

					System.out.println("File Read Done");
					log.fatal("File Read Done");
					operationFlag = manager.uploadKPI4Provisional(kpi4ProvisionalList, uploadFileId, empID);
					operationFlag = true;
				}

			} else {
				operationFlag = false;
				this.addActionMessage("File Already Uploaded");
				return SUCCESS;
			}

		} catch (

		Exception e) {
			e.printStackTrace();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception(getText("global.system.error"));
		} finally {
			fis.close();
		}

		if (operationFlag) {
			if (uploadFileId != 0) {
				FileUploadDetail fileModel = manager.getUploadFileDetails(uploadFileId);
				this.addActionMessage("Data Uploaded Successfully. Total Records= " + fileModel.getNo_of_records()
						+ " Successfull Records= " + fileModel.getSuccessful_records() + " Error Records = "
						+ fileModel.getError_records());
			} else
				this.addActionMessage("Data Uploaded Successfully");
			return SUCCESS;
		} else {
			// this.addActionError("System Error Occured");
			return SUCCESS;
		}

	}

	@SkipValidation
	public String exitKPIFileUpload() throws Exception {
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

}