package com.lti.exception;

public class HrException extends Exception{

	private static final long serialVersionUID = 1L;

	public HrException() {
		super();
	}

	public HrException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HrException(String message, Throwable cause) {
		super(message, cause);
	}

	public HrException(String message) {
		super(message);
	}

	public HrException(Throwable cause) {
		super(cause);
	}
	
}
