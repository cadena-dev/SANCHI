package com.saanchi.report;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

//import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class PDFReportAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** List to use as our JasperReports dataSource. */
	private HttpServletRequest request;
	private HttpServletResponse response;

	private static final Logger log = Logger.getLogger(PDFReportAction.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");
	String reportDownloadName = "Test.pdf";
	Calendar c = Calendar.getInstance();

	public String execute() throws Exception {

		Connection con = null;
		String printFileName = "";
		JasperPrint jasperPrint = null;
		String repPath = request.getParameter("reportPath") == null ? "" : request.getParameter("reportPath");
		String reportName = request.getParameter("reportName") == null ? "" : request.getParameter("reportName");
		String officeCode = request.getParameter("officeCode") == null ? "" : request.getParameter("officeCode");
		String yearCode = request.getParameter("yearCode") == null ? "" : request.getParameter("yearCode");

		String monthCode = request.getParameter("monthCode") == null ? "" : request.getParameter("monthCode");
		String reportType = request.getParameter("reportType") == null ? "" : request.getParameter("reportType");
		
		
		
		
		
		if((request.getParameter("DOWNLOAD").equals("N"))&&(reportType.trim().length()>0)) {
			
			if(reportType.equalsIgnoreCase("SAANCHI_1A.jasper"))
				repPath ="/report/SAANCHI_1A.jasper";
			
			else if(reportType.equalsIgnoreCase("SAANCHI_1B.jasper"))
				repPath ="/report/SAANCHI_1B.jasper";
			
			else if(reportType.equalsIgnoreCase("SAANCHI_1C.jasper"))
				repPath ="/report/SAANCHI_1C.jasper";
			
			else if(reportType.equalsIgnoreCase("SAANCHI_1D.jasper"))
				repPath ="/report/SAANCHI_1D.jasper";
			
			else if(reportType.equalsIgnoreCase("SAANCHI_2A.jasper"))
				repPath ="/report/SAANCHI_2A.jasper";
			
			else if(reportType.equalsIgnoreCase("SAANCHI_2B.jasper"))
				repPath ="/report/SAANCHI_2B.jasper";
			
			else if(reportType.equalsIgnoreCase("SAANCHI_2C.jasper"))
				repPath ="/report/SAANCHI_2C.jasper";
			
			else if(reportType.equalsIgnoreCase("SAANCHI_2D.jasper"))
				repPath ="/report/SAANCHI_2D.jasper";
			
			
			
			else if(reportType.equalsIgnoreCase("SAANCHI_3A.jasper"))
				repPath ="/report/SAANCHI_3A.jasper";
			
			else if(reportType.equalsIgnoreCase("SAANCHI_3B.jasper"))
				repPath ="/report/SAANCHI_3B.jasper";
			
			
			else if(reportType.equalsIgnoreCase("SAANCHI_4A.jasper"))
				repPath ="/report/SAANCHI_4A.jasper";
			
			else if(reportType.equalsIgnoreCase("SAANCHI_4B.jasper"))
				repPath ="/report/SAANCHI_4B.jasper";
			
			
			else if(reportType.equalsIgnoreCase("SAANCHI_4C.jasper"))
				repPath ="/report/SAANCHI_4C.jasper";
			
			else if(reportType.equalsIgnoreCase("SAANCHI_4D.jasper"))
				repPath ="/report/SAANCHI_4D.jasper";
			
			else if(reportType.equalsIgnoreCase("SAANCHI_MASTER_REPORT.jasper"))
				repPath ="/report/SAANCHI_MASTER_REPORT.jasper";
		}
		
		
if((request.getParameter("DOWNLOAD").equals("N"))&&(reportType.trim().length()==0)) {
			
		
				repPath ="/report/SAANCHI_1A.jasper";
		}
		
		

		File reportFile = new File(request.getSession(false).getServletContext().getRealPath(repPath));

		HashMap reportParams = new HashMap();
		reportParams.put("SUBREPORT_DIR", reportFile.getParentFile().getAbsolutePath());
		reportParams.put("officeCode", officeCode.trim());
		reportParams.put("yearMonth", (yearCode + monthCode));

		System.out.println(repPath + " ," + reportName + "," + officeCode + ", " + yearCode + ", " + monthCode + ", "
				+ (yearCode + monthCode));

		reportDownloadName = "MIS.pdf";

		if (!reportFile.exists())
			throw new JRRuntimeException(
					"File WebappReport.jasper not found. The report design must be compiled first.");
		con = Datasource.getDataSource();
		JasperReport jasperReport = null;
		try {
			jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
		} catch (JRException e) {
			System.out.println("I Am HERE 1");
			e.printStackTrace();
			throw new RuntimeException("Exception while loading the report", e);
		}
		try {

			System.out.println("jasperReport " + jasperReport);
			System.out.println("reportParams " + reportParams);
			System.out.println("con " + con);

			if (request.getParameter("DOWNLOAD").equals("Y")) {
				System.out.println("Starting.....");
				byte[] bytes = null;
				bytes = JasperRunManager.runReportToPdf(jasperReport, reportParams, con);
				response.reset();
				response.resetBuffer();
				response.addHeader("Content-Disposition", "inline; filename=" + reportDownloadName);
				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
				ouputStream.close();

				System.out.println("Report Name " + request.getSession().getAttribute("reportName"));
				// request.setAttribute("reportBody", jasperPrint);
				System.out.println("Ending.....");
			}

			else if (request.getParameter("DOWNLOAD").equals("N")) {
				System.out.println("Starting.....");
				byte[] bytes = null;
				bytes = JasperRunManager.runReportToPdf(jasperReport, reportParams, con);
				response.reset();
				response.resetBuffer();
				response.addHeader("Content-Disposition", "inline; filename=" + reportDownloadName);
				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
				ouputStream.close();

				System.out.println("Report Name " + request.getSession().getAttribute("reportName"));
				// request.setAttribute("reportBody", jasperPrint);
				System.out.println("Ending.....");

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			log.fatal("Exception---", ex);

		} finally {
			if (null != con)
				try {
					con.close();
				} catch (Exception e) {
					throw new RuntimeException("Exception while closing Connection ", e);
				}
		}

		return SUCCESS;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;

	}

	public String exitReport() throws Exception {
		return "home";
	}

}
