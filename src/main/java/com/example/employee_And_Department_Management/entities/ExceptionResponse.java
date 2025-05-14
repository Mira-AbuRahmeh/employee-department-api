package com.example.employee_And_Department_Management.entities;

import java.time.LocalDate;
import java.util.List;

public class ExceptionResponse {

    String status;
    String message;
    LocalDate timeStamp;
    List<String> errors;

    public ExceptionResponse(String status, String message, LocalDate timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public ExceptionResponse(String status, String message, LocalDate timeStamp, List<String> errors) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public List<String> getErrors() {
        return errors;
    }
}
