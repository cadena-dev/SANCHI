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
					<h5>Change Request File Validation</h5>
				</div>
				<div class="card-block">

					<s:form action="ChangeRequestValidate" validate="true" namespace="/"
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

						<s:if test="%{!changeExportValidationLogicList.isEmpty()}">

							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th scope="col"><font color="blue"><b>#</b></font></th>
										<th scope="col"><font color="blue"><b>WORK REFERENCE NUMBER</b></font></th>
										<th scope="col"><font color="blue"><b>PERMIT REFERENCE NUMBER</b></font></th>
										<th scope="col"><font color="blue"><b>CHANGE REQUEST TYPE</b></font></th>										
										<th scope="col"><font color="blue"><b>APPLICATION TYPE</b></font></th>
										<th scope="col"><font color="blue"><b>TYPE OF WORK</b></font></th>
										<th scope="col"><font color="blue"><b>CHANGE STATUS</b></font></th>
										<th scope="col"><font color="blue"><b>CHANGE SUBMISSION DATE</b></font></th>
										<th scope="col"><font color="blue"><b>ASSESSMENT DISCOUNT</b></font></th>
										<th scope="col"><font color="blue"><b>PA PROPOSED START DATE</b></font></th>
										<th scope="col"><font color="blue"><b>PA PROPOSED END DATE</b></font></th>
										<th scope="col"><font color="blue"><b>DURATION</b></font></th>
									</tr>
								</thead>

								<%
								int i = 0;
								%>

								<s:iterator value="changeExportValidationLogicList" var="changeExportValidationLogicList">

									<%
									i++;
									%>

									<tbody>
										<tr>
										
                                             
											<th scope="row"><font color="blue"> <%=i%></font></th>
											<s:hidden name="change_request_stage_id" id="change_request_stage_id"/>
											<s:hidden name="promoter_name" id="promoter_name"/>
											
											
											<s:if test="change_request_reference_number_validated==true">	
											<td><font color="black"><input type="text"  name="change_request_reference_number" id="change_request_reference_number" maxlength="100" class="field" value="<s:property value="change_request_reference_number" />"></font>
												</td>
											</s:if>	
											
											<s:elseif test="change_request_reference_number_validated==false">	
										    <td bgcolor="red"><input type="text" name="change_request_reference_number" id="change_request_reference_number" maxlength="100" class="field" value="<s:property value="change_request_reference_number" />">	</td>		
											</s:elseif>	
												
											<s:if test="permit_reference_number_validated==true">	
											<td><font color="black"><input type="text"  name="permit_reference_number" id="permit_reference_number" maxlength="100" class="field" value="<s:property value="permit_reference_number" />"></font></td>
										   </s:if>	
										   
										   <s:elseif  test="permit_reference_number_validated==false">	
											<td bgcolor="red"><input type="text" name="permit_reference_number" id="permit_reference_number" maxlength="100" class="field" value="<s:property value="permit_reference_number" />">	</td>			
										   </s:elseif >	
										   
										   <s:if test="change_request_type_validated==true">	
										   <td><font color="black"><input type="text"  name="change_request_type" id="change_request_type" maxlength="100" class="field" value="<s:property value="change_request_type" />"></font></td>
											</s:if>			
													
													 <s:elseif test="change_request_type_validated==false">	
										  <td bgcolor="red"><input type="text" name="change_request_type" id="change_request_type" maxlength="100" class="field" value="<s:property value="change_request_type" />">	</td>			
											</s:elseif>		
														
														
											 <s:if test="application_type_validated==true">				
											<td ><font color="black"><input type="text"  name="application_type" id="application_type" maxlength="100" class="field" value="<s:property value="application_type" />"></font></td>
											</s:if>
											
											 <s:elseif test="application_type_validated==false">				
											<td bgcolor="red"><input type="text" name="application_type" id="application_type" maxlength="100" class="field" value="<s:property value="application_type" />">	</td>		
											</s:elseif>				
													<s:if test="type_of_work_validated==true">		
														<td><font color="black"><input type="text"   name="type_of_work" id="type_of_work" maxlength="100" class="field" value="<s:property value="type_of_work" /> "></font></td>
														</s:if>
														
														 <s:elseif test="type_of_work_validated==false">		
														<td bgcolor="red"><input type="text" name="type_of_work" id="type_of_work" maxlength="100" class="field" value="<s:property value="type_of_work" />">	</td>
														</s:elseif>
														
														
													
														
														<s:if test="change_status_validated==true">		
														<td><font color="black"><input type="text"  name="change_status" id="change_status" maxlength="100" class="field" value="<s:property value="change_status" />"></font></td>
														</s:if>
														
														<s:elseif test="change_status_validated==false">		
														<td bgcolor="red"><input type="text" name="change_status" id="change_status" maxlength="100" class="field" value="<s:property value="change_status" />">	</td>
														</s:elseif>
														
														<s:if test="change_submission_date_validated==true">		
														<td><font color="black"><input type="text"  name="change_submission_date" id="change_submission_date" maxlength="100" class="field" value="<s:property value="change_submission_date" />"></font></td>
														</s:if>
														
														<s:elseif test="change_submission_date_validated==false">		
														<td bgcolor="red"><input type="text" name="change_submission_date" id="change_submission_date" maxlength="100" class="field" value="<s:property value="change_submission_date" />">	</td>
														</s:elseif>
														
														<s:if test="assessment_discount_validated==true">	
														<td><font color="black"><input type="text"  name="assessment_discount" id="assessment_discount" maxlength="100" class="field" value="<s:property value="assessment_discount" />"></font></td>
														</s:if>
														
														<s:elseif test="assessment_discount_validated==false">	
														<td bgcolor="red"><input type="text" name="assessment_discount" id="assessment_discount" maxlength="100" class="field" value="<s:property value="assessment_discount" />">	</td>
														</s:elseif>
														
														
														
														
														<s:if test="pa_proposed_start_date_validated==true">	
														<td><font color="black"><input type="text"  name="pa_proposed_start_date" id="pa_proposed_start_date" maxlength="100" class="field" value="<s:property value="pa_proposed_start_date" />"></font></td>
														</s:if>
														
														<s:elseif test="pa_proposed_start_date_validated==false">	
														<td bgcolor="red"><input type="text" name="pa_proposed_start_date" id="pa_proposed_start_date" maxlength="100" class="field" value="<s:property value="pa_proposed_start_date" />">	</td>
														</s:elseif>
														
														<s:if test="pa_proposed_end_date_validated==true">	
														<td><font color="black"><input type="text"  name="pa_proposed_end_date" id="pa_proposed_end_date" maxlength="100" class="field" value="<s:property value="pa_proposed_end_date" />"></font></td>
														</s:if>
														
														<s:elseif test="pa_proposed_end_date_validated==false">	
														<td bgcolor="red"><input type="text" name="pa_proposed_end_date" id="pa_proposed_end_date" maxlength="100" class="field" value="<s:property value="pa_proposed_end_date" />">	</td>
														</s:elseif>
														
														<s:if test="duration_validated==true">	
														<td><font color="black"><input type="text"  name="duration" id="duration" maxlength="100" class="field" value="<s:property value="duration" />"></font></td>
														</s:if>
														
														<s:elseif test="duration_validated==false">	
														<td bgcolor="red"><input type="text" name="duration" id="duration" maxlength="100" class="field" value="<s:property value="duration" />">	</td>
														</s:elseif>
														
														
													
														
										</tr>

									</tbody>
								</s:iterator>
							</table>

						</s:if>
					</div>
					
					<s:if test="%{changeExportValidationLogicList.isEmpty()}">
					<div class="form-group row">
       
       
						<div class="col-6">

							<s:textarea name="comment"
							id="comment" cols="30" rows="4" />

						</div>
                 

						
						
                  
       
       </div>
       </s:if>
       
       
       
					
					<div class="form-group row">
							<div class="col-md-12 text-center">
								<s:if test="%{!changeExportValidationLogicList.isEmpty()}">
									<s:submit method="saveChangeRequestDataToStage"
										class="btn waves-effect waves-light btn-info" value="Save"
										theme="simple" />
								</s:if>
								<s:if test="%{changeExportValidationLogicList.isEmpty()}">
									<s:submit method="confirmChangeRequestToValidation"
										class="btn waves-effect waves-light btn-info" value="Confirm"
										theme="simple" />
								</s:if>

								<input type="button" name="Exit" value="Exit"
									class="btn waves-effect waves-light btn-info"
									onClick="window.location.href='exitChangeRequestValidate';">
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
