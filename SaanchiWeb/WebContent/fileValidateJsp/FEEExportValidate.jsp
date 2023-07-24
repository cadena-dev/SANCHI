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
					<h5>Fee Export File Validation</h5>
				</div>
				<div class="card-block">

					<s:form action="FEEExportValidate" validate="true" namespace="/"
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

						<s:if test="%{!feeValidationLogicList.isEmpty()}">

							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th scope="col"><font color="blue"><b>#</b></font></th>
										<th scope="col"><font color="blue"><b>ORG CODE</b></font></th>
										<th scope="col"><font color="blue"><b>ORG NAME</b></font></th>
										<th scope="col"><font color="blue"><b>PROMOTER WORKSTREAM</b></font></th>
										<th scope="col"><font color="blue"><b>WORK CATEGORY</b></font></th>
										<th scope="col"><font color="blue"><b>TRANSACTION TYPE</b></font></th>
										<th scope="col"><font color="blue"><b>DISCOUNT PERCENTAGE</b></font></th>
										<th scope="col"><font color="blue"><b>PERMIT REFERENCE</b></font></th>
										<th scope="col"><font color="blue"><b>GRANTED DATE</b></font></th>
										<th scope="col"><font color="blue"><b>USRN</b></font></th>
										<th scope="col"><font color="blue"><b>STREET</b></font></th>
										<th scope="col"><font color="blue"><b>TOWN</b></font></th>
										<th scope="col"><font color="blue"><b>ROAD CATEGORY</b></font></th>
										<th scope="col"><font color="blue"><b>IS WORKS TRAFFIC SENSITIVE</b></font></th>
										<th scope="col"><font color="blue"><b>IS STREET TRAFFIC SENSITIVE</b></font></th>
										<th scope="col"><font color="blue"><b>TRAFFIC MANAGEMENT TYPE</b></font></th>
										<th scope="col"><font color="blue"><b>PROPOSED START DATE</b></font></th>
										<th scope="col"><font color="blue"><b>PROPOSED END DATE</b></font></th>
										<th scope="col"><font color="blue"><b>CHANGE REQUEST REFERENCE</b></font></th>
									</tr>
								</thead>

								<%
								int i = 0;
								%>

								<s:iterator value="feeValidationLogicList" var="feeValidationLogicList">

									<%
									i++;
									%>

									<tbody>
										<tr>
										
                                             
											<th scope="row"><font color="blue"> <%=i%></font></th>
											<s:hidden name="fee_export_stage_id" id="fee_export_stage_id"/>
											
											
											<s:if test="org_code_validate==true">	
											<td><font color="black"><input type="text"  name="org_code" id="org_code" maxlength="100" class="field" value="<s:property value="org_code" />">	</font>
												</td>
											</s:if>	
											
											<s:elseif test="org_code_validate==false">	
										    <td bgcolor="red"><input type="text" name="org_code" id="org_code" maxlength="100" class="field" value="<s:property value="org_code" />">	</td>		
											</s:elseif>	
												
											<s:if test="org_name_validate==true">	
											<td><font color="black"><input type="text"  name="org_name" id="org_name" maxlength="100" class="field" value="<s:property value="org_name" />"></font></td>
										   </s:if>	
										   
										   <s:elseif  test="org_name_validate==false">	
											<td bgcolor="red"><input type="text" name="org_name" id="org_name" maxlength="100" class="field" value="<s:property value="org_name" />">	</td>			
										   </s:elseif >	
										   
										   <s:if test="promoter_workstream_validate==true">	
										   <td><font color="black"><input type="text"  name="promoter_workstream" id="promoter_workstream" maxlength="100" class="field" value="<s:property value="promoter_workstream" />"></font></td>
											</s:if>			
													
													 <s:elseif test="promoter_workstream_validate==false">	
										  <td bgcolor="red"><input type="text" name="promoter_workstream" id="promoter_workstream" maxlength="100" class="field" value="<s:property value="promoter_workstream" />">	</td>			
											</s:elseif>		
														
														
											 <s:if test="work_category_validate==true">				
											<td ><font color="black"><input type="text"  name="work_category" id="work_category" maxlength="100" class="field" value="<s:property value="work_category" />"></font></td>
											</s:if>
											
											 <s:elseif test="work_category_validate==false">				
											<td bgcolor="red"><input type="text" name="work_category" id="work_category" maxlength="100" class="field" value="<s:property value="work_category" />">	</td>		
											</s:elseif>				
														
													 <s:if test="transaction_type_validate==true">		
														<td><font color="black"><input type="text"  name="transaction_type" id="transaction_type" maxlength="100" class="field" value="<s:property value="transaction_type" />"></font></td>
														</s:if>
														
														 <s:elseif test="transaction_type_validate==false">		
														<td bgcolor="red"><input type="text" name="transaction_type" id="transaction_type" maxlength="100" class="field" value="<s:property value="transaction_type" />">	</td>
														</s:elseif>
														
														
														<s:if test="discount_percentage_validate==true">		
														<td><font color="black"><input type="text"  name="discount_percentage" id="discount_percentage" maxlength="1000" class="field" value="<s:property value="discount_percentage" />"></font></td>
														</s:if>
														
														<s:elseif test="discount_percentage_validate==false">		
														<td bgcolor="red"><input type="text" name="discount_percentage" id="discount_percentage" maxlength="1000" class="field" value="<s:property value="discount_percentage" />">	</td>
														</s:elseif>
														
														<s:if test="permit_reference_validate==true">		
														<td><font color="black"><input type="text"  name="permit_reference" id="permit_reference" maxlength="100" class="field" value="<s:property value="permit_reference" />"></font></td>
														</s:if>
														
														<s:elseif test="permit_reference_validate==false">		
														<td bgcolor="red"><input type="text" name="permit_reference" id="permit_reference" maxlength="100" class="field" value="<s:property value="permit_reference" />">	</td>
														</s:elseif>
														
														
														<s:if test="granted_date_validate==true">		
														<td><font color="black"><input type="text"  name="granted_date" id="granted_date" maxlength="100" class="field" value="<s:property value="granted_date" />"></font></td>
														</s:if>
														
														<s:elseif test="granted_date_validate==false">		
														<td bgcolor="red"><input type="text" name="granted_date" id="granted_date" maxlength="100" class="field" value="<s:property value="granted_date" />">	</td>
														</s:elseif>
														
														<s:if test="usrn_validate==true">	
														<td><font color="black"><input type="text"  name="usrn" id="usrn" maxlength="100" class="field" value="<s:property value="usrn" />"></font></td>
														</s:if>
														
														<s:elseif test="usrn_validate==false">	
														<td bgcolor="red"><input type="text" name="usrn" id="usrn" maxlength="100" class="field" value="<s:property value="usrn" />">	</td>
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
														
														<s:if test="road_category_validate==true">	
														<td><font color="black"><input type="text"  name="road_category" id="road_category" maxlength="100" class="field" value="<s:property value="road_category" />"></font></td>
														</s:if>
														
														<s:elseif test="road_category_validate==false">	
														<td bgcolor="red"><input type="text" name="road_category" id="road_category" maxlength="100" class="field" value="<s:property value="road_category" />">	</td>
														</s:elseif>
														
														<s:if test="is_works_traffic_sensitive_validate==true">	
														<td><font color="black"><input type="text"  name="is_works_traffic_sensitive" id="is_works_traffic_sensitive" maxlength="100" class="field" value="<s:property value="is_works_traffic_sensitive" />">	</font></td>
														</s:if>
														
														<s:elseif test="is_works_traffic_sensitive_validate==false">	
														<td bgcolor="red"><input type="text" name="is_works_traffic_sensitive" id="is_works_traffic_sensitive" maxlength="100" class="field" value="<s:property value="is_works_traffic_sensitive" />">	</td>
														</s:elseif>
														
														
														
														
														<s:if test="is_street_traffic_sensitive_validate==true">	
														<td><font color="black"><input type="text"  name="is_street_traffic_sensitive" id="is_street_traffic_sensitive" maxlength="100" class="field" value="<s:property value="is_street_traffic_sensitive" />">	</font></td>
														</s:if>
														
														<s:elseif test="is_street_traffic_sensitive_validate==false">	
														<td bgcolor="red"><input type="text" name="is_street_traffic_sensitive" id="is_street_traffic_sensitive" maxlength="100" class="field" value="<s:property value="is_street_traffic_sensitive" />">	</td>
														</s:elseif>
														
														
														<s:if test="traffic_management_type_validate==true">	
														<td><font color="black"><input type="text"  name="traffic_management_type" id="traffic_management_type" maxlength="100" class="field" value="<s:property value="traffic_management_type" />">	</font></td>
														</s:if>
														
														<s:elseif test="traffic_management_type_validate==false">	
														<td bgcolor="red"><input type="text" name="traffic_management_type" id="traffic_management_type" maxlength="100" class="field" value="<s:property value="traffic_management_type" />">	</td>
														</s:elseif>
														
														<s:if test="proposed_start_date_validate==true">	
														<td><font color="black"><input type="text"  name="proposed_start_date" id="proposed_start_date" maxlength="100" class="field" value="<s:property value="proposed_start_date" />">	</font></td>
														</s:if>
														
														<s:elseif test="proposed_start_date_validate==false">	
														<td bgcolor="red"><input type="text" name="proposed_start_date" id="proposed_start_date" maxlength="100" class="field" value="<s:property value="proposed_start_date" />">	</td>
														</s:elseif>
														
														<s:if test="proposed_end_date_validate==true">	
														<td><font color="black"><input type="text"  name="proposed_end_date" id="proposed_end_date" maxlength="100" class="field" value="<s:property value="proposed_end_date" />">	</font></td>
														</s:if>
														
														<s:elseif test="proposed_end_date_validate==false">	
														<td bgcolor="red"><input type="text" name="proposed_end_date" id="proposed_end_date" maxlength="100" class="field" value="<s:property value="proposed_end_date" />">	</td>
														</s:elseif>
														
														
															<s:if test="change_request_reference_validate==true">	
														<td><font color="black"><input type="text"  name="change_request_reference" id="change_request_reference" maxlength="100" class="field" value="<s:property value="change_request_reference" />">	</font></td>
														</s:if>
														
														<s:elseif test="change_request_reference_validate==false">	
														<td bgcolor="red"><input type="text" name="change_request_reference" id="change_request_reference" maxlength="100" class="field" value="<s:property value="change_request_reference" />">	</td>
														</s:elseif>
										</tr>

									</tbody>
								</s:iterator>
							</table>

						</s:if>
					</div>
					
					<s:if test="%{feeValidationLogicList.isEmpty()}">
					<div class="form-group row">
       
       
						<div class="col-6">

							<s:textarea name="comment"
							id="comment" cols="30" rows="4" />

						</div>
                 

						
						
                  
       
       </div>
       </s:if>
       
       
       
					
					<div class="form-group row">
							<div class="col-md-12 text-center">
								<s:if test="%{!feeValidationLogicList.isEmpty()}">
									<s:submit method="saveDataFeeToStage"
										class="btn waves-effect waves-light btn-info" value="Save"
										theme="simple" />
								</s:if>
								<s:if test="%{feeValidationLogicList.isEmpty()}">
									<s:submit method="confirmFeeToValidation"
										class="btn waves-effect waves-light btn-info" value="Confirm"
										theme="simple" />
								</s:if>

								<input type="button" name="Exit" value="Exit"
									class="btn waves-effect waves-light btn-info"
									onClick="window.location.href='exitFEEExportValidate';">
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
