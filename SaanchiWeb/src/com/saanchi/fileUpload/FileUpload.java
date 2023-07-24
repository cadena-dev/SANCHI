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
import com.saanchi.hibernate.model.PAExportProvisional;
import com.saanchi.hibernate.model.PAExportStage;
import com.saanchi.hibernate.util.HibernateManager;

public class FileUpload extends ActionSupport implements ServletContextAware, ServletRequestAware {

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

	private static final Logger log = Logger.getLogger(FileUpload.class);
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
			yearList = new ArrayList<CommonOptionsVO>();

			int year = Calendar.getInstance().get(Calendar.YEAR)-1;
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

	public String uploadFile() throws Exception {

		FileInputStream fis = new FileInputStream(fileUpload);
		List<FPNExportProvisional> fpnModelListlList = new ArrayList<FPNExportProvisional>();
		List<FeeExportProvisional> feeModelListlList = new ArrayList<FeeExportProvisional>();
		List<ChangeRequestProvisional> changModelListlList = new ArrayList<ChangeRequestProvisional>();
		List<PAExportProvisional> paExportModelListlList = new ArrayList<PAExportProvisional>();
		
		List<FPNExportStage> fpnStagelList = new ArrayList<FPNExportStage>();
		List<PAExportStage> paStagelList = new ArrayList<PAExportStage>();
		
		List<ChangeRequestStage> changeStagelList = new ArrayList<ChangeRequestStage>();
		List<FeeExportStage> feeStagelList = new ArrayList<FeeExportStage>();
		
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

				FPNExportProvisional fpnModel;
				FeeExportProvisional feeModel;
				ChangeRequestProvisional changeModel;
				PAExportProvisional paExportModel;
				
				FPNExportStage   fpnStage;
				PAExportStage    paStage;
				ChangeRequestStage changeStage;
				FeeExportStage   feeStage;

				if (this.fileType.equalsIgnoreCase("FPN_Export")) {

					while (iterator.hasNext()) {
						Row nextRow = iterator.next();
						Iterator<Cell> cellIterator = nextRow.cellIterator();
						fpnModel = new FPNExportProvisional();
						fpnStage= new FPNExportStage();
						
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();

							if (nextRow.getRowNum() >= 1) {

								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_NUMERIC:

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 17)) {
										fpnModel.setUsrn(new BigDecimal(cell.getNumericCellValue()));
										fpnStage.setUsrn(new BigDecimal(cell.getNumericCellValue()));									
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 19))
										fpnModel.setWorkstream_prefix(new BigDecimal(cell.getNumericCellValue()));

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 14)) {
										fpnModel.setIssue_date_time(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										fpnStage.setIssue_date_time(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										
									}

									else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(21))) {
										fpnModel.setStatus_changed_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										fpnStage.setStatus_changed_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									break;

								case Cell.CELL_TYPE_STRING:

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 0)) {
										fpnModel.setWorks_reference_number(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										fpnStage.setWorks_reference_number(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 1)) {
										fpnModel.setFpn_number(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										fpnStage.setFpn_number(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 2)) {
										fpnModel.setFpn_status(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										fpnStage.setFpn_status(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 3)) {
										fpnModel.setOffence_code(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										fpnStage.setOffence_code(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										 
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 4)) {
										fpnModel.setOffence_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
										
										fpnStage.setOffence_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
										
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 5)) {
										fpnModel.setOffence_details(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										fpnStage.setOffence_details(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 6))
										fpnModel.setAuthorised_officer_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 7))
										fpnModel.setOfficer_contact_details(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 8))
										fpnModel.setOfficer_address(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 9))
										fpnModel.setRepresentations_contact(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 10))
										fpnModel.setRepresentations_contact_address(
												cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 11))
										fpnModel.setPayment_methods(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 12)) {
										fpnModel.setWorks_promoter(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										fpnStage.setWorks_promoter(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 13)) {
										fpnModel.setIssuing_authority(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										fpnStage.setIssuing_authority(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 14)) {
										fpnModel.setIssue_date_time(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
										
										fpnStage.setIssue_date_time(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
										
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 15)) {
										fpnModel.setStreet_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										fpnStage.setStreet_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 16))
										fpnModel.setArea_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 17)) {
										fpnModel.setUsrn(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim()));

										fpnStage.setUsrn(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim()));
									}
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 18))
										fpnModel.setAttach_photo_evidence(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 19))
										fpnModel.setWorkstream_prefix(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim()));

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 20))
										fpnModel.setTown(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 21)) {
										fpnModel.setStatus_changed_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
										
										fpnStage.setStatus_changed_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}

									break;

								}

							}

							if (nextRow.getRowNum() >= 1) {

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(4))) {
									fpnModel.setOffence_date(
											nextRow.getCell(4) == null ? null : nextRow.getCell(4).getDateCellValue());
									
									fpnStage.setOffence_date(
											nextRow.getCell(4) == null ? null : nextRow.getCell(4).getDateCellValue());
								}

								else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(14))) {
									fpnModel.setIssue_date_time(nextRow.getCell(14) == null ? null
											: nextRow.getCell(14).getDateCellValue());
									
									fpnStage.setIssue_date_time(nextRow.getCell(14) == null ? null
											: nextRow.getCell(14).getDateCellValue());

								}

								else if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(21))) {
									fpnModel.setStatus_changed_date(nextRow.getCell(21) == null ? null
											: nextRow.getCell(21).getDateCellValue());
									
									fpnStage.setStatus_changed_date(nextRow.getCell(21) == null ? null
											: nextRow.getCell(21).getDateCellValue());
								}

							}

						}

						if (nextRow.getRowNum() >= 1) {
							fpnModel.setCreated_at(currentDate);
							fpnModel.setCreated_by(Integer.parseInt(empID));
							fpnModel.setUpload_date(currentDate);
							fpnModel.setFile_name(fileUploadFileName);
							fpnModel.setOffice_code(Integer.parseInt(this.officeCode));
							int yearMonth = Integer.parseInt(yearCode + monthCode);
							fpnModel.setYear_month(yearMonth);
							
							
							
							fpnStage.setCreated_at(currentDate);
							fpnStage.setCreated_by(Integer.parseInt(empID));
							fpnStage.setUpload_date(currentDate);
							fpnStage.setFile_name(fileUploadFileName);
							fpnStage.setOffice_code(Integer.parseInt(this.officeCode));
							fpnStage.setYear_month(yearMonth);
							
							fpnModelListlList.add(fpnModel);
							fpnStagelList.add(fpnStage);
						}

					}

					System.out.println("File Read Done");
					log.fatal("File Read Done");
					operationFlag = manager.uploadFPNExportProvisional(fpnModelListlList,fpnStagelList ,uploadFileId, empID);
					operationFlag = true;
				}

				else if (this.fileType.equalsIgnoreCase("Fee_Export")) {

					while (iterator.hasNext()) {
						Row nextRow = iterator.next();
						Iterator<Cell> cellIterator = nextRow.cellIterator();
						feeModel = new FeeExportProvisional();
						feeStage  = new FeeExportStage();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();

							if (nextRow.getRowNum() >= 1) {

								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_NUMERIC:

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 0)) {
										feeModel.setOrg_code(
												String.valueOf(new BigDecimal(cell.getNumericCellValue())));
										
										feeStage.setOrg_code(
												String.valueOf(new BigDecimal(cell.getNumericCellValue())));
										
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 5)) {
										feeModel.setDiscount_percentage(new BigDecimal(cell.getNumericCellValue()));
										
										feeStage.setDiscount_percentage(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 7)) {
										feeModel.setGranted_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										
										feeStage.setGranted_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 8)) {
										feeModel.setUsrn(new BigDecimal(cell.getNumericCellValue()));
										
										feeStage.setUsrn(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 14)) {
										feeModel.setWorkstream_prefix(new BigDecimal(cell.getNumericCellValue()));
										
										feeStage.setWorkstream_prefix(new BigDecimal(cell.getNumericCellValue()));
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 18)) {
										feeModel.setProposed_start_date(cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										
										feeStage.setProposed_start_date(cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 19)) {
										feeModel.setProposed_end_date(cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										
										feeStage.setProposed_end_date(cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 20)) {
										feeModel.setActual_start_date(cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										
										feeStage.setActual_start_date(cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 21)) {
										feeModel.setActual_end_date(cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										
										feeStage.setActual_end_date(cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									break;

								case Cell.CELL_TYPE_STRING:

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 0)) {
										feeModel.setOrg_code(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setOrg_code(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 1)) {
										feeModel.setOrg_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setOrg_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 2)) {
										feeModel.setPromoter_workstream(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setPromoter_workstream(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 3)) {
										feeModel.setWork_category(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setWork_category(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 4)) {
										feeModel.setTransaction_type(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setTransaction_type(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 5)) {
										feeModel.setDiscount_percentage(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim().replaceAll("%", "")));
										
										feeStage.setDiscount_percentage(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim().replaceAll("%", "")));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 6)) {
										feeModel.setPermit_reference(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setPermit_reference(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 7)) {
										feeModel.setGranted_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
										
										feeStage.setGranted_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 8)) {
										feeModel.setUsrn(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim()));
										
										feeStage.setUsrn(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 9)) {
										feeModel.setStreet(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setStreet(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 10)) {
										feeModel.setTown(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setTown(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 11)) {
										feeModel.setRoad_category(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setRoad_category(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 12)) {
										feeModel.setIs_works_traffic_sensitive(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setIs_works_traffic_sensitive(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 13)) {
										feeModel.setIs_street_traffic_sensitive(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setIs_street_traffic_sensitive(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 14)) {
										feeModel.setWorkstream_prefix(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim()));
										
										feeStage.setWorkstream_prefix(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 15)) {
										feeModel.setArea(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setArea(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 16)) {
										feeModel.setIs_ttro_required(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setIs_ttro_required(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 17)) {
										feeModel.setTraffic_management_type(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setTraffic_management_type(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 18)) {
										feeModel.setProposed_start_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
										
										feeStage.setProposed_start_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 19)) {
										feeModel.setProposed_end_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
										
										feeStage.setProposed_end_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 20)) {
										feeModel.setActual_start_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
										
										feeStage.setActual_start_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 21)) {
										feeModel.setActual_end_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
										
										feeStage.setActual_end_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 22)) {
										feeModel.setChange_request_reference(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										feeStage.setChange_request_reference(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									break;

								}

							}

							if (nextRow.getRowNum() >= 1) {
								
								if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 7)) {
									feeModel.setGranted_date(nextRow.getCell(7) == null ? null
											: nextRow.getCell(7).getDateCellValue());
									
									feeStage.setGranted_date(nextRow.getCell(7) == null ? null
											: nextRow.getCell(7).getDateCellValue());
								}


								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(18))) {
									feeModel.setProposed_start_date(nextRow.getCell(18) == null ? null
											: nextRow.getCell(18).getDateCellValue());
									
									feeStage.setProposed_start_date(nextRow.getCell(18) == null ? null
											: nextRow.getCell(18).getDateCellValue());
								}

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(19))) {
									feeModel.setProposed_end_date(nextRow.getCell(19) == null ? null
											: nextRow.getCell(19).getDateCellValue());
									
									feeStage.setProposed_end_date(nextRow.getCell(19) == null ? null
											: nextRow.getCell(19).getDateCellValue());
								}

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(20))) {
									feeModel.setActual_start_date(nextRow.getCell(20) == null ? null
											: nextRow.getCell(20).getDateCellValue());
									
									feeStage.setActual_start_date(nextRow.getCell(20) == null ? null
											: nextRow.getCell(20).getDateCellValue());
									
								}

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(21))) {
									feeModel.setActual_end_date(nextRow.getCell(21) == null ? null
											: nextRow.getCell(21).getDateCellValue());
									
									feeStage.setActual_end_date(nextRow.getCell(21) == null ? null
											: nextRow.getCell(21).getDateCellValue());
								}

							}

						}

						if (nextRow.getRowNum() >= 1) {

							feeModel.setCreated_at(currentDate);
							feeModel.setCreated_by(Integer.parseInt(empID));
							feeModel.setUpload_date(currentDate);
							feeModel.setFile_name(fileUploadFileName);
							feeModel.setOffice_code(Integer.parseInt(this.officeCode));
							int yearMonth = Integer.parseInt(yearCode + monthCode);
							feeModel.setYear_month(yearMonth);
							feeModelListlList.add(feeModel);
							
							
							feeStage.setCreated_at(currentDate);
							feeStage.setCreated_by(Integer.parseInt(empID));
							feeStage.setUpload_date(currentDate);
							feeStage.setFile_name(fileUploadFileName);
							feeStage.setOffice_code(Integer.parseInt(this.officeCode));
							feeStage.setYear_month(yearMonth);
							
							
							feeStagelList.add(feeStage);

						}

					}

					System.out.println("File Read Done");
					log.fatal("File Read Done");
					operationFlag = manager.uploadFeeExportProvisional(feeModelListlList,feeStagelList, uploadFileId, empID);
					operationFlag = true;

				}

				else if (this.fileType.equalsIgnoreCase("Change_Request")) {

					while (iterator.hasNext()) {
						Row nextRow = iterator.next();
						Iterator<Cell> cellIterator = nextRow.cellIterator();
						changeModel = new ChangeRequestProvisional();
						changeStage    = new ChangeRequestStage();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();

							if (nextRow.getRowNum() >= 1) {

								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_NUMERIC:

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 10)) {
										changeModel.setPa_actual_start_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										
										changeStage.setPa_actual_start_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 11)) {
										changeModel.setPa_actual_end_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										
										changeStage.setPa_actual_end_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 14)) {
										changeModel.setAssessment_discount(new BigDecimal(cell.getNumericCellValue()));
										
										changeStage.setAssessment_discount(new BigDecimal(cell.getNumericCellValue()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 20)) {
										changeModel.setSubmission_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										
										changeStage.setChange_submission_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 21))
										changeModel.setDeem_by_date(
												cell.getDateCellValue() == null ? null : cell.getDateCellValue());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 22))
										changeModel.setWorkstream_prefix(new BigDecimal(cell.getNumericCellValue()));

									break;

								case Cell.CELL_TYPE_STRING:

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 0)) {
										changeModel.setChange_request_reference_number(
												cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
										
										changeStage.setChange_request_reference_number(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
										
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 1))
										changeModel.setHighway_authority_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 2)) {
										changeModel.setPromoter_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										changeStage.setPromoter_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 3)) {
										changeModel.setPermit_reference_number(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										changeStage.setPermit_reference_number(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 4)) {
										changeModel.setChange_request_type(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										changeStage.setChange_request_type(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 5)) {
										changeModel.setChange_status(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										changeStage.setChange_status(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 6))
										changeModel.setStatus_change_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 7))
										changeModel
												.setReasonable_period_end_date(cell.getStringCellValue() == null ? null
														: sdf1.parse(cell.getStringCellValue().trim()));

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 8)) {
										changeModel.setPa_proposed_start_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
										
										changeStage.setPa_proposed_start_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 9)) {
										changeModel.setPa_proposed_end_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
										
										changeStage.setPa_proposed_end_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 10)) {
										changeModel.setPa_actual_start_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
										
										changeStage.setPa_actual_start_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 11)) {
										changeModel.setPa_actual_end_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
										
										changeStage.setPa_actual_end_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 12))
										changeModel.setWorks_status(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 13))
										changeModel.setAssessment_comment(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 14)) {
										changeModel.setAssessment_discount(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim().replaceAll("%", "")));
										changeStage.setAssessment_discount(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim().replaceAll("%", "")));
										
										
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 15))
										changeModel.setChange_reason(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 16))
										changeModel.setWorks_location(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 17))
										changeModel.setStreet_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 18))
										changeModel.setArea_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 19)) {
										changeModel.setWorks_category(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										changeStage.setType_of_work(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 20)) {
										changeModel.setSubmission_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
										
										changeStage.setChange_submission_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 21))
										changeModel.setDeem_by_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 22))
										changeModel.setWorkstream_prefix(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim()));

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 23))
										changeModel.setTown(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									break;

								}

							}

							if (nextRow.getRowNum() >= 1) {

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(6)))
									changeModel.setStatus_change_date(
											nextRow.getCell(6) == null ? null : nextRow.getCell(6).getDateCellValue());

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(7))) {
									changeModel.setReasonable_period_end_date(
											nextRow.getCell(7) == null ? null : nextRow.getCell(7).getDateCellValue());
								}

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(8))) {
									changeModel.setPa_proposed_start_date(
											nextRow.getCell(8) == null ? null : nextRow.getCell(8).getDateCellValue());
									
									changeStage.setPa_proposed_start_date(
											nextRow.getCell(8) == null ? null : nextRow.getCell(8).getDateCellValue());
								}

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(9))) {
									changeModel.setPa_proposed_end_date(
											nextRow.getCell(9) == null ? null : nextRow.getCell(9).getDateCellValue());
									
									changeStage.setPa_proposed_end_date(
											nextRow.getCell(9) == null ? null : nextRow.getCell(9).getDateCellValue());
								}

							}

						}

						if (nextRow.getRowNum() >= 1) {
							
							if((changeModel.getAssessment_discount()==null)||(changeStage.getAssessment_discount()==null)) {
								
								changeModel.setAssessment_discount(new BigDecimal(0));
								changeStage.setAssessment_discount(new BigDecimal(0));
							}
							

							changeModel.setCreated_at(currentDate);
							changeModel.setCreated_by(Integer.parseInt(empID));
							changeModel.setUpload_date(currentDate);
							changeModel.setFile_name(fileUploadFileName);
							changeModel.setOffice_code(Integer.parseInt(this.officeCode));
							int yearMonth = Integer.parseInt(yearCode + monthCode);
							changeModel.setYear_month(yearMonth);
							changModelListlList.add(changeModel);
							
							
							changeStage.setApplication_type("Variation");
							changeStage.setCreated_at(currentDate);
							changeStage.setCreated_by(Integer.parseInt(empID));
							changeStage.setUpload_date(currentDate);
							changeStage.setFile_name(fileUploadFileName);
							changeStage.setOffice_code(Integer.parseInt(this.officeCode));
							changeStage.setYear_month(yearMonth);
							
							if(changeStage.getPa_proposed_end_date()!=null
								&& 	changeStage.getPa_proposed_start_date()!=null) {
								
								changeStage.setDuration(new BigDecimal(helper.numberOfDays(changeStage.getPa_proposed_start_date(), changeStage.getPa_proposed_end_date())));
							}
							
							changeStagelList.add(changeStage);

						}

					}

					System.out.println("File Read Done");
					log.fatal("File Read Done");
					operationFlag = manager.uploadChangeRequestProvisional(changModelListlList, changeStagelList,uploadFileId, empID);
					operationFlag = true;

				}

				else if (this.fileType.equalsIgnoreCase("PA_Export")) {
					


					while (iterator.hasNext()) {
						Row nextRow = iterator.next();
						Iterator<Cell> cellIterator = nextRow.cellIterator();
						paExportModel = new PAExportProvisional();
						paStage    = new PAExportStage();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();

							if (nextRow.getRowNum() >= 1) {

								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_NUMERIC:
									
									 if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 3)) {
										paExportModel.setUsrn(new BigDecimal(cell.getNumericCellValue()));
										paStage.setUsrn(new BigDecimal(cell.getNumericCellValue()));
										
									 }
									
									 else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 5)) {
										 paExportModel.setRoad_category(String.valueOf(cell.getNumericCellValue()));
										 
										 paStage.setReinstatement_category(String.valueOf(cell.getNumericCellValue())); 
										 
									 }
									 
									 else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 11))
										 paExportModel.setPromoter_contact_details(
													cell.getDateCellValue() == null ? null : String.valueOf(cell.getNumericCellValue()));
									 
									 else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 13))
										 paExportModel.setContractor_contact_details(
													cell.getDateCellValue() == null ? null : String.valueOf(cell.getNumericCellValue()));
									 
									 
									 else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 15))
										 paExportModel.setAdditional_contact_number(
													cell.getDateCellValue() == null ? null : String.valueOf(cell.getNumericCellValue()));

									 else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 18)) {
										 paExportModel.setProposed_start_time(
													cell.getDateCellValue() == null ? sdf2.parse("00:00") : cell.getDateCellValue());
										 
										 paStage.setProposed_start_time(cell.getDateCellValue() == null ? sdf2.parse("00:00") : cell.getDateCellValue());
									 }
									 
									 
									 else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 20)) {
										 paExportModel.setProposed_end_time(
													cell.getDateCellValue() == null ? sdf2.parse("00:00") : cell.getDateCellValue());
										 
										 paStage.setProposed_end_time(cell.getDateCellValue() == null ? sdf2.parse("00:00") : cell.getDateCellValue());
									 }
									 
									 else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 26))
										 paExportModel.setProject_reference_number(
													cell.getDateCellValue() == null ? null : String.valueOf(cell.getNumericCellValue()));
									 
									 else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 41))
										 paExportModel.setStatus_changed_date(
													cell.getDateCellValue() == null ? null : cell.getDateCellValue());

									
									 else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 48)) {
										 paExportModel.setAssessment_discount(new BigDecimal(cell.getNumericCellValue()));
										 paStage.setDiscount(new BigDecimal(cell.getNumericCellValue()));
										 
									 }
									 
									 else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 50))
										 paExportModel.setWorkstream_prefix(new BigDecimal(cell.getNumericCellValue()));
									 
									 else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 57))
										 paExportModel.setHs2_acknowledged_date_time(
													cell.getDateCellValue() == null ? null : cell.getDateCellValue());
									 
									 else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 61)) 
										 paExportModel.setActual_start_date(
													cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										
									 
									 
									 else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 62)) 
										 paExportModel.setActual_end_date(
													cell.getDateCellValue() == null ? null : cell.getDateCellValue());
										 
										
									 
										else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 7)) {
											paExportModel.setWorks_location_description(
													String.valueOf( cell.getNumericCellValue()));
											
											paStage.setLocation(
													 String.valueOf( cell.getNumericCellValue()));
										}
									

									break;

								case Cell.CELL_TYPE_STRING:

									if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 0)) {
										paExportModel.setWork_reference_number(
												cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
										
										paStage.setWork_reference_number(cell.getStringCellValue() == null ? null
														: cell.getStringCellValue().trim());
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 1)) {
										paExportModel.setStreet_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										paStage.setStreet(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
									}

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 2))
										paExportModel.setArea_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 3))
										paExportModel.setUsrn(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim()));

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 4))
										paExportModel.setHighway_authority(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 5)) {
										paExportModel.setRoad_category(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
										 paStage.setReinstatement_category(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
									}
									

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 6))
										paExportModel.setWorks_location(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 7)) {
										paExportModel.setWorks_location_description(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										paStage.setLocation(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 8))
										paExportModel.setWorks_location_coordinates(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 9))
										paExportModel.setDesignations_applicable_to_works(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 10)) {
										paExportModel.setPromoter_organisation(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										paStage.setPromoter_name(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 11))
										paExportModel.setPromoter_contact_details(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									

									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 12))
										paExportModel.setApproved_contractor(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 13))
										paExportModel.setContractor_contact_details(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 14))
										paExportModel.setAdditional_contact(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 15))
										paExportModel.setAdditional_contact_number(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 16))
										paExportModel.setAdditional_contact_email(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 17)) {
										paExportModel.setProposed_start_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
										
										 paStage.setEastimated_start_date(cell.getStringCellValue() == null ? null
													: sdf1.parse(cell.getStringCellValue().trim()));		
										
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 18)) {
										paExportModel.setProposed_start_time(cell.getStringCellValue() == null ? sdf2.parse("00:00")
												: sdf2.parse(cell.getStringCellValue().trim()));
										
										paStage.setProposed_start_time(cell.getStringCellValue() == null ? sdf2.parse("00:00")
												: sdf2.parse(cell.getStringCellValue().trim()));
										
									}
									
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 19)) {
										paExportModel.setProposed_end_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
										
										paStage.setProposed_end_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 20)) {
										paExportModel.setProposed_end_time(cell.getStringCellValue() == null ? sdf2.parse("00:00")
												: sdf2.parse(cell.getStringCellValue().trim()));
										
										paStage.setProposed_end_time(cell.getStringCellValue() == null ? sdf2.parse("00:00")
												: sdf2.parse(cell.getStringCellValue().trim()));
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 21)) {
										paExportModel.setTotal_works_duration(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										paStage.setDuration_of_work(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 22)) {
										paExportModel.setActivity_type(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
										paStage.setActivity_type(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 23)) {
										paExportModel.setDescription_of_work(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
									 paStage.setWork_description(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());	
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 24)) {
										paExportModel.setIs_an_excavation_required(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
									 paStage.setWorks_with_excavation(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());	
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 25)) {
										paExportModel.setWorks_category(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										paStage.setType_of_work(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										
										
										
										
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 26))
										paExportModel.setProject_reference_number(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 27)) {
										paExportModel.setTraffic_management_type(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
									
										 paStage.setTraffic_management_method(cell.getStringCellValue() == null ? null
													: cell.getStringCellValue().trim());
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 28)) {
										paExportModel.setAssessment_status(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										paStage.setPermit_status(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 29)) {
										paExportModel.setIs_deemed(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										
										
										
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 30))
										paExportModel.setFootway_closure(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 31))
										paExportModel.setIs_lane_rental_applicable(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 32))
										paExportModel.setIs_traffic_management_plan_required(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 33))
										paExportModel.setIs_environmental_health_notifiable(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 34)) {
										paExportModel.setIs_there_collaborative_working(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
									     paStage.setCollaborative_working(cell.getStringCellValue() == null ? null
													: cell.getStringCellValue().trim());
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 35))
										paExportModel.setPermit_conditions(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 36))
										paExportModel.setWork_status(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 37)) {
										paExportModel.setDeadline_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
										
									   paStage.setPermit_deeming_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 38)) {
										paExportModel.setPermit_status(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 39))
										paExportModel.setReasons_for_refusal(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 40))
										paExportModel.setAssessment_comments(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 41))
										paExportModel.setStatus_changed_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 42))
										paExportModel.setLane_rental_assessment_outcome_id(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 43))
										paExportModel.setLane_rental_assessment_charges_agreed(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 44))
										paExportModel.setLane_rental_assessment_charge_band(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 45))
										paExportModel.setLane_rental_assessment_chargeable_days(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 46))
										paExportModel.setLane_rental_assessment_additional_details(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 47)) {
										paExportModel.setPermit_reference_number(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
										paStage.setPermit_reference_number(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 48)) {
										paExportModel.setAssessment_discount(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim().replaceAll("%", "")));
										
									    paStage.setDiscount(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim().replaceAll("%", "")));
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 49))
										paExportModel.setAssessment_discount_reason(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 50))
										paExportModel.setWorkstream_prefix(cell.getStringCellValue() == null ? null
												: new BigDecimal(cell.getStringCellValue().trim()));
									
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 51))
										paExportModel.setHs2_in_act_limits(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 52))
										paExportModel.setHs2_consultation_requested_response_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 53))
										paExportModel.setHs2_highway_exemption(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 54))
										paExportModel.setHs2_is_consent(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 55))
										paExportModel.setHs2_is_consultation(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 56))
										paExportModel.setHs2_acknowledged(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 57))
										paExportModel.setHs2_acknowledged_date_time(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 58))
										paExportModel.setEver_modification_requested(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 59))
										paExportModel.setModification_request_details(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 60))
										paExportModel.setIs_duration_challenged(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 61))
										paExportModel.setActual_start_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 62))
										paExportModel.setActual_end_date(cell.getStringCellValue() == null ? null
												: sdf.parse(cell.getStringCellValue().trim()));
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 63))
										paExportModel.setRevoke_reason(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 64))
										paExportModel.setReasonable_period_end_date(cell.getStringCellValue() == null ? null
												: sdf1.parse(cell.getStringCellValue().trim()));
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 65)) {
										paExportModel.setTown(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									     
										paStage.setTown(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
										
									}
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 66))
										paExportModel.setIs_covid_19_response(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 67))
										paExportModel.setHs2_permit_additional_usrns(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 68))
										paExportModel.setExcavation_carried_out(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 69))
										paExportModel.setLinked_section_81(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 70))
										paExportModel.setHas_attachments(cell.getStringCellValue() == null ? null
												: cell.getStringCellValue().trim());
									
									

									break;

								}

							}

							if (nextRow.getRowNum() >= 1) {

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(17))) {
									paExportModel.setProposed_start_date(
											nextRow.getCell(17) == null ? null : nextRow.getCell(17).getDateCellValue());
									
									 paStage.setEastimated_start_date(nextRow.getCell(17) == null ? null : nextRow.getCell(17).getDateCellValue());	
								}
								
								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(19))) {
									paExportModel.setProposed_end_date(
											nextRow.getCell(19) == null ? null : nextRow.getCell(19).getDateCellValue());
									
									 paStage.setProposed_end_date(
											 nextRow.getCell(19) == null ? null : nextRow.getCell(19).getDateCellValue());	
								}

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(37))) {
									paExportModel.setDeadline_date(
											nextRow.getCell(37) == null ? null : nextRow.getCell(37).getDateCellValue());
									
									paStage.setPermit_deeming_date(nextRow.getCell(37) == null ? null : nextRow.getCell(37).getDateCellValue());
								}

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(52)))
									paExportModel.setHs2_consultation_requested_response_date(
											nextRow.getCell(52) == null ? null : nextRow.getCell(52).getDateCellValue());

								if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(64)))
									paExportModel.setReasonable_period_end_date(
											nextRow.getCell(64) == null ? null : nextRow.getCell(64).getDateCellValue());
								
								
								
								
								
							

							}

						}

						if (nextRow.getRowNum() >= 1) {

							paExportModel.setCreated_at(currentDate);
							paExportModel.setCreated_by(Integer.parseInt(empID));
							paExportModel.setUpload_date(currentDate);
							paExportModel.setFile_name(fileUploadFileName);
							paExportModel.setOffice_code(Integer.parseInt(this.officeCode));
							int yearMonth = Integer.parseInt(yearCode + monthCode);
							paExportModel.setYear_month(yearMonth);
							paExportModelListlList.add(paExportModel);
							
							if(paExportModel.getProposed_start_time()==null||paStage.getProposed_start_time()==null)
							{
								paExportModel.setProposed_start_time(sdf2.parse("00:00"));
								paStage.setProposed_start_time(sdf2.parse("00:00"));
							}
							
							
							if(paExportModel.getProposed_end_time()==null||paStage.getProposed_end_time()==null)
							{
								paExportModel.setProposed_end_time(sdf2.parse("00:00"));
								paStage.setProposed_end_time(sdf2.parse("00:00"));
							}
							
							
							 if (paExportModel.getWorks_category().trim().contains("PAA")) {
								paStage.setApplication_type("PAA");
								}
								else  {
								paStage.setApplication_type("Permit");
								}
							 
							 
							 
							 if(paExportModel.getIs_deemed().trim().equalsIgnoreCase("Yes")) {
									
									paStage.setPermit_status("Deemed");
							}

							 else  if(paExportModel.getIs_deemed()==null || paExportModel.getIs_deemed().trim().length()==0) {
									
									if(paExportModel.getDeadline_date()!=null)
									{
									    if(paExportModel.getDeadline_date().compareTo(currentDate)<0)
									    	paStage.setPermit_status("Cancelled");
									    else  if(paExportModel.getDeadline_date().compareTo(currentDate)>=0)
									    	paStage.setPermit_status("Pending");
										
									}
									else 
										paStage.setPermit_status(paExportModel.getAssessment_status());
							}
							 
							 else
								 paStage.setPermit_status(paExportModel.getAssessment_status());
							
							 if(paStage.getPermit_status()==null) {
								 paStage.setPermit_status("Pending");
							 }
							 
							if(paStage.getApplication_type()!=null && paStage.getApplication_type().trim().length()>0)
							{
								if(paStage.getApplication_type().equalsIgnoreCase("Permit")) {
									
									System.out.println("AT PERMIT DATE");
									
									if(paExportModel.getWorks_category().contains("Minor")||
											paExportModel.getWorks_category().contains("Immediate")) {
										
										System.out.println("AT PERMIT DATE-1");
										
                                          if(paExportModel.getDeadline_date()!=null) {
                                        	  System.out.println("AT PERMIT DATE-1.1");
                                        	  paStage.setDate_of_permits(helper.dateMinus(paExportModel.getDeadline_date(),2));
                                          }
									}    
                                     else if(paExportModel.getWorks_category().contains("Major")||
      											paExportModel.getWorks_category().contains("Standard")) {
                                    	 System.out.println("AT PERMIT DATE-2");
                                              if(paExportModel.getDeadline_date()!=null) {
                                            	  System.out.println("AT PERMIT DATE-2.2");
                                            	  paStage.setDate_of_permits(helper.dateMinus(paExportModel.getDeadline_date(),5));
                                              }      
 
									}
								}
									

									System.out.println("AT PERMIT DATE "+paStage.getDate_of_permits());
								
							}
							
								else if(paStage.getApplication_type().equalsIgnoreCase("PAA")) {
									
									if(paExportModel.getWorks_category().equalsIgnoreCase("Major")) {
										
                                          if(paExportModel.getDeadline_date()!=null) {
                                        	  paStage.setDate_of_permits(helper.dateMinus(paExportModel.getDeadline_date(),28));
                                          }
                                      
									}
 
                                          
                                          if(paExportModel.getWorks_category().equalsIgnoreCase("Major (PAA)")) {
      										
                                              if(paExportModel.getDeadline_date()!=null) {
                                            	  paStage.setDate_of_permits(helper.dateMinus(paExportModel.getDeadline_date(),28));
                                              }
									}
								}
								
							
							if(paStage.getDate_of_permits()==null) {
								 paStage.setDate_of_permits(paExportModel.getDeadline_date());
							}
							
							
							paStage.setCreated_at(currentDate);
							paStage.setCreated_by(Integer.parseInt(empID));
							paStage.setUpload_date(currentDate);
							paStage.setFile_name(fileUploadFileName);
							paStage.setOffice_code(Integer.parseInt(this.officeCode));
							paStage.setYear_month(yearMonth);
							
							
							paStagelList.add(paStage);
							
						}

					}

					System.out.println("File Read Done");
					log.fatal("File Read Done");
					operationFlag = manager.uploadPAExportProvisional(paExportModelListlList,paStagelList ,uploadFileId, empID);
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
	public String exitFileUpload() throws Exception {
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
			}else if (!validator.requiredFiledValidate(yearCode.trim())) {
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