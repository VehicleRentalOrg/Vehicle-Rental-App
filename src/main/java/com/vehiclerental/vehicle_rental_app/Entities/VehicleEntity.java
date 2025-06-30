package com.vehiclerental.vehicle_rental_app.Entities;

import com.vehiclerental.vehicle_rental_app.enums.VehicleStatus;
import com.vehiclerental.vehicle_rental_app.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "VRA", name = "VEHICLE")
public class VehicleEntity {

    @Id
    @SequenceGenerator(name = "vehicle_seq", sequenceName = "VRA.VEHICLE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_seq")
    private long vehicleId;

    @Column(name = "MAKE", nullable = false, length = 20)
    private String make;

    @Column(name = "YEAR", nullable = false)
    private Integer year;

    @Column(name = "COLOUR", length = 30)
    private String colour;

    @Column(name = "VIN", unique = true, nullable = false, length = 17)
    private String vin;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", length = 30, nullable = false)
    private VehicleType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 20, nullable = false)
    private VehicleStatus status;

    @Column(name = "FEATURES", columnDefinition = "TEXT")
    private String features;

    // Constructors
    public VehicleEntity() {}

    public VehicleEntity(String make,
                         Integer year,
                         String colour,
                         String vin,
                         VehicleType type,
                         VehicleStatus status,
                         String features) {
        this.make = make;
        this.year = year;
        this.colour = colour;
        this.vin = vin;
        this.type = type;
        this.status = status;
        this.features = features;
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "id=" + vehicleId +
                ", make='" + make + '\'' +
                ", year=" + year +
                ", colour='" + colour + '\'' +
                ", vin='" + vin + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", features='" + features + '\'' +
                '}';
    }
}
