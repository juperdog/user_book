package com.jup.bookorder.bookorder.controllers;

import com.jup.bookorder.bookorder.dto.responses.BookResponse;
import com.jup.bookorder.bookorder.services.BookService;
import com.jup.bookorder.controllers.AbstractDefaultController;
import com.jup.bookorder.response.ResponseModel;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
@RestController
public class BooksController extends AbstractDefaultController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public HttpEntity<ResponseModel> getBook(HttpServletRequest request) {
        return new ResponseEntity(new BookResponse(bookService.getBookWithRecommendation()), HttpStatus.OK);
    }
}
