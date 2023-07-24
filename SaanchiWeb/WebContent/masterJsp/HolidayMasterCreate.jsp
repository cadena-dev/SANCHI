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
				<h5>Add Holidays</h5>
					
			</div>
			<div class="card-block">
			
	<s:form action="HolidayMasterEntry" validate="true" namespace="/" method="post">

	     
	     

                 	<div class="col-md-12">
						<div class="row">
							<s:actionerror theme="saanchi" cssClass="form-control form-bg-danger" />
							<s:actionmessage theme="saanchi" cssClass="form-control form-bg-info" />
						</div>
					</div>
					
					
                    <div class="form-group row">
       
       
						<div class="col-6">
								<s:textfield name="description" id="description" placeholder="Description"
							cssClass="form-control"  required="required" style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50" onblur="javascript:this.value=this.value.toUpperCase();">
							<s:fielderror fieldName="description" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
</div>
						
						<div class="col-6">
						
						

						<s:textfield name="holidate_date" type="date" id="holidate_date" placeholder="Holiday Date(dd-MM-yyyy)"
							cssClass="form-control"  required="required"   style="padding-top:11px; padding-bottom: 13px;"
							theme="simple" max="2099-12-31" onblur="javascript:this.value=this.value.toUpperCase();" autocomplete="off">
							<s:fielderror fieldName="holidate_date" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
					
						</div>
                  
       
       </div>
      
						
                  
       
      
       
       

       
      
			
<div class="form-group row">
						<div class="col-md-12 text-center">
							<s:submit method="addHolidayMaster" class="btn waves-effect waves-light btn-info" value="Add" theme="simple" />
							<input type="button" name="Back" value="Back" class="btn waves-effect waves-light btn-info" onClick="openList()"> 
							<input type="button" name="Exit" value="Exit" class="btn waves-effect waves-light btn-info" onClick="window.location.href='exitHolidayMaster';">
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
		
		
		
		var targetString = "HolidayMasterList.action"; 
	
		
	window.open(targetString, '_self');
	
	}
	catch(err)
	{
		alert(err.message);
		}
	
}	

</script>
</html>
