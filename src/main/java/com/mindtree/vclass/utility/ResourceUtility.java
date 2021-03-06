package com.mindtree.vclass.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This utility is used to implement operation for resources
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class ResourceUtility {

	/**
	 * Load the credential from the config
	 * 
	 * @return return loaded credential
	 */
	public Properties loadConfig() {
		
		Properties configuration = null;
		InputStream configFile = null;
		
		try {

			// Read config file from the drive
			configFile = getClass().getResourceAsStream("/config.properties");
		
			// Load the config properties
			configuration = new Properties();
			configuration.load(configFile);
			
			// Close file resources
			configFile.close();

		} catch (IOException e) {
			
			// Log the IO exception details
			System.out.println("Filed to load config file ! \n Cause: " + e.getMessage());
		}
		
		return configuration;
	}
}
