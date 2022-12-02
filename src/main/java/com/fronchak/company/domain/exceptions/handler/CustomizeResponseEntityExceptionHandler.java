package com.fronchak.company.domain.exceptions.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fronchak.company.domain.exceptions.ExceptionResponse;
import com.fronchak.company.domain.exceptions.ResourceNotFoundException;

@RestController
@RestControllerAdvice
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(
			ResourceNotFoundException e, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		HttpStatus status = HttpStatus.NOT_FOUND;
		response.setTimestamp(Instant.now());
		response.setError("Resource not found");
		response.setMessage(e.getMessage());
		response.setStatus(status.value());
		response.setPath(request.getDescription(false));
		return ResponseEntity.status(status).body(response);
	}
}
