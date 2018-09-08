package com.jup.bookorder.bookorder.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
@Getter
public class BadRequestException extends RuntimeException {

    private BindingResult BindingResult;

    public BadRequestException(BindingResult BindingResult){
        this.BindingResult = BindingResult;
    }

    public BadRequestException(String message){
        super(message);
    }

}
