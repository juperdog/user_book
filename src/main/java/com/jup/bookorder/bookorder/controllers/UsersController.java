package com.jup.bookorder.bookorder.controllers;

import com.jup.bookorder.bookorder.dto.requests.OrderRequest;
import com.jup.bookorder.bookorder.dto.requests.UserRequest;
import com.jup.bookorder.bookorder.dto.responses.OrderResponse;
import com.jup.bookorder.bookorder.entities.User;
import com.jup.bookorder.bookorder.exception.BadRequestException;
import com.jup.bookorder.bookorder.securities.UserCredential;
import com.jup.bookorder.bookorder.services.OrderService;
import com.jup.bookorder.response.Response;
import com.jup.bookorder.utils.Utils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jup.bookorder.controllers.AbstractDefaultController;
import com.jup.bookorder.response.ResponseModel;
import com.jup.bookorder.bookorder.services.UsersService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.ParseException;

@RestController
public class UsersController extends AbstractDefaultController{
	
	@Autowired
	UsersService usersService;

	@Autowired
	OrderService orderService;

	@Autowired
	private UserCredential userCredential;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public HttpEntity<ResponseModel> getUser() {
		return new ResponseEntity(userCredential.getUser(), HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public HttpEntity<ResponseModel> saveTodo(@RequestBody @Valid UserRequest userRequest, BindingResult validResult) {

		//validation
		if(validResult.hasErrors()){
			throw new BadRequestException(validResult);
		}

		User user = new User();
		user.setUsername(userRequest.getUsername());
		user.setPassword(userRequest.getPassword());
		user.setDateOfBirth(Utils.stringToDate(userRequest.getDateOfBirth()));
		usersService.save(user);

		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/users/orders", method = RequestMethod.POST)
	public HttpEntity<ResponseModel> orderBook(@RequestBody OrderRequest orderRequest) {
		BigDecimal price = orderService.order(userCredential.getUser(), orderRequest.getOrders());

		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setPrice(price.setScale(2));

		return new ResponseEntity(orderResponse, HttpStatus.OK);
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
