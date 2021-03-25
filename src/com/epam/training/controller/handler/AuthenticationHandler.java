package com.epam.training.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.epam.training.exception.JWTAuthenticationException;

public class AuthenticationHandler implements AuthenticationEntryPoint {

	//401
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.sendError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
	}

	//401
    @org.springframework.web.bind.annotation.ExceptionHandler(value = JWTAuthenticationException.class)
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         JWTAuthenticationException e) throws IOException, ServletException {
        response.sendError(
                HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }

    //403
    @org.springframework.web.bind.annotation.ExceptionHandler(value = AccessDeniedException.class)
    public void commence(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
            throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
    }

    //500
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public void commence(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
