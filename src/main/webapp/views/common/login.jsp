<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!-- Include Header Section -->

	<%@ include file = "../layout/frontend/header.jsp" %>
	
<!-- End Header Section -->


<!-- Start content section -->
	<div id="login">
	
		<!-- Heading of registration form -->
		<div class="heading">
			<h4 id="caption">Login</h4>
		</div>
		<!-- End of Heading of registration form -->
		
		<!-- Form design of registration -->
		<div class="form">
			<form action="login" method="post" autocomplete="on" autofocus>
				
				<% if (request.getParameter("loginError") != null) {%>
					<p id="loginError">${ param.loginError }</p>
				<% } %>
				
				<label for="username">Username :</label><br />
				<input type="email" id="username" name="username" 
					placeholder="Enter your username" required 
					value="<% for (Cookie cookie: request.getCookies()) {
						 
								if (cookie.getName().equals("username")) {
									out.print(cookie.getValue());
									break;
								}
					 		}
						 %>">
				<p class="error" id="usernameError"></p>
				
				<label for="password">Password :</label><br />
				<input type="password" id="password" name="password" 
					placeholder="Enter your password" required 
					value="<% for (Cookie cookie: request.getCookies()) {
						 
								if (cookie.getName().equals("password")) {
									out.print(cookie.getValue());
									break;
								}
					 		}
						 %>">
				<p class="error" id="passwordError"></p>
				
				<input type="checkbox" id="rememberMe" name="rememberMe" checked="checked">
				<label for="rememberMe">Remember Me</label><br />
				<input type="submit" id="submit" value="Submit">
			</form>
		</div>
		
	</div>
<!-- End content section -->


<!-- Include Footer Section -->

	<%@ include file = "../layout/frontend/footer.jsp" %>

<!-- End Footer Section -->