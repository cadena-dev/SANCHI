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
				<h5>Update Promoter Master</h5>
					
			</div>
			<div class="card-block">
			
	<s:form action="PromoterMasterEdit" validate="true" namespace="/" method="post">

	     
	      <s:hidden name="promoter_master_id" id="promoter_master_id" />	

                 	<div class="col-md-12">
						<div class="row">
							<s:actionerror theme="saanchi" cssClass="form-control form-bg-danger" />
							<s:actionmessage theme="saanchi" cssClass="form-control form-bg-info" />
						</div>
					</div>
					
					
                    <div class="form-group row">
       
       
						<div class="col-6">
								<s:textfield name="promoter_name" id="promoter_name" placeholder="Promoter Name"
							cssClass="form-control"  required="required"
							theme="simple" maxlength="50" onblur="javascript:this.value=this.value.toUpperCase();">
							<s:fielderror fieldName="promoter_name" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                 

						
						<div class="col-6">

						<s:textfield name="promoter_short_name" id="promoter_short_name" placeholder="Promoter Short Name"
							cssClass="form-control"  required="required"
							theme="simple" maxlength="50" onblur="javascript:this.value=this.value.toUpperCase();">
							<s:fielderror fieldName="promoter_short_name" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                  
       
       </div>
       
        <div class="form-group row">
       
       
						<div class="col-6">
								<s:textfield name="org_reference_number" id="org_reference_number" placeholder="Org Reference No."
							cssClass="form-control"  required="required"
							theme="simple" maxlength="50" onblur="javascript:this.value=this.value.toUpperCase();">
							<s:fielderror fieldName="org_reference_number" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                 

						
						<div class="col-6">

						<s:textfield name="prefix" id="prefix" placeholder="Prefix"
							cssClass="form-control"  required="required"
							theme="simple" maxlength="50" >
							<s:fielderror fieldName="prefix" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                  
       
       </div>
       
       <div class="form-group row">
       
       
						<div class="col-6">
								<s:textfield name="promoter_mail" id="promoter_mail" placeholder="Promoter e-Mail ID"
							cssClass="form-control"  required="required"
							theme="simple" maxlength="50" onblur="javascript:this.value=this.value.toUpperCase();">
							<s:fielderror fieldName="promoter_mail" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                 

						
						<div class="col-6">

						<s:textfield name="promoter_contact_no" id="promoter_contact_no" placeholder="Promoter Phone No."
							cssClass="form-control"  required="required"
							theme="simple" maxlength="10" onblur="javascript:this.value=this.value.toUpperCase();" >
							<s:fielderror fieldName="promoter_contact_no" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                  
       
       </div>
       
      <div class="form-group row">
       
       
						<div class="col-6">
								<s:textfield name="promoter_mobile_no" id="promoter_mobile_no" placeholder="Promoter Mobile No."
							cssClass="form-control"  required="required"
							theme="simple" maxlength="10" onblur="javascript:this.value=this.value.toUpperCase();">
							<s:fielderror fieldName="promoter_mobile_no" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

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
							<s:submit method="updatePromoterMaster" class="btn waves-effect waves-light btn-info" value="Update" theme="simple" />
							<s:submit method="deletePromoterMaster" class="btn waves-effect waves-light btn-info" value="Delete" theme="simple" />
							<input type="button" name="Back" value="Back" class="btn waves-effect waves-light btn-info" onClick="openList()"> 
							<input type="button" name="Exit" value="Exit" class="btn waves-effect waves-light btn-info" onClick="window.location.href='exitPromoterMasterEdit';">
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
		
		
		
		var targetString = "PromoterMasterList.action"; 
	
		
	window.open(targetString, '_self');
	
	}
	catch(err)
	{
		alert(err.message);
		}
	
}	
</script>
</html>
