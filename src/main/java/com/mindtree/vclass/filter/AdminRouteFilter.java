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

import com.mindtree.vclass.model.User;

/**
 * Servlet Filter implementation class AdminRouteFilter is used to 
 * filter and authenticate the admin request/response
 * 
 * @author D-HDKR
 * @version 1.0
 */
@WebFilter("/admin/*")
public class AdminRouteFilter implements Filter {

    /**
     * Instantiate the filter
     */
    public AdminRouteFilter() {
    	super();
    }

	
    @Override
    /**
	 * Destroy the filter life cycle
	 * 
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
		throw new UnsupportedOperationException();
	}

	/**
	 * Filter the admin request and response
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
				
				if (user.getRole().equals("Admin")) {
					
					// If role is admin, pass the request
					chain.doFilter(request, response);
				} else {
					
					// If role is not admin, return back
					httpResponse.sendRedirect(httpRequest.getContextPath() +  "/home");
				}
			} else {

				// If session is invalid
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
			}
		} catch (ServletException e) {
			
			// Log the service/IO exception details
			System.out.println(e.getMessage());
		}
	}

	@Override
	/**
	 * Initilaze the filter life cycle
	 * 
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		throw new UnsupportedOperationException();
	}

}
