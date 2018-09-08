package com.jup.bookorder.bookorder.controllers;

import com.jup.bookorder.bookorder.dto.requests.UserLoginRequest;
import com.jup.bookorder.bookorder.services.LoginService;
import com.jup.bookorder.controllers.AbstractDefaultController;
import com.jup.bookorder.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by wasan_kha on 9/3/2018 AD.
 */
@Controller
public class LoginController extends AbstractDefaultController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HttpEntity<ResponseModel> getTodoList(@RequestBody @Valid UserLoginRequest userCredential) {
        return new ResponseEntity(loginService.login(userCredential), HttpStatus.OK);
    }
}
