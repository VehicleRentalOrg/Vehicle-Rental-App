package com.vehiclerental.vehicle_rental_app.repositories;

import com.vehiclerental.vehicle_rental_app.entities.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
}
