<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Include Header Section -->

	<%@ include file = "partial/header.jsp" %>
	
<!-- End Header Section -->



<!-- Include Content Section -->

	<% 
		String location = request.getHeader("Location");
	
		if (location != null) {
			
			request.getRequestDispatcher("../../common/" + location + ".jsp")
			.include(request, response);
			
		} else { 
	%>
	
		<%@ include file = "../../common/home.jsp" %>
		
	<% } %>	
	
<!-- End Content Section -->
	
	

<!-- Include Footer Section -->

	<%@ include file = "partial/footer.jsp" %>

<!-- End Footer Section -->