package com.mindtree.vclass.model;

import java.sql.Date;
import java.util.Map;

/**
 * This class is used to implement User Operation
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class User implements Model  {
	
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Properties of the user
	 */
	private long id;
	private String name;
	private byte age;
	private String username;
	private String password;
	private String role;
	private Map<String, String> address;
	private Date createdAt;
	private Date updatedAt;
	
	/**
	 * Instantiate the User prperties 
	 */
	public User() {
		super();
	}

	/**
	 * Initialize the User properties
	 * 
	 * @param id
	 * @param name
	 * @param age
	 * @param username
	 * @param password
	 * @param role
	 * @param address
	 * @param createdAt
	 * @param updatedAt
	 */
	public User(String name, byte age, String username, 
			String password, String role, Map<String, String> address) {
		super();
		this.name = name;
		this.age = age;
		this.username = username;
		this.password = password;
		this.role = role;
		this.address = address;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public byte getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(byte age) {
		this.age = age;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the address
	 */
	public Map<String, String> getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Map<String, String> address) {
		this.address = address;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
}
