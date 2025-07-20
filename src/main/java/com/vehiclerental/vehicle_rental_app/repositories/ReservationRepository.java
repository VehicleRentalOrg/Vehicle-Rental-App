package com.vehiclerental.vehicle_rental_app.repositories;

import com.vehiclerental.vehicle_rental_app.entities.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
}
