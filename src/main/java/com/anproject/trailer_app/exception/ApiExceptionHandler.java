package com.anproject.trailer_app.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
		
		ApiException apiException = new ApiException(
				e.getMessage(),
				HttpStatus.BAD_REQUEST,
				ZonedDateTime.now(ZoneId.of("Z"))
		);
		
		return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);	
	}

    @ExceptionHandler(value = {ApiNotFoundException.class})
    public ResponseEntity<ApiException> handleApiNotFoundException(ApiNotFoundException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
    
}
