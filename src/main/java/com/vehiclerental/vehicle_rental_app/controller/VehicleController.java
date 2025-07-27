package com.vehiclerental.vehicle_rental_app.controller;

import com.vehiclerental.vehicle_rental_app.constants.CommonConstants;
import com.vehiclerental.vehicle_rental_app.model.VehicleListResponse;
import com.vehiclerental.vehicle_rental_app.services.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CommonConstants.VEHICLE_BASE)
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping(CommonConstants.GET_ALL)
    public VehicleListResponse getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping(CommonConstants.GET_BY_ID)
    public VehicleListResponse getVehicleById(@PathVariable String id) {
        return vehicleService.getVehicleById(id);
    }

//    @PostMapping(CommonConstants.CREATE)
//    public void createVehicle(@RequestBody Vehicle vehicle) {
//        vehicleService.createVehicle(vehicle);
//    }

}


