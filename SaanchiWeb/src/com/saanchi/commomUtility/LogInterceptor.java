package com.saanchi.commomUtility;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LogInterceptor  implements Interceptor {


	private static final long serialVersionUID = 1L;
	

		@Override
        public String intercept(ActionInvocation invocation) throws Exception {
                
			Map<String, Object> sessionAttributes = invocation.getInvocationContext().getSession();
			  ActionContext context = invocation.getInvocationContext();  
			  HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);  
			  HttpServletResponse response =(HttpServletResponse)context.get(ServletActionContext.HTTP_RESPONSE);
			      
			  
			  
			  
			  String userip = request.getRemoteAddr();
		    	System.out.println("userip" + userip);
			  
			  
		    	HttpServletResponse httpResponse = null;
		    	  if (response instanceof HttpServletResponse){
		    	  httpResponse = (HttpServletResponse) response;
		    	  }
		    	 
		    	  if ("127.0.0.2".equals(userip)) {
		    		  httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN,"You are not allowed to access the servlet!");
		    	  return Action.ERROR;  
		    	  }
		    	
		    	  else{  
			    String empID = (String) sessionAttributes.get("_EMPCODE");
               String branchCode =(String) sessionAttributes.get("_LOCATIONID");
               String groupID =(String) sessionAttributes.get("_USERGROUP");
               String displayName =(String) sessionAttributes.get("DISPLAYNAME");
           
                
       
           
         
                if ((empID == null)||(branchCode == null)||(groupID == null)||(displayName == null))
                {
                        return Action.LOGIN;
                } 
                else 
                {
                	    return invocation.invoke();
                }
                
                
		    	  }
        }

		@Override
		public void destroy() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void init() {
			// TODO Auto-generated method stub
			
		}
}