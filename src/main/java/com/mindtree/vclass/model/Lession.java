package com.mindtree.vclass.model;

import java.sql.Date;

import com.mindtree.vclass.dao.UserDAO;
import com.mindtree.vclass.exception.DAOException;

/**
 * This class is used to implement Lession Operation
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class Lession implements Model  {
	
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Properties of the user
	 */
	private long id;
	private String title;
	private long user;
	private String slug;
	private String description;
	private String video;
	private boolean isPublished;
	private boolean isApproved;
	private Date createdAt;
	private Date updatedAt;
	
	/**
	 * Instantiate the User prperties 
	 */
	public Lession() {
		super();
	}

	
	/**
	 * Initialize the properties
	 * 
	 * @param id
	 * @param title
	 * @param user
	 * @param slug
	 * @param description
	 * @param video
	 * @param isPublished
	 * @param isApproved
	 * @param createdAt
	 * @param updatedAt
	 */
	public Lession(long id, String title, long user, String slug, 
			String description, String video, boolean isPublished,
			boolean isApproved, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.user = user;
		this.slug = slug;
		this.description = description;
		this.video = video;
		this.isPublished = isPublished;
		this.isApproved = isApproved;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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
	 * @return the user
	 */
	public User getUser() {
		
		User userDetails = null;
			
		try {
			// Retrive the user details 
			userDetails = new UserDAO().read(this.user);
		} catch (DAOException e) {
			// Log the exception message
			System.out.println(e.getMessage());
		}
		
		return userDetails;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(long user) {
		this.user = user;
	}


	/**
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}


	/**
	 * @param slug the slug to set
	 */
	public void setSlug(String slug) {
		this.slug = slug;
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
	 * @return the video
	 */
	public String getVideo() {
		return video;
	}


	/**
	 * @param video the video to set
	 */
	public void setVideo(String video) {
		this.video = video;
	}


	/**
	 * @return the isPublished
	 */
	public boolean isPublished() {
		return isPublished;
	}


	/**
	 * @param isPublished the isPublished to set
	 */
	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}


	/**
	 * @return the isApproved
	 */
	public boolean isApproved() {
		return isApproved;
	}


	/**
	 * @param isApproved the isApproved to set
	 */
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}


	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}


	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}


	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
