<%@page import="java.util.List"%>
<%@page import="com.mindtree.vclass.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>


<!-- Start header section -->
	<jsp:include page="../../layout/backend/header.jsp">
		<jsp:param name="title" value="Staff Details"/>
	</jsp:include>
<!-- end header section -->

    <div class="container-fluid">
	
		<div id="index" class="shadow-lg p-4 mb-5 bg-white rounded"> 
			
			<a href="staff/create">
				<button class="btn-outline-secondary">Create Staff</button><br /><br />
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
				  	 List<User> staffs = (List<User>) request.getAttribute("staffs"); 
				  	 for(int i = 0; i < staffs.size(); i++) { 
				  		 User staff = staffs.get(i);
				  %>
					  
					  <tr>
					  	<td><%= i + 1 %></td>
					  	<td><%= staff.getName() %></td>
					  	<td><%= staff.getAge() %></td>
					  	<td><%= staff.getUsername() %></td>
					  	<td><%= staff.getAddress().get("city") %></td>
					  	<td><%= staff.getAddress().get("state") %></td>
					  	<td><%= staff.getAddress().get("country") %></td>
					  	<td><%= staff.getAddress().get("pin") %></td>
					  	<td>
					  		<form>
					  			<input type="hidden" name="username" value="<%= staff.getUsername() %>">
					  			<div>
					  			<button type="submit" formaction="staff/update" formmethod="get" >
					  				<i class="fa fa-edit"></i>
					  			</button>
					  			<button type="submit" formaction="staff/delete" formmethod="post">
					  				<i class="fa fa-trash"></i>
					  			</button>
					  			</div>
					  		</form>
					  	</td>
					  </tr>
				  
				  <% } %>	
				</tbody>		  
			</table>
			
			<% if (staffs.size() == 0) { %>
				<strong><%= "No staffs found." %></strong>
			<% } %>
			
		</div>
		
		<br /><br /> <br />
		
	</div>
  </div>
  </div>
	
<!-- Start footer section -->
	<jsp:include page="../../layout/backend/footer.jsp"></jsp:include>
<!-- End footer section -->
	