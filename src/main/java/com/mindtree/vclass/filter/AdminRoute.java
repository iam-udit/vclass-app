package com.mindtree.vclass.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdminRoute is used to 
 * filte and authenticate the admin request/response
 * 
 * @author D-HDKR
 * @version 1.0
 */
@WebFilter("/admin/*")
public class AdminRoute implements Filter {

    /**
     * Instantiate the filter
     */
    public AdminRoute() {
    	super();
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * Filter the admin request and response
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			
			HttpSession session =  request.getSession(false);
			
			if (session != null && session.getAttribute("user") != null) {
				
			}
			
		} catch (S e) {
			// TODO: handle exception
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
