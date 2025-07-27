package com.vehiclerental.vehicle_rental_app.model;

import com.vehiclerental.vehicle_rental_app.entities.Reservation;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class ReservationListResponse extends BaseResponse {
    private List<Reservation> reservationList;
}
