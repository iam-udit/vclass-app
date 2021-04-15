package com.mindtree.vclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mindtree.vclass.exception.ConnectionFailedException;
import com.mindtree.vclass.exception.DAOException;
import com.mindtree.vclass.model.Lession;
import com.mindtree.vclass.utility.DBUtility;

/**
 * This dao is used to perform Lession database operations
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class LessionDAO implements DAO<Lession> {

	/**
	 * Instantiate the dao
	 */
	public LessionDAO() {
		super();
	}
	
	@Override
	/**
	 * Get a lession details from DB by ID
	 * 
	 * @param id id of the lession
	 * @return return lession details if found, otherwise null
	 * @throws DAOException if any DAO exception occure
	 */
	public Lession read(long id) throws DAOException {
		
		Lession lession = null;
		
		try {
			
			// Get the lession details by filtering slug
			Optional<Lession> optionalLession = read().stream()
					.filter(tempLession -> (tempLession.getId() == id)).findFirst();
			
			if (optionalLession.isPresent()) {
				
				// Store the lession details 
				lession = optionalLession.get();
			}
		}  catch (DAOException daoException) {
			
			// Throw the DAO exception
			throw daoException;
		}
		
		return lession;
	}
	
	@Override
	/**
	 * Get a lession details from DB by slug
	 * 
	 * @param slug slug of the lession
	 * @return return lession details if found, otherwise null
	 * @throws DAOException if any DAO exception occure
	 */
	public Lession read(String slug) throws DAOException {
		
		Lession lession = null;
		
		try {
			
			// Get the lession details by filtering slug
			Optional<Lession> optionalLession = read().stream().filter(tempLession -> 
					(tempLession.getSlug().equals(slug))).findFirst();
			
			if (optionalLession.isPresent()) {
				
				// Store the lession details 
				lession = optionalLession.get();
			}
		}  catch (DAOException daoException) {
			
			// Throw the DAO exception
			throw daoException;
		}
		
		return lession;
	}
	

	@Override
	/**
	 * Get all the lession details from the DB
	 * 
	 * @return return list of lession details if found, otherwise null
	 * @throws DAOException if any DAO exception occure
	 */
	public List<Lession> read() throws DAOException {
		
		DBUtility utility = new DBUtility();
		List<Lession> lessions = new ArrayList<Lession>();
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			// Establish the database connection
			connection = utility.getConnection();
			
			String SQL = "SELECT id, title, user_id, slug, description, "
					+ "video, is_published, is_approved, created_at, "
					+ "updated_at FROM lessions";
			
			preparedStatement = connection.prepareStatement(SQL);
			
			// Exceute the statement and retieve the lession details
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
								
				Lession lession = new Lession();
				
				// Set the lession properties
				lession.setId(resultSet.getLong("id"));
				lession.setTitle(resultSet.getString("title"));
				lession.setUser(resultSet.getLong("user_id"));
				lession.setSlug(resultSet.getString("slug"));
				lession.setDescription(resultSet.getString("description"));
				lession.setVideo(resultSet.getString("video"));
				lession.setApproved(resultSet.getBoolean("is_approved"));
				lession.setPublished(resultSet.getBoolean("is_published"));
				lession.setCreatedAt(resultSet.getDate("created_at"));
				lession.setUpdatedAt(resultSet.getDate("updated_at"));
				
				// Add the lession details to list
				lessions.add(lession);
			}
			
		}  catch (ConnectionFailedException | SQLException e) {
			
			// Wrap and throw the SQL/ConnectionFailed into DAO exception
			throw new DAOException(e.getMessage(), e.getCause());
		} finally {
			
			// Close all the resources
			utility.closeResource(connection);
			utility.closeResource(preparedStatement);
			utility.closeResource(resultSet);
		}
		
		return lessions;
	}

	@Override
	/**
	 * Create a new lession in DB
	 * 
	 * @param lession 	new lession details
	 * @return return true if new lession created, otherwise false
	 * @throws DAOException if any DAO exception occure
	 */
	public boolean create(Lession lession) throws DAOException {
		
		boolean isLessionCreated = false;
		DBUtility utility = new DBUtility();
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			// Establish the database connection
			connection = utility.getConnection();
			
			
			// Store lession credential details
			String CREATE_LESSION_SQL = "INSERT INTO lessions(title, user_id, slug, "
					+ "description, video, is_published, is_approved) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(CREATE_LESSION_SQL);
			preparedStatement.setString(1, lession.getTitle());
			preparedStatement.setLong(2, lession.getUser().getId());
			preparedStatement.setString(3, lession.getSlug());
			preparedStatement.setString(4, lession.getDescription());
			preparedStatement.setString(5, lession.getVideo());
			preparedStatement.setBoolean(6, lession.isPublished());
			preparedStatement.setBoolean(7, lession.isApproved());

			// Exeucte the statement
			if (preparedStatement.executeUpdate() > 0) {
				
				// If lession successfully created
				isLessionCreated = true;
			}
		}  catch (ConnectionFailedException | SQLException e) {
			
			// Wrap and throw the SQL/ConnectionFailed into DAO exception
			throw new DAOException(e.getMessage(), e.getCause());
		} finally {
			
			// Close all the resources
			utility.closeResource(connection);
			utility.closeResource(preparedStatement);
		}
		
		return isLessionCreated;
	}

	@Override
	/**
	 * Update an existing lession details
	 * 
	 * @param lession new lession details
	 * @return return true if lession details updated, otherwise false
	 * @throws DAOException if any DAO exception occure
	 */
	public boolean update(Lession lession) throws DAOException {

		boolean isLessionUpdated = false;
		DBUtility utility = new DBUtility();
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			// Establish the database connection
			connection = utility.getConnection();
			
			
			// Update lession table details
			String UPDATE_LESSION_SQL = "UPDATE lessions SET title = ?, description = ?, "
					+ "video = ?, is_published = ?, is_approved = ? WHERE slug = ?";
			
			preparedStatement = connection.prepareStatement(UPDATE_LESSION_SQL);
			preparedStatement.setString(1, lession.getTitle());
			preparedStatement.setString(2, lession.getDescription());
			preparedStatement.setString(3, lession.getVideo());
			preparedStatement.setBoolean(4, lession.isPublished());
			preparedStatement.setBoolean(5, lession.isApproved());
			preparedStatement.setString(6, lession.getSlug());

			// Update the lession
			if (preparedStatement.executeUpdate() > 0) {
				
				// If lession details updated
				isLessionUpdated = true;
			}			
		}  catch (ConnectionFailedException | SQLException e) {
			
			// Wrap and throw the SQL/ConnectionFailed into DAO exception
			throw new DAOException(e.getMessage(), e.getCause());
		} finally {
			
			// Close all the resources
			utility.closeResource(connection);			
			utility.closeResource(preparedStatement);
		}
		
		return isLessionUpdated;
	}

	@Override
	/**
	 * Delete the lession details from DB
	 * 
	 * @param slug 	slug of lession
	 * @return return true if lession details updated, otherwise false
	 * @throws DAOException if any DAO exception occure
	 */
	public boolean delete(String slug) throws DAOException {
		
		boolean isLessionDeleted = false;
		DBUtility utility = new DBUtility();
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			// Establish the database connection
			connection = utility.getConnection();
			
			// Delete lession credential details
			String DELETE_LESSION_SQL = "DELETE FROM lessions WHERE slug = ?";
			
			preparedStatement = connection.prepareStatement(DELETE_LESSION_SQL);
			preparedStatement.setString(1, slug);
			
			// Delete the lession details
			if (preparedStatement.executeUpdate() > 0) {
				
				// If the lession is deleted
				isLessionDeleted = true;
			}			
		} catch (ConnectionFailedException | SQLException e) {
		
			// Wrap and throw the SQL/ConnectionFailed into DAO exception
			throw new DAOException(e.getMessage(), e.getCause());
		} finally {
			
			// Close all the resources
			utility.closeResource(connection);			
			utility.closeResource(preparedStatement);
		}
		
		return isLessionDeleted;
	}

}
