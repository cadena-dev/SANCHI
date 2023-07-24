package com.saanchi.commomUtility;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;


 public class SaanchiStartupServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet{

	 private static final long serialVersionUID = 1L;
	 private static final Logger log = Logger.getLogger(SaanchiStartupServlet.class);
	public SaanchiStartupServlet() {
		super();
	}   	 	  	  	  
	
		public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext servletContext = config.getServletContext(); 
		
		
		try {
			UtilityServices services = new UtilityServices();
			servletContext.setAttribute("USERLOV",services.getUserLOV());
	  	}
			
			
			catch (Exception e) {
				log.fatal("Exception---",e);
		}
	}   
}