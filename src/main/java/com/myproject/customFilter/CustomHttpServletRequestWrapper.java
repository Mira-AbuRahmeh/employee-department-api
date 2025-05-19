package com.myproject.customFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.HashMap;
import java.util.Map;

public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final Map<String, String> customHeaders = new HashMap<>();

    public CustomHttpServletRequestWrapper(HttpServletRequest request,String headerName, String headerValue) {
        super(request);
        customHeaders.put(headerName,headerValue);

    }

    @Override
    public String getHeader(String name) {
        String headerValue = customHeaders.get(name);
        return (headerValue != null) ? headerValue : super.getHeader(name);
    }
}
