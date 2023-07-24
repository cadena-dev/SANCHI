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
				<h5>Update Holidays</h5>
					
			</div>
			<div class="card-block">
			
	<s:form action="HolidayMasterEdit" validate="true" namespace="/" method="post">

	     
	      <s:hidden name="holidaye_master_id" id="holidaye_master_id" />	

                 	<div class="col-md-12">
						<div class="row">
							<s:actionerror theme="saanchi" cssClass="form-control form-bg-danger" />
							<s:actionmessage theme="saanchi" cssClass="form-control form-bg-info" />
						</div>
					</div>
					
					
                    <div class="form-group row">
       
       
						<div class="col-6">
								<s:textfield  name="description" id="description" placeholder="Description"
							cssClass="form-control"  required="required" style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50" onblur="javascript:this.value=this.value.toUpperCase();">
							<s:fielderror fieldName="description" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                 
                 

						
						<div class="col-6">

					<s:textfield name="holidate_date" type="date" id="holidate_date"
							cssClass="form-control"  required="required"   style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" max="2099-12-31" onblur="javascript:this.value=this.value.toUpperCase();" autocomplete="off">
							<s:fielderror fieldName="holidate_date" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                 
       
       </div>
       
        <div class="form-group row">
       
       
							
                 
<div class="col-6">

							<s:select   headerKey=""
								list="activityList" listKey="typeCode"
								listValue="typeDesc" name="isactive" id="isactive"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="isactive" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>
						
						
                  
       
       </div>
       
              
       

      
<div class="form-group row">
						<div class="col-md-12 text-center">
							<s:submit method="updateHolidayMaster" class="btn waves-effect waves-light btn-info" value="Update" theme="simple" onclick="javascript:return confirm('Are you okay to update the Holiday?');" />
							<%-- <s:submit method="deleteHolidayMaster" class="btn waves-effect waves-light btn-info" value="Delete" theme="simple" /> --%>
							<input type="button" name="Cancel" value="Cancel" class="btn waves-effect waves-light btn-info" onClick="openList()"> 
							<!-- <input type="button" name="Cancel" value="Cancel" class="btn waves-effect waves-light btn-info" onClick="window.location.href='exitHolidayEdit';"> -->
						</div>
					</div>

	</s:form>
	</div>
	</div>
	</div>
	</div>
	
	
	
	<s:hidden name="empID" id="empID" />
	<s:hidden name="holiday" id="holiday" />
	
	
</body>
<script type="text/javascript">
window.history.forward(0);

function openList(){
	
	try{
		
		
		
		var targetString = "HolidayMasterList.action"; 
	
		
	window.open(targetString, '_self');
	
	}
	catch(err)
	{
		alert(err.message);
		}
	
}	

window.onload = function calculateValueNewStampValue(value1) {

	

	var options = "";
	try {
		
		var formInput =  'holiday='
			+ document.getElementById("holiday").value;
			$
					.getJSON(
							'holidate_get.action',
							formInput,
							function(data) {
								var res =  data.holidate_date;
								document.getElementById("holidate_date").value = res;
							});
			
	}

	catch (Exception) {
		alert("Error" + Exception);
	}

}

</script>
</html>
