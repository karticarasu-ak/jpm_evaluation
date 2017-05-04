package com.jpm.evaluation.exception;

/**
 * The Base Class for any custom exception for this project
 * Can be used in future to add properties
 * 
 * @author AK
 *
 */
public class BaseCustomException extends Exception {

	private static final long serialVersionUID = 1L;

	public BaseCustomException(String message) {
		super(message);

	}

}
