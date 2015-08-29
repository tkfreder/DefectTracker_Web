<%@ page import="java.util.ArrayList"%>
<%@ page import="java2.ateam.Defect"%>
<%@ page import="java2.ateam.User"%>
<%@ page import="java2.ateam.Priority"%>
<%@ page import="java2.ateam.Status"%>

<!DOCTYPE>
<html>
<head>
<jsp:include page="bootstrap_include.jsp" />
<style type="text/css">
	<%@ include file="../css/Styles.css"%>
</style>

<meta charset="utf-8">
<title>Team A - Add User</title>
</head>
<body>

	<div class="container form-group center" style="width: 700px">
		<div class="well_no_bottom_margin_blue well-sm center"
			style="margin-top: 30px">
			<h1_white> <label for="header">Team A Defect
				Tracker</label> </h1_white>

		</div>


		<nav class="navbar navbar-blue">
			<ul class="nav navbar-nav">
				<li><a href="index.html"><strong>Home</strong></a></li>
				<li><a href="EditDefect"><strong>New Defect</strong></a></li>
				<li><a href="SearchDefect"><strong>View Defects</strong></a></li>
				<li class="active"><a href="AddUser"><strong>Add User</strong></a></li>
			</ul>

		</nav>
		
		<br>
			
			<div class="row">
				<div class="col-sm-8 leftMiddle">
					
					<% if (request.getAttribute("message") != null){ 
				switch ((String)request.getAttribute("message")){
				
					case "add_user_success":
					%>
						<div class="alert-success-no-background">
  								<strong>Add user successful.</strong>
						</div>
					<%
						break;
					
					case "add_user_failure":
					%>
						<div class="alert-danger-no-background">
						  <strong>Error: user was not created.</strong>
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
	
		<br />
		<form name="AddUserForm" action="AddUser" method="POST">
			<div class="row">
				<div class="col-sm-2 right">
					<label for="title">First Name:</label>
				</div>
				<div class="col-sm-8">

					<input type="text" class="form-control" id="firstName" name="firstName" style="width: 250px" required>

				</div>
			</div>
			<br />
			
			<div class="row">
				<div class="col-sm-2 right">
					<label for="title">Last Name:</label>
				</div>
				<div class="col-sm-8">

					<input type="text" class="form-control" id="lastName" name="lastName" style="width: 250px" required>

				</div>
			</div>
			<br />
			
			<div class="row">
				<div class="col-sm-2 right">
					<label for="title">Email Address:</label>
				</div>
				<div class="col-sm-8">

					<input type="text" class="form-control" id="emai" name="email" style="width: 250px" required>

				</div>
			</div>
			<br />
			
			<br />
			<br /> 
				<input type="submit" class="btn btn-primary button-rounded" name="submit" value="Submit"/>
		</form>
	
</body>
</html>