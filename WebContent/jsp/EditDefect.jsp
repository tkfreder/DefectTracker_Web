<%@ page import="java.util.ArrayList" %>
<%@ page import="java2.ateam.Defect" %>
<%@ page import="java2.ateam.User" %>
<%@ page import="java2.ateam.Priority" %>
<%@ page import="java2.ateam.Status" %>

<!DOCTYPE>
<html>
<head>
	<jsp:include page="bootstrap_include.jsp" />
	<style type="text/css">
		<%@ include file="../css/Styles.css" %>
	</style>

<meta  charset="utf-8">
<title>Team A - Submit New Defect</title>
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
		        <li class="active"><a href="EditDefect"><strong>New Defect</strong></a></li>
		        <li><a href="ViewAll"><strong>View Defects</strong></a></li> 
		      </ul>

		</nav>
		<br />
		<form name="Edit_Defect_Form" action="SubmitDefect" method="POST">		
			<div class="row">
				<div class="col-sm-2 right">
					<label for="defect">
					
					<% if (request.getAttribute("defect") != null){ %>	
						
						Defect #:
							
					<% } %>
					
					
					</label>
				</div>
				<div class="col-sm-8 leftMiddle">
					
						<% if (request.getAttribute("defect") != null){ %>	
						
							<% Defect defect = (Defect)request.getAttribute("defect"); %>
						
							<%= defect.getId() %><input type="hidden" name="id" value="<%= defect.getId() %>">
							
					<% } %>
						
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-sm-2 right">
					<label for="title">Title:</label>
				</div>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="title" name="title" required>
					
					<% if (request.getAttribute("defect") != null){ %>	
						
						<% Defect defect = (Defect)request.getAttribute("defect"); %>
						
						value="<%= defect.getSummary() %>"
							
					<% } %>
						style="width: 500px">
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-sm-2 right">
					<label for="sel1">Status:</label>
				</div>
				<div class="col-sm-8">
					<select class="form-control" id="sel1" name="status" required>
						style="width: 200px">
						<option value="">Choose:</option>
						<%
							ArrayList<Status> statusList = (ArrayList<Status>) request.getAttribute("statusList");
							
							for (int i = 0; i < statusList.size(); i++) {
						%>
											
						<option value="<%=statusList.get(i).getStatusId() %>"
						
						<% if (request.getAttribute("defect") != null){
							
							Defect defect = (Defect)request.getAttribute("defect");
						
								if (defect.getStatus().getStatusName().equals(statusList.get(i).getStatusName())){ %>
									selected
							<%  } 
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
					<select class="form-control" id="sel2" name="priority" required>
						style="width: 200px">
						<option value="">Choose:</option>
						
						<%
							ArrayList<Priority> priorityList = (ArrayList<Priority>) request.getAttribute("priorityList");
							
							for (int i = 0; i < priorityList.size(); i++) {
						%>
											
						<option value="<%=priorityList.get(i).getPriorityId()%>"
						
						<% if (request.getAttribute("defect") != null){
							
							Defect defect = (Defect)request.getAttribute("defect");
						
								if (defect.getPriority().getName().equals(priorityList.get(i).getName())){ %>
									selected
							<%  } 
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
					<select class="form-control" id="sel3" name="assignee" required>
						style="width: 200px">
						
						<option value="">Choose:</option>
						<%
							ArrayList<User> userList = (ArrayList<User>) request.getAttribute("userList");
							
							for (int i = 0; i < userList.size(); i++) {
						%>
											
						<option value="<%=userList.get(i).getUserId()%>"
						
						<% if (request.getAttribute("defect") != null){
							
							Defect defect = (Defect)request.getAttribute("defect");
						
								if (defect.getAssigneeId().getUserId() == userList.get(i).getUserId()){ %>
									selected
							<%  } 
							}
							%>
						
						
						><%=userList.get(i).getFirstName() + " " + userList.get(i).getLastName()%></option>
						
						<% } %>
						
					</select>
				</div>
			</div>
			<br />

			<div class="row">
				<div class="col-sm-2 right">
					<label for="description">Description:</label>
				</div>
				<div class="col-sm-8">					
					<%  String description = "";
						
						if (request.getAttribute("defect") != null){
								Defect defect = (Defect)request.getAttribute("defect");
								description = defect.getDescription();
						} %>
						<textarea class="form-control left" rows="5" id="description" name="description" style="width: 500px" required><%= description %></textarea>
				</div>
			</div>
			
			  
			<br /> 
			
<!-- 			here we need to display a label that says something like "Defect XX submitted".  Insert needs to occur only if this was a new defect. -->
<!-- 			otherwise we do an update to the db... figured could use a hidden value to pass to the submitDefect servlet.  That servlet could then -->
<!-- 			just reload this page with the defect that was created or something... -->

			
			<br /> 
			<input type="submit" class="btn btn-primary button-rounded" value="Save">

		</form>		
	</div>	
</body>
</html>