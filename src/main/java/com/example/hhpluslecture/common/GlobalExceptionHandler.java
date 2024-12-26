package com.example.hhpluslecture.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<?> handleCustomException(final CustomException exception) {
		return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
	}
}
