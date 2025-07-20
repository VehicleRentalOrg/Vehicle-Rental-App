package com.vehiclerental.vehicle_rental_app.services;

import com.vehiclerental.vehicle_rental_app.entities.Reservation;
import com.vehiclerental.vehicle_rental_app.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(final ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(String id) {
        return reservationRepository.findById(id);
    }

    public void createReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }


}
