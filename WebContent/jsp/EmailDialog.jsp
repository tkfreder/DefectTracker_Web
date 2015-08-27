<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java2.ateam.Defect"%>
<%@ page import="java2.ateam.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="bootstrap_include.jsp" />
<style type="text/css">
	<%@ include file="../css/Styles.css"%>
	
</style>
<title>Team A - Email Defect</title>
</head>
<body>

	<script>
		function SelectMoveRows(SS1, SS2) {
			var SelID = '';
			var SelText = '';
			// Move rows from SS1 to SS2 from bottom to top
			for (i = SS1.options.length - 1; i >= 0; i--) {
				if (SS1.options[i].selected == true) {
					SelID = SS1.options[i].value;
					SelText = SS1.options[i].text;
					var newRow = new Option(SelText, SelID);
					SS2.options[SS2.length] = newRow;
					SS1.options[i] = null;
				}
			}
			SelectSort(SS2);
		}

		function SelectSort(SelList) {
			var ID = '';
			var Text = '';
			for (x = 0; x < SelList.length - 1; x++) {
				for (y = x + 1; y < SelList.length; y++) {
					if (SelList[x].text > SelList[y].text) {
						// Swap rows
						ID = SelList[x].value;
						Text = SelList[x].text;
						SelList[x].value = SelList[y].value;
						SelList[x].text = SelList[y].text;
						SelList[y].value = ID;
						SelList[y].text = Text;
					}
				}
			}
			ButtonCheck();
		}

		function ButtonCheck() {
			if (document.getElementById("emailTo").options.length == 0) {

				document.getElementById("sendEmailButton").disabled = true;

			} else {

				document.getElementById("sendEmailButton").disabled = false;
			}
		}
	</script>

	<div class="container form-group center" style="width: 700px">
		<div class="well well-sm center" style="margin-top: 30px">
			<h1>
				<label for="header">Send Email</label>
			</h1>
			<form action="/A_Defect_Tracker/index.html" method="POST">
				<input class="btn btn-default button-rounded" type="submit"
					value="Home">
			</form>
		</div>

		<form action="ProcessEmail" method="POST">
			<div class="center" style="display: inline-block">
				<div id="left" style="float: left; width: 200px">
					<center>
						<b>Options:</b><br>
					</center>
					<select class="form-control" id="selectEmailTo"
						name="SelectRecipients" size="5" multiple="multiple">
						<%
							ArrayList<User> userList = (ArrayList<User>) request.getAttribute("userList");
							Defect defect = (Defect) request.getAttribute("defect");
							String userName;
	
							for (User user : userList) {
						%>
						<option value="<%=user.getUserId()%>">
							<%=user.getFirstName() + " " + user.getLastName()%>
						</option>
						<%
							}
						%>
					</select>
				</div>
				<div id="center" style="float: left; padding-top:15px">
					<img src="${pageContext.request.contextPath}/images/right.png"
						class="img-rounded"
						onClick="SelectMoveRows(document.getElementById('selectEmailTo'), document.getElementById('emailTo'))">
					<br> <img
						src="${pageContext.request.contextPath}/images/left.png"
						class="img-rounded"
						onClick="SelectMoveRows(document.getElementById('emailTo'), document.getElementById('selectEmailTo'))">
				</div>
				<div id="right" style="float:left;width: 200px">
					<center>
						<b>Send to:</b><br>
					</center>
					<select class="form-control" id="emailTo" name="EmailRecipients"
						size="5" multiple="multiple">
					</select>
				</div>
				<div style="clear:both">
				</div>
			</div>
			<br>
			<br>
			<input class="btn btn-primary button-rounded"type="submit"
				value="Send email" id="sendEmailButton" disabled="true">


		</form>
	</div>
	<!-- 
<H1>Some test data:</H1>
<p><b> (This will all go away down here)</b></p>
<p>ID: <%=defect.getId()%></p>
<p>Description: <%=defect.getDescription()%></p>
<p>Summary: <%=defect.getSummary()%></p>
<p>Assignee: <%=defect.getAssigneeId().getFirstName() + " " + defect.getAssigneeId().getLastName()%></p>
<p>Priority: <%=defect.getPriority().getName()%></p>
<p>Status: <%=defect.getStatus().getStatusName()%></p>
 -->

	<script>
		SelectSort(document.getElementById('selectEmailTo'));
	</script>

</body>
</html>