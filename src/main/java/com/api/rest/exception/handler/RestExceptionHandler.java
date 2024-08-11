package com.api.rest.exception.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.rest.dto.ErrorDetails;
import com.api.rest.dto.ValidationError;
import com.api.rest.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@RestController
public class RestExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
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

	@ExceptionHandler(MethodArgumentNotValidException.class)
	//public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest httpServletRequest){
		public @ResponseBody ErrorDetails handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest httpServletRequest){
		ErrorDetails errorD= new ErrorDetails();
		errorD.setTimeStamp(new Date().getTime());
		errorD.setStatus(HttpStatus.BAD_REQUEST.value());
		String requestPath=(String)httpServletRequest.getAttribute("javax.servlet.error.request_uri");
		if( requestPath==null) {
			requestPath=httpServletRequest.getRequestURI();
		}
		errorD.setTitle("Validacion fallida");
		errorD.setDetail("La validacion de entrada fallo");
		errorD.setDeveloperMessage(exception.getMessage());
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		for(FieldError fieldError:fieldErrors) {
			List<ValidationError> validationErrorList = errorD.getErrors().get(fieldError.getField());
		
			if(validationErrorList==null) {
				validationErrorList=new ArrayList<ValidationError>();
				errorD.getErrors().put(fieldError.getField(),validationErrorList);
			}
			
			ValidationError validationError= new ValidationError();
			validationError.setCode(fieldError.getCode());
			//validationError.setMessage(fieldError.getDefaultMessage());
			validationError.setMessage(messageSource.getMessage(fieldError, null));
			validationErrorList.add(validationError);
			
		}
		return errorD;
		//return new ResponseEntity<>(errorD,null,HttpStatus.BAD_REQUEST);
	}
	
}
