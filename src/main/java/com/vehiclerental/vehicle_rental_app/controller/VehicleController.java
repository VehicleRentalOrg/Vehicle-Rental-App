package com.vehiclerental.vehicle_rental_app.controller;

import com.vehiclerental.vehicle_rental_app.constants.CommonConstants;
import com.vehiclerental.vehicle_rental_app.entities.Vehicle;
import com.vehiclerental.vehicle_rental_app.services.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CommonConstants.VEHICLE_BASE)
@AllArgsConstructor
public class VehicleController {

    @Autowired
    private final VehicleService vehicleService;

    @GetMapping(CommonConstants.GET_ALL)
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping(CommonConstants.GET_BY_ID)
    public Optional<Vehicle> getVehicleById(@PathVariable String id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping(CommonConstants.CREATE)
    public void createVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.createVehicle(vehicle);
    }

}


