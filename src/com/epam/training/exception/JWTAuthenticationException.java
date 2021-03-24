package com.epam.training.exception;

import org.springframework.http.HttpStatus;

public class JWTAuthenticationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;

    public JWTAuthenticationException(String explanation, HttpStatus status) {
        super(explanation);
        this.httpStatus = status;
    }

    public HttpStatus getStatus() {
        return httpStatus;
    }
}
