package com.vehiclerental.vehicle_rental_app.controller;

import com.vehiclerental.vehicle_rental_app.entities.Reservation;
import com.vehiclerental.vehicle_rental_app.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/all")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/id")
    public Optional<Reservation> getReservationById(@RequestParam String id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping("/create")
    public void createReservation(@RequestBody Reservation reservation) {
        reservationService.createReservation(reservation);
    }
}
