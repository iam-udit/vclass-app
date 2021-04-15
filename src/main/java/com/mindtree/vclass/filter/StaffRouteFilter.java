package com.mindtree.vclass.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mindtree.vclass.exception.ServiceException;
import com.mindtree.vclass.model.Lession;
import com.mindtree.vclass.model.User;
import com.mindtree.vclass.service.LessionService;
import com.mindtree.vclass.service.Service;

/**
 * Servlet Filter implementation class StaffRouteFilter is used to 
 * filter and authenticate the staff request/response
 * 
 * @author D-HDKR
 * @version 1.0
 */
@WebFilter("/staff/*")
public class StaffRouteFilter implements Filter {

    /**
     * Instantiate the filter
     */
    public StaffRouteFilter() {
    	super();
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * Filter the staff request and response
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			HttpSession session =  httpRequest.getSession(false);
			
			// Check user is authenticated or not
			if (session != null && session.getAttribute("user") != null) {
								
				User user = (User) session.getAttribute("user");
				
				if (user != null && user.getRole().equals("Staff")) {
					
					String slug = request.getParameter("slug");
					Service<Lession> service = new LessionService();
					
					if (slug != null && service.isExists(slug)
							&& service.read(slug).getUser().getId() != user.getId()) {
						
						// Forbid to access the lession
						httpResponse.sendError(401,
								"You are not permited to access others lession !");
					} 
						
					// If role is staff, pass the request
					chain.doFilter(request, response);
				} else {
					
					// If role is not satff, return back
					httpResponse.sendRedirect(httpRequest.getContextPath() +  "/home");
				}
			} else {
				
				// If session is invalid
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
			}
		} catch (ServletException | ServiceException e) {
			
			// Log the service/IO exception details
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
