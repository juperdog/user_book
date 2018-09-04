package com.jup.bookorder.bookorder.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jup.bookorder.controllers.AbstractDefaultController;
import com.jup.bookorder.response.ResponseModel;
import com.jup.bookorder.bookorder.services.UsersService;

@RestController
public class UsersController extends AbstractDefaultController{
	
	@Autowired
	UsersService todoService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public HttpEntity<ResponseModel> getUser(HttpServletRequest request) {
		return new ResponseEntity(request.getAttribute("security_user"), HttpStatus.OK);
	}

	/*@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public HttpEntity<ResponseModel> getTodoList(
			@RequestParam(value = "number", required = false) Integer number,
			@RequestParam(value = "size", required = false) Integer size) {
		return new ResponseModel(Response.SUCCESS, todoService.getTodoPage(number, size)).build(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/todos/{id}", method = RequestMethod.GET)
	public HttpEntity<ResponseModel> getTodo(@PathVariable("id") long id) {
		return new ResponseModel(Response.SUCCESS, todoService.getTodoById(id)).build(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/todos", method = RequestMethod.POST)
	public HttpEntity<ResponseModel> saveTodo(@RequestBody @Valid User todo, BindingResult validResult) {
		return new ResponseModel(Response.SUCCESS, todoService.saveTodo(todo)).build(HttpStatus.OK);
	}

	@RequestMapping(value = "/todos/{id}", method = RequestMethod.PUT)
	public HttpEntity<ResponseModel> updateTodo(@RequestBody @Valid User todo, @PathVariable("id") long id, BindingResult validResult) {
		todo.setId(id);
		return new ResponseModel(Response.SUCCESS, todoService.updateTodo(todo)).build(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/todos/{id}", method = RequestMethod.DELETE)
	public HttpEntity<ResponseModel> delTodo(@PathVariable("id") long id) {
		todoService.deleteTodo(id);
		return new ResponseModel(Response.SUCCESS, "").build(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/todos/{id}/status/{status}", method = RequestMethod.PUT)
	public HttpEntity<ResponseModel> updateTodo(@PathVariable("id") long id, @PathVariable("status") String status) {
		return new ResponseModel(Response.SUCCESS, todoService.updateStatus(id, status)).build(HttpStatus.OK);
	}*/
}
