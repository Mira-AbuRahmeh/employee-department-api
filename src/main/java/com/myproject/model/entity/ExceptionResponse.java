package com.myproject.model.entity;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

public class ExceptionResponse {

    String status;
    String message;
    LocalDate timeStamp;
    String path;

    public ExceptionResponse( String status,String message, LocalDate timeStamp,String path) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
        this.path = path;
    }


    public String getMessage() {
        return message;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public String getPath() {
        return path;
    }

    public String getStatus() {
        return status;
    }
}
