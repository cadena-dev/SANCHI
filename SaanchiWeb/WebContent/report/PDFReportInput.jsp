<%@include file="../commonJsp/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<s:head theme="saanchi" />
</head>
<body oncontextmenu="return false;">





		<div class="row">
	<div class="col-sm-12 col-md-12">
		<div class="card">
			<div class="card-header" align="center">
				<h5>Initiate Report</h5>
			</div>
			<div class="card-block">
			
		<s:form action="InitiatePDFReportAction" validate="true" namespace="/"
		method="post">
	     
	     <div class="card-header" align="center">
				<h5>	<s:property value="heading" /></h5>
			</div>

                 	<div class="col-md-12">
						<div class="row">
							<s:actionerror theme="saanchi" cssClass="form-control form-bg-danger" />
							<s:actionmessage theme="saanchi" cssClass="form-control form-bg-info" />
						</div>
					</div>

  <s:if test="%{(event=='DASHBOARD')}">           
	        
       <div class="form-group row">
       
       
						<div class="col-6">

							<s:select headerKey=""
								headerValue="Highways Authority"
								list="officeList" listKey="typeCode"
								listValue="typeDesc" name="officeCode" id="officeCode"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="officeCode" theme="saanchi"
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
						
						<div class="col-6">

							<s:select headerKey="" headerValue="Report Type"
								list="reportTypeList" listKey="typeCode"
								listValue="typeDesc" name="reportType" id="reportType"
								cssClass="form-control" theme="simple" required="false" />
							<s:fielderror fieldName="monthCode" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>
					
                  
       
       </div>
       </s:if>
				
		<s:hidden name="heading" id="heading"/>
		<s:hidden name="reportPath" id="reportPath"/>
		<s:hidden name="reportName" id= "reportName"/>
		<s:hidden name="event" id="event"/>
			<br> <br>
		<div class="form-group row">
						<div class="col-md-12 text-center">
			
		
				<input type="button" class="button" name="Show" value="Show"
					onclick="openReport('PDFReportAction.action?reportPath=<s:property value="reportPath"/>&reportName=<s:property value="reportName"/>&DOWNLOAD=N')" />
					
					<input type="button" class="button" name="Download" value="Download"
					onclick="openReport('PDFReportAction.action?reportPath=<s:property value="reportPath"/>&reportName=<s:property value="reportName"/>&DOWNLOAD=Y')">
				
			
			</div>

		</div>


</s:form>
</div>
</div>
</div>
</div>

</body>
<script>


   
   
	
	function openReport(targetString) {

		try {
			
			if(document.getElementById("event").value=='DASHBOARD'){
				  
		targetString += "&officeCode="
				+ document.getElementById("officeCode").value;
		targetString += "&yearCode="
			+ document.getElementById("yearCode").value;
		targetString += "&monthCode="
			+ document.getElementById("monthCode").value;
		targetString += "&reportType="
			+ document.getElementById("reportType").value;
		 }
			 
			
				
			 
		window
				.open(
						targetString,
						"",
						"width=700, height=600,menubar=yes,location=1,status=1,scrollbars=yes,resizable=yes");
		
	
		

		} catch (Exception) {
			alert("Error" + Exception);
		}

	}
	
	
	
</script>
</html>

