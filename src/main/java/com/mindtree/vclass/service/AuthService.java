package com.mindtree.vclass.service;

import javax.servlet.http.HttpSession;

import com.mindtree.vclass.dao.UserDao;
import com.mindtree.vclass.exception.DAOException;
import com.mindtree.vclass.exception.NotFoundException;
import com.mindtree.vclass.exception.ServiceException;
import com.mindtree.vclass.model.User;

/**
 * This service is designed to perform authentication operation
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class AuthService {

	
	/**
	 * Instantiate the service
	 */
	public AuthService() {
		super();
	}
	
	/**
	 * Execute the user login operation
	 * 
	 * @param user	email and password of the user
	 * @return	return true if user credential match, otherwise false
	 * @throws ServiceException if any service exception occure
	 */
	public boolean login(User user) throws ServiceException {
		
		boolean isAuthSuccess = false;
		
		if (new UserService().isExists(user.getUsername())) {

			try {
				
				// Read the user details from DB
				String originalPassword = 
						new UserDao().read(user.getUsername()).getPassword();
				
				if (user.getPassword().equals(originalPassword)) {
					
					// If user credential verified
					isAuthSuccess = true;
				}
				
			} catch (DAOException e) {
				
				// Wrap and throw the Dao exception into service 
				throw new ServiceException(e.getMessage(), e.getCause());
			}
		} else {

			// If user not exists, throw error
			throw new NotFoundException("Ops! User is not exists.");
		}
		
		return isAuthSuccess;
	}

	/**
	 * Execute the user logout operation
	 * 
	 * @param session 	session credential of the user
	 * @return	return true if the user logged out, otherwise false
	 * @throws ServiceException if any service exception occure
	 */
	public boolean logout(HttpSession session) throws ServiceException {
		
		boolean isLoggedOut = false;
		
		if (session != null) {
			
			try {
			
				// Remove user details from the session
				session.removeAttribute("user");
				
				// Invalidate the session 
				session.invalidate();
				
				isLoggedOut = true;
				
			} catch (IllegalStateException e) {
	
				// Wrap and throw the Dao exception into service 
				throw new ServiceException("Session is invalid state !", e.getCause());
			}
		}
		
		return isLoggedOut;
	}

	
}
