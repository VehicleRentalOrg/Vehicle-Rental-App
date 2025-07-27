package com.vehiclerental.vehicle_rental_app.model;

import com.vehiclerental.vehicle_rental_app.entities.Employee;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class EmployeeListResponse extends BaseResponse {
    private List<Employee> employeeList;
}
