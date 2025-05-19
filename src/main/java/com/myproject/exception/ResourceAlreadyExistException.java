package com.myproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistException extends  RuntimeException{

    public ResourceAlreadyExistException(String resourceName , String resourceKey, String resourceValue){
        super(String.format("%s with %s : %s already exist",resourceName,resourceKey,resourceValue));
    }

}
