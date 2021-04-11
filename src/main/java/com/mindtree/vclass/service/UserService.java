package com.mindtree.vclass.service;

import java.util.List;

import com.mindtree.vclass.dao.DAO;
import com.mindtree.vclass.dao.UserDao;
import com.mindtree.vclass.exception.DAOException;
import com.mindtree.vclass.exception.DuplicateFlagException;
import com.mindtree.vclass.exception.NotFoundException;
import com.mindtree.vclass.exception.ServiceException;
import com.mindtree.vclass.model.User;

/**
 * This service is implemented to perform user operation
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class UserService implements Service<User> {

	
	private DAO<User> dao;
	
	/**
	 * Instantiate the service 
	 */
	public UserService() {
		
		super();
		dao = new UserDao();
	}
	
	@Override
	/**
	 * Check whether a user is exists or not
	 * 
	 * @param username	email of the user
	 * @return return true if user exists, otherwise false
	 * @throws ServiceException if any service exception occure
	 */
	public boolean isExists(String username) throws ServiceException {
		
		boolean isExists = false;
		
		try {
			
			// Retrieve the user details from DB
			User user = dao.read(username);
			
			if (user != null) {
				
				// If the user exists
				isExists = true;
			}
		} catch (DAOException e) {
			
			// Wrap and throw the Dao exception into service 
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		
		return isExists;
	}

	@Override
	/**
	 * Read a user details by username
	 *  
	 * @param username email of the user
	 * @return return user details if found, otherwise null
	 * @throws ServiceException if any service exception occur
	 */
	public User read(Object username) throws ServiceException {
		
		User user = null;
		
		try {
			
			// Read the user details from DB
			user = dao.read(username);
			
		} catch (DAOException e) {
			
			// Wrap and throw the Dao exception into service 
			throw new ServiceException(e.getMessage(), e.getCause());
		}
			
		return user;
	}

	@Override
	/**
	 * Read all user details from DB
	 * 
	 * @return list of user details
	 * @throws ServiceException if any service exception occure
	 * 
	 */
	public List<User> read() throws ServiceException {

		List<User> users = null;

		try {

			// Read all the user details from DB
			users = dao.read();

		} catch (DAOException e) {
			
			// Wrap and throw the Dao exception into service 
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		
		return users;
	}

	@Override
	/**
	 * Create a new user details
	 * 
	 * @param user 	new user details
	 * @return return true if new user created, otherwise false
	 * @throws ServiceException if any service exception occure
	 */
	public boolean create(User user) throws ServiceException {
		
		boolean isUserCreated = false;
		
		if (!isExists(user.getUsername())) {
			
			try {
				
				// Create a new user
				isUserCreated = dao.create(user);
				
			} catch (DAOException e) {
				
				// Wrap and throw the Dao exception into service 
				throw new ServiceException(e.getMessage(), e.getCause());
			}
		} else {
			
			// If user already exists, throw error
			throw new DuplicateFlagException("Ops! User is already exists.");
		}
		
		return isUserCreated;
	}

	@Override
	/**
	 * Update the existing user details 
	 * 
	 * @param user	 user new details
	 * @return return true if the user details updated, otherwise false
	 * @throws ServiceException if any service exception occure	 
	 */
	public boolean update(User user) throws ServiceException {
	
		boolean isUserUpdated = false;
		
		if (isExists(user.getUsername())) {
			
			try {
				
				// Upadate the user details
				isUserUpdated = dao.update(user);
				
			} catch (DAOException e) {
				
				// Wrap and throw the Dao exception into service 
				throw new ServiceException(e.getMessage(), e.getCause());
			}
		} else {
			
			// If user not exists, throw error
			throw new NotFoundException("Ops! User is not exists.");
		}
		
		return isUserUpdated;
	}

	@Override
	/**
	 * Delete the user details from DB
	 * 
	 * @param username	 email of the user
	 * @return return true if the user details deleted, otherwise false
	 * @throws ServiceException if any service exception occure	 
	 */
	public boolean delete(Object username) throws ServiceException {

		boolean isUserDeleted = false;
		
		if (isExists((String) username)) {
			
			try {
				
				// Delete the user details
				isUserDeleted = dao.delete(username);
				
			} catch (DAOException e) {
				
				// Wrap and throw the Dao exception into service 
				throw new ServiceException(e.getMessage(), e.getCause());
			}
		} else {
			
			// If user not exists, throw error
			throw new NotFoundException("Ops! User is not exists.");
		}
		
		return isUserDeleted;
	}
}
