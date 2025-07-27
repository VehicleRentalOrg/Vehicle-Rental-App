package com.vehiclerental.vehicle_rental_app.services;

import com.vehiclerental.vehicle_rental_app.constants.CommonConstants;
import com.vehiclerental.vehicle_rental_app.entities.Vehicle;
import com.vehiclerental.vehicle_rental_app.exception.ResourceNotFoundException;
import com.vehiclerental.vehicle_rental_app.model.VehicleListResponse;
import com.vehiclerental.vehicle_rental_app.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleListResponse getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return VehicleListResponse.builder().status(HttpStatus.OK).message(CommonConstants.SUCCESS).vehicleList(vehicles).build();
    }

    public VehicleListResponse getVehicleById(String id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        if (optionalVehicle.isPresent()) {
            return VehicleListResponse.builder().status(HttpStatus.OK).message(CommonConstants.SUCCESS).vehicleList(List.of(optionalVehicle.get())).build();
        } else {
            throw new ResourceNotFoundException("Vehicle with ID " + id + " not found");
        }
    }

//    Optionally, you can wrap this in a BaseResponse if needed
//    public void createVehicle(Vehicle vehicle) {
//        vehicleRepository.save(vehicle);
//    }
}
