<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
   
   

<!-- Start header section -->
	<jsp:include page="../../layout/backend/header.jsp">
		<jsp:param name="title" value="Create Student"/>
	</jsp:include>
<!-- end header section -->
    

    <div class="container-fluid">
	
		<div id="index" class="shadow-lg p-4 mb-5 bg-white rounded"> 
			
			<div class="bg-dark border-danger text-light text-center border">
  				<strong>Create New Student</strong>
			</div> <br />
			
			<% if (request.getParameter("status") != null) { %>
				<div class="alert alert-secondary">
	  				<strong>${ param.status }</strong>
				</div>
			<% } %>
			
			<form action="<%= request.getContextPath() + "/admin/student/create" %>" method="post">
			 
			  <div class="form-row">
			    <div class="form-group col-md-6">
			      <label for="name">Name</label>
			      <input type="text" 
			      		 class="form-control" name="name" id="name" placeholder="Name" required>
			    </div>
			    <div class="form-group col-md-6">
			      <label for="age">Age</label>
			      <input type="number" class="form-control" 
			             id="age" name="age" placeholder="Age" max="100" min="19" required>
			    </div>
			  </div>
			   
			  <div class="form-row">
			    <div class="form-group col-md-6">
			      <label for="username">Username</label>
			      <input type="email" class="form-control" 
			             id="username" name="username" placeholder="Username" required>
			    </div>
			    <div class="form-group col-md-6">
			      <label for="role">Role</label>
			      <input type="text" class="form-control" 
			      		 id="role" name="role" value="Student" disabled="disabled" required>
			    </div>
			  </div>
			  
			  <div class="form-row">
			    <div class="form-group col-md-6">
			      <label for="password">Password</label>
			      <input type="password" class="form-control" 
			             id="password" name="password" placeholder="password" required>
			    </div>
			    <div class="form-group col-md-6">
			      <label for="confirmPassword">Confirm Password</label>
			      <input type="password" class="form-control" 
			      		 id="confirmPassword" name="confirmPassword" required>
			    </div>
			   </div>
			  
			  <div class="form-row">
			    <div class="form-group col-md-3">
			      <label for="city">City</label>
			      <input type="text" class="form-control" name="city" id="city" required>
			    </div>
			    <div class="form-group col-md-3">
			      <label for="state">State</label>
		      	  <input type="text" class="form-control" name="state" id="state" required>
			    </div>
			    <div class="form-group col-md-3">
			      <label for="country">Country</label>
			      <input type="text" class="form-control" name="country" id="country" required>
			    </div>
			    <div class="form-group col-md-3">
			      <label for="pin">Zip</label>
			      <input type="text" class="form-control" name="pin" id="pin" required>
			    </div>
			  </div>
			  <br />
			  <div>
		  		 <button type="submit" class="btn btn-outline-success">Register</button>
		  		 <button type="reset" class="btn btn-outline-warning">Reset</button>
		  		 <a href="<%= request.getContextPath() + "/admin/student" %>">
		  			 <button type="button" class="btn btn-outline-danger">Go Back</button>
			  	</a>
			  </div>
			</form>
	
		</div>
		
		<br /><br /> <br />
		
	</div>
  </div>
  </div>
	
<!-- Start footer section -->
	<jsp:include page="../../layout/backend/footer.jsp"></jsp:include>
<!-- End footer section -->
	