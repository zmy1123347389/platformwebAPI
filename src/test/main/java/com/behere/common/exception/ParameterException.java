package com.behere.common.exception;

/**
 * 参数异常
 * 
 * @version 1.0
 * @author Behere
 */
public class ParameterException extends Exception {
	private static final long serialVersionUID = 1L;

	public ParameterException(String message) {
		super(message);
	}

	public ParameterException() {
		super();
	}
}
