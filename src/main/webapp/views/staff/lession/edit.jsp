<%@page import="com.mindtree.vclass.model.Lession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
   
   

<!-- Start header section -->
	<jsp:include page="../../layout/backend/header.jsp">
		<jsp:param name="title" value="Update Lession"/>
	</jsp:include>
<!-- end header section -->
    

    <div class="container-fluid">
	
		<div id="index"> 
			
			<div class="bg-dark border-danger text-light text-center border">
  				<strong>Edit The Lession</strong>
			</div> <br />
			
			<% if (request.getParameter("status") != null) { %>
				<div class="alert alert-secondary">
	  				<strong>${ param.status }</strong>
				</div>
			<% } %>
			
			<% Lession lession = (Lession) request.getAttribute("lession"); %>
			
			<form action="<%= request.getContextPath() + "/staff/lession/update" %>" 
				method="post">
			 
				 <div class="shadow-lg p-4 mb-5 bg-white rounded">
				  	 <div class="form-row">
					    <div class="form-group col-md-12">
					      <label for="title">Title</label>
					      <input type="text" value="<%= lession.getTitle() %>"
					      		 class="form-control" name="title" id="title" placeholder="Title" required>
					      <input type="hidden" name="slug" value="<%= lession.getSlug() %>">
					    </div>
					  </div>
					   
					  <div class="form-row">
					 	 <div class="form-group col-md-6 mt-3">
					      <label for="video">Video</label>
					      <input type="file" class="form-control" accept=".mp4"
					             id="video" name="video" max="102400">
					     </div>
					    <div class="form-group col-md-4 mt-5">
					      <input <% if (lession.isPublished()) { out.print("checked"); } %>
					      		type="checkbox"  class="form-control" id="publish" name="publish"> 
					       <label for="publish" 
					       		class="col-sm-2 col-form-label">Publish</label>
					    </div>
					  </div>
				 </div>
				  
			     <div class="shadow-lg p-4 mb-5 bg-white rounded">
				   <div class="form-row">
					  	<div class="head col-md-12">
					  		<p>Description</p><hr>
					  	</div>
					    <div class="form-group col-md-12">
					      <textarea id="description" rows="10"
					      	name="description"><%= lession.getDescription() %></textarea>
					    </div>
				  </div>
				  <div>
			  		 <button type="submit" class="btn btn-outline-primary">Update</button>
			  		 <a href="<%= request.getContextPath() + "/staff/lession" %>">
			  			 <button type="button" class="btn btn-outline-danger">Go Back</button>
				  	 </a>
			  	  </div>
			    </div>
			 
			  <br />
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

   
   