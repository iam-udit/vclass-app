<%@page import="java.util.List"%>
<%@page import="com.mindtree.vclass.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>


<!-- Start header section -->
	<jsp:include page="../../layout/backend/header.jsp">
		<jsp:param name="title" value="Student Details"/>
	</jsp:include>
<!-- end header section -->

    <div class="container-fluid">
	
		<div id="index"  class="shadow-lg p-4 mb-3 mr-5 bg-white rounded"> 
			
			<a href="student/create">
				<button class="btn-outline-secondary">Create Student</button><br /><br />
			</a>
			
			<% if (request.getParameter("status") != null) { %>
				<div class="alert alert-secondary">
	  				<strong>${ param.status }</strong>
				</div>
			<% } %>
				
			<table border="1px" class="table table-striped table-hover" >
				
				<thead class="thead-dark">  
				  <tr>
				  	<th scope="col">SL No.</th>
				  	<th scope="col">Name</th>
				  	<th scope="col">Age</th>
				  	<th scope="col">Username</th>
				  	<th scope="col">City</th>
				  	<th scope="col">State</th>
				  	<th scope="col">Country</th>
				  	<th scope="col">Pin</th>
				  	<th scope="col">Action</th>
				  </tr>
				</thead>
				  
				<tbody>
				  <% 
				  	 List<User> students = (List<User>) request.getAttribute("students"); 
				  	 for(int i = 0; i < students.size(); i++) { 
				  		 User student = students.get(i);
				  %>
					  
					  <tr>
					  	<td><%= i + 1 %></td>
					  	<td><%= student.getName() %></td>
					  	<td><%= student.getAge() %></td>
					  	<td><%= student.getUsername() %></td>
					  	<td><%= student.getAddress().get("city") %></td>
					  	<td><%= student.getAddress().get("state") %></td>
					  	<td><%= student.getAddress().get("country") %></td>
					  	<td><%= student.getAddress().get("pin") %></td>
					  	<td>
					  		<form>
					  			<input type="hidden" name="username" value="<%= student.getUsername() %>">
					  			<div>
					  			<button type="submit" formaction="student/update" formmethod="get" >
					  				<i class="fa fa-edit"></i>
					  			</button>
					  			<button type="submit" formaction="student/delete" formmethod="post">
					  				<i class="fa fa-trash"></i>
					  			</button>
					  			</div>
					  		</form>
					  	</td>
					  </tr>
				  
				  <% } %>	
				</tbody>		  
			</table>
			
			<% if (students.size() == 0) { %>
				<strong><%= "No students found." %></strong> 
			<% } %>
		
		</div>
		
		<br /><br /> <br />
		
	</div>
  </div>
  </div>
	
<!-- Start footer section -->
	<jsp:include page="../../layout/backend/footer.jsp"></jsp:include>
<!-- End footer section -->
	