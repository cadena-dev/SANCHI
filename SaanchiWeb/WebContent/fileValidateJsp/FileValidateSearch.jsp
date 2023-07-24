<%@include file="../commonJsp/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<s:head theme="saanchi" />
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<sx:head />
</head>
<body oncontextmenu="return false;">



		<div class="card">
			<div class="card-header" align="center">
				<h5>Search File for Data validation</h5>
			</div>
			<div class="card-block">
			
	<s:form action="FileValidateSearch" validate="true" namespace="/" method="post">

	     

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
								list="officeList" listKey="typeCode"
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
						<div class="col-md-12 text-center">
							<s:submit method="searchFileForValidation" class="btn waves-effect waves-light btn-info" value="Search" theme="simple" />
							<input type="button" name="Exit" value="Exit" class="btn waves-effect waves-light btn-info" onClick="window.location.href='exitFileValidateSearch';">
						</div>
					</div>
</div>

	</s:form>
	
	<br><br>
	
	       			
<s:if test="%{!fileList.isEmpty()}">
	<div class="container">		
						
						 <div class="table-responsive">

 
				
				<table id="mytbl" class="table table-responsive table-bordered table-hover">
  <thead>
   <tr class="table-info">
      <th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">#</th>
      <th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">File Name</th>
      <th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Total Records</th>
      <th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Successful Records</th>
       <th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Upload Date</th>
    </tr>
  </thead>
  
  <%
					int i = 0;
					%>

					<s:iterator value="fileList" var="fileList">

						<%
						i++;
						%>
  
    <tbody>
    <%
								if(i%2!=0)
								{
								%>
								
									<tr class="table-info">
									
								<%} else { %>
								
								<tr>
								<%} %>	
    
    	<s:if test="%{fileType == 'FPN_Export'}">
    <s:url action="FPNExportValidate.action" id="fpnExport">
   
    
    <s:param name="file_name">
	<s:property value="type_code_1" />
	</s:param>
    
    </s:url>
     </s:if>
     
     
     <s:if test="%{fileType == 'PA_Export'}">
    <s:url action="PAExportValidate.action" id="fpnExport">
   
    
    <s:param name="file_name">
	<s:property value="type_code_1" />
	</s:param>
    
    </s:url>
     </s:if>
     
     
       <s:if test="%{fileType == 'Change_Request'}">
    <s:url action="ChangeRequestValidate.action" id="fpnExport">
   
    
    <s:param name="file_name">
	<s:property value="type_code_1" />
	</s:param>
    
    </s:url>
     </s:if>
     
     
      <s:if test="%{fileType == 'Fee_Export'}">
    <s:url action="FEEExportValidate.action" id="fpnExport">
   
    
    <s:param name="file_name">
	<s:property value="type_code_1" />
	</s:param>
    
    </s:url>
     </s:if>
    
      <th scope="row"><font color="blue"> <%=i%></font></th>
      <td><s:a href="%{fpnExport}"><font color="blue"><s:property value="type_code_1" /></font></s:a></td>
      <td><font color="black"><s:property value="type_code_2" /></font></td>
    <td><font color="black"><s:property value="type_code_3" /></font></td>
     <td><font color="black"><s:property value="type_code_4" /></font></td>
    </tr>
   
  </tbody>
  </s:iterator>
</table>
							
								
</div>	
	 </div> 
	 </s:if>	
	</div>
	</div>
	
	
	
	
	
	
	<s:hidden name="empID" id="empID" />
</body>
<script type="text/javascript">

$(document).ready(function() {
    $('#mytbl').DataTable();
} );


window.history.forward(0);
</script>
</html>
