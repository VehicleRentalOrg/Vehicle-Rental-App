package com.vehiclerental.vehicle_rental_app.controller;

import com.vehiclerental.vehicle_rental_app.constants.CommonConstants;
import com.vehiclerental.vehicle_rental_app.entities.Employee;
import com.vehiclerental.vehicle_rental_app.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CommonConstants.EMPLOYEE_BASE)
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    @GetMapping(CommonConstants.GET_ALL)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(CommonConstants.GET_BY_ID)
    public Optional<Employee> getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping(CommonConstants.CREATE)
    public void createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
    }
}
