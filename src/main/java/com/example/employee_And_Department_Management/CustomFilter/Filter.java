package com.example.employee_And_Department_Management.CustomFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;
import java.util.logging.Logger;

@Component
public class Filter extends OncePerRequestFilter {
    Logger log = Logger.getLogger(Filter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("request method: "+request.getMethod()+" url:"+request.getRequestURL()+" Time:"+ LocalDate.now().toString());
        String correlationId=request.getHeader("correlation-id");
        if( correlationId==null ||correlationId.isEmpty()){
            correlationId= UUID.randomUUID().toString();
            request.setAttribute("correlation-id",correlationId);
        }
        response.setHeader("correlation-id",correlationId);

        filterChain.doFilter(request, response);
    }
}
