package com.fronchak.company.domain.exceptions.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fronchak.company.domain.exceptions.DatabaseException;
import com.fronchak.company.domain.exceptions.ExceptionResponse;
import com.fronchak.company.domain.exceptions.ResourceNotFoundException;

@RestController
@RestControllerAdvice
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(
			ResourceNotFoundException e, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ExceptionResponse response = buildResponse(e, request, "Resource not found", status);
		return ResponseEntity.status(status).body(response);
	}
	
	private ExceptionResponse buildResponse(Exception e, WebRequest request, String error, HttpStatus status) {
		ExceptionResponse response = new ExceptionResponse();
		response.setTimestamp(Instant.now());
		response.setError(error);
		response.setMessage(e.getMessage());
		response.setStatus(status.value());
		response.setPath(request.getDescription(false));
		return response;
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<ExceptionResponse> handleDatabaseException(
			DatabaseException e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ExceptionResponse response = buildResponse(e, request, "Integrity violation", status);
		return ResponseEntity.status(status).body(response);
	}
}
