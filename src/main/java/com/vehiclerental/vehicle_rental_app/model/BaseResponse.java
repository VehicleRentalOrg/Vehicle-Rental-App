package com.vehiclerental.vehicle_rental_app.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseResponse<T> {
    private int status;
    private String message;
    private String path;
    private String responseTime;
    private LocalDateTime timestamp;
}

