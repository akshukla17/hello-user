package com.todo.hellouser.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.todo.hellouser.model.DefaultErrorResponse;

@ControllerAdvice
public class HelloUserRestExceptionalHandler{

	@ExceptionHandler(NoDataFoundException.class)
	public final ResponseEntity<DefaultErrorResponse> noDataFoundException(NoDataFoundException ex){
		DefaultErrorResponse errorResponse = new DefaultErrorResponse(new Date(), ex.getMessage());
		
		return new ResponseEntity<DefaultErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<DefaultErrorResponse> badRequestException(BadRequestException ex){
		DefaultErrorResponse errorResponse = new DefaultErrorResponse(new Date(), ex.getMessage());
		
		return new ResponseEntity<DefaultErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<DefaultErrorResponse> unhandledException(Exception ex){
		DefaultErrorResponse errorResponse = new DefaultErrorResponse(new Date(), ex.getMessage());
		
		return new ResponseEntity<DefaultErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
