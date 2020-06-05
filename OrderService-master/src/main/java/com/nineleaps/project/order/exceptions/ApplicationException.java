package com.nineleaps.project.order.exceptions;

public class ApplicationException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8756135837235640370L;

	private String errorCode;
	
	private Object[] args;

	public ApplicationException() {
	}
	
	public ApplicationException(String errorCode, Object... args) {
		this.errorCode = errorCode;
		this.args = args;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
	
}
