<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <title>SaanchiWeb</title>
    <link href="../SaanchiWeb/loginCss/bootstrap.min.css" rel="stylesheet">
    <link href="../SaanchiWeb/loginCss/signin.css" rel="stylesheet">

  </head>
  <body class="text-center" style="background-image: url(../SaanchiWeb/image/bg.jpg);" oncontextmenu="return false;">
   <s:form action="LoginAction" class="form-1" autocomplete="off" >
      <img class="mb-4" src="../SaanchiWeb/image/SmallLogo.png" alt="" >
  
       <div class="form-group form-default">
       <s:actionerror theme="saanchi" cssClass="errorMsg" />
		<s:actionmessage theme="saanchi" cssClass="infoMsg" />
       </div>
      <div class="form-group form-default">
      	<s:textfield name="userName" placeholder="Username or email" cssClass="form-control" required="true" autofocus="true"/>
    </div>
    <br>
    <div class="form-group form-default">
      <s:password name="password" placeholder="Password" cssClass="form-control" required="true" autofocus="true"/>
     </div>
     
     
     <br>
    
     <div class="form-group form-default">
         <s:submit value="Login" cssClass="btn waves-effect waves-light btn-info"/>
    </div>
    
    
        
  </s:form>
  </body>
</html>