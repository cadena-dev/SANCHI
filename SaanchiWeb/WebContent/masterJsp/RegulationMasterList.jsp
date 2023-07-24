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
					<h5>LIST OF REGULATIONS</h5>
				</div>
				<div class="card-block">

					<s:form action="RegulationMasterList" validate="true" namespace="/"
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

							<s:if test="%{!regulationMasterList.isEmpty()}">

								<table class="table-responsive table-bordered table-hover table-condensed">
									<thead>
										<tr>
											<th scope="col"><font color="blue"><b>#</b></font></th>
											<th scope="col"><font color="blue"><b>REGULATION ID</b></font></th>
											<th scope="col"><font color="blue"><b>REGULATION CODE</b></font></th>
											<th scope="col"><font color="blue"><b>DESCRIPTION</b></font></th>
											<th scope="col"><font color="blue"><b>ACTION</b></font></th>
										</tr>
									</thead>

									<%
									int i = 0;
									%>

									<s:iterator value="regulationMasterList" var="regulationMasterList">

										<%
										i++;
										%>

										<tbody>
											<tr>


												<th scope="row"><font color="blue"> <%=i%></font></th>
											

												<td><font color="black"><s:property value="regulation_id" /></font></td>
												<td><font color="black"><s:property value="regulation_code" /></font></td>
												<td><font color="black"><s:property value="description" /></font></td>
											

												<td><input type="button" name="Edit" value="Edit/Delete" class="button"
					onClick="updateRecord('<s:property value="regulation_master_id" />','<s:property value="regulation_id" />',
					'<s:property value="regulation_code" />','<s:property value="description" />','<s:property value="isactive" />')"> </td>
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
	
	
	
	
	function updateRecord(regulation_master_id, regulation_id,regulation_code,description,isactive) {
		try{
			
					
		
			var targetString = "RegulationMasterEdit.action?regulation_master_id="
				+ regulation_master_id+"&regulation_id="+regulation_id
				+"&regulation_code="+regulation_code
				+"&description="+description
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
