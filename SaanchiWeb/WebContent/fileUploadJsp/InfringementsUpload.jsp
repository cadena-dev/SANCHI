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
				<h5>Infringements Data import</h5>
			</div>
			<div class="card-block">
			
	<s:form action="infringementsUpload" validate="true" namespace="/" method="post" enctype="multipart/form-data">

	     

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
								listValue="typeDesc" name="officeCode" id="officeCode"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="officeCode" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>
                 

						
						<div class="col-6">

							<s:select headerKey="" headerValue="File Type"
								list="fileTypeList" listKey="typeCode"
								listValue="typeDesc" name="fileType" id="fileType"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="fileType" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>
                  
       
       </div>
				

 <div class="form-group row">
       
       
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
       
       
						<div class="col-6">
						
						<s:file name="fileUpload" label="File" size="40" cssClass="form-control"  theme="simple" required="required"/>
						
						</div>
						
						</div>
			
<div class="form-group row">
						<div class="col-md-12 text-center">
							<s:submit method="uploadInfringementsFile" class="btn waves-effect waves-light btn-info" value="Upload" theme="simple" />
							<input type="button" name="Exit" value="Exit" class="btn waves-effect waves-light btn-info" onClick="window.location.href='exitInfringementsUpload';">
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
