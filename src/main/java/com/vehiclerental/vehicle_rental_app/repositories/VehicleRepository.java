package com.vehiclerental.vehicle_rental_app.repositories;

import com.vehiclerental.vehicle_rental_app.entities.VehicleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<VehicleEntity, String> {
}
