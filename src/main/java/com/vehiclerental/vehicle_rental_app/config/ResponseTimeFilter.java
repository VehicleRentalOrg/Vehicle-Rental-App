package com.vehiclerental.vehicle_rental_app.config;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class ResponseTimeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        chain.doFilter(request, response);
    }
}