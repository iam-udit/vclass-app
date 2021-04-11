package com.mindtree.vclass.exception;


/**
 * This class is used to implement DB connection exception
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class ConnectionFailedException extends DAOException {

	private static final long serialVersionUID = 1L;


	/**
	 * Exception without initialization
	 */
	public ConnectionFailedException() {
		super();
	}

	/**
	 * @param message	message of the exception
	 * @param cause		cause of the exception
	 * @param enableSuppression		suppression status
	 * @param writableStackTrace	writable stack trace status
	 */
	public ConnectionFailedException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message	message of the exception
	 * @param cause		cause of the message
	 */
	public ConnectionFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message	message for the exception
	 */
	public ConnectionFailedException(String message) {
		super(message);
	}

	/**
	 * @param cause		cause of the exception
	 */
	public ConnectionFailedException(Throwable cause) {
		super(cause);
	}
	
	@Override
	/**
	 * Return the message of the exception
	 */
	public String getMessage() {
		return super.getMessage();
	}
}
