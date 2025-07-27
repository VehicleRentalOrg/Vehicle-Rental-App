package com.vehiclerental.vehicle_rental_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseResponse {
    private HttpStatus status;
    private String message;
    private String path;
    private String responseTime;
    private LocalDateTime timestamp;

    public int getStatus() {
        return status != null ? status.value() : 0;
    }

}

