package com.mindtree.vclass.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mindtree.vclass.exception.ConnectionFailedException;


/**
 * This utility is used to implement DB operation
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class DBUtility {

	
	private static Connection connection;
	private static final String DB_USER_NAME;
	private static final String DB_PASSWORD;
	private static final String DB_URL;
	
	
	/**
	 * Instantiate the DB utility
	 */
	public DBUtility() {
		super();
	}
	
	/**
	 * Set the DB configuration
	 */
	static {
							
		Properties config = new ResourceUtility().loadConfig();
		
		// Load and set the DB credentials
		DB_URL = config.getProperty("DB_URL");
		DB_USER_NAME = config.getProperty("DB_USER_NAME");
		DB_PASSWORD = config.getProperty("DB_PASSWORD");
	}
	
	
	/**
	 * Get the MySQL database connection
	 * 
	 * @return return connection instance, if connected otherwise null
	 * @throws SQLException 
	 */
	public Connection getConnection() throws ConnectionFailedException {
				
		try {
			if (connection == null || connection.isClosed()) {
				
				// Establish the DB connection
				connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
			}
		} catch (SQLException e) {
			
			// Throw the connection failed exception
			throw new ConnectionFailedException("Database connection failed ! \n" 
					+ "Cause: " + e.getMessage(), e.getCause());
		}
		
		return connection;
	}
	
	/**
	 * Close the connection resource
	 * 
	 * @param connection 	unclosed connection instance
	 */
	public void closeResource(Connection connection) throws ConnectionFailedException {
		
		if (connection != null) {

			try {
				
				// Close the connection resource
				connection.close();
				
			} catch (SQLException e) {
				
				// Throw the connection failed exception
				throw new ConnectionFailedException("Database connection couldn't close ! \n" 
						+ "Cause: " + e.getMessage(), e.getCause());
			}
		}
	}
	
	/**
	 * Close the prepared statement resource
	 * 
	 * @param prepareStatement 	unclosed prepare statement instance
	 */
	public void closeResource(PreparedStatement prepareStatement) throws ConnectionFailedException {
		
		if (prepareStatement != null) {

			try {
				
				// Close the statement request resource
				prepareStatement.close();
				
			} catch (SQLException e) {
				
				// Throw the connection failed exception
				throw new ConnectionFailedException("PreparedStatement couldn't close ! \n" 
						+ "Cause: " + e.getMessage(), e.getCause());
			}
		}
	}
	
	/**
	 * Close the result set resource
	 * 
	 * @param resultSet 	unclosed result set instance
	 */
	public void closeResource(ResultSet resultSet) throws ConnectionFailedException {
		
		if (resultSet != null) {

			try {
				
				// Close the resultset resource
				resultSet.close();
				
			} catch (SQLException e) {
				
				// Throw the connection failed exception
				throw new ConnectionFailedException("ResultSet couldn't close ! \n" 
						+ "Cause: " + e.getMessage(), e.getCause());
			}
		}
	}
}
