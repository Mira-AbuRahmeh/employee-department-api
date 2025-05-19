package com.myproject.customFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;


@Component
public class Filter extends OncePerRequestFilter {
    Logger log = LoggerFactory.getLogger(Filter.class.getName());


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String correlationId=request.getHeader("correlation-id");
        HttpServletRequest requestToUse=request;
        if( correlationId==null || correlationId.isEmpty()){
            correlationId= UUID.randomUUID().toString();
            requestToUse= new  CustomHttpServletRequestWrapper(request,"correlation-id",correlationId);

        }

            log.info(String.format("{Request-method:%s ,URL:%s, Time:%s, Correlation-id:%s}",request.getMethod(),request.getRequestURL(),LocalDate.now(),correlationId));


        response.setHeader("correlation-id",correlationId);
        filterChain.doFilter(requestToUse, response);

        log.info(String.format("{Response-status: %s ,Time:%s, Correlation-id:%s}",response.getStatus(),LocalDate.now(),response.getHeader("correlation-id")));
    }
}
