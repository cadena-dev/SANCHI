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
					<h5>FPN File Validation</h5>
				</div>
				<div class="card-block">

					<s:form action="FPNExportValidate" validate="true" namespace="/"
						method="post"  theme="bootstrap" >

						<div class="col-md-12">
							<div class="row">
								<s:actionerror theme="saanchi"
									cssClass="form-control form-bg-danger" />
								<s:actionmessage theme="saanchi"
									cssClass="form-control form-bg-info" />
							</div>
						</div>
						
						
						
									
										
	
						

					<div class="table-responsive" >

						<s:if test="%{!fpnValidationLogicList.isEmpty()}">

							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th scope="col"><font color="blue"><b>#</b></font></th>
										<th scope="col"><font color="blue"><b>Works
													reference number</b></font></th>
										<th scope="col"><font color="blue"><b>FPN
													number</b></font></th>
										<th scope="col"><font color="blue"><b>FPN
													status</b></font></th>
										<th scope="col"><font color="blue"><b>Offence
													code</b></font></th>

										<th scope="col"><font color="blue"><b>Offence
													date</b></font></th>
										<th scope="col"><font color="blue"><b>Offence
													details</b></font></th>
										<th scope="col"><font color="blue"><b>Issue
													date and time</b></font></th>
										<th scope="col"><font color="blue"><b>Street
													name</b></font></th>
										<th scope="col"><font color="blue"><b>USRN</b></font></th>
										<th scope="col"><font color="blue"><b>Works
													promoter</b></font></th>
										<th scope="col"><font color="blue"><b>Issuing
													authority</b></font></th>
										<th scope="col"><font color="blue"><b>Status
													changed date</b></font></th>
										<th scope="col"><font color="blue"><b>Amount
													Paid</b></font></th>
									</tr>
								</thead>

								<%
								int i = 0;
								%>

								<s:iterator value="fpnValidationLogicList" var="fpnValidationLogicList">

									<%
									i++;
									%>

									<tbody>
										<tr>
										
                                             
											<th scope="row"><font color="blue"> <%=i%></font></th>
											<s:hidden name="fpn_export_stage_id" id="fpn_export_stage_id"/>
											
											
											<s:if test="works_reference_number_validate==true">	
											<td><font color="black"><input type="text"  name="works_reference_number" id="works_reference_number" maxlength="100" class="field" value="<s:property value="works_reference_number" />">	</font>
												</td>
											</s:if>	
											
											<s:elseif test="works_reference_number_validate==false">	
										    <td bgcolor="red"><input type="text" name="works_reference_number" id="works_reference_number" maxlength="100" class="field" value="<s:property value="works_reference_number" />">	</td>		
											</s:elseif>	
												
											<s:if test="fpn_number_validate==true">	
											<td><font color="black"><input type="text"  name="fpn_number" id="fpn_number" maxlength="100" class="field" value="<s:property value="fpn_number" />"></font></td>
										   </s:if>	
										   
										   <s:elseif  test="fpn_number_validate==false">	
											<td bgcolor="red"><input type="text" name="fpn_number" id="fpn_number" maxlength="100" class="field" value="<s:property value="fpn_number" />">	</td>			
										   </s:elseif >	
										   
										   <s:if test="fpn_status_validate==true">	
										   <td><font color="black"><input type="text"  name="fpn_status" id="fpn_status" maxlength="100" class="field" value="<s:property value="fpn_status" />"></font></td>
											</s:if>			
													
													 <s:elseif test="fpn_status_validate==false">	
										  <td bgcolor="red"><input type="text" name="fpn_status" id="fpn_status" maxlength="100" class="field" value="<s:property value="fpn_status" />">	</td>			
											</s:elseif>		
														
														
											 <s:if test="offence_code_validate==true">				
											<td ><font color="black"><input type="text"  name="offence_code" id="offence_code" maxlength="100" class="field" value="<s:property value="offence_code" />"></font></td>
											</s:if>
											
											 <s:elseif test="offence_code_validate==false">				
											<td bgcolor="red"><input type="text" name="offence_code" id="offence_code" maxlength="100" class="field" value="<s:property value="offence_code" />">	</td>		
											</s:elseif>				
														
													 <s:if test="offence_date_validate==true">		
														<td><font color="black"><input type="text"  name="offence_date" id="offence_date" maxlength="100" class="field" value="<s:property value="offence_date" />"></font></td>
														</s:if>
														
														 <s:elseif test="offence_date_validate==false">		
														<td bgcolor="red"><input type="text" name="offence_date" id="offence_date" maxlength="100" class="field" value="<s:property value="offence_date" />">	</td>
														</s:elseif>
														
														
														<s:if test="offence_details_validate==true">		
														<td><font color="black"><input type="text"  name="offence_details" id="offence_details" maxlength="1000" class="field" value="<s:property value="offence_details" />"></font></td>
														</s:if>
														
														<s:elseif test="offence_details_validate==false">		
														<td bgcolor="red"><input type="text" name="offence_details" id="offence_details" maxlength="1000" class="field" value="<s:property value="offence_details" />">	</td>
														</s:elseif>
														
														<s:if test="issue_date_time_validate==true">		
														<td><font color="black"><input type="text"  name="issue_date_time" id="issue_date_time" maxlength="100" class="field" value="<s:property value="issue_date_time" />"></font></td>
														</s:if>
														
														<s:elseif test="issue_date_time_validate==false">		
														<td bgcolor="red"><input type="text" name="issue_date_time" id="issue_date_time" maxlength="100" class="field" value="<s:property value="issue_date_time" />">	</td>
														</s:elseif>
														
														
														<s:if test="street_name_validate==true">		
														<td><font color="black"><input type="text"  name="street_name" id="street_name" maxlength="100" class="field" value="<s:property value="street_name" />"></font></td>
														</s:if>
														
														<s:elseif test="street_name_validate==false">		
														<td bgcolor="red"><input type="text" name="street_name" id="street_name" maxlength="100" class="field" value="<s:property value="street_name" />">	</td>
														</s:elseif>
														
														<s:if test="uern_validate==true">	
														<td><font color="black"><input type="text"  name="usrn" id="usrn" maxlength="100" class="field" value="<s:property value="usrn" />"></font></td>
														</s:if>
														
														<s:elseif test="uern_validate==false">	
														<td bgcolor="red"><input type="text" name="usrn" id="usrn" maxlength="100" class="field" value="<s:property value="usrn" />">	</td>
														</s:elseif>
														
														<s:if test="works_promoter_validate==true">	
														<td><font color="black"><input type="text"  name="works_promoter" id="works_promoter" maxlength="100" class="field" value="<s:property value="works_promoter" />"></font></td>
														</s:if>
														
														<s:elseif test="works_promoter_validate==false">	
														<td bgcolor="red"><input type="text" name="works_promoter" id="works_promoter" maxlength="100" class="field" value="<s:property value="works_promoter" />">	</td>
														</s:elseif>
														
														<s:if test="issuing_authority_validate==true">	
														<td><font color="black"><input type="text"  name="issuing_authority" id="issuing_authority" maxlength="100" class="field" value="<s:property value="issuing_authority" />"></font></td>
														</s:if>
														
														<s:elseif test="issuing_authority_validate==false">	
														<td bgcolor="red"><input type="text" name="issuing_authority" id="issuing_authority" maxlength="100" class="field" value="<s:property value="issuing_authority" />">	</td>
														</s:elseif>
														
														<s:if test="status_changed_date_validate==true">	
														<td><font color="black"><input type="text"  name="status_changed_date" id="status_changed_date" maxlength="100" class="field" value="<s:property value="status_changed_date" />"></font></td>
														</s:if>
														
														<s:elseif test="status_changed_date_validate==false">	
														<td bgcolor="red"><input type="text" name="status_changed_date" id="status_changed_date" maxlength="100" class="field" value="<s:property value="status_changed_date" />">	</td>
														</s:elseif>
														
														<s:if test="amount_paid_validate==true">	
														<td><font color="black"><input type="text"  name="amount_paid" id="amount_paid" maxlength="100" class="field" value="<s:property value="amount_paid" />">	</font></td>
														</s:if>
														
														<s:elseif test="amount_paid_validate==false">	
														<td bgcolor="red"><input type="text" name="amount_paid" id="amount_paid" maxlength="100" class="field" value="<s:property value="amount_paid" />">	</td>
														</s:elseif>
										</tr>

									</tbody>
								</s:iterator>
							</table>

						</s:if>
					</div>
					
					<s:if test="%{fpnValidationLogicList.isEmpty()}">
					<div class="form-group row">
       
       
						<div class="col-6">

							<s:textarea name="comment"
							id="comment" cols="30" rows="4" />

						</div>
                 

						
						
                  
       
       </div>
       </s:if>
       
       
       
					
					<div class="form-group row">
							<div class="col-md-12 text-center">
								<s:if test="%{!fpnValidationLogicList.isEmpty()}">
									<s:submit method="saveDataToStage"
										class="btn waves-effect waves-light btn-info" value="Save"
										theme="simple" />
								</s:if>
								<s:if test="%{fpnValidationLogicList.isEmpty()}">
									<s:submit method="confirmToValidation"
										class="btn waves-effect waves-light btn-info" value="Confirm"
										theme="simple" />
								</s:if>

								<input type="button" name="Exit" value="Exit"
									class="btn waves-effect waves-light btn-info"
									onClick="window.location.href='exitFPNExportValidate';">
							</div>
						</div>
				
					
	<s:hidden name="empID" id="empID" />
	<s:hidden name="file_name" id="file_name" />

</s:form>	


	
				</div>
			</div>
		</div>
	</div>







</body>
<script type="text/javascript">
	window.history.forward(0);

	window.history.replaceState('', '', '?');
</script>
</html>
