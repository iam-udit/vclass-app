package com.mindtree.vclass.model;

import java.sql.Date;

/**
 * This class is used to implement the news operation
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class News implements Model {

	
	private static final long serialVersionUID = 1L;

	/**
	 * Properties of News
	 */
	private int id;
	private String title;
	private String description;
	private Date createdAt;
	private Date updatedAt;
	
	/**
	 * Instantiate the News 
	 */
	public News() {
		super();
	}

	/**
	 * Initialize the news properties
	 * 
	 * @param id
	 * @param title
	 * @param description
	 * @param createdAt
	 * @param updatedAt
	 */
	public News(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param createdAt the create date to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param updatedAt the update date to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
}
