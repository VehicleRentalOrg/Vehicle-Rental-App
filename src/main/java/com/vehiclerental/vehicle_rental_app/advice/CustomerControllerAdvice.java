package com.vehiclerental.vehicle_rental_app.advice;

import com.vehiclerental.vehicle_rental_app.controller.CustomerController;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice(assignableTypes = CustomerController.class)
public class CustomerControllerAdvice extends GlobalControllerAdvice{
}
