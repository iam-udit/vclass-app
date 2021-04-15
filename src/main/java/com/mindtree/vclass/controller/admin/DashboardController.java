package com.mindtree.vclass.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/admin/dashboard")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		

    	try {
    			
    		// Forward to appication home page
    		request.getRequestDispatcher("../views/admin/dashboard.jsp")
    		.forward(request, response);
    		
		} catch (ServletException e) {
		
			// Log the service/IO exception details
			System.out.println(e.getMessage());
		}
	}
}
