<%@include file="../commonJsp/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<s:head theme="saanchi" />
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<sx:head />
</head>
<body oncontextmenu="return false;">





	
	<s:form action="UpdateInvoiceDetail" validate="true" namespace="/" method="post">
	

		<div class="card">
			<div class="card-header" align="center">
				<h5>Update Invoice Detail</h5>
					
			</div>
			<div class="card-block">
			
	

	     
	     

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
								listValue="typeDesc" name="office_location" id="office_location"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="office_location" theme="saanchi"
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
                  </div>
                  
                  
                  
 <div class="form-group row">
						
						<div class="col-6">

							<s:select headerKey="" headerValue="Month"
								list="monthList" listKey="typeCode"
								listValue="typeDesc" name="monthCode" id="monthCode"
								cssClass="form-control" theme="simple" required="required"  onchange="loadPromoter(this.value);"/>
							<s:fielderror fieldName="monthCode" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>
						
						
						
						<div class="col-6">

							<s:select headerKey="" headerValue="Promoter Name"
								list="promoterList" listKey="typeCode"
								listValue="typeDesc" name="promoterName" id="promoterName"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="promoterName" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>
                  
       
       </div>
       
    
       

       
      
			
<div class="form-group row">
						<div class="col-md-12 text-center">
							<s:submit method="searchInvoiceDetail" class="btn waves-effect waves-light btn-info" value="Search" theme="simple" />
							
							<input type="button" name="Exit" value="Exit" class="btn waves-effect waves-light btn-info" onClick="window.location.href='exitChargingStatement';">
						</div>
					</div>
					
  </div> 					
					<br>
					
					
						<s:if test="%{(status=='false')}">	
					<div class="container border border-primary">		
						
						 <div class="form-group row">
       
       
						<div class="col-6">
                             <p class="text-primary"><b>Permit Fee</b></p>
							<s:textfield name="permit_fee_amount" id="permit_fee_amount" placeholder="Permit Fee"
							cssClass="form-control"  required="false" readonly="true" style="text-align:right;background-color: #C0C0C0; padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50">
							<s:fielderror fieldName="permit_fee_amount" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						</div>
                 

						
						<div class="col-6">
                            <p class="text-primary"><b>Discount Applied</b></p>
							<s:textfield name="discount_applied" id="discount_applied" placeholder="Discount Applied"
							cssClass="form-control"  required="false" readonly="true" style="text-align:right;background-color: #C0C0C0; padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50">
							<s:fielderror fieldName="discount_applied" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						</div>
						
					
                  </div>
                  
                
                   <div class="form-group row">
       
      
						<div class="col-6">
                             <p class="text-primary"><b>Invoiceable Amount</b></p>
							<s:textfield name="invoiceable_amount" id="invoiceable_amount" placeholder="Invoiceable Amount"
							cssClass="form-control input-lg"  required="false" readonly="true" style="text-align:right;background-color: #C0C0C0; padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50">
							<s:fielderror fieldName="invoiceable_amount" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						</div>
                 

						
						<div class="col-6">
                            <p class="text-primary"><b>Statement Issued</b></p>
							<s:select headerKey="" headerValue="Select"
								list="statementIssuedList" listKey="typeCode"
								listValue="typeDesc" name="statement_issued" id="statement_issued"
								cssClass="form-control input-sm" theme="simple" required="required" />
							<s:fielderror fieldName="statement_issued" theme="saanchi"
								cssClass="smallErrorMsg" />
								
								
							
						</div>
						
					
                  </div>
                 
                  
                   <div class="form-group row">
       
       
						<div class="col-6">
                             <p class="text-primary"><b>Statement Issue Date</b></p>
							<s:textfield name="statement_issued_date" type="date" id="statement_issued_date"
							cssClass="form-control"  required="true" 
							theme="simple" max="2099-12-31" style="padding-top:11px; padding-bottom: 13px;">
							<s:fielderror fieldName="statement_issued_date" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						</div>
                 

						
						<div class="col-6">
                            <p class="text-primary"><b>Promoter Response</b></p>
							<s:select headerKey="" headerValue="Select"
								list="promoterResponseList" listKey="typeCode"
								listValue="typeDesc" name="promoter_response" id="promoter_response"
								cssClass="form-control" theme="simple" required="required" onchange="enableDisableField(this.value);" />
							<s:fielderror fieldName="promoter_response" theme="saanchi"
								cssClass="smallErrorMsg" />
							
						</div>
						
					
                  </div>
                  
                   <div class="form-group row">
       
       
						<div class="col-6">
                             <p class="text-primary"><b>Challenged Amount</b></p>
							<s:textfield name="challenged_amount" id="challenged_amount" placeholder="Challenged Amount"
							cssClass="form-control"  required="false" readonly="true" style="text-align:right;background-color: #C0C0C0; padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="10" onblur="changeValue(this.value);">
							<s:fielderror fieldName="challenged_amount" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						</div>
						
						<div class="col-6">
                             <p class="text-primary"><b>Invoiced Amount</b></p>
							<s:textfield name="invoiced_amount" id="invoiced_amount" placeholder="Invoiced Amount"
							cssClass="form-control"  required="false" readonly="true" style="text-align:right;background-color: #C0C0C0; padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="10">
							<s:fielderror fieldName="invoiced_amount" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						</div>
						
						</div>
						
						
						
						
                   <div class="form-group row">
       
       
						<div class="col-6">
                             <p class="text-primary"><b>Invoiced Date</b></p>
							<s:textfield name="invoiced_date" type="date" id="invoiced_date" 
							cssClass="form-control"  required="true"
							theme="simple" max="2099-12-31" style="padding-top:11px; padding-bottom: 13px;">
							<s:fielderror fieldName="invoiced_date" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						</div>
						
						<div class="col-6">
                             <p class="text-primary"><b>Order Number</b></p>
							<s:textfield name="order_number" id="order_number" placeholder="Order Number"
							cssClass="form-control"  required="true"
							theme="simple" maxlength="20" style="padding-top:11px; padding-bottom: 13px;">
							<s:fielderror fieldName="order_number" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						</div>
						
						
						</div>
						
						
						 <div class="form-group row">
       
       
						
						
						<div class="col-6">
                             <p class="text-primary"><b>Invoice Number</b></p>
							<s:textfield name="invoice_no" id="invoice_no" placeholder="Invoice Number"
							cssClass="form-control"  required="true"
							theme="simple" maxlength="20" style="padding-top:11px; padding-bottom: 13px;">
							<s:fielderror fieldName="invoice_no" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						</div>
						
						
						</div>
						
						
						<div class="form-group row">
						<div class="col-md-12 text-center">
							<s:submit method="invoiceDetailFirstUpdate" class="btn waves-effect waves-light btn-info" value="Update" theme="simple" />
							
						</div>
					</div>
						
				</div>		
						
						</s:if>
						
						
						
						<s:if test="%{(status_final=='false')}">		
							<div class="container border border-primary">		
						
						 <div class="form-group row">
       
       
						<div class="col-6">
                             <p class="text-primary"><b>Permit Fee</b></p>
							<s:textfield name="permit_fee_amount" id="permit_fee_amount" placeholder="Permit Fee"
							cssClass="form-control"  required="false" readonly="true" style="text-align:right;background-color: #C0C0C0; padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50">
							<s:fielderror fieldName="permit_fee_amount" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						</div>
                 

						
						<div class="col-6">
                            <p class="text-primary"><b>Discount Applied</b></p>
							<s:textfield name="discount_applied" id="discount_applied" placeholder="Discount Applied"
							cssClass="form-control"  required="false" readonly="true" style="text-align:right;background-color: #C0C0C0; padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50">
							<s:fielderror fieldName="discount_applied" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						</div>
						
					
                  </div>
                  
                
                   <div class="form-group row">
       
      
						<div class="col-6">
                             <p class="text-primary"><b>Invoiceable Amount</b></p>
							<s:textfield name="invoiceable_amount" id="invoiceable_amount" placeholder="Invoiceable Amount"
							cssClass="form-control input-lg"  required="false" readonly="true" style="text-align:right;background-color: #C0C0C0; padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50">
							<s:fielderror fieldName="invoiceable_amount" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						</div>
						
						
						<div class="col-6">
                             <p class="text-primary"><b>Challenged Amount</b></p>
							<s:textfield name="challenged_amount" id="challenged_amount" placeholder="Challegned Amount"
							cssClass="form-control input-lg"  required="false" readonly="true" style="text-align:right;background-color: #C0C0C0; padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50">
							<s:fielderror fieldName="challenged_amount" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						</div>
						
						</div>
						  <div class="form-group row">
						  
						  
						  
						<div class="col-6">
                             <p class="text-primary"><b>Invoiced Amount</b></p>
							<s:textfield name="invoiced_amount" id="invoiced_amount" placeholder="invoiced_amount"
							cssClass="form-control input-lg"  required="false" readonly="true" style="text-align:right;background-color: #C0C0C0; padding-top:11px; padding-bottom: 13px;"
							theme="simple" maxlength="50">
							<s:fielderror fieldName="invoiced_amount" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						</div>
						
						
						<div class="col-6">
                             <p class="text-primary"><b>Paid Amount</b></p>
							<s:textfield name="paid_amount" id="paid_amount" placeholder="Paid Amount"
							cssClass="form-control"  required="true"
							theme="simple" maxlength="20" style="padding-top:11px; padding-bottom: 13px;">
							<s:fielderror fieldName="paid_amount" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						</div>
						
                 </div>
                 
                    <div class="form-group row">
       
      
						<div class="col-6">
						 <p class="text-primary"><b>Paid Date</b></p>
							<s:textfield name="paid_date" type="date" id="paid_date" 
							cssClass="form-control"  required="true"
							theme="simple" max="2099-12-31" style="padding-top:11px; padding-bottom: 13px;">
							<s:fielderror fieldName="paid_date" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						
						
						</div>
						
						<div class="col-6">
						
						    <p class="text-primary"><b>Comments</b></p>
							<s:textfield name="comments" id="comments" placeholder="Comments"
							cssClass="form-control"  required="true"
							theme="simple" maxlength="50" style="padding-top:11px; padding-bottom: 13px;">
							<s:fielderror fieldName="comments" theme="saanchi"
								cssClass="smallErrorMsg" />
						</s:textfield>
						
						
						</div>
						
						
						
						</div>
                  
                  
                  
                  <div class="form-group row">
						<div class="col-md-12 text-center">
							<s:submit method="invoiceDetailSecondUpdate" class="btn waves-effect waves-light btn-info" value="Update" theme="simple" />
							
						</div>
					</div>
                  
                  </div>
						
						</s:if>
					
					

	
	</div>
	</div>

	<s:hidden name="empID" id="empID" />
	<s:hidden name="invoice_detail_id" id="invoice_detail_id" />
	<s:hidden name="status" id="status" />
	<s:hidden name="status_final" id="status_final" />
	
	</s:form>
	
	
	
</body>
<script type="text/javascript">
window.history.forward(0);


function loadPromoter(value)  
{
	
	  try{
	    var formInput= 'monthCode='+value+"&yearCode="+document.getElementById("yearCode").value+"&office_location="+document.getElementById("office_location").value;
	       var options='';   
	    var exchangeDeed=''; 
	      $("#promoterName").html(options);  
	      $.getJSON('loadPromoterName.action', formInput,function(data) {
	    	options = '<option selected value="">Select</option>';
	    	$.each(data.promoterList, function(i,item){
	    		options += '<option value="' + item.typeCode + '">' + item.typeDesc + '</option>';  
	        });  
	          
	        $("#promoterName").html(options);   
	    }); 
	      
	  }
	  catch(Exception)
	  {
		  alert("Error" + Exception);
	  }
	
	
}	


function enableDisableField(value)
{
	  if(value=='Challenged')
		{
		  document.getElementById("challenged_amount").value="0"; 
	document.getElementById("challenged_amount").readOnly=false;
	document.getElementById("challenged_amount").style="text-align:right;background-color: #FFFFFF; padding-top:11px; padding-bottom: 13px;";
		}
	  
	  else if(value=='Approved' || value=='Deemed' ||  value=='Not Applicable' || value=="" )
		{
		  document.getElementById("challenged_amount").value="0"; 
	document.getElementById("challenged_amount").readOnly=true;
	document.getElementById("challenged_amount").style="text-align:right;background-color: #C0C0C0; padding-top:11px; padding-bottom: 13px;";
	 document.getElementById("invoiced_amount").value= document.getElementById("invoiceable_amount").value;
		}
}

function changeValue(value){
	
	if(!isNaN(value)){
		
		document.getElementById("invoiced_amount").value=document.getElementById("invoiced_amount").value-value;
	}
	else
		{
		alert("Please input a valid amount.")
		}
}
</script>
</html>
