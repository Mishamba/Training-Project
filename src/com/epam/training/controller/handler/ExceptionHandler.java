package com.epam.training.controller.handler;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.epam.training.controller.reponse.Response;
import com.epam.training.exception.ControllerException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = AsyncRequestTimeoutException.class)
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException arg0,
			HttpHeaders arg1, HttpStatus arg2, WebRequest arg3) {
		return new ResponseEntity<Object>(new Response(arg0.getMessage()), arg2);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = BindException.class)
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = ConversionNotSupportedException.class)
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = HttpMediaTypeNotAcceptableException.class)
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = HttpMessageNotReadableException.class)
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = HttpMessageNotWritableException.class)
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = MissingPathVariableException.class)
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = MissingServletRequestParameterException.class)
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = MissingServletRequestPartException.class)
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = NoHandlerFoundException.class)
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = ServletRequestBindingException.class)
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}

	@Override
	@org.springframework.web.bind.annotation.ExceptionHandler(value = TypeMismatchException.class)
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new Response(ex.getMessage()), status);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = ControllerException.class)
	protected ResponseEntity<Object> handleControllerException(ControllerException exception, HttpHeaders httpHeaders, 
			HttpStatus httpStatus, WebRequest webRequest) {
		return new ResponseEntity<Object>(new Response(exception.getMessage()), httpStatus);
	}
}
