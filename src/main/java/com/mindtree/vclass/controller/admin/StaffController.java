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
 * Servlet implementation class StaffController used to implement
 * Staff controller operations
 * 
 * @author D-HDKR
 * @version 1.0
 */
@WebServlet( urlPatterns = { 
		"/admin/staff",
		"/admin/staff/create", 
		"/admin/staff/update", 
		"/admin/staff/delete" 
})

public class StaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiate the controller
     * 
     * @see HttpServlet#HttpServlet()
     */
    public StaffController() {
        super();
    }

    @Override
	/**
	 * Perform load balancing the request
	 * 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		
		String route = request.getServletPath();
		
		try {
			
			switch (route) {
			
			case "/admin/staff":
				
				// Send request to index
				index(request, response);
				break;
				
			case "/admin/staff/create":
				
				// Send request to create
				create(request, response);
				break;
				
			case "/admin/staff/update":
					
				// Send request to update
				update(request, response);
				break;
			
			case "/admin/staff/delete":
				
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
	 * Delete a staff detials
	 * 
	 * @param request
	 * @param response
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			if (request.getMethod().equals("POST")) {
				
				String status = "";
				Service<User> service = new UserService();

				// Get the username of the staff to be delete
				String username = request.getParameter("username");
				
				if (service.isExists(username) && service.delete(username)) {
					
					// Return the success response
					status =  "Staff details deleted !";
				} else {
					
					// Return the error message
					status =  "Staff details delete failed !";
				}
								
				// Send status to the previous page
				response.sendRedirect(request.getContextPath() +  "/admin/staff?status="+status);
				
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
				request.setAttribute("staff", service.read(username));
				
				// Forward the request to eidt page
				getServletContext().getRequestDispatcher("/views/admin/staff/edit.jsp")
				.forward(request, response);
							
			} else if (request.getMethod().equals("POST")) {
				
				User staff = null;
				String status = "";

				if (service.isExists(username)) {	
					
					// Get user new details
					staff = getStaffToUpdate(request);

					if (service.update(staff)) {

						// Return success response
						status = "Staff updated Successfully !";
					} else {
						
						// Return the error response
						status = "Staff updation failed !";
					}	
				} else {
					
					// If user already exists
					status = "Staff is not exists !";
				}
				
				// Redirect to response page
				response.sendRedirect(request.getContextPath() 
						+  "/admin/staff/update?username=" + username + "&status=" + status);
			}
		} catch (IOException | ServiceException | ServletException e) {
			
			// Log the exception messages
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Get updated staff details 
	 * 
	 * @param request
	 * @param response
	 * @return	return new staff details to be update
	 */
	private User getStaffToUpdate(HttpServletRequest request) {
		
		User staff = null;

		try {
			
			staff = new User();
			
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
			staff.setId(userDetails.getId());
			staff.setName((name != null) ? name : userDetails.getName());
			staff.setAge((age != null) ? Byte.parseByte(age) : userDetails.getAge() );
			staff.setRole(userDetails.getRole());
			staff.setUsername(userDetails.getUsername());
			staff.setPassword((password != null) ? password : userDetails.getPassword());
			staff.setAddress(address);
			
		} catch (ServiceException e) {
			
			// Log the exception messages
			System.out.println(e.getMessage());
		}
	
		return staff;
	}

	/**
	 * Create the a new staff in DB
	 * 
	 * @param request
	 * @param response
	 */
	private void create(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			
			if (request.getMethod().equals("GET")) {
				
				// Redirect to the create view page
				getServletContext().getRequestDispatcher("/views/admin/staff/create.jsp")
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
				user.setRole("Staff");
				
				Map<String, String> address = new HashMap<String, String>();
				address.put("city", request.getParameter("city"));
				address.put("state", request.getParameter("state"));
				address.put("country", request.getParameter("country"));
				address.put("pin", request.getParameter("pin"));
				
				user.setAddress(address);
				
				if (!service.isExists(user.getUsername())) {
					
					if (service.create(user)) {

						// Return success response
						status = "Staff registerd Successfully !";
					} else {
						
						// Return the error response
						status = "Staff registration failed !";
					}	
				} else {
					
					// If user already exists
					status = "Staff already exists !";
				}
				
				// Send the request to previous page
				response.sendRedirect(request.getContextPath() + "/admin/staff/create?status=" + status);
			}
		} catch (IOException | ServiceException | ServletException e) {
			
			// Log the exception messages
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Handle Index view of the staff
	 * 
	 * @param request 
	 * @param response
	 */
	private void index(HttpServletRequest request, HttpServletResponse response) {
		
		try {

			// Get all the staff details
			List<User> staffs = new UserService().read()
					.stream().filter(staff -> staff.getRole().equals("Staff"))
					.collect(Collectors.toList());
			
			request.setAttribute("staffs", staffs);
			
			// Send the request to admin->staff->index
			getServletContext().getRequestDispatcher("/views/admin/staff/index.jsp")
			.forward(request, response);
			
		} catch (ServletException | IOException | ServiceException e) {
			
			// Log the exception messages
			System.out.println(e.getMessage());
		}
		
	}
}
