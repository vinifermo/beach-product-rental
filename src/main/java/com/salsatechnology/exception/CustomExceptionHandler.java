package com.salsatechnology.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String PRODUCT_ORDER_NOT_FOUND_FOR_THIS_USERNAME = "Product order not found for this username";

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseError handle(EntityNotFoundException ex) {
        ex.printStackTrace();
        return new ResponseError(ex.getMessage());
    }
}