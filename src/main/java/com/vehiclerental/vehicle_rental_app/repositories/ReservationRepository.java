package com.vehiclerental.vehicle_rental_app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<ReservationRepository, String> {
}
