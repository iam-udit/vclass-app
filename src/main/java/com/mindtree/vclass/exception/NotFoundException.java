package com.mindtree.vclass.exception;


/**
 * This class is used to implement not found exception
 * 
 * @author D-HDKR
 * @version 1.0
 */
public class NotFoundException extends ServiceException {

	private static final long serialVersionUID = 1L;


	/**
	 * Exception without initialization
	 */
	public NotFoundException() {
		super();
	}

	/**
	 * @param message	message of the exception
	 * @param cause		cause of the exception
	 * @param enableSuppression		suppression status
	 * @param writableStackTrace	writable stack trace status
	 */
	public NotFoundException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message	message of the exception
	 * @param cause		cause of the message
	 */
	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message	message for the exception
	 */
	public NotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause		cause of the exception
	 */
	public NotFoundException(Throwable cause) {
		super(cause);
	}
}
