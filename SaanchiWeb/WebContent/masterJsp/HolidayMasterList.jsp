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
					<h5>Update Holidays</h5>
				</div>
				<div class="card-block">

					<s:form action="HolidayMasterList" validate="true" namespace="/"
						method="post" >

						<div class="col-md-12">
							<div class="row">
								<s:actionerror theme="saanchi"
									cssClass="form-control form-bg-danger" />
								<s:actionmessage theme="saanchi"
									cssClass="form-control form-bg-info" />
							</div>
						</div>
<div class="text-left">
            
                <input type="button" name="Add" value="Add" class="button"
					onClick="addRecord()">
           
               <input type="button" name="Edit" value="Edit" class="button"
					onClick="javascript: updateRecord()">
            
               <input type="button" name="Delete" value="Delete" class="button"
					onClick="javascript:return confirm('Are you okay to delete the Holiday?')&&deleteRecord()">
        
        </div>


	                   <div class="table-responsive">
							
							<s:if test="%{!holiadyMasterList.isEmpty()}">

									<table id="mytbl" class="table table-responsive table-bordered table-hover">
									
									
									
									<thead>
										<tr class="table-info">
										<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Select</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Srl. No.</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">HOLIDAY ID</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">HOLIDAY DATE</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">DESCRIPTION</th>
										
										<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Active/In Active</th>
												
										</tr>
									</thead>
                                   
									<%
									int i = 0;
									%>
<tbody>
									<s:iterator value="holiadyMasterList" var="holiadyMasterList">
									<s:if test="isactive==true">
										<%
										i++;
										%>

										
											<%
								if(i%2!=0)
								{
								%>
								
									<tr class="table-info">
									
								<%} else { %>
								
								<tr>
								<%} %>	

<td align="center"> <input type="radio" id="selection" name="selection" value="<s:property value="holidaye_master_id" />~<s:date name="holidate_date" format="dd/MM/yyyy" />~<s:property value="description" />~<s:property value="isactive" />"></td>
												<td align="center"><font color="blue"> <%=i%></font></td>
										

												<td align="center"><font color="black"><s:property value="holidaye_master_id" /></font></td>
												<td><font color="black"><s:date name="holidate_date" format="dd/MM/yyyy" />	</font></td>
												<td><font color="black"><s:property value="description" /></font></td>
												<td align="center"><font color="black"><s:property value="isactive" /></font></td>

											</tr>
                                 </s:if>
                                 
                                 
                                 <s:if test="isactive==false">
										<%
										i++;
										%>holiday

										
											<%
								if(i%2!=0)
								{
								%>
								
									<tr class="table-info">
									
								<%} else { %>
								
								<tr>
								<%} %>	

<td align="center"> <input type="radio" id="selection" name="selection" value="<s:property value="holidaye_master_id" />~<s:date name="holidate_date" format="dd/MM/yyyy" />~<s:property value="description" />~<s:property value="isactive" />"></td>
												<td align="center"><font color="red"> <%=i%></font></td>
										

												<td align="center"><font color="red"><s:property value="holidaye_master_id" /></font></td>
												<td><font color="red"><s:date name="holidate_date" format="dd/MM/yyyy" />	</font></td>
												<td><font color="red"><s:property value="description" /></font></td>
												<td align="center"><font color="red"><s:property value="isactive" /></font></td>

											</tr>
                                 </s:if>
										
									</s:iterator>
									</tbody>
								</table>

							</s:if>
						</div>




	                




			




					</s:form>



				</div>
			</div>





<s:hidden name="empID" id="empID" />

</body>
<script type="text/javascript">

$(document).ready(function() {
    $('#mytbl').DataTable();
} );

window.history.forward(0);
window.history.replaceState('','','?');
	
	
	
	
function updateRecord() {
	try{
		
		var rates = document.getElementsByName('selection');
		var rate_value;
		var myArray;
		for(var i = 0; i < rates.length; i++){
		    if(rates[i].checked){
		        rate_value = rates[i].value;
		    
		    }
		}
		
		if(!rate_value || rate_value.length === 0){
			alert("Please select one row");
		}
		else{
			
			
			myArray = rate_value.split("~");	
		
			var targetString = "HolidayMasterEdit.action?holidaye_master_id="
				+ myArray[0]
				+"&holidate_date="+myArray[1]
				+"&description="+myArray[2]
				+"&isactive="+myArray[3]
			    +"&holiday="+myArray[1];
		
			
		window.open(targetString, '_self');
		}
		}
		catch(err)
		{
			alert(err.message);
			}

	}
	
function addRecord(){
	
	try{
		
		
		
		var targetString = "HolidayMasterEntry.action"; 
	
		
	window.open(targetString, '_self');
	
	}
	catch(err)
	{
		alert(err.message);
		}
	
}	


function deleteRecord() {
	try{
		
		var rates = document.getElementsByName('selection');
		var rate_value;
		var myArray;
		for(var i = 0; i < rates.length; i++){
		    if(rates[i].checked){
		        rate_value = rates[i].value;
		    
		    }
		}
		
		if(!rate_value || rate_value.length === 0){
			alert("Please select one row");
		}
		else{
			
			
		
			
			myArray = rate_value.split("~");
			
			
			
			var formInput= "holidaye_master_id="
				+ myArray[0] + "&empID="+document.getElementById("empID").value;

		   
	      $.getJSON('deleteHoliday.action', formInput,function(data) {
	    	  
	    	  alert(data.message);
				
	    	  var targetString = "HolidayMasterList.action"; 
				window.open(targetString, '_self');
	    	       });  
	          
	        
	   
		
		
		}

	} catch (err) {
		alert(err.message);
	}

}
	
	
</script>
</html>
