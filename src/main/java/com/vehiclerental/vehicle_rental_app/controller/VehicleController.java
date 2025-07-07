package com.vehiclerental.vehicle_rental_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {

    @GetMapping("/api/hello")
    public String publicHello() {
        return "Hello, This is a public endpoint! ";
    }

    @GetMapping("/secure/hello")
    public String secureHello() {
        return "Hello, This is a secured endpoint!";
    }


    @GetMapping("/secure/Test")
    public String secureTest() {
        return "Hello, This is a test endpoint!";
    }
}


