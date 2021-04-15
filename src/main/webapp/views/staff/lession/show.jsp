<%@page import="java.util.List"%>
<%@page import="com.mindtree.vclass.model.Lession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>


<% Lession lession = (Lession) request.getAttribute("lession"); %>

<!-- Start header section -->
	<jsp:include page="../../layout/backend/header.jsp">
		<jsp:param name="title" value="<%= lession.getTitle() %>"/>
	</jsp:include>
<!-- end header section -->

	
    <div class="container-fluid" style="overflow-y: hidden">
	
		<div id="index"  class="shadow-lg p-4 mb-3 mr-5 bg-white rounded"> 
			
			<div class="head">
				<h2><%= lession.getTitle() %></h2>
				<br /> <br  />
				<small><strong>Creator: <%= lession.getUser().getName() %></strong></small> | 
				<small><strong> Dated On: <%= lession.getCreatedAt() %></strong></small>				
				<hr>
			</div>
			
			<div class="body m">
				
				<iframe width="100%" height="315"
				src="https://www.youtube.com/embed/tgbNymZ7vqY?controls=1">
				</iframe>
				<br /><br /> <br />
				
				<div><%= lession.getDescription() %></div>
			<div>
			
			<br />
			<br />
			<a href="<%= request.getContextPath() + "/staff/lession" %>"
				style="position: absolute; left: 420px;">
				<button type="button" class="btn-outline-danger">Go Back</button>
			</a>			
		</div>
		
		<br /><br /> <br />
		
	</div>
  </div>
  </div>
	
<!-- Start footer section -->
	<jsp:include page="../../layout/backend/footer.jsp"></jsp:include>
<!-- End footer section -->
	