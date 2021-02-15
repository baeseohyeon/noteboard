package com.noteboard.noteboard.controller;

import com.noteboard.noteboard.exception.NoItemError;
import com.noteboard.noteboard.exception.PathFormatError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.NoSuchElementException;

@ControllerAdvice
public class PostExceptionController {

    @ExceptionHandler(NoSuchElementException.class)
    public @ResponseBody
    NoItemError noItemErrorHandler(NoSuchElementException e){
        NoItemError noItem = new NoItemError();
        noItem.setMessage("Item Not Exists!");
        return noItem;
    }

    @ExceptionHandler(NumberFormatException.class)
    public @ResponseBody
    PathFormatError noItemErrorHandler(NumberFormatException e){
        PathFormatError formatError = new PathFormatError();
        formatError.setMessage("Invalid Path Variable!");
        return formatError;
    }
}
