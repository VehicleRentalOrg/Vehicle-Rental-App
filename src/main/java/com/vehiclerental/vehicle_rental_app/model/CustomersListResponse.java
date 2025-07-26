package com.vehiclerental.vehicle_rental_app.model;

import com.vehiclerental.vehicle_rental_app.entities.Customer;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class CustomersListResponse extends BaseResponse {
    private List<Customer> customerList;
}
