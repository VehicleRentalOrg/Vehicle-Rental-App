package com.vehiclerental.vehicle_rental_app.controller;

import com.vehiclerental.vehicle_rental_app.model.CustomersListResponse;
import com.vehiclerental.vehicle_rental_app.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public CustomersListResponse getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomersListResponse getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

//    @PostMapping("/create")
//    public BaseResponse<Customer> createCustomer(@RequestBody Customer customer) {
//        return customerService.createCustomer(customer);
//    }
}
