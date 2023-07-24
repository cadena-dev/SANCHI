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
					<h5>Update Users</h5>
				</div>
				<div class="card-block">

					<s:form action="UserMasterList" validate="true" namespace="/"
						method="post">

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
					onClick="javascript:updateRecord()">
            
               <input type="button" name="Delete" value="Delete" class="button"
					onClick="javascript:return confirm('Are you okay to delete the User?')&&deleteRecord()">
        
        </div>
				
				
							
					



						<div class="table-responsive">   

							<s:if test="%{!userMasterList.isEmpty()}">

							<table id="mytbl" class="table table-responsive table-bordered table-hover">
									<thead>
										<tr class="table-info">
										<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Select</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Srl. No.</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">USER
														NAME</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">EMPLOYEE
														NAME</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">USER
														MAIL</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">USER
														PHONE NO.</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Highways Authority</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">USER
														TYPE</th>
										<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Active/In Active</th>				
											
										</tr>
									</thead>

									<%
									int i = 0;
									%>
	<tbody>
									<s:iterator value="userMasterList" var="userMasterList">
									
									
	<s:if test="user_activity == 'XX' ">
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
<td align="center"> <input type="radio" id="selection" name="selection" value="<s:property value="emp_no" />~<s:property value="office_location" />~<s:property value="user_name" />~<s:property value="user_password" />~<s:property value="user_mail" />~<s:property value="user_contact_no" />~<s:property value="user_address" />~<s:property value="isactive" />~<s:property value="user_type" />~<s:property value="emp_name" />"></td>												
	
												<td align="center"><font color="blue"> <%=i%></font></td>


												<td><font color="blue"><s:property
															value="user_name" /></font></td>
												<td><font color="blue"><s:property
															value="emp_name" /></font></td>
												<td><font color="blue"><s:property
															value="user_mail" /></font></td>
												<td><font color="blue"><s:property
															value="user_contact_no" /></font></td>
												<td><font color="blue"><s:property
															value="office_desc" /></font></td>
												<td><font color="blue"><s:property
															value="user_type_desc" /></font></td>
											
												<td align="center"><font color="blue">True</font></td>
											
													
													
											
											</tr>
</s:if>
									
									
									
										<s:if test="user_activity=='YY'">
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
<td align="center"> <input type="radio" id="selection" name="selection" value="<s:property value="emp_no" />~<s:property value="office_location" />~<s:property value="user_name" />~<s:property value="user_password" />~<s:property value="user_mail" />~<s:property value="user_contact_no" />~<s:property value="user_address" />~<s:property value="isactive" />~<s:property value="user_type" />~<s:property value="emp_name" />"></td>												
	
												<td align="center"><font color="red"> <%=i%></font></td>


												<td><font color="red"><s:property
															value="user_name" /></font></td>
												<td><font color="red"><s:property
															value="emp_name" /></font></td>
												<td><font color="red"><s:property
															value="user_mail" /></font></td>
												<td><font color="red"><s:property
															value="user_contact_no" /></font></td>
												<td><font color="red"><s:property
															value="office_desc" /></font></td>
												<td><font color="red"><s:property
															value="user_type_desc" /></font></td>
											
												<td align="center"><font color="red">False</font></td>
											
													
													
											
											</tr>
</s:if>	



										<s:if test="user_activity=='ZZ'">
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
<td align="center"><font color="black">Deleted</font></td>												
	
												<td align="center"><font color="black"> <%=i%></font></td>


												<td><font color="black"><s:property
															value="user_name" /></font></td>
												<td><font color="black"><s:property
															value="emp_name" /></font></td>
												<td><font color="black"><s:property
															value="user_mail" /></font></td>
												<td><font color="black"><s:property
															value="user_contact_no" /></font></td>
												<td><font color="black"><s:property
															value="office_desc" /></font></td>
												<td><font color="black"><s:property
															value="user_type_desc" /></font></td>
											
												<td align="center"><font color="black">Deleted</font></td>
											
													
													
											
											</tr>
</s:if>	

									</s:iterator>
									</tbody>
								</table>

							</s:if>
						</div>





<!-- 
						<div class="form-group row">
							<div class="col-md-12 text-center">


								<input type="button" name="Exit" value="Exit"
									class="btn waves-effect waves-light btn-info"
									onClick="window.location.href='exitUserMasterList';">



							</div>
						</div>

 -->


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
	window.history.replaceState('', '', '?');

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
				
			var targetString = "UserMasterEdit.action?emp_no="
					+ myArray[0] + "&office_location=" + myArray[1]
					+ "&user_name=" + myArray[2] + "&user_password="
					+ myArray[3] + "&user_mail=" + myArray[4]+"&user_contact_no="+myArray[5]
					+"&user_address="+myArray[6]+"&isactive="+myArray[7]+"&user_type="+myArray[8]+"&emp_name="+myArray[9];

			window.open(targetString, '_self');
			}

		} catch (err) {
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
				var formInput= "emp_no="
					+ myArray[0] + "&office_location=" + myArray[1]
					+ "&user_name=" + myArray[2] + "&user_password="
					+ myArray[3] + "&user_mail=" + myArray[4]+"&user_contact_no="+myArray[5]
					+"&user_address="+myArray[6]+"&isactive="+myArray[7]+"&user_type="+myArray[8]+"&emp_name="+myArray[9]+"&empID="+document.getElementById("empID").value;

			   
		      $.getJSON('deleteUser.action', formInput,function(data) {
		    	  
		    	  alert(data.message);
					
		    	  var targetString = "UserMasterList.action"; 
					window.open(targetString, '_self');
		    	       });  
		          
		        
		   
			
			
			}

		} catch (err) {
			alert(err.message);
		}

	}
	
	function addRecord(){
		
		try{
			
			
			
			var targetString = "UserMasterEntry.action"; 
		
			
		window.open(targetString, '_self');
		
		}
		catch(err)
		{
			alert(err.message);
			}
		
	}	
</script>
</html>
