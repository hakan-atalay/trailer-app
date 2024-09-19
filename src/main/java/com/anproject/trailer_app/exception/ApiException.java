package com.anproject.trailer_app.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiException {
	private final String message;
	private final HttpStatus httpStatus;
	private final ZonedDateTime timestamp;
	
}
