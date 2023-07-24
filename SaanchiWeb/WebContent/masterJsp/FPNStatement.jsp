<%@include file="../commonJsp/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<s:head theme="saanchi" />
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<sx:head />
</head>
<body oncontextmenu="return false;">





	
	
	
		<div class="row">
	<div class="col-sm-12 col-md-12">
		<div class="card">
			<div class="card-header" align="center">
				<h5>FPN Statement Generation</h5>
					
			</div>
			<div class="card-block">
			
	<s:form action="FPNStatementGeneration" validate="true" namespace="/" method="post">

	     
	     

                 	<div class="col-md-12">
						<div class="row">
							<s:actionerror theme="saanchi" cssClass="form-control form-bg-danger" />
							<s:actionmessage theme="saanchi" cssClass="form-control form-bg-info" />
						</div>
					</div>
					
					
               
       
       
<div class="container border border-primary">
       
       <div class="form-group row">
       
       <div class="col-6">

							<s:select headerKey=""
								headerValue="Highways Authority"
								list="officeList" listKey="typeCodeDesc"
								listValue="typeDesc" name="office_location" id="office_location"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="office_location" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>
					
                 

						
						<div class="col-6">

							<s:select headerKey=""
								headerValue="Year"
								list="yearList" listKey="typeCode"
								listValue="typeDesc" name="yearCode" id="yearCode"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="yearCode" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>
						
						
						<div class="col-6">

							<s:select headerKey="" headerValue="Month"
								list="monthList" listKey="typeCode"
								listValue="typeDesc" name="monthCode" id="monthCode"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="monthCode" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>
                  </div>
                  
                  

       
       
       

       
      
			
<div class="form-group row">
						<div class="col-md-12 text-center">
							<s:submit method="generateFPNFile" class="btn waves-effect waves-light btn-info" value="Generate" theme="simple" />
							<s:if test="fileName.length() > 0">
         <a
						href="servlet/DownloadServlet?filePath=<s:property value="fileName"/>"><font
						color="BLUE"><b>Download zip</b></font></a>
				</s:if>
							<input type="button" name="Exit" value="Exit" class="btn waves-effect waves-light btn-info" onClick="window.location.href='exitFPNStatement';">
						</div>
					</div>
					
					</div>

	</s:form>
	</div>
	</div>
	</div>
	</div>
	
	
	
	<s:hidden name="empID" id="empID" />
</body>
<script type="text/javascript">
window.history.forward(0);
</script>
</html>
