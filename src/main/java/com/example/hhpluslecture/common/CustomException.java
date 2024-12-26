package com.example.hhpluslecture.common;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	private final HttpStatus status;
	private final String message;

	public CustomException(ErrorMessage errorMessage) {
		this.status = errorMessage.getHttpStatus();
		this.message = errorMessage.getMessage();
	}
}
