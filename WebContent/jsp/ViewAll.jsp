<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.ArrayList" %>
<%@ page import="java2.ateam.Defect" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="bootstrap_include.jsp" />
<style type="text/css">
    <%@include file="../css/Styles.css" %>
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A Team Defect Tracker</title>
</head>
<body>
	<div class="container form-group center" style="width: 700px">
		<div class="well well-sm center" style="margin-top: 30px">
			<h1>
				<label for="header">View Defects</label>
			</h1>
			<form action="/A_Defect_Tracker/index.html" method="POST" >
				<input class="btn btn-default button-rounded" type="submit" value="Home">
			</form>
		</div>
			<% if (request.getAttribute("message") != null){ 
				
				switch ((String)request.getAttribute("message")){
				
					case "insert_success":
					%>
						<div class="alert-success-no-background">
  								<strong>Insert new defect successful.</strong>
						</div>
					<%
						break;
					
					case "insert_failure":
					%>
						<div class="alert-danger-no-background">
						  <strong>Error: defect record was not created.</strong>
						</div>
						
					<%	
						break;
					
					case "update_success":
					%>
						<div class="alert-success-no-background">
  								<strong>Update successful.</strong>
						</div>
					<%
						break;
					
					case "update_failure":
					%>
						<div class="alert-danger-no-background">
						  <strong>Error: defect record was not updated.  Try again.</strong>
						</div>
					
					<% 
						break;
					
					
					case "email_success":
						%>
							<div class="alert-success-no-background">
  								<strong>Email sent successfully.</strong>
						</div>
						
						<% 
							break;
						
						
					case "email_failure":
						%>
							<div class="alert-danger-no-background">
							  <strong>Error: The email failed to send. Try again.</strong>
							</div>
						
						<% 
							break;
						
					default:
						break;
				}
				
			}
			%>
		<br>

		<div class="scrollVertical">
		<TABLE
			class="table table-bordered table-hover table-condensed table-striped"
			BORDER="1" width="100%">
			<THEAD>
				<TR style="background-color: #FFEBCD">
					<TH>Defect #</TH>
					<TH>Status</TH>
					<TH>Priority</TH>
					<TH>Assignee</TH>
					<TH>Email</TH>
				</TR>
			</THEAD>
			<%
				ArrayList<Defect> defectList = (ArrayList<Defect>) request.getAttribute("defectList");
					
				for (int i = 0; i < defectList.size(); i++) {
			%>
			<TR>
				<TD ALIGN="center"><a
					href="EditDefect?id=<%=defectList.get(i).getId()%>"><%=defectList.get(i).getId()%></a></TD>
				<TD ALIGN="center"><%=defectList.get(i).getStatus().getStatusName()%></TD>
				<TD ALIGN="center"><%=defectList.get(i).getPriority().getName()%></TD>
				<TD ALIGN="center"><%=defectList.get(i).getAssigneeId().getFirstName() + " " + defectList.get(i).getAssigneeId().getLastName()%></TD>
				<TD ALIGN="center"><a href="SendEmail?id=<%=defectList.get(i).getId()%>" class="btn btn-default btn-xs button-rounded-high">
				<span class="glyphicon glyphicon-envelope"></span></a></TD>
			</TR>
			<%
				}
			%>
		</TABLE>
</div>
	</div>
</body>
</html>