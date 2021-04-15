package com.mindtree.vclass.service;

import java.util.List;

import com.mindtree.vclass.dao.DAO;
import com.mindtree.vclass.dao.LessionDAO;
import com.mindtree.vclass.exception.DAOException;
import com.mindtree.vclass.exception.DuplicateFlagException;
import com.mindtree.vclass.exception.NotFoundException;
import com.mindtree.vclass.exception.ServiceException;
import com.mindtree.vclass.model.Lession;

/**
 * This service is implemented to perform lession operation
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class LessionService implements Service<Lession> {

	
	private DAO<Lession> dao;
	
	/**
	 * Instantiate the service 
	 */
	public LessionService() {
		
		super();
		dao = new LessionDAO();
	}
	
	@Override
	/**
	 * Check whether a lession is exists or not
	 * 
	 * @param slug	slug of the lession
	 * @return return true if lession exists, otherwise false
	 * @throws ServiceException if any service exception occure
	 */
	public boolean isExists(String slug) throws ServiceException {
		
		boolean isExists = false;
		
		try {
			
			// Retrieve the lession details from DB
			Lession lession = dao.read(slug);
			
			if (lession != null) {
				
				// If the lession exists
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
	 * Read a lession details by id
	 *  
	 * @param id id of the lession
	 * @return return lession details if found, otherwise null
	 * @throws ServiceException if any service exception occur
	 */
	public Lession read(long id) throws ServiceException {
		
		Lession lession = null;
		
		try {
			
			// Read the lession details from DB
			lession = dao.read(id);
			
		} catch (DAOException e) {
			
			// Wrap and throw the Dao exception into service 
			throw new ServiceException(e.getMessage(), e.getCause());
		}
			
		return lession;
	}

	@Override
	/**
	 * Read a lession details by slug
	 *  
	 * @param slug slug of the lession
	 * @return return lession details if found, otherwise null
	 * @throws ServiceException if any service exception occur
	 */
	public Lession read(String slug) throws ServiceException {
		
		Lession lession = null;
		
		try {
			
			// Read the lession details from DB
			lession = dao.read(slug);
			
		} catch (DAOException e) {
			
			// Wrap and throw the Dao exception into service 
			throw new ServiceException(e.getMessage(), e.getCause());
		}
			
		return lession;
	}

	@Override
	/**
	 * Read all lession details from DB
	 * 
	 * @return list of lession details
	 * @throws ServiceException if any service exception occure
	 * 
	 */
	public List<Lession> read() throws ServiceException {

		List<Lession> lessions = null;

		try {

			// Read all the lession details from DB
			lessions = dao.read();

		} catch (DAOException e) {
			
			// Wrap and throw the Dao exception into service 
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		
		return lessions;
	}

	@Override
	/**
	 * Create a new lession details
	 * 
	 * @param lession 	new lession details
	 * @return return true if new lession created, otherwise false
	 * @throws ServiceException if any service exception occure
	 */
	public boolean create(Lession lession) throws ServiceException {
		
		boolean isLessionCreated = false;
		
		if (!isExists(lession.getSlug())) {
			
			try {
				
				// Create a new lession
				isLessionCreated = dao.create(lession);
				
			} catch (DAOException e) {
				
				// Wrap and throw the Dao exception into service 
				throw new ServiceException(e.getMessage(), e.getCause());
			}
		} else {
			
			// If lession already exists, throw error
			throw new DuplicateFlagException("Ops! Lession is already exists.");
		}
		
		return isLessionCreated;
	}

	@Override
	/**
	 * Update the existing lession details 
	 * 
	 * @param lession	 lession new details
	 * @return return true if the lession details updated, otherwise false
	 * @throws ServiceException if any service exception occure	 
	 */
	public boolean update(Lession lession) throws ServiceException {
	
		boolean isLessionUpdated = false;
		
		if (isExists(lession.getSlug())) {
			
			try {
				
				// Upadate the lession details
				isLessionUpdated = dao.update(lession);
				
			} catch (DAOException e) {
				
				// Wrap and throw the Dao exception into service 
				throw new ServiceException(e.getMessage(), e.getCause());
			}
		} else {
			
			// If lession not exists, throw error
			throw new NotFoundException("Ops! Lession is not exists.");
		}
		
		return isLessionUpdated;
	}

	@Override
	/**
	 * Delete the lession details from DB
	 * 
	 * @param slug	 slug of the lession
	 * @return return true if the lession details deleted, otherwise false
	 * @throws ServiceException if any service exception occure	 
	 */
	public boolean delete(String slug) throws ServiceException {

		boolean isLessionDeleted = false;
		
		if (isExists(slug)) {
			
			try {
				
				// Delete the lession details
				isLessionDeleted = dao.delete(slug);
				
			} catch (DAOException e) {
				
				// Wrap and throw the Dao exception into service 
				throw new ServiceException(e.getMessage(), e.getCause());
			}
		} else {
			
			// If lession not exists, throw error
			throw new NotFoundException("Ops! Lession is not exists.");
		}
		
		return isLessionDeleted;
	}
}
