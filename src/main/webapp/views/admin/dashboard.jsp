<%@page import="com.mindtree.vclass.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>


<!-- Start header section -->
	<jsp:include page="../layout/backend/header.jsp">
		<jsp:param name="title" value="Dashboard"/>
	</jsp:include>
<!-- end header section -->

    <div class="container-fluid">
	
		<%
			User user = (User) session.getAttribute("user");
		%>
		
		<div class="row">
		
			<div class="col-4 border-danger shadow-lg p-4 m-5">
				<div class="border-warning bg-dark text-light text-center" >
				 	<strong>Admin Profile</strong>
				 </div><br /><br />
				<p><strong>Name: <%= user.getName() %></strong></p>
				<p><strong>Username: <%= user.getUsername() %></strong></p>
				<p><strong>Role: <%= user.getRole() %></strong></p>
			</div>
			
			<div class="col">
				<div class="row">
					<div class="col-5 border-danger shadow-lg p-2 mt-5 m-2">
						<div class="border-warning bg-dark text-light text-center" >
					 		<strong>Total Student</strong>
					 	</div><br />
					 	....
				   </div>
				   <div class="col-5 border-danger shadow-lg p-2 mt-5 m-2">
						<div class="border-warning bg-dark text-light text-center" >
					 		<strong>Total Staff</strong>
					 	</div><br />
					 	....
				   </div>
				</div>
				<div class="row">
					<div class="col-5 border-danger shadow-lg p-2 mt-5 m-2">
						<div class="border-warning bg-dark text-light text-center" >
					 		<strong>Total Lession</strong>
					 	</div><br />
					 	....
				   </div>
				   <div class="col-5 border-danger shadow-lg p-2 mt-5 m-2">
						<div class="border-warning bg-dark text-light text-center" >
					 		<strong>Total News</strong>
					 	</div><br />
					 	....
				   </div>
			   </div>
			   <div class="row">
					<div class="col-5 border-danger shadow-lg p-2 mt-5 m-2">
						<div class="border-warning bg-dark text-light text-center" >
					 		<strong>Total Doubts</strong>
					 	</div><br />
					 	....
				   </div>
				   <div class="col-5 border-danger shadow-lg p-2 mt-5 m-2">
						<div class="border-warning bg-dark text-light text-center" >
					 		<strong>Total Answer</strong>
					 	</div><br />
					 	....
				   </div>
				</div>
			</div>
		</div>
	</div>
  </div>
  </div>
	
<!-- Start footer section -->
	<jsp:include page="../layout/backend/footer.jsp"></jsp:include>
<!-- End footer section -->
	