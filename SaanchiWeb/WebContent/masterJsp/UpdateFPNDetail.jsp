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
			<h5>Update FPN Detail</h5>

		</div>
		<div class="card-block">

			<s:form action="UpdateFPNDetail" validate="true" namespace="/"
				method="post">





				<div class="col-md-12">
					<div class="row">
						<s:actionerror theme="saanchi"
							cssClass="form-control form-bg-danger" />
						<s:actionmessage theme="saanchi"
							cssClass="form-control form-bg-info" />
					</div>
				</div>




				<div class="container border border-primary">

					<div class="form-group row">


						<div class="col-6">

							<s:select headerKey="" headerValue="Highways Authority"
								list="officeList" listKey="typeCode" listValue="typeDesc"
								name="office_location" id="office_location"
								cssClass="form-control" theme="simple" required="required" />
							<s:fielderror fieldName="officeCode" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>



						<div class="col-6">

							<s:select headerKey="" headerValue="File Type"
								list="fileTypeList" listKey="typeCode" listValue="typeDesc"
								name="fileType" id="fileType" cssClass="form-control"
								theme="simple" required="required" />
							<s:fielderror fieldName="fileType" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>


					</div>


					<div class="form-group row">


						<div class="col-6">

							<s:select headerKey="" headerValue="Year" list="yearList"
								listKey="typeCode" listValue="typeDesc" name="yearCode"
								id="yearCode" cssClass="form-control" theme="simple"
								required="required" />
							<s:fielderror fieldName="yearCode" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>



						<div class="col-6">

							<s:select headerKey="" headerValue="Month" list="monthList"
								listKey="typeCode" listValue="typeDesc" name="monthCode"
								id="monthCode" cssClass="form-control" theme="simple"
								required="required" />
							<s:fielderror fieldName="monthCode" theme="saanchi"
								cssClass="smallErrorMsg" />

						</div>


					</div>







					<div class="form-group row">
						<div class="col-md-12 text-center">
							<s:submit method="searchFPNDetail"
								class="btn waves-effect waves-light btn-info" value="Search"
								theme="simple" />

							<input type="button" name="Exit" value="Exit"
								class="btn waves-effect waves-light btn-info"
								onClick="window.location.href='exitUpdateFPN';">
						</div>
					</div>

				</div>
				<br>


				<div class="table-responsive">

					<s:if test="%{!fpnInfringementsList.isEmpty()}">

						<table class="table table-responsive table-bordered table-hover">

							<thead>
								<tr class="table-info">
									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Srl.No.</th>
									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Works
										reference number</th>
									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Offence
										Code</th>
									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Offence
										Details</th>
									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Work
										Promoter</th>

									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">FPN
										Number</th>

									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Issue
										Date</th>

									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Amount</th>

									<th
										style="font-weight: bold; font-size: 12pt; background-color: #0000FF;">Received Date</th>

								</tr>
							</thead>

							<%
							int i = 0;
							%>

							<s:iterator value="fpnInfringementsList"
								var="fpnInfringementsList">

								<%
								i++;
								%>

								<tbody>
									<tr>


										<th scope="row"><font color="blue"> <%=i%></font></th>
										<s:hidden name="fpn_export_infringements_id"
											id="fpn_export_infringements_id" />

										<s:hidden name="first_update_status" id="first_update_status" />

										<s:hidden name="second_update_status"
											id="second_update_status" />

										<td><font color="black"><s:property
													value="works_reference_number" /> </font></td>

										<td><font color="black"><s:property
													value="offence_code" /> </font></td>

										<td><textarea class="form-control" rows="5"><s:property
													value="offence_details" /></textarea></td>


										<td><font color="black"><s:property
													value="works_promoter" /> </font></td>

										<s:if test="first_update_status==null">
											<td><font color="black"><input type="text"
													name="fpn_number" id="fpn_number" maxlength="100"
													class="field"></font></td>

											<td><font color="black"><input type="text"
													name="issue_date_time" id="issue_date_time" maxlength="10"
													class="field"></font></td>

											<td><font color="black"><input type="text"
													name="amount_paid" id="amount_paid" maxlength="10"
													class="field"></font></td>


												<td><font color="black"><input type="text" readonly style="background-color: #727272"
													name="received_date" id="received_date" maxlength="10"
													class="field"></font></td>
										</s:if>

										<s:if test="first_update_status!=null">
											<td><font color="black"><s:property
														value="fpn_number" /> </font></td>

											<td><font color="black"><s:property
														value="issue_date_time" /> </font></td>

											<td><font color="black"><s:property
														value="amount_paid" /> </font></td>
														
										<s:if test="second_update_status==null">
										
										<td><font color="black"><input type="text"
													name="received_date" id="received_date" maxlength="10"
													class="field"></font></td>
										
										</s:if>		
										
															
												

										</s:if>

									</tr>

								</tbody>
							</s:iterator>
						</table>


						<div class="form-group row">
							<div class="col-md-12 text-center">
								<s:submit method="saveFPNDetails"
									class="btn waves-effect waves-light btn-info" value="Update"
									theme="simple" />

							</div>
						</div>


					</s:if>
				</div>

				<s:hidden name="empID" id="empID" />
				<s:hidden name="rowNumber" id="rowNumber" />


			</s:form>

		</div>








	</div>




</body>
<script type="text/javascript">
	window.history.forward(0);
</script>
</html>
