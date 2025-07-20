package com.vehiclerental.vehicle_rental_app.controller;

import com.vehiclerental.vehicle_rental_app.entities.Vehicle;
import com.vehiclerental.vehicle_rental_app.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    @Autowired
    public VehicleController(final VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/all")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/id")
    public Optional<Vehicle> getVehicleById(@RequestParam String id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping("/create")
    public void createVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.createVehicle(vehicle);
    }

    @GetMapping("/api/hello")
    public String publicHello() {
        return "Hello, This is a public endpoint!";
    }

    @GetMapping("/secure/hello")
    public String secureHello() {
        return "Hello, This is a secured endpoint!";
    }
}


