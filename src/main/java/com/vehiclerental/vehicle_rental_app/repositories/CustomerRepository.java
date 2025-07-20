package com.vehiclerental.vehicle_rental_app.repositories;

import com.vehiclerental.vehicle_rental_app.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

}
