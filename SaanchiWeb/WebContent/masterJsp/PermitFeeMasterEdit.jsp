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
				<h5>Update Charges</h5>
					
			</div>
			<div class="card-block">
			
	<s:form action="PermitFeeMasterEdit" validate="true" namespace="/" method="post">

	       <s:hidden name="permit_fee_master_id" id="permit_fee_master_id" />	
	     

                 	<div class="col-md-12">
						<div class="row">
							<s:actionerror theme="saanchi" cssClass="form-control form-bg-danger" />
							<s:actionmessage theme="saanchi" cssClass="form-control form-bg-info" />
						</div>
					</div>
					
					
                    <div class="form-group row">
       
						
						<div class="col-6">

						 <s:select headerKey=""
								headerValue="Highway Authority"
								list="officeList" listKey="typeCodeDesc"
								listValue="typeDesc" name="highway_authority_id" id="highway_authority_id"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="highway_authority_id" theme="saanchi"
								cssClass="smallErrorMsg" />


						</div>
						
						
						<div class="col-6">
					             <s:select headerKey=""
								headerValue="Application Type"
								list="applicationTypeList" listKey="typeCode"
								listValue="typeDesc" name="application_type" id="application_type" onchange="loadWorkCategory(this.value);"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="application_type" theme="saanchi"
								cssClass="smallErrorMsg" />
                    


						</div>
                  
                  
       
       </div>
       
        <div class="form-group row">
       
       <div class="col-6">
													
						
						 <s:select headerKey=""
								headerValue="Work Category"
								list="workCategoryList" listKey="typeCode"
								listValue="typeDesc" name="work_category" id="work_category"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="work_category" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>
						
						<div class="col-6">
						
						<s:select headerKey=""
								headerValue="Road category"
								list="roadTypeList" listKey="typeCode"
								listValue="typeDesc" name="road_category" id="road_category"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="road_category" theme="saanchi"
								cssClass="smallErrorMsg" />
						

						</div>
                  
       
       </div>
       
       
<div class="form-group row">
       
       
						
                 

							<div class="col-6">
								<s:textfield name="fee_amount" id="fee_amount" placeholder="Fee Amount"
							cssClass="form-control"  required="required" style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50" >
							<s:fielderror fieldName="fee_amount" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
						
						
						<div class="col-6">

							<s:select headerKey=""
								headerValue="Traffic Sensitivity"
								list="traffic_sensitivityList" listKey="typeCode"
								listValue="typeDesc" name="traffic_sensitivity" id="traffic_sensitivity"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="traffic_sensitivityList" theme="saanchi"
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
							<s:submit method="updatePermitFeeMaster" class="btn waves-effect waves-light btn-info" value="Update" theme="simple" onclick="javascript:return confirm('Are you okay to update the Charge?');"/>
							<%-- <s:submit method="deletePermitFeeMaster" class="btn waves-effect waves-light btn-info" value="Delete" theme="simple" /> --%>
							<input type="button" name="Cancel" value="Cancel" class="btn waves-effect waves-light btn-info" onClick="openList()"> 
							<!-- <input type="button" name="Cancel" value="Cancel" class="btn waves-effect waves-light btn-info" onClick="window.location.href='exitPermitFeeMasterEdit';"> -->
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
		
		
		
		var targetString = "PermitFeeMasterList.action"; 
	
		
	window.open(targetString, '_self');
	
	}
	catch(err)
	{
		alert(err.message);
		}
	
}	

function loadWorkCategory(value)  
{
	
	  try{
	    var formInput= 'application_type='+value;  
	    var options='';   
	      $("#work_category").html(options);  
	      $.getJSON('workcategory_editget.action', formInput,function(data) {
	    		options = '<option selected value="">Work Cateory</option>';
	    	$.each(data.workCategoryList, function(i,item){
	    		options += '<option value="' + item.typeCode + '">' + item.typeDesc + '</option>';  
	        });  
	          
	        $("#work_category").html(options);   
	    });  
	  }
	  catch(Exception)
	  {
		  alert("Error" + Exception);
	  }
	
	
}	
</script>
</html>
