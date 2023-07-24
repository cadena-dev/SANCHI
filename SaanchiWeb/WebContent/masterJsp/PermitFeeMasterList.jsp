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
			<h5>Update Charges</h5>
		</div>
		<div class="card-block">

			<s:form action="PermitFeeMasterList" validate="true" namespace="/"
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
						onClick="javascript:return confirm('Are you okay to delete the Charge?')&&deleteRecord()">

				</div>
				<br>

				<div class="table-responsive">

					<s:if test="%{!permitFeeMasterList.isEmpty()}">

						<table id="mytbl" class="table table-responsive table-bordered table-hover">
							<thead>
								<tr class="table-info">
									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">
										Select</th>
									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Srl.No.</th>
									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Highway
										Authority name</th>
									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Application
										type</th>
									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Work
										Category</th>
									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Road
										Category</th>
									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Traffic
										Sensitivity</th>
									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Fee
										Amount</th>
<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Active/In Active</th>
												
								</tr>
							</thead>

							<%
							int i = 0;
							%>
<tbody>
							<s:iterator value="permitFeeMasterList" var="permitFeeMasterList">
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
											value="<s:property value="permit_fee_master_id" />~<s:property value="highway_authority_id" />~<s:property value="highway_authority_name" />~<s:property value="application_type" />~<s:property value="work_category" />~<s:property value="road_category" />~<s:property value="traffic_sensitivity" />~<s:property value="fee_amount" />~<s:property value="isactive" />"></td>

										<td align="center"><font color="blue"> <%=i%></font></td>

										<td><font color="blue"><s:property
													value="highway_authority_name" /></font></td>
										<td><font color="blue"><s:property
													value="application_type" /></font></td>
										<td><font color="blue"><s:property
													value="work_category" /></font></td>
										<td><font color="blue"><s:property
													value="road_category" /></font></td>
										<td><font color="blue"><s:property
													value="traffic_sensitivity" /></font></td>
										<td align="right"><font color="blue"><s:property
													value="fee_amount" /></font></td>

										<td align="right"><font color="blue">True</font></td>

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
											value="<s:property value="permit_fee_master_id" />~<s:property value="highway_authority_id" />~<s:property value="highway_authority_name" />~<s:property value="application_type" />~<s:property value="work_category" />~<s:property value="road_category" />~<s:property value="traffic_sensitivity" />~<s:property value="fee_amount" />~<s:property value="isactive" />"></td>

										<td align="center"><font color="red"> <%=i%></font></td>

										<td><font color="red"><s:property
													value="highway_authority_name" /></font></td>
										<td><font color="red"><s:property
													value="application_type" /></font></td>
										<td><font color="red"><s:property
													value="work_category" /></font></td>
										<td><font color="red"><s:property
													value="road_category" /></font></td>
										<td><font color="red"><s:property
													value="traffic_sensitivity" /></font></td>
										<td align="right"><font color="red"><s:property
													value="fee_amount" /></font></td>
													
													<td align="right"><font color="red">False</font></td>

									</tr>
</s:if>


<s:if test="isactive==null">
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
													value="highway_authority_name" /></font></td>
										<td><font color="black"><s:property
													value="application_type" /></font></td>
										<td><font color="black"><s:property
													value="work_category" /></font></td>
										<td><font color="black"><s:property
													value="road_category" /></font></td>
										<td><font color="black"><s:property
													value="traffic_sensitivity" /></font></td>
										<td align="right"><font color="black"><s:property
													value="fee_amount" /></font></td>
													
													<td align="right"><font color="black">Deleted</font></td>

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
									onClick="window.location.href='exitPermitFeeMasterList';">



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

				var targetString = "PermitFeeMasterEdit.action?permit_fee_master_id="
						+ myArray[0]
						+ "&highway_authority_id="
						+ myArray[1].trim()
						+ " ~ "
						+ myArray[2].trim()
						+ "&highway_authority_name="
						+ myArray[2]
						+ "&application_type="
						+ myArray[3]
						+ "&work_category="
						+ myArray[4]
						+ "&road_category="
						+ myArray[5]
						+ "&traffic_sensitivity="
						+ myArray[6]
						+ "&fee_amount="
						+ myArray[7] + "&isactive=" + myArray[8];
				
				
				

				window.open(targetString, '_self');
			}
		} catch (err) {
			alert(err.message);
		}

	}

	function addRecord() {

		try {

			var targetString = "PermitFeeMasterEntry.action";

			window.open(targetString, '_self');

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

				var formInput = "permit_fee_master_id=" + myArray[0]
						+ "&empID=" + document.getElementById("empID").value;

				$.getJSON('removePermitFee.action', formInput, 
						function(data) {
				});

				alert("Charge deleted successfully.");

				
				var targetString = "PermitFeeMasterList.action";
				window.open(targetString, '_self');				
			
			}

		} catch (err) {
			alert(err.message);
		}

	}
</script>
</html>
