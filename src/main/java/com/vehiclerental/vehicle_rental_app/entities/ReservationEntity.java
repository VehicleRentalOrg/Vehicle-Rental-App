package com.vehiclerental.vehicle_rental_app.entities;

import com.vehiclerental.vehicle_rental_app.enums.ReservationStatus;
import com.vehiclerental.vehicle_rental_app.enums.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "reservations")
public class ReservationEntity {

    @Id
    private String reservationId; // MongoDB ID, typically String or ObjectId

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String pickupLocation;
    private String dropOffLocation;
    @DBRef
    private VehicleEntity vehicle;
    @DBRef
    private EmployeeEntity employee;
    @DBRef
    private CustomerEntity customer;
    private ReservationType reservationType;
    private ReservationStatus reservationStatus;
    private boolean isInsuranceSelected;
}
