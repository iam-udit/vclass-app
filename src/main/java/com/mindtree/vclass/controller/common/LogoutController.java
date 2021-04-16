package com.mindtree.vclass.controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mindtree.vclass.exception.ServiceException;
import com.mindtree.vclass.service.AuthService;

/**
 * Servlet implementation class Logout is used to perform user logout operation
 * 
 * @author D-HDKR
 * @version 1.0
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
    }

	/**
	 * Log out the user account and invalid the session
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			// Log out the user
			boolean isLoggedOut = 
					new AuthService().logout(request.getSession(false));
			
			if (isLoggedOut) {
				
				// Redirect the user to home
				response.sendRedirect("home");
			} else {
				
				// Send the user to the back
				request.setAttribute("logoutError", "User logout failed !");
				request.getRequestDispatcher(
						request.getServletPath()).forward(request, response);
			}
		} catch (ServiceException | IOException | ServletException e) {
			
			// Log the Service/IO/Servlet exception details
			System.out.println(e.getMessage());
		}
	}
}
