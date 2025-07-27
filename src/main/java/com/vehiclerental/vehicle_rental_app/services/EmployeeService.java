package com.vehiclerental.vehicle_rental_app.services;

import com.vehiclerental.vehicle_rental_app.constants.CommonConstants;
import com.vehiclerental.vehicle_rental_app.entities.Employee;
import com.vehiclerental.vehicle_rental_app.exception.ResourceNotFoundException;
import com.vehiclerental.vehicle_rental_app.model.EmployeeListResponse;
import com.vehiclerental.vehicle_rental_app.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeListResponse getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return EmployeeListResponse.builder().status(HttpStatus.OK).message(CommonConstants.SUCCESS).employeeList(employees).build();
    }

    public EmployeeListResponse getEmployeeById(String id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            return EmployeeListResponse.builder().status(HttpStatus.OK).message(CommonConstants.SUCCESS).employeeList(List.of(optionalEmployee.get())).build();
        } else {
            throw new ResourceNotFoundException("Employee with ID " + id + " not found");
        }
    }

//    public void createEmployee(Employee employee) {
//        employeeRepository.save(employee);
//    }
}
