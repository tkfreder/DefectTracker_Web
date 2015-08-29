<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.ArrayList" %>
<%@ page import="java2.ateam.Defect" %>
<%@ page import="java2.ateam.User" %>
<%@ page import="java2.ateam.Status" %>
<%@ page import="java2.ateam.Priority" %>

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

		<div class="well_no_bottom_margin_blue well-sm center" style="margin-top: 30px">
			<h1_white>
				<label for="header">Team A Defect Tracker</label>
			</h1_white>
			
		</div>
	
		
		<nav class="navbar navbar-blue">
		      <ul class="nav navbar-nav">
		        <li ><a href="index.html"><strong>Home</strong></a></li>
		        <li><a href="EditDefect"><strong>New Defect</strong></a></li>
		        <li class="active"><a href="SearchDefect"><strong>View Defects</strong></a></li> 
		      </ul>

		</nav>
		<div class="container center_div">
		<form name="Search_Defect_Form" action="SearchDefect" method="POST">
			
			<br />
			<div class="row">
				<div class="col-sm-2 right">
					<label for="sel1">Status:</label>
				</div>
				<div class="col-sm-8">
					<select class="form-control" id="sel1" name="status"
						style="width: 200px" >

						<option value="">Choose:</option>
						<%
							ArrayList<Status> statusList = (ArrayList<Status>) request.getAttribute("statusList");
							
							for (int i = 0; i < statusList.size(); i++) {
						%>

						<option value="<%=statusList.get(i).getStatusId() %>"
						
						<%-- only set dropdown if displaying search parameters, that is, the page that directed to this one was SearchDefect.java --%>
						<% if (request.getParameter("status") != null && request.getAttribute("javax.servlet.forward.request_uri").toString().contains("SearchDefect") ){
							
							if (statusList.get(i).getStatusId().equals(request.getParameter("status"))){ %>
							selected <%  } 
							
							}
						%>
						
						><%=statusList.get(i).getStatusName()%></option>

						<% } %>

					</select>
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-sm-2 right">
					<label for="sel2">Priority:</label>
				</div>
				<div class="col-sm-8">
					<select class="form-control" id="sel2" name="priority"
						style="width: 200px" >
						<option value="">Choose:</option>

						<%
							ArrayList<Priority> priorityList = (ArrayList<Priority>) request.getAttribute("priorityList");
							
							for (int i = 0; i < priorityList.size(); i++) {
						%>

						<option value="<%=priorityList.get(i).getPriorityId()%>"
						
						<% if (request.getParameter("priority") != null && request.getAttribute("javax.servlet.forward.request_uri").toString().contains("SearchDefect")){
							
							if (String.valueOf(priorityList.get(i).getPriorityId()).equals(request.getParameter("priority"))){ %>
							selected <%  } 
							}
						
						%>						
						
						><%=priorityList.get(i).getName()%></option>

						<% } %>
					</select>
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-sm-2 right">
					<label for="sel3">Assignee:</label>
				</div>
				<div class="col-sm-8">
					<select class="form-control" id="sel3" name="assignee"
						style="width: 200px" >

						<option value="">Choose:</option>
						<%
							ArrayList<User> userList = (ArrayList<User>) request.getAttribute("userList");
							
							for (int i = 0; i < userList.size(); i++) {
						%>

						<option value="<%=userList.get(i).getUserId()%>"
						
						<% if (request.getParameter("assignee") != null  && request.getAttribute("javax.servlet.forward.request_uri").toString().contains("SearchDefect")){
							
							if (String.valueOf(userList.get(i).getUserId()).equals(request.getParameter("assignee"))){ %>
							selected <%  } 
							}
						
			
						%>		
						
						><%=userList.get(i).getFirstName() + " " + userList.get(i).getLastName()%></option>

						<% } %>

					</select>
				</div>
			</div>
			
			<br>
			
			<div class="row">
				<div class="col-sm-2 right">
				</div>
				<div class="col-sm-8" style="width: 200px" >
					<input type="submit" class="btn btn-primary button-rounded"	value="Search">
				</div>
				</form>
				<div class="col-sm-8" style="width: 200px" >
					<input class="btn btn-primary button-rounded"	value="Display All"  onClick= "location.href = 'SearchDefect';">
				</div>
			</div>
		 
			<br>
			
			<div class="row">
				<div class="col-sm-2 right">
				</div>
				<div class="col-sm-8" style="width: 200px" >
					
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
					
				</div>
			</div>
	
		</div>
		<br>
		<div class="scrollVertical">
		<TABLE
			class="table table-bordered table-hover table-condensed table-striped"
			BORDER="1" width="100%">
			<THEAD>
				<TR style="background-color: #336699">
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