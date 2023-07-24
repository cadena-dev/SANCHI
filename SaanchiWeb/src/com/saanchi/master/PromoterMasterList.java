package com.saanchi.master;

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
import com.saanchi.commomUtility.CommonOptionsNineVO;
import com.saanchi.commomUtility.CommonOptionsVO;
import com.saanchi.commomUtility.CommonValidation;
import com.saanchi.hibernate.model.ChangeRequestProvisional;
import com.saanchi.hibernate.model.ChangeRequestStage;
import com.saanchi.hibernate.model.FPNExportProvisional;
import com.saanchi.hibernate.model.FPNExportStage;
import com.saanchi.hibernate.model.FeeExportProvisional;
import com.saanchi.hibernate.model.FileUploadDetail;
import com.saanchi.hibernate.model.OfficeCreateModel;
import com.saanchi.hibernate.model.PAExportProvisional;
import com.saanchi.hibernate.model.PAExportStage;
import com.saanchi.hibernate.model.PromoterMasterModel;
import com.saanchi.hibernate.util.HibernateManager;
import com.saanchi.optionVO.ChangeRequestVO;
import com.saanchi.optionVO.FPNValidationVO;
import com.saanchi.optionVO.PAValidateVO;

public class PromoterMasterList extends ActionSupport implements ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String empID;

	private ServletContext context;
	private HttpServletRequest request;

	
	List<PromoterMasterModel> promoterMasterList = new ArrayList<PromoterMasterModel>();
	
	private static final Logger log = Logger.getLogger(PromoterMasterList.class);
	HibernateManager manager = new HibernateManager();
	CommonHelper helper = new CommonHelper();
	private static CommonValidation validator = new CommonValidation();

	public void setServletContext(ServletContext arg0) {
		this.context = arg0;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	



	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	

	

	public List<PromoterMasterModel> getPromoterMasterList() {
		return promoterMasterList;
	}

	public void setPromoterMasterList(List<PromoterMasterModel> promoterMasterList) {
		this.promoterMasterList = promoterMasterList;
	}

	@SkipValidation
	public String execute() throws Exception {

		empID = (String) request.getSession().getAttribute("_EMPCODE");

		int returnMessage = 1;
		String returnValue = SUCCESS;
		String returnText = "System Error Occured.";

		try {
			

			promoterMasterList = new ArrayList<PromoterMasterModel>();
			

			promoterMasterList = manager.searchPromoterMaster(promoterMasterList);
			
			log.fatal("Office List Size "+promoterMasterList.size());

			if (promoterMasterList.size() > 0) {

				returnMessage = 1;
				
			}

			else {
				returnMessage = 0;
				returnText = "No Data Found.";
			}

		} catch (Exception e) {
			returnMessage = 0;
			log.fatal(helper.writeLogInUpperCase(e));

		}

		if ((empID == null) || (empID.length() == 0)) {
			throw new Exception(getText("Invalid Session. Please Login"));
		}

		if (returnMessage == 1) {
			returnValue = SUCCESS;
			
		}

		else if (returnMessage == 0) {
			this.addActionError(returnText);
			return "home";
		}
		return returnValue;
	}

	@SkipValidation
	public String exitPromoterMasterList() throws Exception {
		return "home";
	}


}