package com.vehiclerental.vehicle_rental_app.repositories;

import com.vehiclerental.vehicle_rental_app.entities.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
