<%@page import="net.sf.jasperreports.engine.export.JRCsvExporter"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.export.JRHtmlExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRHtmlExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRCsvExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRCsvExporterParameter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 

try{
	
	if("Y".equalsIgnoreCase(request.getParameter("DOWNLOAD"))){

		
		JRCsvExporter csvExporter = new JRCsvExporter(); 
		out.clear();
	    csvExporter.setParameter(JRCsvExporterParameter.JASPER_PRINT,request.getAttribute("reportBody")); 
	    csvExporter.setParameter(JRCsvExporterParameter.OUTPUT_WRITER, out); 
	    response.setContentType("application/octet-stream"); 
	    response.setHeader("Content-Disposition", "attachment; filename="+request.getAttribute("reportName")+".pdf"); 
	    csvExporter.exportReport();
	
	}
	else{
		
		JRCsvExporter csvExporter = new JRCsvExporter(); 
		out.clear();
	    csvExporter.setParameter(JRCsvExporterParameter.JASPER_PRINT,request.getAttribute("reportBody")); 
	    csvExporter.setParameter(JRCsvExporterParameter.OUTPUT_WRITER, out); 
	    response.setContentType("application/octet-stream"); 
	    response.setHeader("Content-Disposition", "attachment; filename="+request.getAttribute("reportName")+".pdf"); 
	    csvExporter.exportReport();
		
		
}
}
catch(Exception e){
	System.out.println("Error");
	e.printStackTrace();
}

%>


