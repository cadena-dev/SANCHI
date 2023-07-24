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
				<h5>Add Regulation Master</h5>
					
			</div>
			<div class="card-block">
			
	<s:form action="RegulationMasterEntry" validate="true" namespace="/" method="post">

	     
	     

                 	<div class="col-md-12">
						<div class="row">
							<s:actionerror theme="saanchi" cssClass="form-control form-bg-danger" />
							<s:actionmessage theme="saanchi" cssClass="form-control form-bg-info" />
						</div>
					</div>
					
					
                    <div class="form-group row">
       
       
						<div class="col-6">
								<s:textfield name="regulation_id" id="regulation_id" placeholder="Regulation ID"
							cssClass="form-control"  required="required"
							theme="simple" maxlength="50" onblur="javascript:this.value=this.value.toUpperCase();">
							<s:fielderror fieldName="regulation_id" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                 

						
						<div class="col-6">

						<s:textfield name="regulation_code" id="regulation_code" placeholder="Regulation Code"
							cssClass="form-control"  required="required"
							theme="simple" maxlength="50" onblur="javascript:this.value=this.value.toUpperCase();">
							<s:fielderror fieldName="regulation_code" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                  
       
       </div>
       
        <div class="form-group row">
       
       
						<div class="col-6">
								<s:textfield name="description" id="description" placeholder="Description"
							cssClass="form-control"  required="required"
							theme="simple" maxlength="50" onblur="javascript:this.value=this.value.toUpperCase();">
							<s:fielderror fieldName="description" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>

						</div>
                 

						
						
                  
       
       </div>
       
       

       
      
			
<div class="form-group row">
						<div class="col-md-12 text-center">
							<s:submit method="addRegulationrMaster" class="btn waves-effect waves-light btn-info" value="Add" theme="simple" />
							<input type="button" name="Exit" value="Exit" class="btn waves-effect waves-light btn-info" onClick="window.location.href='exitRegulationMaster';">
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
