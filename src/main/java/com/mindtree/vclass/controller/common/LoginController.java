package com.mindtree.vclass.controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mindtree.vclass.exception.ServiceException;
import com.mindtree.vclass.model.User;
import com.mindtree.vclass.service.AuthService;
import com.mindtree.vclass.service.Service;
import com.mindtree.vclass.service.UserService;

/**
 * Servlet implementation class Login is used to implement Login operation
 * 
 * @author D-HDKR
 * @version 1.0
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
    	
        super();
    }

    @Override
    /**
     * Redirect to login view page
     * 
     * @see HttpServlet#doGeet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    	try {
			
    		HttpSession session = request.getSession(false);
    		
    		if (session == null || session.getAttribute("user") == null) {
				
        		// Forward to the login view
    			request.getRequestDispatcher("views/common/login.jsp")
    			.forward(request, response);
    	
			} else {
				
				// If a authenticate user, Send back to the home
				response.sendRedirect("home");
			}
    		
		} catch (IOException | ServletException e) {
			
			// Log the service/IO exception details
			System.out.println(e.getMessage());
		}
    }
    
    @Override
	/**
	 * Execute the user login operation
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	
		try {
			
			Service<User> service = new UserService();
					
			// Get user input from the request
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			if (service.isExists(username)) {
				
				User userAuth = new User();
				userAuth.setUsername(username);
				userAuth.setPassword(password);
				
				// Authenticate user credential
				boolean isAuthSuccess = new AuthService().login(userAuth);
				
				if (isAuthSuccess) {
					
					User user = service.read(username);
					
					// Store user data in session
					request.getSession().setAttribute("user", user);
					// Store remember me token
					rememberMe(request, response);
					
					if (user.getRole().equals("Admin")) {
						// Redirect to the admin dashboard
						response.sendRedirect("admin/dashboard");
					} else if (user.getRole().equals("Staff")) {
						// Redirect to the staff dashboard
						response.sendRedirect("staff/dashboard");
					} else if (user.getRole().equals("Student")) {
						// Redirect user to welcome page
						response.sendRedirect("welcome");
					}
				} else {
					// Send login error message to user
					response.sendRedirect("login?loginError=User credential mismatched !");
				}
			} else {
				// Send If user is not exists
				response.sendRedirect("login?loginError=User is not exists !");
			}			
		} catch (ServiceException | IOException e) {
			
			// Log the service/IO exception details
			System.out.println(e.getMessage());
		}
	}

	/***
	 * Add/Remove remember me token in browser cookie
	 * 
	 * @param request	HttpSerlvet request
	 * @param response	HttpServlet response
	 * @param username	username of the user
	 * @param password	password of the user
	 */
	private void rememberMe(HttpServletRequest request, HttpServletResponse response) {
		
		
		// Get user input from the request
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");

		if (rememberMe != null) {
						
			// Create the username cookie
			Cookie usernameCookie =  new Cookie("username", username);
			usernameCookie.setMaxAge(3600 * 24 * 30);
			usernameCookie.setHttpOnly(true);
			usernameCookie.setPath("/");
			
			// Create password cookie
			Cookie passwordCookie = new Cookie("password", password);
			passwordCookie.setMaxAge(3600 * 24 * 30);
			passwordCookie.setHttpOnly(true);
			passwordCookie.setPath("/");
			
			response.addCookie(usernameCookie);
			response.addCookie(passwordCookie);

		} else {
						
			for (Cookie cookie : request.getCookies()) {
				
				if (cookie.getName().equals("username") 
						|| cookie.getName().equals("password")) {
					
					// Delete the username/password cookie
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
	}
}

