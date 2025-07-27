package com.vehiclerental.vehicle_rental_app.services;

import com.vehiclerental.vehicle_rental_app.constants.CommonConstants;
import com.vehiclerental.vehicle_rental_app.entities.Reservation;
import com.vehiclerental.vehicle_rental_app.exception.ResourceNotFoundException;
import com.vehiclerental.vehicle_rental_app.model.ReservationListResponse;
import com.vehiclerental.vehicle_rental_app.repositories.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;

    public ReservationListResponse getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return ReservationListResponse.builder()
                .status(HttpStatus.OK)
                .message(CommonConstants.SUCCESS)
                .reservationList(reservations)
                .build();
    }

    public ReservationListResponse getReservationById(String id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation with ID " + id + " not found"));

        return ReservationListResponse.builder()
                .status(HttpStatus.OK)
                .message(CommonConstants.SUCCESS)
                .reservationList(List.of(reservation))
                .build();
    }

//    public void createReservation(Reservation reservation) {
//        reservationRepository.save(reservation);
//    }


}
