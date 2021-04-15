<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>${ param.title }</title>

  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link href="<%= request.getContextPath() + "/assets/styles/bootstrap.min.css" %>" rel="stylesheet">
  <link href="<%= request.getContextPath() + "/assets/styles/backend.css" %>" rel="stylesheet">
  <!-- Custom styles for this template -->
  <link href="<%= request.getContextPath() + "/assets/styles/simple-sidebar.css" %>" rel="stylesheet">
</head>

<body>

  <div class="d-flex" id="wrapper">
    
    <!-- Start sidebar section -->
    	<jsp:include page="sidebar.jsp"></jsp:include>
    <!-- End sidebar section -->


    <!-- Start header section -->
    <div id="page-content-wrapper">

      <nav class="navbar navbar-expand-lg navbar-light bg-secondary border-bottom">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle text-light" href="#" id="navbarDropdown" 
                 role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Action
              </a>
              <div class="dropdown-menu dropdown-menu-right bg-secondary" 
                  aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="<%= request.getContextPath() + "/home" %>">Home</a>
                <a class="dropdown-item"  href="<%= request.getContextPath() + "/logout" %>">Logout</a>
              </div>
            </li>
          </ul>
        </div>
      </nav>

  <!-- End header section -->

 