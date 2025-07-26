package com.vehiclerental.vehicle_rental_app.model;

import com.vehiclerental.vehicle_rental_app.entities.Customer;
import com.vehiclerental.vehicle_rental_app.entities.Vehicle;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class VehicleListResponse extends BaseResponse {
    private List<Vehicle> vehicleList;
}
