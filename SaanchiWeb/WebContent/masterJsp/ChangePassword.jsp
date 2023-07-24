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
				<h5>Change Password</h5>
					
			</div>
			<div class="card-block">
			
	<s:form action="ChangePassword" validate="true" namespace="/" method="post">

	     
	     

                 	<div class="col-md-12">
						<div class="row">
							<s:actionerror theme="saanchi" cssClass="form-control form-bg-danger" />
							<s:actionmessage theme="saanchi" cssClass="form-control form-bg-info" />
						</div>
					</div>
					
					
                    <div class="form-group row">
       
       
						<div class="col-6">
								<s:textfield name="oldPassword" id="oldPassword" placeholder="Old Password"
							cssClass="form-control"  required="required" style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="20">
							<s:fielderror fieldName="oldPassword" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
</div>
						</div>
						
						
						  <div class="form-group row">
						<div class="col-6">

						<s:textfield name="newPassword"  id="newPassword" placeholder="New Password"
							cssClass="form-control"  required="required"   style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="20" >
							<s:fielderror fieldName="newPassword" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                  </div>
       
       
      
						  <div class="form-group row">
						<div class="col-6">

						<s:textfield name="confirmPassword" id="confirmPassword" placeholder="Confirm Password"
							cssClass="form-control"  required="required"   style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="20" >
							<s:fielderror fieldName="confirmPassword" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                  </div>
                  
       
      
       
       

       
      
			
<div class="form-group row">
						<div class="col-md-12 text-center">
							<s:submit method="updatePassword" class="btn waves-effect waves-light btn-info" value="Change" theme="simple" />
							<input type="button" name="Exit" value="Exit" class="btn waves-effect waves-light btn-info" onClick="window.location.href='exitChangePassword';">
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
