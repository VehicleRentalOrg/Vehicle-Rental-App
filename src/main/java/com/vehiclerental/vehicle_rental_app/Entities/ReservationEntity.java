package com.vehiclerental.vehicle_rental_app.Entities;

import com.vehiclerental.vehicle_rental_app.enums.ReservationStatus;
import com.vehiclerental.vehicle_rental_app.enums.ReservationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "VRA", name = "RESERVATION")
public class ReservationEntity {

    @Id
    @SequenceGenerator(name = "reservation_seq", sequenceName = "VRA_RES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq")
    private Long reservationId;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "PICKUP_LOCATION", nullable = false, length = 100)
    private String pickupLocation;

    @Column(name = "DROP_OFF_LOCATION", nullable = false, length = 100)
    private String dropOffLocation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "VEHICLE_ID", nullable = false)
    private VehicleEntity vehicle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    private EmployeeEntity employee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESERVATION_TYPE", nullable = false)
    private ReservationType reservationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESERVATION_STATUS", nullable = false)
    private ReservationStatus reservationStatus;

    @Column(name = "IS_INSURANCE_SELECTED", nullable = false)
    private boolean isInsuranceSelected;

    public ReservationEntity(LocalDateTime startDate,
                             LocalDateTime endDate,
                             String pickupLocation,
                             String dropOffLocation,
                             VehicleEntity vehicle,
                             EmployeeEntity employee,
                             CustomerEntity customer,
                             ReservationType reservationType,
                             ReservationStatus reservationStatus,
                             boolean isInsuranceSelected) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.vehicle = vehicle;
        this.employee = employee;
        this.customer = customer;
        this.reservationType = reservationType;
        this.reservationStatus = reservationStatus;
        this.isInsuranceSelected = isInsuranceSelected;
    }

    @Override
    public String toString() {
        return "ReservationEntity{" +
                "reservationId=" + reservationId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropOfLocation='" + dropOffLocation + '\'' +
                ", vehicleId=" + vehicle +
                ", employeeId=" + employee +
                ", customerId=" + customer +
                ", reservationType=" + reservationType +
                ", reservationStatus=" + reservationStatus +
                ", isInsuranceSelected=" + isInsuranceSelected +
                '}';
    }
}
