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
					<h5>LIST OF PROMOTERS</h5>
				</div>
				<div class="card-block">

					<s:form action="PromoterMasterList" validate="true" namespace="/"
						method="post" >

						<div class="col-md-12">
							<div class="row">
								<s:actionerror theme="saanchi"
									cssClass="form-control form-bg-danger" />
								<s:actionmessage theme="saanchi"
									cssClass="form-control form-bg-info" />
							</div>
						</div>




						<div class="table-responsive">

							<s:if test="%{!promoterMasterList.isEmpty()}">

								<table class="table-responsive table-bordered table-hover table-condensed">
									<thead>
										<tr>
											<th scope="col"><font color="blue"><b>#</b></font></th>
											<th scope="col"><font color="blue"><b>PROMOTER NAME</b></font></th>
											<th scope="col"><font color="blue"><b>PROMOTER SHORT NAME</b></font></th>
											<th scope="col"><font color="blue"><b>ORG REFERENCE NO.</b></font></th>
											<th scope="col"><font color="blue"><b>PREFIX</b></font></th>
											<th scope="col"><font color="blue"><b>PROMOTER MAIL ID</b></font></th>
											<th scope="col"><font color="blue"><b>PROMOTER PHONE NO.</b></font></th>
											<th scope="col"><font color="blue"><b>PROMOTER MOBILE NO.</b></font></th>
											<th scope="col"><font color="blue"><b>ACTION</b></font></th>
										</tr>
									</thead>

									<%
									int i = 0;
									%>

									<s:iterator value="promoterMasterList" var="promoterMasterList">

										<%
										i++;
										%>

										<tbody>
											<tr>


												<th scope="row"><font color="blue"> <%=i%></font></th>
											

												<td><font color="black"><s:property value="promoter_name" /></font></td>
												<td><font color="black"><s:property value="promoter_short_name" /></font></td>
												<td><font color="black"><s:property value="org_reference_number" /></font></td>
												<td><font color="black"><s:property value="prefix" /></font></td>
												<td><font color="black"><s:property value="promoter_mail" /></font></td>
												<td><font color="black"><s:property value="promoter_contact_no" /></font></td>												
												<td><font color="black"><s:property value="promoter_mobile_no" /></font></td>												
												

												<td><input type="button" name="Edit" value="Edit/Delete" class="button"
					onClick="updateRecord('<s:property value="promoter_master_id" />','<s:property value="promoter_name" />',
					'<s:property value="promoter_short_name" />','<s:property value="org_reference_number" />','<s:property value="prefix" />',
					'<s:property value="promoter_mail" />','<s:property value="promoter_contact_no" />','<s:property value="promoter_mobile_no" />',
					'<s:property value="isactive" />')"> </td>
											</tr>
											
										

										</tbody>
									</s:iterator>
								</table>

							</s:if>
						</div>






						<div class="form-group row">
							<div class="col-md-12 text-center">


								<input type="button" name="Exit" value="Exit"
									class="btn waves-effect waves-light btn-info"
									onClick="window.location.href='exitPromoterMasterList';">
									
									
									
							</div>
						</div>




					</s:form>



				</div>
			</div>
		</div>
	</div>







</body>
<script type="text/javascript">
window.history.forward(0);
window.history.replaceState('','','?');
	
	
	
	
	function updateRecord(promoter_master_id, promoter_name,promoter_short_name,org_reference_number,prefix,promoter_mail,promoter_contact_no,promoter_mobile_no,isactive) {
		try{
			
					
		
			var targetString = "PromoterMasterEdit.action?promoter_master_id="
				+ promoter_master_id+"&promoter_name="+promoter_name
				+"&promoter_short_name="+promoter_short_name
				+"&org_reference_number="+org_reference_number
				+"&prefix="+prefix+"&promoter_mail="+promoter_mail+
				"&promoter_contact_no="+promoter_contact_no+"&promoter_mobile_no="+promoter_mobile_no
				+"&isactive="+isactive;
		
			
		window.open(targetString, '_self');
		
		}
		catch(err)
		{
			alert(err.message);
			}

	}
	

	
	
	
</script>
</html>
