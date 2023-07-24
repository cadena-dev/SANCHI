package com.saanchi.commomUtility;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;  
import com.opensymphony.xwork2.Action;  
import com.opensymphony.xwork2.ActionContext;  
import com.opensymphony.xwork2.ActionInvocation;  
import com.opensymphony.xwork2.interceptor.Interceptor;  

public class SaanchiFilter implements Interceptor{  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<?> grpEventMap;
    public void destroy() {  
      
    }  
    public void init() {  
        
    }  

       
    @SuppressWarnings("deprecation")
	public String intercept(ActionInvocation invocation) throws Exception {  
        
        ActionContext context = invocation.getInvocationContext();  
        HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);  
        String path = request.getRequestURI();  
        HttpServletResponse response =(HttpServletResponse)context.get(ServletActionContext.HTTP_RESPONSE);
     
        
    	String userGroup =(String)request.getSession().getAttribute("_USERGROUP");
    	String empID = (String) request.getSession().getAttribute("_EMPCODE");
		String branchCode = (String) request.getSession().getAttribute("_LOCATIONID");
		
		
  
    	Map<?, ?> application = invocation.getInvocationContext().getApplication();
    	grpEventMap=  (List<?>)application.get("USERLOV");  
    	
    	String userip = request.getRemoteAddr();
    	
    	HttpServletResponse httpResponse = null;
    	  if (response instanceof HttpServletResponse){
    	  httpResponse = (HttpServletResponse) response;
    	  }
    	 
    	  if ("127.0.0.2".equals(userip)) {
    		  httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN,"You are 	  not allowed to access the servlet!");
    	  return Action.ERROR;  
    	  }
    	
    	  else{
        if((userGroup== null)||(empID==null)||(branchCode==null)){  
        	 request.setAttribute("error", "Session Invalid. Try to Login again");  
        	 return Action.ERROR;  
        }else{  
            StringBuffer groups=extractPermissibleGroups(grpEventMap,extractEventCodeFromUrl(path));
       
            
            
            if(groups.indexOf(userGroup)<0){  
                    request.setAttribute("error", "You are not authorized to do this operation");  
                    return Action.ERROR;  
            }  
            else
            {
            	
            	 
            	request.getSession().putValue("_USERGROUP", userGroup);
            	request.getSession().putValue("_EMPCODE", empID);
            	request.getSession().putValue("_LOCATIONID", branchCode);
            }
            
            
        }  
            
            
            
        }
        
        return invocation.invoke();  
    }
	private String extractEventCodeFromUrl(String url) {
		int beginIndex = url.indexOf('/',1);
		StringBuffer eventCode = new StringBuffer(url.substring(beginIndex+1,url.length()-7));
		eventCode.append(".action");
		return eventCode.toString();
	}
	
	private StringBuffer extractPermissibleGroups(List<?> grpEvent,String actualPath)
	{
		StringBuffer permissibleGroups=new StringBuffer();
		ListIterator<?> listItr = grpEvent.listIterator();
		while (listItr.hasNext()) {
		CommonOptionsVO optionVO = (CommonOptionsVO) listItr.next();
		if(optionVO.getTypeDesc().equals(actualPath))
			permissibleGroups.append(optionVO.getTypeCode());
		}
		return permissibleGroups;
	}
	
}  
