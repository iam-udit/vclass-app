package com.mindtree.vclass.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mindtree.vclass.exception.ServiceException;
import com.mindtree.vclass.model.User;
import com.mindtree.vclass.service.Service;
import com.mindtree.vclass.service.UserService;

/**
 * Servlet implementation class StudentController used to implement
 * Student controller operations
 * 
 * @author D-HDKR
 * @version 1.0
 */
@WebServlet( urlPatterns = { 
		"/admin/student",
		"/admin/student/create", 
		"/admin/student/update", 
		"/admin/student/delete" 
})

public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiate the controller
     * 
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
        super();
    }

    @Override
	/**
	 * Perform load balancing the request
	 * 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String route = request.getServletPath();
		
		try {
			
			switch (route) {
			
			case "/admin/student":
				
				// Send request to index
				index(request, response);
				break;
				
			case "/admin/student/create":
				
				// Send request to create
				create(request, response);
				break;
				
			case "/admin/student/update":
					
				// Send request to update
				update(request, response);
				break;
			
			case "/admin/student/delete":
				
				//Send request to delete
				delete(request, response);
				break;
		
			default:
				
				// No route found
				response.sendError(404);
				break;
			}
		} catch (IOException e) {
			
			// Log the exception messages
			System.out.println(e.getMessage());
		}
		
	}

	
	/**
	 * Delete a student detials
	 * 
	 * @param request
	 * @param response
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			if (request.getMethod().equals("POST")) {
				
				String status = "";
				Service<User> service = new UserService();

				// Get the username of the student to be delete
				String username = request.getParameter("username");
				
				if (service.isExists(username) && service.delete(username)) {
					
					// Return the success response
					status =  "Student details deleted !";
				} else {
					
					// Return the error message
					status =  "Student details delete failed !";
				}
								
				// Send status to the previous page
				response.sendRedirect(request.getContextPath() +  "/admin/student?status="+status);
				
			} else {
				
				// If method is not post -> send method not supported
				response.sendError(405);
			}
			
		} catch (IOException | ServiceException e) {
			
			// Log the exception messages
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Update the user new details
	 * 
	 * @param request
	 * @param response
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			Service<User> service = new UserService();
			String username = request.getParameter("username");
			
			if (request.getMethod().equals("GET")) {
				
				// Get the useranme and set the user details
				request.setAttribute("student", service.read(username));
				
				// Forward the request to eidt page
				getServletContext().getRequestDispatcher("/views/admin/student/edit.jsp")
				.forward(request, response);
							
			} else if (request.getMethod().equals("POST")) {
				
				User student = null;
				String status = "";

				if (service.isExists(username)) {	
					
					// Get user new details
					student = getStudentToUpdate(request, response);

					if (service.update(student)) {

						// Return success response
						status = "Student updated Successfully !";
					} else {
						
						// Return the error response
						status = "Student updation failed !";
					}	
				} else {
					
					// If user already exists
					status = "Student is not exists !";
				}
				
				// Redirect to response page
				response.sendRedirect(request.getContextPath() 
						+  "/admin/student/update?username=" + username + "&status=" + status);
			}
		} catch (IOException | ServiceException | ServletException e) {
			
			// Log the exception messages
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Get updated student details 
	 * 
	 * @param request
	 * @param response
	 * @return	return new student details to be update
	 */
	private User getStudentToUpdate(HttpServletRequest request, HttpServletResponse response) {
		
		User student = null;

		try {
			
			student = new User();
			
			// Retrieve the user updated value
			String name = request.getParameter("name");
			String age = request.getParameter("age");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String country = request.getParameter("country");
			String pin = request.getParameter("pin");


			// Retrieve user previous details
			User userDetails = new UserService().read(username);
			
			// Set the user new address details
			Map<String, String> address = new HashMap<String, String>();
			address.put("city", (city != null) ? city : userDetails.getAddress().get("city"));
			address.put("state", (state != null) ? state : userDetails.getAddress().get("state"));
			address.put("country", (country != null) ? country : userDetails.getAddress().get("country"));
			address.put("pin", (pin != null) ? pin : userDetails.getAddress().get("pin"));

			// Set the user new input
			student.setId(userDetails.getId());
			student.setName((name != null) ? name : userDetails.getName());
			student.setAge((age != null) ? Byte.parseByte(age) : userDetails.getAge() );
			student.setRole(userDetails.getRole());
			student.setUsername(userDetails.getUsername());
			student.setPassword((password != null) ? password : userDetails.getPassword());
			student.setAddress(address);
			
		} catch (ServiceException e) {
			
			// Log the exception messages
			System.out.println(e.getMessage());
		}
	
		return student;
	}

	/**
	 * Create the a new student in DB
	 * 
	 * @param request
	 * @param response
	 */
	private void create(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			
			if (request.getMethod().equals("GET")) {
				
				// Redirect to the create view page
				getServletContext().getRequestDispatcher("/views/admin/student/create.jsp")
				.forward(request, response);
							
			} else if (request.getMethod().equals("POST")) {
				
				String status = "";
				
				User user = new User();
				Service<User> service = new UserService();
				
				// Retrieve & Set the user input
				user.setName(request.getParameter("name"));
				user.setAge(Byte.parseByte(request.getParameter("age")));
				user.setUsername(request.getParameter("username"));
				user.setPassword(request.getParameter("password"));
				user.setRole("Student");
				
				Map<String, String> address = new HashMap<String, String>();
				address.put("city", request.getParameter("city"));
				address.put("state", request.getParameter("state"));
				address.put("country", request.getParameter("country"));
				address.put("pin", request.getParameter("pin"));
				
				user.setAddress(address);
				
				if (!service.isExists(user.getUsername())) {
					
					if (service.create(user)) {

						// Return success response
						status = "Student registerd Successfully !";
					} else {
						
						// Return the error response
						status = "Student registration failed !";
					}	
				} else {
					
					// If user already exists
					status = "Student already exists !";
				}
				
				// Send the request to previous page
				response.sendRedirect(request.getContextPath() + "/admin/student/create?status=" + status);
			}
		} catch (IOException | ServiceException | ServletException e) {
			
			// Log the exception messages
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Handle Index view of the student
	 * 
	 * @param request 
	 * @param response
	 */
	private void index(HttpServletRequest request, HttpServletResponse response) {
		
		try {

			// Get all the student details
			List<User> students = new UserService().read()
					.stream().filter(student -> student.getRole().equals("Student"))
					.collect(Collectors.toList());
			
			request.setAttribute("students", students);
			
			// Send the request to admin->student->index
			getServletContext().getRequestDispatcher("/views/admin/student/index.jsp")
			.forward(request, response);
			
		} catch (ServletException | IOException | ServiceException e) {
			
			// Log the exception messages
			System.out.println(e.getMessage());
		}
		
	}
}
