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
					<h5>PA File Validation</h5>
				</div>
				<div class="card-block">

					<s:form action="PAExportValidate" validate="true" namespace="/"
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

						<s:if test="%{!paValidationLogicList.isEmpty()}">

							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th scope="col"><font color="blue"><b>#</b></font></th>
										<th scope="col"><font color="blue"><b>DATE OF PERMITS</b></font></th>
										<th scope="col"><font color="blue"><b>PROMOTER NAME</b></font></th>
										<th scope="col"><font color="blue"><b>WORK REFERENCE NUMBER</b></font></th>
										<th scope="col"><font color="blue"><b>PERMIT REFERENCE NUMBER</b></font></th>
										<th scope="col"><font color="blue"><b>APPLICATION TYPE</b></font></th>
										<th scope="col"><font color="blue"><b>TYPE OF WORK</b></font></th>
										<th scope="col"><font color="blue"><b>LOCATION</b></font></th>
										<th scope="col"><font color="blue"><b>STREET</b></font></th>
										<th scope="col"><font color="blue"><b>TOWN</b></font></th>
										<th scope="col"><font color="blue"><b>USRN</b></font></th>
										<th scope="col"><font color="blue"><b>ACTIVITY TYPE</b></font></th>
										<th scope="col"><font color="blue"><b>WORK DESCRIPTION</b></font></th>
										<th scope="col"><font color="blue"><b>WORKS WITH EXCAVATION</b></font></th>
										<th scope="col"><font color="blue"><b>REINSTATEMENT CATEGORY</b></font></th>
										<th scope="col"><font color="blue"><b>TRAFFIC MANAGEMENT METHOD</b></font></th>
										<th scope="col"><font color="blue"><b>COLLABORATIVE WORKING</b></font></th>
										<th scope="col"><font color="blue"><b>EASTIMATED START DATE</b></font></th>
										<th scope="col"><font color="blue"><b>PROPOSED START TIME</b></font></th>
										<th scope="col"><font color="blue"><b>PROPOSED END DATE</b></font></th>
										<th scope="col"><font color="blue"><b>PROPOSED END TIME</b></font></th>
										<th scope="col"><font color="blue"><b>DURATION OF WORK</b></font></th>
										<th scope="col"><font color="blue"><b>PERMIT STATUS</b></font></th>
										<th scope="col"><font color="blue"><b>PERMIT DEEMING DATE</b></font></th>
										<th scope="col"><font color="blue"><b>DISCOUNT</b></font></th>
									</tr>
								</thead>

								<%
								int i = 0;
								%>

								<s:iterator value="paValidationLogicList" var="paValidationLogicList">

									<%
									i++;
									%>

									<tbody>
										<tr>
										
                                             
											<th scope="row"><font color="blue"> <%=i%></font></th>
											<s:hidden name="pa_export_stage_id" id="pa_export_stage_id"/>
											
											
											<s:if test="date_of_permits_validate==true">	
											<td><font color="black"><input type="text"  name="date_of_permits" id="date_of_permits" maxlength="100" class="field" value="<s:property value="date_of_permits" />"></font>
												</td>
											</s:if>	
											
											<s:elseif test="date_of_permits_validate==false">	
										    <td bgcolor="red"><input type="text" name="date_of_permits" id="date_of_permits" maxlength="100" class="field" value="<s:property value="date_of_permits" />">	</td>		
											</s:elseif>	
												
											<s:if test="promoter_name_validate==true">	
											<td><font color="black"><input type="text"  name="promoter_name" id="promoter_name" maxlength="100" class="field" value="<s:property value="promoter_name" />"></font></td>
										   </s:if>	
										   
										   <s:elseif  test="promoter_name_validate==false">	
											<td bgcolor="red"><input type="text" name="promoter_name" id="promoter_name" maxlength="100" class="field" value="<s:property value="promoter_name" />">	</td>			
										   </s:elseif >	
										   
										   <s:if test="work_reference_number_validate==true">	
										   <td><font color="black"><input type="text"  name="work_reference_number" id="work_reference_number" maxlength="100" class="field" value="<s:property value="work_reference_number" />"></font></td>
											</s:if>			
													
													 <s:elseif test="work_reference_number_validate==false">	
										  <td bgcolor="red"><input type="text" name="work_reference_number" id="work_reference_number" maxlength="100" class="field" value="<s:property value="work_reference_number" />">	</td>			
											</s:elseif>		
														
														
											 <s:if test="permit_reference_number_validate==true">				
											<td ><font color="black"><input type="text"  name="permit_reference_number" id="permit_reference_number" maxlength="100" class="field" value="<s:property value="permit_reference_number" />"></font></td>
											</s:if>
											
											 <s:elseif test="permit_reference_number_validate==false">				
											<td bgcolor="red"><input type="text" name="permit_reference_number" id="permit_reference_number" maxlength="100" class="field" value="<s:property value="permit_reference_number" />">	</td>		
											</s:elseif>				
														
													 <s:if test="application_type_validate==true">		
														<td><font color="black"><input type="text"   name="application_type" id="application_type" maxlength="100" class="field" value="<s:property value="application_type" /> "></font></td>
														</s:if>
														
														 <s:elseif test="application_type_validate==false">		
														<td bgcolor="red"><input type="text" name="application_type" id="application_type" maxlength="100" class="field" value="<s:property value="application_type" />">	</td>
														</s:elseif>
														
														
														<s:if test="type_of_work_validate==true">		
														<td><font color="black"><input type="text" name="type_of_work"  id="type_of_work" maxlength="1000" class="field" value="<s:property value="type_of_work" />"></font></td>
														</s:if>
														
														<s:elseif test="type_of_work_validate==false">		
														<td bgcolor="red"><input type="text" name="type_of_work" id="type_of_work" maxlength="1000" class="field" value="<s:property value="type_of_work" />">	</td>
														</s:elseif>
														
														<s:if test="location_validate==true">		
														<td><font color="black"><input type="text"  name="location" id="location" maxlength="100" class="field" value="<s:property value="location" />"></font></td>
														</s:if>
														
														<s:elseif test="location_validate==false">		
														<td bgcolor="red"><input type="text" name="location" id="location" maxlength="100" class="field" value="<s:property value="location" />">	</td>
														</s:elseif>
														
														
														<s:if test="street_validate==true">		
														<td><font color="black"><input type="text"  name="street" id="street" maxlength="100" class="field" value="<s:property value="street" />"></font></td>
														</s:if>
														
														<s:elseif test="street_validate==false">		
														<td bgcolor="red"><input type="text" name="street" id="street" maxlength="100" class="field" value="<s:property value="street" />">	</td>
														</s:elseif>
														
														<s:if test="town_validate==true">	
														<td><font color="black"><input type="text"  name="town" id="town" maxlength="100" class="field" value="<s:property value="town" />"></font></td>
														</s:if>
														
														<s:elseif test="town_validate==false">	
														<td bgcolor="red"><input type="text" name="town" id="town" maxlength="100" class="field" value="<s:property value="town" />">	</td>
														</s:elseif>
														
														<s:if test="usrn_validate==true">	
														<td><font color="black"><input type="text"  name="usrn" id="usrn" maxlength="100" class="field" value="<s:property value="usrn" />"></font></td>
														</s:if>
														
														<s:elseif test="usrn_validate==false">	
														<td bgcolor="red"><input type="text" name="usrn" id="usrn" maxlength="100" class="field" value="<s:property value="usrn" />">	</td>
														</s:elseif>
														
														<s:if test="activity_type_validate==true">	
														<td><font color="black"><input type="text"  name="activity_type" id="activity_type" maxlength="100" class="field" value="<s:property value="activity_type" />"></font></td>
														</s:if>
														
														<s:elseif test="activity_type_validate==false">	
														<td bgcolor="red"><input type="text" name="activity_type" id="activity_type" maxlength="100" class="field" value="<s:property value="activity_type" />">	</td>
														</s:elseif>
														
														<s:if test="work_description_validate==true">	
														<td><font color="black"><input type="text"  name="work_description" id="work_description" maxlength="100" class="field" value="<s:property value="work_description" />"></font></td>
														</s:if>
														
														<s:elseif test="work_description_validate==false">	
														<td bgcolor="red"><input type="text" name="work_description" id="work_description" maxlength="100" class="field" value="<s:property value="work_description" />">	</td>
														</s:elseif>
														
														<s:if test="works_with_excavation_validate==true">	
														<td><font color="black"><input type="text"  name="works_with_excavation" id="works_with_excavation" maxlength="100" class="field" value="<s:property value="works_with_excavation" />"></font></td>
														</s:if>
														
														<s:elseif test="works_with_excavation_validate==false">	
														<td bgcolor="red"><input type="text" name="works_with_excavation" id="works_with_excavation" maxlength="100" class="field" value="<s:property value="works_with_excavation" />">	</td>
														</s:elseif>
														
													
														
														<s:if test="reinstatement_category_validate==true">	
														<td><font color="black"><input type="text"  name="reinstatement_category" id="reinstatement_category" maxlength="100" class="field" value="<s:property value="reinstatement_category" />">	</font></td>
														</s:if>
														
														<s:elseif test="reinstatement_category_validate==false">	
														<td bgcolor="red"><input type="text" name="reinstatement_category" id="reinstatement_category" maxlength="100" class="field" value="<s:property value="reinstatement_category" />">	</td>
														</s:elseif>
														
														<s:if test="traffic_management_method_validate==true">	
														<td><font color="black"><input type="text"  name="traffic_management_method" id="traffic_management_method" maxlength="100" class="field" value="<s:property value="traffic_management_method" />"></font></td>
														</s:if>
														
														<s:elseif test="traffic_management_method_validate==false">	
														<td bgcolor="red"><input type="text" name="traffic_management_method" id="traffic_management_method" maxlength="100" class="field" value="<s:property value="traffic_management_method" />">	</td>
														</s:elseif>
														
														<s:if test="collaborative_working_validate==true">	
														<td><font color="black"><input type="text"  name="collaborative_working" id="collaborative_working" maxlength="100" class="field" value="<s:property value="collaborative_working" />"></font></td>
														</s:if>
														
														<s:elseif test="collaborative_working_validate==false">	
														<td bgcolor="red"><input type="text" name="collaborative_working" id="collaborative_working" maxlength="100" class="field" value="<s:property value="collaborative_working" />">	</td>
														</s:elseif>
														
														
														
														<s:if test="eastimated_start_date_validate==true">	
														<td><font color="black"><input type="text"  name="eastimated_start_date" id="eastimated_start_date" maxlength="100" class="field" value="<s:property value="eastimated_start_date" />"></font></td>
														</s:if>
														
														<s:elseif test="eastimated_start_date_validate==false">	
														<td bgcolor="red"><input type="text" name="eastimated_start_date" id="eastimated_start_date" maxlength="100" class="field" value="<s:property value="eastimated_start_date" />">	</td>
														</s:elseif>
													
															
															
																<s:if test="proposed_start_time_validate==true">	
														<td><font color="black"><input type="text"  name="proposed_start_time" id="proposed_start_time" maxlength="100" class="field" value="<s:property value="proposed_start_time" />"></font></td>
														</s:if>
														
														<s:elseif test="proposed_start_time_validate==false">	
														<td bgcolor="red"><input type="text" name="proposed_start_time" id="proposed_start_time" maxlength="100" class="field" value="<s:property value="proposed_start_time" />">	</td>
														</s:elseif>
														
														<s:if test="proposed_end_date_validate==true">	
														<td><font color="black"><input type="text"  name="proposed_end_date" id="proposed_end_date" maxlength="100" class="field" value="<s:property value="proposed_end_date" />"></font></td>
														</s:if>
														
														<s:elseif test="proposed_end_date_validate==false">	
														<td bgcolor="red"><input type="text" name="proposed_end_date" id="proposed_end_date" maxlength="100" class="field" value="<s:property value="proposed_end_date" />">	</td>
														</s:elseif>
														
														<s:if test="proposed_end_time_validate==true">	
														<td><font color="black"><input type="text"   name="proposed_end_time" id="proposed_end_time" maxlength="100" class="field" value="<s:property value="proposed_end_time" />"></font></td>
														</s:if>
														
														<s:elseif test="proposed_end_time_validate==false">	
														<td bgcolor="red"><input type="text" name="proposed_end_time" id="proposed_end_time" maxlength="100" class="field" value="<s:property value="proposed_end_time" />">	</td>
														</s:elseif>
														
														<s:if test="duration_of_work_validate==true">	
														<td><font color="black"><input type="text"  name="duration_of_work" id="duration_of_work" maxlength="100" class="field" value="<s:property value="duration_of_work" />"></font></td>
														</s:if>
														
														<s:elseif test="duration_of_work_validate==false">	
														<td bgcolor="red"><input type="text" name="duration_of_work" id="duration_of_work" maxlength="100" class="field" value="<s:property value="duration_of_work" />">	</td>
														</s:elseif>
														
														<s:if test="permit_status_validate==true">	
														<td><font color="black"><input type="text"  name="permit_status" id="permit_status" maxlength="100" class="field" value="<s:property value="permit_status" />"></font></td>
														</s:if>
														
														<s:elseif test="permit_status_validate==false">	
														<td bgcolor="red"><input type="text" name="permit_status" id="permit_status" maxlength="100" class="field" value="<s:property value="permit_status" />">	</td>
														</s:elseif>
														
														
															<s:if test="permit_deeming_date_validate==true">	
														<td><font color="black"><input type="text"  name="permit_deeming_date" id="permit_deeming_date" maxlength="100" class="field" value="<s:property value="permit_deeming_date" />"></font></td>
														</s:if>
														
															<s:elseif test="permit_deeming_date_validate==false">	
														<td bgcolor="red"><input type="text" name="permit_deeming_date" id="permit_deeming_date" maxlength="100" class="field" value="<s:property value="permit_deeming_date" />">	</td>
														</s:elseif>
														
														
														
														
															<s:if test="discount_validate==true">	
														<td><font color="black"><input type="text"  name="discount" id="discount" maxlength="100" class="field" value="<s:property value="discount" />"></font></td>
														</s:if>
														
															<s:elseif test="discount_validate==false">	
														<td bgcolor="red"><input type="text" name="discount" id="discount" maxlength="100" class="field" value="<s:property value="discount" />">	</td>
														</s:elseif>
													
														
										</tr>

									</tbody>
								</s:iterator>
							</table>

						</s:if>
					</div>
					
					<s:if test="%{paValidationLogicList.isEmpty()}">
					<div class="form-group row">
       
       
						<div class="col-6">

							<s:textarea name="comment"
							id="comment" cols="30" rows="4" />

						</div>
                 

						
						
                  
       
       </div>
       </s:if>
       
       
       
					
					<div class="form-group row">
							<div class="col-md-12 text-center">
								<s:if test="%{!paValidationLogicList.isEmpty()}">
									<s:submit method="savePADataToStage"
										class="btn waves-effect waves-light btn-info" value="Save"
										theme="simple" />
								</s:if>
								<s:if test="%{paValidationLogicList.isEmpty()}">
									<s:submit method="confirmPAToValidation"
										class="btn waves-effect waves-light btn-info" value="Confirm"
										theme="simple" />
								</s:if>

								<input type="button" name="Exit" value="Exit"
									class="btn waves-effect waves-light btn-info"
									onClick="window.location.href='exitPAExportValidate';">
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
