package com.jup.bookorder.bookorder.controllers;

import com.jup.bookorder.bookorder.dto.requests.OrderRequest;
import com.jup.bookorder.bookorder.dto.requests.UserRequest;
import com.jup.bookorder.bookorder.dto.responses.OrderResponse;
import com.jup.bookorder.bookorder.dto.responses.UserResponse;
import com.jup.bookorder.bookorder.entities.Order;
import com.jup.bookorder.bookorder.entities.User;
import com.jup.bookorder.bookorder.exception.BadRequestException;
import com.jup.bookorder.bookorder.securities.UserCredential;
import com.jup.bookorder.bookorder.services.LoginService;
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
import java.util.List;

@RestController
public class UsersController extends AbstractDefaultController{
	
	@Autowired
	UsersService usersService;

	@Autowired
	OrderService orderService;

	@Autowired
	LoginService loginService;

	@Autowired
	private UserCredential userCredential;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public HttpEntity<ResponseModel> getUser() {
		List<Order> orderList =  orderService.getOrderByUser(userCredential.getUser());
		return new ResponseEntity(new UserResponse(userCredential.getUser(), orderList), HttpStatus.OK);
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

	@RequestMapping(value = "/users", method = RequestMethod.DELETE)
	public HttpEntity<ResponseModel> deleteUser() {
		usersService.delete(userCredential.getUser());
		loginService.revokeAccessToken(userCredential.getAccessToken());
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/users/orders", method = RequestMethod.POST)
	public HttpEntity<ResponseModel> orderBook(@RequestBody OrderRequest orderRequest) {
		BigDecimal price = orderService.order(userCredential.getUser(), orderRequest.getOrders());

		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setPrice(price.setScale(2));

		return new ResponseEntity(orderResponse, HttpStatus.OK);
	}




}
