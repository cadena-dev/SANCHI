package com.saanchi.commomUtility;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.saanchi.hibernate.util.HibernateManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	

	private Map<String, Object> session = null;
	private static CommonHelper helper = new CommonHelper();
	private static final Logger log = Logger.getLogger(LoginAction.class);
	HibernateManager manager = new HibernateManager();
	public String home() {
		return SUCCESS;
	}

	// Log Out user
	public String logOut() {

		HttpServletResponse response = null;
		response = ServletActionContext.getResponse();
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Cache-Control", "no-store");

		session.remove("_USER_NAME");
		session.remove("_EMPCODE");
		session.remove("_LOCATIONID");
		session.remove("_USERGROUP");
		session.remove("DISPLAYNAME");
		session.clear();
		addActionMessage("You have been Successfully Logged Out");
		return SUCCESS;
	}

	public String login() throws Exception {
		String loginValue = LOGIN;
		

		try {


			
		
			 
			
			
			if (userName.isEmpty()) {
				this.addActionError("Username can't be blanked");
				loginValue = LOGIN;
			} else if (password.isEmpty()) {
				this.addActionError("Password can't be blanked");
				loginValue = LOGIN;

			}
			
			
			else {
				
				
				

				String returnMap[] = manager.cheekUserValidity(userName, password);

				if ((returnMap[0]).equals("99999XXXXXX")) {
					this.addActionError("Username  and Password mismatch!");
					loginValue = LOGIN;
				} else if (!returnMap[0].equals("99999XXXXXX")) {
				
						
						session.put("_USER_NAME", this.userName);
						session.put("_EMPCODE", returnMap[0]);
						session.put("_LOCATIONID", returnMap[1]);
						session.put("_USERGROUP", returnMap[2]);
						session.put("DISPLAYNAME", returnMap[3]);
					

						
					    
				     
				    
					 loginValue = SUCCESS;
					
				
					
					
				}

			}
		} catch (Exception e) {
			System.out.println("Exception in Login ....");
			e.printStackTrace();
			log.fatal("Exception", e);
			throw new Exception("System Error Occured");
		}
		

		return loginValue;

	}

	public String getUserName() {
		return userName;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setSession(Map<String, Object> map) {
		this.session = map;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}