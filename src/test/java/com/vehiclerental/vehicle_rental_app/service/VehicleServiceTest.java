package com.vehiclerental.vehicle_rental_app.service;

import com.vehiclerental.vehicle_rental_app.constants.CommonConstants;
import com.vehiclerental.vehicle_rental_app.entities.Vehicle;
import com.vehiclerental.vehicle_rental_app.enums.VehicleStatus;
import com.vehiclerental.vehicle_rental_app.enums.VehicleType;
import com.vehiclerental.vehicle_rental_app.exception.ResourceNotFoundException;
import com.vehiclerental.vehicle_rental_app.model.VehicleListResponse;
import com.vehiclerental.vehicle_rental_app.repositories.VehicleRepository;
import com.vehiclerental.vehicle_rental_app.services.VehicleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void getAllVehicles_shouldReturnListOfVehicles() {
        Vehicle v1 = new Vehicle("V001", "Toyota", 2020, "Red", "VIN1234567890",
                VehicleType.SEDAN, VehicleStatus.AVAILABLE, "Sunroof, Bluetooth", 35000);
        Vehicle v2 = new Vehicle("V002", "Honda", 2022, "Black", "VIN0987654321",
                VehicleType.SUV, VehicleStatus.RENTED, "Navigation, Heated Seats", 15000);

        when(vehicleRepository.findAll()).thenReturn(List.of(v1, v2));

        VehicleListResponse response = vehicleService.getAllVehicles();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(CommonConstants.SUCCESS, response.getMessage());
        assertEquals(2, response.getVehicleList().size());

        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    void getVehicleById_shouldReturnOneVehicle() {
        Vehicle v = new Vehicle("V003", "Ford", 2021, "Blue", "VIN3333333333",
                VehicleType.HATCHBACK, VehicleStatus.AVAILABLE, "Bluetooth, Backup Camera", 20000);

        when(vehicleRepository.findById("V003")).thenReturn(Optional.of(v));

        VehicleListResponse response = vehicleService.getVehicleById("V003");

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(CommonConstants.SUCCESS, response.getMessage());
        assertEquals("V003", response.getVehicleList().get(0).getVehicleId());

        verify(vehicleRepository).findById("V003");
    }

    @Test
    void getVehicleById_shouldThrowResourceNotFound_whenVehicleMissing() {
        when(vehicleRepository.findById("0000")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> vehicleService.getVehicleById("0000"));

        verify(vehicleRepository).findById("0000");
    }
}
