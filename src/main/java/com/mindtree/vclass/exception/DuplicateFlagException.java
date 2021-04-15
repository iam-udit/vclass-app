package com.mindtree.vclass.exception;


/**
 * This class is used to implement duplicate entry exception
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class DuplicateFlagException extends ServiceException {

	private static final long serialVersionUID = 1L;


	/**
	 * Exception without initialization
	 */
	public DuplicateFlagException() {
		super();
	}

	/**
	 * @param message	message for the exception
	 */
	public DuplicateFlagException(String message) {
		super(message);
	}
	
	/**
	 * @param cause		cause of the exception
	 */
	public DuplicateFlagException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message	message of the exception
	 * @param cause		cause of the message
	 */
	public DuplicateFlagException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param message	message of the exception
	 * @param cause		cause of the exception
	 * @param enableSuppression		suppression status
	 * @param writableStackTrace	writable stack trace status
	 */
	public DuplicateFlagException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	@Override
	/**
	 * Return the message of the exception
	 */
	public String getMessage() {
		return super.getMessage();
	}
}
