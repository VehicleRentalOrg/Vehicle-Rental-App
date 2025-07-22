package com.vehiclerental.vehicle_rental_app.services;

import com.vehiclerental.vehicle_rental_app.entities.Customer;
import com.vehiclerental.vehicle_rental_app.exception.ResourceNotFoundException;
import com.vehiclerental.vehicle_rental_app.model.CustomersListResponse;
import com.vehiclerental.vehicle_rental_app.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomersListResponse getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return CustomersListResponse.builder().status(200).message("Success").customerList(customers).build();
    }

    public CustomersListResponse getCustomerById(String id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            return CustomersListResponse.builder().status(200).message("Success").customerList(List.of(optionalCustomer.get())).build();
        } else {
            throw new ResourceNotFoundException("Customer with ID " + id + " not found");
        }
    }
//
//    public BaseResponse<Customer> createCustomer(Customer customer) {
//        return customerRepository.save(customer);
//    }
}
