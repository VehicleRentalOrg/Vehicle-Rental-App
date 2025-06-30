package com.vehiclerental.vehicle_rental_app.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@Entity
@Table(schema = "VRA", name = "CUSTOMER")
public class CustomerEntity {

    @Id
    @SequenceGenerator(name = "customer_seq", sequenceName = "VRA_CUST_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    private Long customerId;

    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;

    @Column(name = "ADDRESS", nullable = false, length = 100)
    private String address;

    @Column(name = "CITY", nullable = false, length = 20)
    private String city;

    @Column(name = "ZIP_CODE", nullable = false, length = 6)
    private Integer zipCode;

    @Column(name = "DOB", nullable = false)
    private LocalDate dob;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PHONE_NUM", nullable = false, unique = true, length = 15)
    private String phoneNumber;

    public CustomerEntity() {
    }

    public CustomerEntity(String firstName,
                          String lastName,
                          String address,
                          String city,
                          Integer zipCode,
                          LocalDate dob,
                          String email,
                          String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Transient
    public int getAge() {
        return this.dob != null ? Period.between(this.dob, LocalDate.now()).getYears() : 0;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipCode=" + zipCode +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
