package com.redbank.controllers.advices;

import com.redbank.exceptions.CannotAddException;
import com.redbank.exceptions.ExceptionDetails;
import com.redbank.exceptions.ResourceAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AuthExceptionsAdvice {

    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = {ResourceAlreadyExistsException.class})
    public ResponseEntity<Object> handleResourceAlreadyExistsException (ResourceAlreadyExistsException e){
        Map<String, String> errors = new HashMap<>();
        errors.put("Resource already exists", e.getMessage());
        ExceptionDetails errorDetails = new ExceptionDetails(new Date(), errors);
        return new ResponseEntity<Object>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {CannotAddException.class})
    public ResponseEntity<Object> handleCannotAddException (CannotAddException e){
        Map<String, String> errors = new HashMap<>();
        errors.put("Cannot add", e.getMessage());
        ExceptionDetails errorDetails = new ExceptionDetails(new Date(), errors);
        return new ResponseEntity<Object> (errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ExceptionDetails errorDetails = new ExceptionDetails(
                new Date(),
                errors
        );
        return new ResponseEntity<Object> (errorDetails, HttpStatus.BAD_REQUEST);

//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
//        return errors;

    }
}
