package com.vehiclerental.vehicle_rental_app.controller;

import com.vehiclerental.vehicle_rental_app.constants.CommonConstants;
import com.vehiclerental.vehicle_rental_app.entities.Reservation;
import com.vehiclerental.vehicle_rental_app.services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CommonConstants.RESERVATIONS_BASE)
@AllArgsConstructor
public class ReservationController {

    @Autowired
    private final ReservationService reservationService;

    @GetMapping(CommonConstants.GET_ALL)
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping(CommonConstants.GET_BY_ID)
    public Optional<Reservation> getReservationById(@PathVariable String id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping(CommonConstants.CREATE)
    public void createReservation(@RequestBody Reservation reservation) {
        reservationService.createReservation(reservation);
    }
}
