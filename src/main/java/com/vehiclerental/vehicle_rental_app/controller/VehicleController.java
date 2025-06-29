package com.vehiclerental.vehicle_rental_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VehicleController {
    @GetMapping("/hello")
    public String sayHello() {
        //return "Hello, Vehicle Rental App!";
        return "Hello, This is Vehicle Rental Backend App";
    }
}


