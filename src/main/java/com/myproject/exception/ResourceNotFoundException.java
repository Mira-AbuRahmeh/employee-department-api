package com.myproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName , String resourceKey, String resourceValue) {
        super(String.format("%s with %s : %s not found", resourceName, resourceKey, resourceValue));
    }
}
