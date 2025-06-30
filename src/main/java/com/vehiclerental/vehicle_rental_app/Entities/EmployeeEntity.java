package com.vehiclerental.vehicle_rental_app.Entities;

import com.vehiclerental.vehicle_rental_app.enums.EmpRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "VRA", name = "EMPLOYEE")
public class EmployeeEntity {

    @Id
    @SequenceGenerator(name = "employee_seq", sequenceName = "VRA_EMP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    private Long empId;

    @Column(name = "FIRST_NAME", length = 20, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", length = 20, nullable = false)
    private String lastName;

    @Column(name = "ADDRESS", length = 50, nullable = false)
    private String address;

    @Column(name = "CITY", length = 15, nullable = false)
    private String city;

    @Column(name = "STATE", length = 15, nullable = false)
    private String state;

    @Column(name = "ZIP_CODE", length = 6, nullable = false)
    private String zipCode;

    @Column(name = "DOB", nullable = false)
    private LocalDate dob;

    @Column(name = "EMAIL", length = 50, nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", length = 10, nullable = false)
    private EmpRole role;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDate endDate;

    public EmployeeEntity(String firstName,
                          String lastName,
                          String address,
                          String city,
                          String state,
                          String zipCode,
                          LocalDate dob,
                          String email,
                          EmpRole role,
                          LocalDate startDate,
                          LocalDate endDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.dob = dob;
        this.email = email;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Transient
    public int getAge() {
        return this.dob != null ? Period.between(this.dob, LocalDate.now()).getYears() : 0;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "empId=" + empId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
