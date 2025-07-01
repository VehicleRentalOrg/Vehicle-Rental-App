package com.vehiclerental.vehicle_rental_app.entities;

import com.vehiclerental.vehicle_rental_app.enums.EmpRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "employees")
public class EmployeeEntity {

    @Id
    private String empId;

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private LocalDate dob;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String phoneNum;
    private EmpRole role;
    private LocalDate startDate;
    private LocalDate endDate;

    @Transient
    public int getAge() {
        return this.dob != null ? Period.between(this.dob, LocalDate.now()).getYears() : 0;
    }
}
