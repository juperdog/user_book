package com.jup.bookorder.controllers;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import com.jup.bookorder.bookorder.exception.BadCredentialException;
import com.jup.bookorder.bookorder.exception.BadRequestException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jup.bookorder.response.Response;
import com.jup.bookorder.response.ResponseModel;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDefaultController {

	@ExceptionHandler(value = ConstraintViolationException.class)
	public HttpEntity<ResponseModel> handleConstraintViolationException(
			ConstraintViolationException exception) {
		
		String msg = exception.getConstraintViolations().iterator().next().getMessageTemplate();
		return new ResponseModel(Response.ERROR, msg).build(HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = EntityNotFoundException.class)
	public HttpEntity<ResponseModel> handleEntityNotFoundException(EntityNotFoundException exception) {
		return new ResponseModel(Response.ERROR, exception.getMessage()).build(HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(value = BadCredentialException.class)
	public HttpEntity<ResponseModel> handleUserCredentialException(BadCredentialException exception) {
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(value = RuntimeException.class)
	public HttpEntity<ResponseModel> handleRuntimeExceptionException(RuntimeException exception) {
		exception.printStackTrace();
		return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = BadRequestException.class)
	public HttpEntity<ResponseModel> handleBadRequestException(BadRequestException exception) {
		if(exception.getBindingResult() != null){
			List<String> errorStrList =  new ArrayList<>(exception.getBindingResult().getAllErrors().size());
			for(ObjectError error : exception.getBindingResult().getAllErrors()){
				errorStrList.add(error.getDefaultMessage());
			}
			return new ResponseEntity(errorStrList, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
