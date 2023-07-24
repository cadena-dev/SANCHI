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
import com.saanchi.hibernate.model.FPNExportValidated;
import com.saanchi.hibernate.model.FPNExport_Infringements_Validated;
import com.saanchi.hibernate.model.FeeExportProvisional;
import com.saanchi.hibernate.model.FeeExportStage;
import com.saanchi.hibernate.model.FileUploadDetail;
import com.saanchi.hibernate.model.InfringementsModel;
import com.saanchi.hibernate.model.PAExportProvisional;
import com.saanchi.hibernate.model.PAExportStage;
import com.saanchi.hibernate.util.HibernateManager;

public class infringementsUpload extends ActionSupport implements ServletContextAware, ServletRequestAware {

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

	private static final Logger log = Logger.getLogger(infringementsUpload.class);
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

			CommonOptionsVO optionVO = new CommonOptionsVO("Immediate", "Immediate File");
			fileTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("Start", "Start File");
			fileTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("Stop", "Stop File");
			fileTypeList.add(optionVO);
			optionVO = new CommonOptionsVO("Registration", "Registration File");
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

	public String uploadInfringementsFile() throws Exception {

		FileInputStream fis = new FileInputStream(fileUpload);
		List<InfringementsModel> infringementModelListlList = new ArrayList<InfringementsModel>();
		List<FPNExport_Infringements_Validated> fpnExportValidatedModelList = new ArrayList<FPNExport_Infringements_Validated>();

		boolean operationFlag = false;
		int uploadFileId = 0;
		try {

			int fileID =manager.getFileUploadID(fileUploadFileName);
			Date currentDate = new Date();

			if (fileID == 0) {

				uploadFileId = manager.uploadFile(fileUploadFileName, this.getFileType());
				log.fatal("File Name= " + fileUploadFileName);

				String officeCodeArray[]=officeCode.split("~");
				
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet mySheet = workbook.getSheetAt(0);
				Iterator<Row> iterator = mySheet.iterator();

				InfringementsModel infringementModel;
				Calendar c = Calendar.getInstance();
				while (iterator.hasNext()) {
					Row nextRow = iterator.next();
					Iterator<Cell> cellIterator = nextRow.cellIterator();
					infringementModel = new InfringementsModel();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						if (nextRow.getRowNum() >= 1) {

							switch (cell.getCellType()) {

							case Cell.CELL_TYPE_NUMERIC:

								if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 2)) {
									infringementModel.setStop_notice_recorded_time(
											cell.getDateCellValue() == null ? sdf2.parse("00:00")
													: cell.getDateCellValue());

								}

								else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 4)) {
									infringementModel.setActual_completed_time(
											cell.getDateCellValue() == null ? sdf2.parse("00:00")
													: cell.getDateCellValue());

								}

								break;

							case Cell.CELL_TYPE_STRING:

								if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 0)) {
									infringementModel.setWork_reference_number(cell.getStringCellValue() == null ? null
											: cell.getStringCellValue().trim());

								}

								else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 1)) {

									infringementModel
											.setStop_notice_recorded_date(cell.getStringCellValue() == null ? null
													: sdf1.parse(cell.getStringCellValue().trim()));

								}

								else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 2)) {
									infringementModel.setStop_notice_recorded_time(
											cell.getStringCellValue() == null ? sdf2.parse("00:00")
													: sdf2.parse(cell.getStringCellValue().trim()));

								}

								else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 3)) {

									infringementModel.setActual_completed_date(cell.getStringCellValue() == null ? null
											: sdf1.parse(cell.getStringCellValue().trim()));

								}

								else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 4)) {
									infringementModel.setActual_completed_time(
											cell.getStringCellValue() == null ? sdf2.parse("00:00")
													: sdf2.parse(cell.getStringCellValue().trim()));

								}
								
								
								else if ((nextRow.getRowNum() >= 1) && (cell.getColumnIndex() == 5)) {
									infringementModel.setPromoter_name(cell.getStringCellValue() == null ? null
											: cell.getStringCellValue().trim());
							
								}


								break;

							}

						}

						if (nextRow.getRowNum() >= 1) {

							

							if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(1))) {

								

								infringementModel.setStop_notice_recorded_date(
										nextRow.getCell(1) == null ? null : nextRow.getCell(1).getDateCellValue());

							}

							 if (HSSFDateUtil.isCellDateFormatted(nextRow.getCell(3))) {

								
								infringementModel.setActual_completed_date(
										nextRow.getCell(3) == null ? null : nextRow.getCell(3).getDateCellValue());

							}

						}

					}

					if (nextRow.getRowNum() >= 1) {
						infringementModel.setCreated_at(currentDate);
						infringementModel.setCreated_by(Integer.parseInt(empID));
						infringementModel.setUpload_date(currentDate);
						infringementModel.setFile_name(fileUploadFileName);
						infringementModel.setOffice_code(Integer.parseInt(officeCodeArray[0].trim()));
						int yearMonth = Integer.parseInt(yearCode + monthCode);
						infringementModel.setYear_month(yearMonth);
						infringementModel.setNotice_type(fileType);
						
						c.setTime(infringementModel.getActual_completed_date());
						int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
						
					
						  if(dayOfWeek==1 ||dayOfWeek==7 )
						  infringementModel.setWeekend_flag(true);
						 
						if(manager.searchHolidayDate(infringementModel.getActual_completed_date())>0)
							infringementModel.setHoliday_flag(true);
						
						String fpnStatus=manager.calculateFPN(infringementModel.getActual_completed_date(),
								infringementModel.getStop_notice_recorded_date(),
								infringementModel.getActual_completed_time(),
								infringementModel.getStop_notice_recorded_time(),
								infringementModel.isWeekend_flag(),
								infringementModel.isHoliday_flag(),fileType,infringementModel.getWork_reference_number());
						
						String fpnStatusArray[]=fpnStatus.split("~");
								
						infringementModel.setFpn_comments(fpnStatusArray[0]);	
						infringementModel.setDate_difference(Integer.parseInt(fpnStatusArray[1]));
						infringementModel.setTime_differ(sdf2.parse(fpnStatusArray[2]));
						
						if(fileType.equalsIgnoreCase("Registration"))
						infringementModel.setOffence_code("S70(6)");
						else if(!fileType.equalsIgnoreCase("Registration"))
						infringementModel.setOffence_code("S74(7B)");
						
						
						if(!fpnStatusArray[0].equalsIgnoreCase("No FPN.")) {
							
							
							FPNExport_Infringements_Validated insertValidatedModel = new FPNExport_Infringements_Validated();
							
							

							insertValidatedModel.setWorks_reference_number(infringementModel.getWork_reference_number());
							insertValidatedModel.setFpn_status("Issued");
							insertValidatedModel.setOffence_code(infringementModel.getOffence_code());
							insertValidatedModel.setOffence_date(infringementModel.getActual_completed_date());
							insertValidatedModel.setOffence_details(fpnStatusArray[0]);
							insertValidatedModel.setWorks_promoter(infringementModel.getPromoter_name());
							insertValidatedModel.setIssuing_authority(officeCodeArray[1]);
							insertValidatedModel.setOffice_code(Integer.parseInt(officeCodeArray[0].trim()));
							insertValidatedModel.setYear_month(yearMonth);
							insertValidatedModel.setStatus("INFRINGEMENTS");
							insertValidatedModel.setUpload_date(currentDate);
							insertValidatedModel.setCreated_by(Integer.parseInt(empID));
							insertValidatedModel.setFile_name(fileUploadFileName);
							insertValidatedModel.setCreated_at(currentDate);
							insertValidatedModel.setFile_type(fileType);
							
							fpnExportValidatedModelList.add(insertValidatedModel);
							
						}

						infringementModelListlList.add(infringementModel);

					}

				}

				System.out.println("File Read Done");
				log.fatal("File Read Done");
				operationFlag = manager.uploadInfringementsList(infringementModelListlList, uploadFileId, empID,fpnExportValidatedModelList);
				operationFlag = true;

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
	public String exitInfringementsUpload() throws Exception {
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