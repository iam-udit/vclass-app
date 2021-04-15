<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>


<!-- Start footer section -->
	 <!-- Bootstrap core JavaScript -->
	  <script src="<%= request.getContextPath() + "/assets/scripts/jquery.min.js" %>"></script>
	  <script src="<%= request.getContextPath() + "/assets/scripts/bootstrap.bundle.min.js" %>"></script>	
	  <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
	  	
	  <script>
		  tinymce.init({
			  selector: 'textarea#description'
			});
	  </script>	  
	</body> 

</html>
<!-- End footer section -->
