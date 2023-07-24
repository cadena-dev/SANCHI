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
					<h5>Update Organization</h5>
				</div>
				<div class="card-block">

					<s:form action="OfficeMasterList" validate="true" namespace="/"
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
								onClick="addRecord()"> <input type="button" name="Edit"
								value="Edit" class="button" onClick="javascript:updateRecord()"> <input
								type="button" name="Delete" value="Delete" class="button"
								onClick="javascript:return confirm('Are you okay to delete the Organization?')&&deleteRecord()">

						</div>

                       <br>
						<div class="table-responsive">

							<s:if test="%{!officeMasterList.isEmpty()}">

								<table id="mytbl" class="table table-responsive table-bordered table-hover">
									<thead>
										<tr class="table-info">
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Select</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Srl.No.</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Organization Name</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Organization Address</th>
											<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Organization Type</th>
<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Active/In Active</th>

										</tr>
									</thead>

									<%
									int i = 0;
									%>
	<tbody>
									<s:iterator value="officeMasterList" var="officeMasterList">
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
								

												<td align="center"><input type="radio" id="selection"
													name="selection"
													value="<s:property value="office_master_id" />~<s:property value="office_desc" />~<s:property value="office_address" />~<s:property value="office_type" />~<s:property value="isactive" />"></td>

												<td align="center"><font color="blue"> <%=i%></font></td>


												<td><font color="black"><s:property
															value="office_desc" /></font></td>
												<td><font color="black"><s:property
															value="office_address" /></font></td>
												<td><font color="black"><s:property
															value="office_type" /></font></td>
<td><font color="black"><s:property
															value="isactive" /></font></td>

											</tr>
</s:if>



	<s:if test="isactive==false">
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
								

												<td align="center"><input type="radio" id="selection"
													name="selection"
													value="<s:property value="office_master_id" />~<s:property value="office_desc" />~<s:property value="office_address" />~<s:property value="office_type" />~<s:property value="isactive" />"></td>

												<td align="center"><font color="red"> <%=i%></font></td>


												<td><font color="red"><s:property
															value="office_desc" /></font></td>
												<td><font color="red"><s:property
															value="office_address" /></font></td>
												<td><font color="red"><s:property
															value="office_type" /></font></td>
<td><font color="red"><s:property
															value="isactive" /></font></td>

											</tr>
</s:if>


										
									</s:iterator>
									</tbody>
								</table>

							</s:if>
						</div>






						<!-- <div class="form-group row">
							<div class="col-md-12 text-center">


								<input type="button" name="Exit" value="Exit"
									class="btn waves-effect waves-light btn-info"
									onClick="window.location.href='exitOfficeMasterList';">



							</div>
						</div> -->




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
		try {

			var rates = document.getElementsByName('selection');
			var rate_value;
			var myArray;
			for (var i = 0; i < rates.length; i++) {
				if (rates[i].checked) {
					rate_value = rates[i].value;

				}
			}

			if (!rate_value || rate_value.length === 0) {
				alert("Please select one row");
			} else {

				myArray = rate_value.split("~");

				var targetString = "OfficeMasterEdit.action?office_master_id="
						+ myArray[0] + "&office_desc=" + myArray[1]
						+ "&office_address=" + myArray[2] + "&office_type="
						+ myArray[3] + "&isactive=" + myArray[4];

				window.open(targetString, '_self');

			}

		} catch (err) {
			alert(err.message);
		}

	}

	function deleteRecord() {
		try {

			var rates = document.getElementsByName('selection');
			var rate_value;
			var myArray;
			for (var i = 0; i < rates.length; i++) {
				if (rates[i].checked) {
					rate_value = rates[i].value;

				}
			}

			if (!rate_value || rate_value.length === 0) {
				alert("Please select one row");
			} else {

				myArray = rate_value.split("~");

				var formInput = "office_master_id=" + myArray[0]
						+ "&office_desc=" + myArray[1] + "&office_address="
						+ myArray[2] + "&office_type=" + myArray[3]
						+ "&isactive=" + myArray[4] + "&empID="
						+ document.getElementById("empID").value;

				$.getJSON('deleteOfficeMaster.action', formInput,
						function(data) {

							alert(data.message);

							var targetString = "OfficeMasterList.action";
							window.open(targetString, '_self');
						});

			}

		} catch (err) {
			alert(err.message);
		}

	}

	function addRecord() {

		try {

			var targetString = "OfficeMasterEntry.action";

			window.open(targetString, '_self');

		} catch (err) {
			alert(err.message);
		}

	}
</script>
</html>
