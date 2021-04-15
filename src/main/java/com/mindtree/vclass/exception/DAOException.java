package com.mindtree.vclass.exception;


/**
 * This class is used to implement DAO exception
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class DAOException extends Exception {

	
	private static final long serialVersionUID = 1L;


	/**
	 * Exception without initialization
	 */
	public DAOException() {
		super();
	}

	/**
	 * @param message	message of the exception
	 * @param cause		cause of the exception
	 * @param enableSuppression		suppression status
	 * @param writableStackTrace	writable stack trace status
	 */
	public DAOException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message	message of the exception
	 * @param cause		cause of the message
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message	message for the exception
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * @param cause		cause of the exception
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}
}
