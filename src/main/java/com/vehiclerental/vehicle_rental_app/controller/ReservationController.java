package com.vehiclerental.vehicle_rental_app.controller;

import com.vehiclerental.vehicle_rental_app.constants.CommonConstants;
import com.vehiclerental.vehicle_rental_app.model.ReservationListResponse;
import com.vehiclerental.vehicle_rental_app.services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CommonConstants.RESERVATIONS_BASE)
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping(CommonConstants.GET_ALL)
    public ReservationListResponse getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping(CommonConstants.GET_BY_ID)
    public ReservationListResponse getReservationById(@PathVariable String id) {
        return reservationService.getReservationById(id);
    }

//    @PostMapping(CommonConstants.CREATE)
//    public void createReservation(@RequestBody Reservation reservation) {
//        reservationService.createReservation(reservation);
//    }
}
