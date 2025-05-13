package com.example.employee_And_Department_Management.controllers;

import com.example.employee_And_Department_Management.entities.ExceptionResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handelEntityNotFoundException(EntityNotFoundException exception) {

        ExceptionResponse response = new ExceptionResponse(
                "NOT FOUND",
                exception.getMessage(),
                LocalDate.now(),
                List.of("Entity not found"));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ExceptionResponse> handelPropertyReferenceException(PropertyReferenceException exception) {
        List<String> error= new ArrayList<>();
        error.add("No property "+exception.getPropertyName()+ " found");
        ExceptionResponse response = new ExceptionResponse("BAD_REQUEST",
                "Unknown property " + exception.getPropertyName() + ". Please check your query parameters.",
                LocalDate.now(),
                error);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handelArgumentNotValid(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(e -> errors.add(e.getDefaultMessage()));
        ExceptionResponse response = new ExceptionResponse(
                "BAD_REQUEST",
                "Request validation failed",
                LocalDate.now(),
                errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolation(ConstraintViolationException exception) {
        List<String> errors = new ArrayList<>();
        exception.getConstraintViolations().forEach(e -> errors.add(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(
                "BAD_REQUEST",
                "Request validation failed",
                LocalDate.now(),
                errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }




    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handelArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
        List<String> errors = new ArrayList<>();
        errors.add("Method parameter "+ exception.getParameter().getParameterName()+" failed to convert value type");
        ExceptionResponse response = new ExceptionResponse(
                "BAD_REQUEST",
                "Parameter " + exception.getName() + " must be of type " + exception.getRequiredType().getSimpleName(),
                LocalDate.now(),
                errors);

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handelDatabaseIntegrity(DataIntegrityViolationException exception) {
        List<String> errors = new ArrayList<>();
        ExceptionResponse response = new ExceptionResponse("CONFLICT", "A database error occurred. Please check your input.", LocalDate.now(),List.of("Database Integrity Violation"));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }



}