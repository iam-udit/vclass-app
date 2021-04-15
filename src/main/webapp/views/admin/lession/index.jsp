<%@page import="java.util.List"%>
<%@page import="com.mindtree.vclass.model.Lession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>


<!-- Start header section -->
	<jsp:include page="../../layout/backend/header.jsp">
		<jsp:param name="title" value='<%= response.getHeader("location") + " Lession" %>'/>
	</jsp:include>
<!-- end header section -->

    <div class="container-fluid">
	
		<div id="index"  class="shadow-lg p-4 mb-3 mr-5 bg-white rounded"> 
			
			
			<a href="<%= request.getContextPath() + "/admin/lession/create" %>">
				<button class="btn-outline-secondary">Add Lession</button>
			</a>
			<% if (response.getHeader("location").equals("Pending")) { %>
				<a href="<%= request.getContextPath() + "/admin/lession" %>">
					<button class="btn-outline-secondary">All Lession</button>
				</a>
			<% } else if (response.getHeader("location").equals("All")) { %>
				<a href="lession/pending">
					<button class="btn-outline-secondary">Pending Lession</button>
				</a>
			<% } %>
			
			<br /><br />
			
			<% if (request.getParameter("status") != null) { %>
				<div class="alert alert-secondary">
	  				<strong>${ param.status }</strong>
				</div>
			<% } %>
			
				
			<table border="1px" class="table table-striped table-hover" >
				
				<thead class="thead-dark">  
				  <tr>
				  	<th scope="col">SL No.</th>
				  	<th scope="col">Title</th>
				  	<th scope="col">User</th>
				  	<th scope="col">Video</th>
				  	<th scope="col">Is_Published</th>
				  	<% if (response.getHeader("location").equals("All")) { %>
				  		<th scope="col">Is_Approved</th>
				  	<% } %>
				  	<th scope="col">Action</th>
				  </tr>
				</thead>
				  
				<tbody>
				  <% 
				  	 List<Lession> lessions = (List<Lession>) request.getAttribute("lessions"); 
				  	 for(int i = 0; i < lessions.size(); i++) { 
				  		 Lession lession = lessions.get(i);
				  %>
					  
					  <tr>
					  	<td><%= i + 1 %></td>
					  	<td title="<%= lession.getTitle() %>" >
					  		<% if (lession.getTitle().length() > 10) { %>
					  			<%= lession.getTitle().substring(0, 10) + "..." %>
					  		<% } else { %>
					  			<%= lession.getTitle() %>
					  		<% } %>
					  	</td>
					  	<td><%= lession.getUser().getName() %></td>
					  	<td><%= lession.getVideo() %></td>
					  	<td><%= lession.isPublished()? "Published" : "Not Published" %>
					  	
						<% if (response.getHeader("location").equals("All")) { %>
					  		<td><%= lession.isApproved() ? "Approved" : "Not Approved" %></td>
					  	<% } %>	
					  		
					  	<td>
					  		<form>
					  			<input type="hidden" name="slug" value="<%= lession.getSlug() %>">
					  			<div>
					  			
					  			<button type="submit" title="Show" formmethod="get" 
						  			    formaction="<%= request.getContextPath() + "/admin/lession/show" %>" >
						  				<i class="fa fa-eye"></i>
					  			</button>
						  			
								<% if (response.getHeader("location").equals("Pending")) { %>
						  			<button type="submit" title="Approve" formmethod="post" 
						  			    formaction="<%= request.getContextPath() + "/admin/lession/approve" %>" >
						  				<i class="fa fa-check"></i>
						  			</button>
						  		<% } %>
						  		
					  			<button type="submit" title=Edit" formmethod="get" 
					  				 formaction="<%= request.getContextPath() + "/admin/lession/update" %>">
					  				<i class="fa fa-edit"></i>
					  			</button>
					  			<button type="submit" title="Delete" formmethod="post"
					  				formaction="<%= request.getContextPath() + "/admin/lession/delete" %>">
					  				<i class="fa fa-trash"></i>
					  			</button>
					  			</div>
					  		</form>
					  	</td>
					  </tr>
				  <% } %>	
				</tbody>		  
			</table>
			
			<% if (lessions.size() == 0) { %>
				<strong><%= "No lessions found." %></strong>	 
			<% } %>
			
		</div>
		
		<br /><br /> <br />
		
	</div>
  </div>
  </div>
	
<!-- Start footer section -->
	<jsp:include page="../../layout/backend/footer.jsp"></jsp:include>
<!-- End footer section -->
	