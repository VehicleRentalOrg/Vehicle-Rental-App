package com.vehiclerental.vehicle_rental_app.controller;

import com.vehiclerental.vehicle_rental_app.constants.CommonConstants;
import com.vehiclerental.vehicle_rental_app.model.CustomersListResponse;
import com.vehiclerental.vehicle_rental_app.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CommonConstants.CUSTOMERS_BASE)
@AllArgsConstructor
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @GetMapping(CommonConstants.GET_ALL)
    public CustomersListResponse getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(CommonConstants.GET_BY_ID)
    public CustomersListResponse getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

//    @PostMapping("/create")
//    public BaseResponse<Customer> createCustomer(@RequestBody Customer customer) {
//        return customerService.createCustomer(customer);
//    }
}
