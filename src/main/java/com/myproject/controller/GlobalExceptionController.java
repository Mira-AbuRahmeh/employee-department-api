package com.myproject.controller;

import com.myproject.model.entity.ExceptionResponse;
import com.myproject.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDate;


@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handelResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {

        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.NOT_FOUND.toString(),
                exception.getMessage(),
                LocalDate.now(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handelResourceAlreadyExistException(ResourceAlreadyExistException exception, HttpServletRequest request) {
        ExceptionResponse response=new ExceptionResponse(
                HttpStatus.CONFLICT.toString(),
                exception.getMessage(),
                LocalDate.now(),
                request.getRequestURI()
        );
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handelInternalServerException(Exception exception, HttpServletRequest request) {

        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
               "Unexpected error occurred",
                LocalDate.now(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }


}