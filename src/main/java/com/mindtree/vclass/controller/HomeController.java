package com.mindtree.vclass.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeController is used to provide
 * implemenataion of various home operation
 * 
 * @author D-HDKR
 * @version 1.0
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * Initialize the Home Controller
     * 
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
    }
    
	/**
	 * Redirect to home page
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	response.setHeader("Location", "home");
    	
    	// Forward to appication home page
		request.getRequestDispatcher("views/layout/frontend/app.jsp").forward(request, response);
	}
}
