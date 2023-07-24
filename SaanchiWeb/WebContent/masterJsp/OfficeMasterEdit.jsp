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
				<h5>Edit Organization</h5>
					
			</div>
			<div class="card-block">
			
	<s:form action="OfficeMasterEdit" validate="true" namespace="/" method="post">

	     
	     

                 	<div class="col-md-12">
						<div class="row">
							<s:actionerror theme="saanchi" cssClass="form-control form-bg-danger" />
							<s:actionmessage theme="saanchi" cssClass="form-control form-bg-info" />
						</div>
					</div>
					
					
                    <div class="form-group row">
       
       	<s:hidden name="office_master_id" id="office_master_id" />	
       
						<div class="col-6">
								<s:textfield name="office_desc" id="office_desc" placeholder="Organization Name"
							cssClass="form-control"  required="required" style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="100">
							<s:fielderror fieldName="office_desc" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                 

						
						<div class="col-6">

						<s:textfield name="office_address" id="office_address" placeholder="Organization Address"
							cssClass="form-control"  required="required" style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50" onblur="javascript:this.value=this.value.toUpperCase();">
							<s:fielderror fieldName="office_address" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                  
       
       </div>
       
       <div class="form-group row">
       
       
						<div class="col-6">

							<s:select headerKey=""
								headerValue="Organization Type"
								list="officeTypeList" listKey="typeCode"
								listValue="typeDesc" name="office_type" id="office_type"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="office_type" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>
                 

						
						<div class="col-6">

							<s:select headerKey=""
								headerValue="Activity Status"
								list="activityList" listKey="typeCode"
								listValue="typeDesc" name="isactive" id="isactive"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="isactive" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>
                  
       
       </div>
       
       
       

       
      
			
<div class="form-group row">
						<div class="col-md-12 text-center">
							<s:submit method="updateOfficeMaster" class="btn waves-effect waves-light btn-info" value="Update" theme="simple" onclick="javascript:return confirm('Are you okay to update the Organization?');"/>
							<%-- <s:submit method="deleteOfficeMaster" class="btn waves-effect waves-light btn-info" value="Delete" theme="simple" /> --%>
							<input type="button" name="Cancel" value="Cancel" class="btn waves-effect waves-light btn-info" onClick="openList()"> 
							<!-- <input type="button" name="Cancel" value="Cancel" class="btn waves-effect waves-light btn-info" onClick="window.location.href='exitOfficeMasterEdit';"> -->
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


function openList(){
	
	try{
		
		
		
		var targetString = "OfficeMasterList.action"; 
	
		
	window.open(targetString, '_self');
	
	}
	catch(err)
	{
		alert(err.message);
		}
	
}	
</script>
</html>
