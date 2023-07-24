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
				<h5>Edit User</h5>
					
			</div>
			<div class="card-block">
			
	<s:form action="UserMasterEdit" validate="true" namespace="/" method="post">

	     
	     <s:hidden name="emp_no" id="emp_no" />	

                 	<div class="col-md-12">
						<div class="row">
							<s:actionerror theme="saanchi" cssClass="form-control form-bg-danger" />
							<s:actionmessage theme="saanchi" cssClass="form-control form-bg-info" />
						</div>
					</div>
					
					
                    <div class="form-group row">
       
       
						<div class="col-6">
								<s:textfield name="user_name" id="user_name" placeholder="User Name"
							cssClass="form-control"  required="required" style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50">
							<s:fielderror fieldName="user_name" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                 

						
						<div class="col-6">

						<s:textfield name="emp_name" id="emp_name" placeholder="Name"
							cssClass="form-control"  required="required" style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50" onblur="javascript:this.value=this.value.toUpperCase();">
							<s:fielderror fieldName="emp_name" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                  
       
       </div>
       
        <div class="form-group row">
       
       
						<div class="col-6">
								<s:textfield name="user_mail" id="user_mail" placeholder="e-Mail ID"
							cssClass="form-control"  required="required" style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50" onblur="javascript:this.value=this.value.toUpperCase();">
							<s:fielderror fieldName="user_mail" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                 
                 
                 <div class="col-6">

						<s:textfield name="user_password" id="user_password" placeholder="Password"
							cssClass="form-control"  required="required" style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50" >
							<s:fielderror fieldName="user_password" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>

						
						
                  
       
       </div>
       
       <div class="form-group row">
       
       
						
                 <div class="col-6">
								<s:textfield name="user_contact_no" id="user_contact_no" placeholder="Contact Number"
							cssClass="form-control"  required="required" style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="10" onblur="javascript:this.value=this.value.toUpperCase();">
							<s:fielderror fieldName="user_contact_no" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>

						
						<div class="col-6">

						<s:textfield name="user_address" id="user_address" placeholder="Address"
							cssClass="form-control"  required="required" style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="100" onblur="javascript:this.value=this.value.toUpperCase();" >
							<s:fielderror fieldName="user_address" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                  
                  
                
       
       </div>
       
       <div class="form-group row">
       
       
						
                   <div class="col-6">

							<s:select headerKey=""
								headerValue="Highways Authority"
								list="officeList" listKey="typeCode"
								listValue="typeDesc" name="office_location" id="office_location"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="office_location" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>

						
							<div class="col-6">

							<s:select headerKey=""
								headerValue="User Type"
								list="userTypeList" listKey="typeCode"
								listValue="typeDesc" name="user_type" id="user_type"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="user_type" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>
                  
       
       
       
						
       </div>
       
 
 <div class="form-group row">
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
							<s:submit method="updateUserMaster" class="btn waves-effect waves-light btn-info" value="Update" theme="simple" onclick="javascript:return confirm('Are you okay to update the User?');"/>
							<input type="button" name="Cancel" value="Cancel" class="btn waves-effect waves-light btn-info" onClick="openList()">
							<!-- <input type="button" name="Cancel" value="Cancel" class="btn waves-effect waves-light btn-info" onClick="window.location.href='exitUserMaster';"> -->
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
		
		
		
		var targetString = "UserMasterList.action"; 
	
		
	window.open(targetString, '_self');
	
	}
	catch(err)
	{
		alert(err.message);
		}
	
}	
</script>
</html>
