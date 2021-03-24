package com.epam.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticationException extends AuthenticationException {
	
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
