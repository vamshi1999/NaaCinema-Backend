package com.naacinema.exception;

public class InvalidImageFormatException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidImageFormatException(String message) {
        super(message);
    }
}
