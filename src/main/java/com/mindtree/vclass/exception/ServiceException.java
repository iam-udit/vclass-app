package com.mindtree.vclass.exception;


/**
 * This class is used to implement service exception
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;


	/**
	 * Exception without initialization
	 */
	public ServiceException() {
		super();
	}

	/**
	 * @param message	message for the exception
	 */
	public ServiceException(String message) {
		super(message);
	}
	
	/**
	 * @param cause		cause of the exception
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * @param message	message of the exception
	 * @param cause		cause of the message
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message	message of the exception
	 * @param cause		cause of the exception
	 * @param enableSuppression		suppression status
	 * @param writableStackTrace	writable stack trace status
	 */
	public ServiceException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
