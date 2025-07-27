package com.vehiclerental.vehicle_rental_app.advice;

import com.vehiclerental.vehicle_rental_app.exception.ResourceNotFoundException;
import com.vehiclerental.vehicle_rental_app.model.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse> resourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        Long startTime = (Long) request.getAttribute("startTime");
        long duration = (startTime != null) ? (System.currentTimeMillis() - startTime) : 0;
        BaseResponse response = BaseResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .responseTime(duration + "ms")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
