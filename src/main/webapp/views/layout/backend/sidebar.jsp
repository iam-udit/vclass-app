<%@page import="com.mindtree.vclass.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Start sidebar section -->
	
	<% String role = ((User) request.getSession().getAttribute("user")).getRole().toLowerCase(); %>
	
   <div class="bg-secondary border-right" id="sidebar-wrapper">
      <div class="sidebar-heading bg-dark text-light text-center"><b>V-Class</b></div>
      <div class="list-group list-group-flush">
        <a href="<%= request.getContextPath() + "/" + role + "/dashboard" %>" 
        	class="list-group-item list-group-item-action bg-secondary text-light">Dashboard</a>
        <a href="<%= request.getContextPath() + "/" + role + "/lession" %>"
       		class="list-group-item list-group-item-action bg-secondary text-light">Lession</a>
       		
       <% if (role.equals("admin")) { %>
        <a href="<%= request.getContextPath() + "/admin/news" %>"
            class="list-group-item list-group-item-action bg-secondary text-light">News</a>    
        <a href="<%= request.getContextPath() + "/admin/staff" %>"
            class="list-group-item list-group-item-action bg-secondary text-light">Staff</a>
        <a href="<%= request.getContextPath() + "/admin/student" %>"
           class="list-group-item list-group-item-action bg-secondary text-light">Student</a>
       <% } %>
        <a href="<%= request.getContextPath() + "/" + role + "/profile/edit" %>"
           class="list-group-item list-group-item-action bg-secondary text-light">Profile</a>
      </div>
    </div>

<!-- End sidebar section -->
    