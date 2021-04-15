<%@page import="com.mindtree.vclass.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
   
   

<!-- Start header section -->
	<jsp:include page="../layout/backend/header.jsp">
		<jsp:param name="title" value="Profile"/>
	</jsp:include>
<!-- end header section -->
    

    <div class="container-fluid">
	
		<div id="index" class="shadow-lg p-4 mb-5 bg-white rounded"> 
				
			<div class="bg-dark border-danger text-light text-center border">
  				<strong>Update Your Profile</strong>
			</div> <br />
			
			<% User user = (User) session.getAttribute("user"); %>
			<% if (request.getParameter("status") != null) { %>
				<div class="alert alert-secondary">
	  				<strong>${ param.status }</strong>
				</div>
			<% } %>
			
			<form action="<%= request.getContextPath() + "/staff/profile/update" %>" method="post">
			  <div class="form-row">
			    <div class="form-group col-md-6">
			      <label for="name">Name</label>
			      <input type="text" value="<%= user.getName() %>"
			      		 class="form-control" name="name" id="name" placeholder="Name" required>
			    </div>
			    <div class="form-group col-md-6">
			      <label for="age">Age</label>
			      <input type="number" class="form-control"  value="<%= user.getAge() %>"
			             id="age" name="age" placeholder="Age" max="100" min="19" required>
			    </div>
			  </div>
			   
			  <div class="form-row">
			    <div class="form-group col-md-6">
			      <label for="username">Username</label>
			      <input type="email" class="form-control" value="<%= user.getUsername() %>"
			             id="username" placeholder="Username" disabled="disabled">
			   	  <input type="hidden"  value="<%= user.getUsername() %>" name="username"/>
			    </div>
			    <div class="form-group col-md-6">
			      <label for="role">Role</label>
			      <input type="text" class="form-control" value="<%= user.getRole() %>"
			      		 id="role" name="role" disabled="disabled">
			    </div>
			  </div>
			  
			  <div class="form-row">
			    <div class="form-group col-md-6">
			      <label for="password">New Password</label>
			      <input type="password" class="form-control" value="<%= user.getPassword() %>"
			             id="password" name="password" placeholder="password" required>
			    </div>
			    <div class="form-group col-md-6">
			      <label for="confirmPassword">Confirm Password</label>
			      <input type="password" class="form-control" value="<%= user.getPassword() %>"
			      		 id="confirmPassword" name="confirmPassword" required>
			    </div>
			   </div>
			  
			  <div class="form-row">
			    <div class="form-group col-md-3">
			      <label for="city">City</label>
			      <input type="text" value="<%= user.getAddress().get("city") %>"
			             class="form-control" name="city" id="city" required>
			    </div>
			    <div class="form-group col-md-3">
			      <label for="state">State</label>
		      	  <input type="text"  value="<%= user.getAddress().get("state") %>"
		      	         class="form-control" name="state" id="state" required>
			    </div>
			    <div class="form-group col-md-3">
			      <label for="country">Country</label>
			      <input type="text" value="<%= user.getAddress().get("country") %>"
			             class="form-control" name="country" id="country" required>
			    </div>
			    <div class="form-group col-md-3">
			      <label for="pin">Zip</label>
			      <input type="text"  value="<%= user.getAddress().get("pin") %>"
			             class="form-control" name="pin" id="pin" required>
			    </div>
			  </div>
			  <br />
			  <div>
		  		 <button type="submit" class="btn btn-outline-primary">Update</button>
		  	  </div>
			</form>
	
		</div>
		
		<br /><br /> <br />
		
	</div>
  </div>
  </div>
	
<!-- Start footer section -->
	<jsp:include page="../layout/backend/footer.jsp"></jsp:include>
<!-- End footer section -->
	