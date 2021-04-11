<%@page import="com.mindtree.vclass.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		User user = (User) session.getAttribute("user");
	%>
	<p>Name: <%= user.getName() %></p>
	<p>Age: <%= user.getAge() %></p>
	<p>Role: <%= user.getRole() %></p>
	<p>Username: <%= user.getUsername() %></p>
	<p>Password: <%= user.getPassword() %></p>
	<p>Address: <%= user.getAddress() %></p>
	
</body>
</html>