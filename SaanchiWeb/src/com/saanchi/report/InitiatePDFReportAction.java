package com.saanchi.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.saanchi.commomUtility.CommonOptionsVO;
import com.saanchi.hibernate.util.HibernateManager;

public class InitiatePDFReportAction extends ActionSupport implements ServletRequestAware, ServletContextAware {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(InitiatePDFReportAction.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	
	private String empCode="";
	private String userRole="";
	
	private String heading;
	private String reportPath;
	private String reportName;
	private String event;
	private ServletContext context;
	private String officeCode;
	private String yearCode;
	private String monthCode;
	private String reportType;
	List<CommonOptionsVO> officeList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> yearList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> monthList = new ArrayList<CommonOptionsVO>();
	List<CommonOptionsVO> reportTypeList = new ArrayList<CommonOptionsVO>();

	private HttpServletRequest request;
	HibernateManager manager = new HibernateManager();
	public void setServletContext(ServletContext arg0) {
		this.context = arg0;

	}

	

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getOfficeCode() {
		return officeCode;
	}



	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
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



	public List<CommonOptionsVO> getOfficeList() {
		return officeList;
	}



	public void setOfficeList(List<CommonOptionsVO> officeList) {
		this.officeList = officeList;
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



	public static Logger getLog() {
		return log;
	}

	public String getReportType() {
		return reportType;
	}



	public void setReportType(String reportType) {
		this.reportType = reportType;
	}



	public List<CommonOptionsVO> getReportTypeList() {
		return reportTypeList;
	}



	public void setReportTypeList(List<CommonOptionsVO> reportTypeList) {
		this.reportTypeList = reportTypeList;
	}



	@SkipValidation
	public String execute() throws Exception {
		
		 empCode = (String) request.getSession().getAttribute(
				"_EMPCODE");

		userRole=(String) request.getSession().getAttribute(
				"USER_ROLE");
		
		

		officeList = new ArrayList<CommonOptionsVO>();
		officeList = manager.searchOfficeList();
		yearList = new ArrayList<CommonOptionsVO>();

		int year = Calendar.getInstance().get(Calendar.YEAR)-1;
		CommonOptionsVO optionVO	= new CommonOptionsVO(String.valueOf(year), String.valueOf(year));
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
		

		reportTypeList = new ArrayList<CommonOptionsVO>();
		optionVO = new CommonOptionsVO("SAANCHI_1A.jasper", "1a. Summary view");
		reportTypeList.add(optionVO);
		optionVO = new CommonOptionsVO("SAANCHI_1B.jasper", "1b. Categorisation by Work Type");
		reportTypeList.add(optionVO);
		optionVO = new CommonOptionsVO("SAANCHI_1C.jasper", "1c. Categorisation by Reinstatement Category");
		reportTypeList.add(optionVO);
		optionVO = new CommonOptionsVO("SAANCHI_1D.jasper", "1d. Application processing by Teams");
		reportTypeList.add(optionVO);
		
		
		
		optionVO = new CommonOptionsVO("SAANCHI_2A.jasper", "2a. Charging Summary and Invoicing Details");
		reportTypeList.add(optionVO);
		optionVO = new CommonOptionsVO("SAANCHI_2B.jasper", "2b. Invoicing Trend and Tracker");
		reportTypeList.add(optionVO);
		optionVO = new CommonOptionsVO("SAANCHI_2C.jasper", "2c. Projected Fee Income (average) vs Actual – view 1");
		reportTypeList.add(optionVO);
		optionVO = new CommonOptionsVO("SAANCHI_2D.jasper", "2c. Cumulative Projected Fee Income (average) vs Actual – view 2");
		reportTypeList.add(optionVO);
		
		
		optionVO = new CommonOptionsVO("SAANCHI_3A.jasper", "3a. Fixed Penalty Notices(FPN) Summary");
		reportTypeList.add(optionVO);
		optionVO = new CommonOptionsVO("SAANCHI_3B.jasper", "3b. Compliance by Work Promoter");
		reportTypeList.add(optionVO);
		
		
		optionVO = new CommonOptionsVO("SAANCHI_4A.jasper", "4a. KPI1-Permit Application Status");
		reportTypeList.add(optionVO);
		optionVO = new CommonOptionsVO("SAANCHI_4B.jasper", "4b. KPI2-Conditions Applied");
		reportTypeList.add(optionVO);
		
		
		optionVO = new CommonOptionsVO("SAANCHI_4C.jasper", "4c. KPI3-Revised Durations");
		reportTypeList.add(optionVO);
		optionVO = new CommonOptionsVO("SAANCHI_4D.jasper", "4d. KPI4-Early Starts");
		reportTypeList.add(optionVO);
		
		
		optionVO = new CommonOptionsVO("SAANCHI_MASTER_REPORT.jasper", "All");
		reportTypeList.add(optionVO);
		
		
		
		System.out.println("I am in PDF Report");
		return SUCCESS;
	}

	@SkipValidation
	public String exitPDFReport() throws Exception {
		return "home";
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

}