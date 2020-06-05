package com.nineleaps.project.order.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails errorDeatils = new CustomErrorDetails(new Date(),
				"From MethodArgumentNotValid Exception in Global Exception Handler", ex.getMessage());

		return new ResponseEntity<>(errorDeatils, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails errorDeatils = new CustomErrorDetails(new Date(),
				"From HttpRequestMethodNotSupported Exception in Global Exception Handler Method not allowed",
				ex.getMessage());

		return new ResponseEntity<>(errorDeatils, HttpStatus.METHOD_NOT_ALLOWED);
	}

	// OrderNotFoundException
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<Object> handleOrderIdNotFoundException(OrderNotFoundException ex, WebRequest request) {
		CustomErrorDetails errorDeatils = new CustomErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(true));

		return new ResponseEntity<>(errorDeatils, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		CustomErrorDetails errorDeatils = new CustomErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(true));

		return new ResponseEntity<>(errorDeatils, HttpStatus.BAD_REQUEST);
	}
}