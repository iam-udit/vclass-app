	<%@page import="com.mindtree.vclass.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
	<head>
		<title>${ param.title }</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<link rel="stylesheet" href="assets/styles/layout.css" type="text/css" />
		<script type="text/javascript" src="assets/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="assets/scripts/jquery.ui.min.js"></script>
		<script type="text/javascript" src="assets/scripts/jquery.tabs.setup.js"></script>
	</head>
<body>
	
	<!-- Start header section -->
	<header>
	
	<div class="wrapper col1">
		<br />
		<div id="header">
			<div id="logo">
				<h1>
					<a href="">V-CLASS</a>
				</h1>
				<p>A virtual learning platform</p>
			</div>
			<div class="fl_right">
				<ul>
					<li class="last"><a href="#">Search</a></li>
					<li><a href="#">School Board</a></li>
					
					<% if (session.getAttribute("user") != null) { %>
						<li><a href="logout">Logout</a></li>
					<% } else if (!request.getServletPath().equals("/views/common/login.jsp")) {%>
						<li><a href="login">Login Here</a></li>
					<% } %>
					
				</ul>
				<p>Tel: xxx xxxxxxxxxx | Mail: info@vclass.com</p>
			</div>
			<br class="clear" />
		</div>
	</div>
	
	<div class="wrapper col2">
		<div id="topnav">
			<ul>
				<li class="active"><a href="home">Home</a></li>
				
				<%
					User user = (User) session.getAttribute("user");
					if ( user != null && user.getRole().equals("Admin")) { 
				%>
				
					<li><a href='admin/dashboard'>Dashboard</a></li>
	
				<% } else if (user != null && user.getRole().equals("Staff")) { %>
						
					<li><a href='staff/dashboard'>Dashboard</a></li>
					
				<% } %>
				
				<li><a href="#">News</a></li>
				<li><a href="#">Classes</a></li>
				<li><a href="#">About</a></li>
				<li class="last"><a href="#">Contact us</a></li>				
			</ul>
		</div>
	</div>
	
	</header>
	<!-- End header section -->