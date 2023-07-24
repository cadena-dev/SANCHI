<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SaanchiWeb</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="" />
<!-- Favicon icon -->
<link rel="icon" href="image/saanchi-logo.ico" type="image/x-icon">
<!-- Google font-->
<link href="https://fonts.googleapis.com/css?family=Roboto:400,500"
	rel="stylesheet">
<!-- waves.css -->
<link rel="stylesheet" type="text/css" media="all"
	href="<s:url value="/assets/pages/waves/css/waves.min.css"/>">
<!-- Required Fremwork -->
<link rel="stylesheet" type="text/css"
	href="<s:url value="/assets/css/bootstrap/css/bootstrap.min.css"/>">
<!-- waves.css -->
<link rel="stylesheet" type="text/css" media="all"
	href="<s:url value="/assets/pages/waves/css/waves.min.css"/>">
<!-- themify icon -->
<link rel="stylesheet" type="text/css"
	href="<s:url value="/assets/icon/themify-icons/themify-icons.css"/>">
<!-- Font Awesome -->
<link rel="stylesheet" type="text/css"
	href="<s:url value="/assets/icon/font-awesome/css/font-awesome.min.css"/>">
<!-- scrollbar.css -->
<link rel="stylesheet" type="text/css"
	href="<s:url value="/assets/css/jquery.mCustomScrollbar.css"/>">
<!-- am chart export.css -->
<link rel="stylesheet" type="text/css" media="all"
	href="<s:url value="https://www.amcharts.com/lib/3/plugins/export/export.css" />">
	
		
<!-- Style.css -->
<link rel="stylesheet" type="text/css"
	href="<s:url value="/assets/css/style.css"/>">
<!-- Login Style.css -->
<link rel="stylesheet"
	href="<s:url value="/assets/fonts/icomoon/style.css"/>">
<link rel="stylesheet" href="<s:url value="/assets/css/jquery-ui.css"/>">
<link rel="stylesheet"
	href="<s:url value="/assets/css/jquery.datepick.css"/>">
<link rel="stylesheet"
	href="<s:url value="/assets/css/themes-jquery-ui.css"/>">
	
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/menu.css"/>">
<link rel="stylesheet" type="text/css" href="<s:url value="/css/ams.css"/>">
<script language="JavaScript" src="<s:url value="/js/jquery-1.11.1.min.js"/>"></script>
<script language="JavaScript" src="<s:url value="/js/jquery-ui.min.js"/>"></script>
<script language="JavaScript" src="<s:url value="/js/menu.js"/>"></script>
<link href="addons/datatables/media/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
<script src="addons/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<link href="addons/datatables/TableTools/css/dataTables.tableTools.min.css" rel="stylesheet" type="text/css" />
<script src="addons/datatables/TableTools/js/dataTables.tableTools.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/jquery.datepick.css"/>">
<script language="JavaScript1.2" src="<s:url value="/js/jquery.datepick.js"/>"></script>	


<body>


<%
	
		String dispName = (String) request.getSession().getAttribute(
				"DISPLAYNAME");
		
		
		String userRole=(String) request.getSession().getAttribute(
				"_USERGROUP");
		
				
		if((dispName == null)||(dispName.trim().length() ==0)) 
			response.sendRedirect("/commonJsp/login.jsp");
	%>


						<div class="userIntro">
						
						
						<div align="center">
						
						<a  href="#"><B>SaanchiWeb: Version: UAT-3.006; Release Date:14/07/2023</B></a>
						
						</div>
						<br>
					<div class="header_right">
						<div id="user_nav">
							<ul>
								<li class="user_info">
								<ul class="navigation">
								
								     <a  href="#"><B>Welcome : <% out.print(dispName); %></B></a>
								     	&nbsp&nbsp
									<a href="ChangePassword.action">Change Password</a>
									&nbsp&nbsp<a  href="'logOut';"><B>(Logout)</B></a>
								</ul>                   
								</li>
							</ul>
						</div>
					</div>
					
								
					</div>			
<br><br>
		
			<div align="left">
			
			
				<%
					if(userRole.equals("1")) {
				%>			
						<%@include file="menu_supper_admin.jsp" %>
						
				<%
					}else if(userRole.equals("2")) {%>	
					
					<%@include file="menu_admin.jsp" %>
					
				<%
					}else if(userRole.equals("3")) {%>	
					
						<%@include file="menu_user.jsp" %>
					<%}	%>	
			</div>
		<br>	<br>
		</body>
		</html>
		
											
									