package com.vehiclerental.vehicle_rental_app.entities;

import com.vehiclerental.vehicle_rental_app.enums.VehicleStatus;
import com.vehiclerental.vehicle_rental_app.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "vehicles")
public class VehicleEntity {

    @Id
    private String vehicleId; // MongoDB ID, can be String or ObjectId

    private String make;

    private Integer year;

    private String colour;

    @Indexed(unique = true)
    private String vin;

    private VehicleType type;

    private VehicleStatus status;

    private String features;

    private Integer mileage;
}
