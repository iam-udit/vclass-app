package com.mindtree.vclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.mindtree.vclass.exception.ConnectionFailedException;
import com.mindtree.vclass.exception.DAOException;
import com.mindtree.vclass.model.User;
import com.mindtree.vclass.utility.DBUtility;

/**
 * This dao is used to perform User database operations
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class UserDAO implements DAO<User> {

	/**
	 * Instantiate the dao
	 */
	public UserDAO() {
		super();
	}
	
	@Override
	/**
	 * Get a user details from DB by id
	 * 
	 * @param id id of the user
	 * @return return user details if found, otherwise null
	 * @throws DAOException if any DAO exception occure
	 */
	public User read(long id) throws DAOException {
		
		User user = null;
		
		try {
			
			// Get the user details by filtering username
			Optional<User> optionalUser = read().stream()
					.filter(tempUser -> (tempUser.getId() == id)).findFirst();
			
			if (optionalUser.isPresent()) {
				
				// Store the user details 
				user = optionalUser.get();
			}
		}  catch (DAOException daoException) {
			
			// Throw the DAO exception
			throw daoException;
		}
		
		return user;
	}
	
	@Override
	/**
	 * Get a user details from DB by username
	 * 
	 * @param username email of the user
	 * @return return user details if found, otherwise null
	 * @throws DAOException if any DAO exception occure
	 */
	public User read(String username) throws DAOException {
		
		User user = null;
		
		try {
			
			// Get the user details by filtering username
			Optional<User> optionalUser = read().stream().filter(tempUser -> 
					(tempUser.getUsername().equals(username))).findFirst();
			
			if (optionalUser.isPresent()) {
				
				// Store the user details 
				user = optionalUser.get();
			}
		}  catch (DAOException daoException) {
			
			// Throw the DAO exception
			throw daoException;
		}
		
		return user;
	}
	

	@Override
	/**
	 * Get all the user details from the DB
	 * 
	 * @return return list of user details if found, otherwise null
	 * @throws DAOException if any DAO exception occure
	 */
	public List<User> read() throws DAOException {
		
		DBUtility utility = new DBUtility();
		List<User> users = new ArrayList<User>();
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			// Establish the database connection
			connection = utility.getConnection();
			
			String SQL = "SELECT u.id, u.name, u.age, r.name AS role, u.username, u.password, "
						+ "a.city, a.state, a.country, a.pin, u.created_at, u.updated_at "
						+ "FROM users AS u INNER JOIN addresses AS a ON u.id = a.user_id "
						+ "INNER JOIN role_user AS ru ON u.id = ru.user_id "
						+ "INNER JOIN roles AS r ON ru.role_id = r.id";
			
			preparedStatement = connection.prepareStatement(SQL);
			
			// Exceute the statement and retieve the user details
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
								
				User user = new User();
				
				// Set the user properties
				user.setId(resultSet.getLong("id"));
				user.setName(resultSet.getString("name"));
				user.setAge(resultSet.getByte("age"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setRole(resultSet.getString("role"));
				user.setCreatedAt(resultSet.getDate("created_at"));
				user.setUpdatedAt(resultSet.getDate("updated_at"));
				
				Map<String, String> address = new HashMap<String, String>();	
				
				// Set the user address
				address.put("pin", resultSet.getString("pin"));
				address.put("city", resultSet.getString("city"));
				address.put("state", resultSet.getString("state"));
				address.put("country", resultSet.getString("country"));
				
				user.setAddress(address);
				
				// Add the user details to list
				users.add(user);
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
		
		return users;
	}

	@Override
	/**
	 * Create a new user in DB
	 * 
	 * @param user 	new user details
	 * @return return true if new user created, otherwise false
	 * @throws DAOException if any DAO exception occure
	 */
	public boolean create(User user) throws DAOException {
		
		boolean isUserCreated = false;
		DBUtility utility = new DBUtility();
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			// Establish the database connection
			connection = utility.getConnection();
			
			// Start the transaction
			connection.setAutoCommit(false);
			
			// Store user credential details
			String CREATE_USER_SQL = 
					"INSERT INTO users(name, age, username, password) VALUES(?, ?, ?, ?)";
			
			preparedStatement = 
					connection.prepareStatement(CREATE_USER_SQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setByte(2, user.getAge());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getPassword());

			preparedStatement.executeUpdate();
			
			// Get the deleted user id
			resultSet = preparedStatement.getGeneratedKeys();
			long userID = resultSet.next() ? resultSet.getLong(1) : 0;
			utility.closeResource(preparedStatement);
			utility.closeResource(resultSet);

			// Get the id of the selected role
			preparedStatement = 
					connection.prepareStatement("SELECT id FROM roles WHERE name = ?");
			preparedStatement.setString(1, user.getRole());
			resultSet = preparedStatement.executeQuery();
			long roleID = resultSet.next() ? resultSet.getLong(1) : 0;
			utility.closeResource(preparedStatement);
			utility.closeResource(resultSet);
			
			// Store the user role details
			String CREATE_USER_ROLE_SQL = "INSERT INTO role_user(user_id, role_id) VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(CREATE_USER_ROLE_SQL);
			preparedStatement.setLong(1, userID);
			preparedStatement.setLong(2, roleID);
			preparedStatement.executeUpdate();
			utility.closeResource(preparedStatement);
			
			// Store user address details
			String CREATE_ADDRESS_SQL = 
					"INSERT INTO addresses(user_id, city, state, country, pin) VALUES(?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(CREATE_ADDRESS_SQL);
			preparedStatement.setLong(1, userID);
			
			Map<String, String> address = user.getAddress();
			preparedStatement.setString(2, address.get("city"));
			preparedStatement.setString(3, address.get("state"));
			preparedStatement.setString(4, address.get("country"));
			preparedStatement.setString(5, address.get("pin"));		
			preparedStatement.executeUpdate();
			
			// Commit the inserted record
			connection.commit();
			isUserCreated = true;
			
		}  catch (ConnectionFailedException | SQLException e) {
			
			// Rollback the insert details
			try {
				connection.rollback();
			} catch (SQLException | NullPointerException e1) {}
			
			// Wrap and throw the SQL/ConnectionFailed into DAO exception
			throw new DAOException(e.getMessage(), e.getCause());
		} finally {
			
			// Close all the resources
			utility.closeResource(connection);
			utility.closeResource(preparedStatement);
			utility.closeResource(resultSet);
		}
		
		return isUserCreated;
	}

	@Override
	/**
	 * Update an existing user details
	 * 
	 * @param user new user details
	 * @return return true if user details updated, otherwise false
	 * @throws DAOException if any DAO exception occure
	 */
	public boolean update(User user) throws DAOException {

		boolean isUserUpdated = false;
		DBUtility utility = new DBUtility();
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			// Establish the database connection
			connection = utility.getConnection();
			
			// Start the transaction
			connection.setAutoCommit(false);
			
			// Update user table details
			String UPDATE_USER_SQL = 
					"UPDATE users SET name = ?, age = ?, password = ? WHERE id = ?";
			
			preparedStatement = connection.prepareStatement(UPDATE_USER_SQL);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setByte(2, user.getAge());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setLong(4, user.getId());
			
			preparedStatement.executeUpdate();
			utility.closeResource(preparedStatement);
			
			// Update the user address 
			String UPDATE_ADDRESS_SQL = 
					"UPDATE addresses SET city = ?, state = ?, country = ?, pin = ? WHERE user_id = ?";
			
			preparedStatement = connection.prepareStatement(UPDATE_ADDRESS_SQL);
			Map<String, String> newAddress = user.getAddress();
			
			preparedStatement.setString(1, newAddress.get("city"));
			preparedStatement.setString(2, newAddress.get("state"));
			preparedStatement.setString(3, newAddress.get("country"));
			preparedStatement.setString(4, newAddress.get("pin"));
			preparedStatement.setLong(5, user.getId());

			preparedStatement.executeUpdate();
			
			// Commit the updated record
			connection.commit();
			isUserUpdated = true;
			
		}  catch (ConnectionFailedException | SQLException e) {
			
			// Role back the update data
			try {
				connection.rollback();
			} catch (SQLException | NullPointerException e1) {}
				
			// Wrap and throw the SQL/ConnectionFailed into DAO exception
			throw new DAOException(e.getMessage(), e.getCause());
		} finally {
			
			// Close all the resources
			utility.closeResource(connection);			
			utility.closeResource(preparedStatement);
		}
		
		return isUserUpdated;
	}

	@Override
	/**
	 * Delete the user details from DB
	 * 
	 * @param username 	email of user
	 * @return return true if user details updated, otherwise false
	 * @throws DAOException if any DAO exception occure
	 */
	public boolean delete(String username) throws DAOException {
		
		boolean isUserDeleted = false;
		DBUtility utility = new DBUtility();
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			// Establish the database connection
			connection = utility.getConnection();
			
			// Start the transaction
			connection.setAutoCommit(false);
			
			// Delete user credential details
			String DELETE_USER_SQL = "DELETE FROM users WHERE username = ?";
			preparedStatement = 
					connection.prepareStatement(DELETE_USER_SQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, username);
			preparedStatement.executeUpdate();
						
			// Get the deleted user id
			resultSet = preparedStatement.getGeneratedKeys();
			long userID = resultSet.next() ? resultSet.getLong(1) : 0;
			utility.closeResource(preparedStatement);
			
			// Delete the user role details
			String DELETE_USER_ROLE_SQL = "DELETE FROM role_user WHERE user_id = ?";
			preparedStatement = connection.prepareStatement(DELETE_USER_ROLE_SQL);
			preparedStatement.setLong(1, userID);
			preparedStatement.executeUpdate();
			utility.closeResource(preparedStatement);
			
			// Delete user address details
			String DELETE_ADDRESS_SQL = "DELETE FROM addresses WHERE user_id = ?";
			preparedStatement = connection.prepareStatement(DELETE_ADDRESS_SQL);
			preparedStatement.setLong(1, userID);
			preparedStatement.executeUpdate();
			
			// Commit the updated record
			connection.commit();
			isUserDeleted = true;
			
		} catch (ConnectionFailedException | SQLException e) {
			
			// Role back the update data
			try {
				connection.rollback();
			} catch (SQLException| NullPointerException e1) {}
				
			// Wrap and throw the SQL/ConnectionFailed into DAO exception
			throw new DAOException(e.getMessage(), e.getCause());
		} finally {
			
			// Close all the resources
			utility.closeResource(connection);			
			utility.closeResource(preparedStatement);
			utility.closeResource(resultSet);
		}
		
		return isUserDeleted;
	}

}
