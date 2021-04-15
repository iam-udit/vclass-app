package com.mindtree.vclass.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
 * Servlet implementation class SettingController is used to perform 
 * operation on admin profile
 * 
 * @author D-HDKR 
 * @version 1.0
 */
@WebServlet(urlPatterns = { "/admin/profile/edit", "/admin/profile/update" })

public class SettingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingController() {
        super();
    }

	/**
	 * Redirect to admin profile view
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// Forward the request to eidt page
			getServletContext().getRequestDispatcher("/views/admin/profile.jsp")
			.forward(request, response);
			
		} catch (IOException | ServletException e) {
			
			// Log the exception messages
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Update the admin profile details
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
		try {
		
			String status = "";
			Service<User> service = new UserService();
			
			// Get user new details
			User admin = getAdminToUpdate(request, response);

			if (service.update(admin)) {

				request.getSession().setAttribute("user", 
						service.read(admin.getUsername()));
				
				// Return success response
				status = "Profile updated Successfully !";
			} else {
				
				// Return the error response
				status = "Profile updation failed !";
			}	
				
			// Redirect to response page
			response.sendRedirect(request.getContextPath() 
					+  "/admin/profile/edit?status=" + status);
			
		} catch (IOException | ServiceException e) {
			
			// Log the exception messages
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Get updated admin details 
	 * 
	 * @param request
	 * @param response
	 * @return	return new student details to be update
	 */
	private User getAdminToUpdate(HttpServletRequest request, HttpServletResponse response) {
		
		User admin = null;

		try {
			
			admin = new User();
			
			// Retrieve the user updated value
			String name = request.getParameter("name");
			String age = request.getParameter("age");
			String password = request.getParameter("password");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String country = request.getParameter("country");
			String pin = request.getParameter("pin");


			// Retrieve user previous details
			User adminDetails = (User) request.getSession().getAttribute("user");
			
			// Set the user new address details
			Map<String, String> address = new HashMap<String, String>();
			address.put("city", (city != null) ? city : adminDetails.getAddress().get("city"));
			address.put("state", (state != null) ? state : adminDetails.getAddress().get("state"));
			address.put("country", (country != null) ? country : adminDetails.getAddress().get("country"));
			address.put("pin", (pin != null) ? pin : adminDetails.getAddress().get("pin"));

			// Set the user new input
			admin.setId(adminDetails.getId());
			admin.setName((name != null) ? name : adminDetails.getName());
			admin.setAge((age != null) ? Byte.parseByte(age) : adminDetails.getAge() );
			admin.setRole(adminDetails.getRole());
			admin.setUsername(adminDetails.getUsername());
			admin.setPassword((password != null) ? password : adminDetails.getPassword());
			admin.setAddress(address);
			
		} catch (NullPointerException | IllegalStateException e) {
			
			// Log the exception messages
			System.out.println(e.getMessage());
		}
	
		return admin;
	}

}
