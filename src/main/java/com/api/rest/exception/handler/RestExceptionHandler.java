package com.api.rest.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.rest.dto.ErrorDetails;
import com.api.rest.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest httpServletRequest){
		ErrorDetails errorD= new ErrorDetails();
		errorD.setTimeStamp(new Date().getTime());
		errorD.setStatus(HttpStatus.NOT_FOUND.value());
		errorD.setTitle("Recurso No Encontrado");
		errorD.setDetail(exception.getClass().getName());
		errorD.setDeveloperMessage(exception.getMessage());
		return new ResponseEntity<>(errorD,null,HttpStatus.NOT_FOUND);
	}

}
