package com.nineleaps.project.order.exceptions;

import org.springframework.validation.Errors;

public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8394259523680639893L;

	public static final String CLIENT_ERROR = "Client Error";
	
	private final transient Errors errors;
	
	public ValidationException(final String message, final Errors errors) {
		super(message);
		this.errors = errors;
	}
	
	public Errors getErrors()
	{
		return errors;
	}
}