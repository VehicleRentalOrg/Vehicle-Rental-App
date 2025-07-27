package com.vehiclerental.vehicle_rental_app.controller;

import com.vehiclerental.vehicle_rental_app.config.TestSecurityConfig;
import com.vehiclerental.vehicle_rental_app.constants.CommonConstants;
import com.vehiclerental.vehicle_rental_app.entities.Customer;
import com.vehiclerental.vehicle_rental_app.entities.Employee;
import com.vehiclerental.vehicle_rental_app.entities.Reservation;
import com.vehiclerental.vehicle_rental_app.entities.Vehicle;
import com.vehiclerental.vehicle_rental_app.enums.ReservationStatus;
import com.vehiclerental.vehicle_rental_app.enums.ReservationType;
import com.vehiclerental.vehicle_rental_app.enums.VehicleStatus;
import com.vehiclerental.vehicle_rental_app.enums.VehicleType;
import com.vehiclerental.vehicle_rental_app.model.ReservationListResponse;
import com.vehiclerental.vehicle_rental_app.services.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ReservationController.class)
@Import({TestSecurityConfig.class, ReservationControllerTest.TestServiceConfig.class})
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReservationService reservationService;

    private final String RESERVATION_BASE = CommonConstants.RESERVATIONS_BASE;
    private final String MOCK_AUTH_HEADER = "Bearer mock-token-123";

    @TestConfiguration
    public static class TestServiceConfig {
        @Bean
        @Primary
        public ReservationService reservationService() {  // ✅ lowercase method name
            return Mockito.mock(ReservationService.class);
        }
    }

    @Test
    void getAllReservations_shouldReturnOk() throws Exception {
        Employee employee1 = new Employee("E001", "John", "Doe", "123 St", "NY", "NY", "10001", LocalDate.of(1990, 1, 1), "john@example.com", "555-1111", null, LocalDate.of(2020, 1, 1), null);

        Employee employee2 = new Employee("E002", "Jane", "Smith", "456 St", "LA", "CA", "90001", LocalDate.of(1992, 2, 2), "jane@example.com", "555-2222", null, LocalDate.of(2021, 2, 2), null);

        Vehicle vehicle1 = new Vehicle("V001", "Toyota", 2020, "Red", "Avalon", "VIN1234567890", VehicleType.SEDAN, VehicleStatus.AVAILABLE, "Sunroof, Bluetooth", 35000);

        Vehicle vehicle2 = new Vehicle("V002", "Ford", 2022, "Black", "Ferrari", "VIN0987654321", VehicleType.SUV, VehicleStatus.MAINTENANCE, "Navigation, Heated Seats", 12000);

        Customer customer1 = new Customer("C001", "Alice", "Johnson", "123 Main St", "New York", "10001", LocalDate.of(1990, 5, 15), "alice.johnson@example.com", "555-1234");

        Customer customer2 = new Customer("C002", "Bob", "Williams", "456 Elm St", "Los Angeles", "90001", LocalDate.of(1985, 8, 25), "bob.williams@example.com", "555-5678");

        Reservation reservation1 = new Reservation("R001", LocalDateTime.of(2025, 7, 28, 10, 0), LocalDateTime.of(2025, 7, 30, 10, 0), "New York", "Boston", vehicle1, employee1, customer1, ReservationType.DAILY, ReservationStatus.COMPLETED, true);

        Reservation reservation2 = new Reservation("R002", LocalDateTime.of(2025, 8, 5, 9, 0), LocalDateTime.of(2025, 8, 7, 18, 0), "San Francisco", "Los Angeles", vehicle2, employee2, customer2, ReservationType.MONTHLY, ReservationStatus.CANCELLED, false);

        ReservationListResponse mockList = ReservationListResponse.builder().status(HttpStatus.OK).message("Success").reservationList(List.of(reservation1, reservation2)).build();

        Mockito.when(reservationService.getAllReservations()).thenReturn(mockList);

        mockMvc.perform(get(RESERVATION_BASE + CommonConstants.GET_ALL).header("Authorization", MOCK_AUTH_HEADER)).andExpect(status().isOk()).andExpect(jsonPath("$.reservationList.length()").value(2));
    }

    @Test
    void getReservationById_shouldReturnOk() throws Exception {
        Employee emp1 = new Employee("E001", "John", "Doe", "123 St", "NY", "NY", "10001", LocalDate.of(1990, 1, 1), "john@example.com", "555-1111", null, LocalDate.of(2020, 1, 1), null);

        Vehicle vehicle2 = new Vehicle("V002", "Ford", 2022, "Black", "Fusion", "VIN0987654321", VehicleType.SUV, VehicleStatus.MAINTENANCE, "Navigation, Heated Seats", 12000);

        Customer customer2 = new Customer("C002", "Bob", "Williams", "456 Elm St", "Los Angeles", "90001", LocalDate.of(1985, 8, 25), "bob.williams@example.com", "555-5678");

        Reservation reservation = new Reservation("R002", LocalDateTime.of(2025, 8, 5, 9, 0), LocalDateTime.of(2025, 8, 7, 18, 0), "San Francisco", "Los Angeles", vehicle2, emp1, customer2, ReservationType.MONTHLY, ReservationStatus.CANCELLED, false);

        ReservationListResponse mockResponse = ReservationListResponse.builder().status(HttpStatus.OK).message("Success").reservationList(List.of(reservation)).build();

        Mockito.when(reservationService.getReservationById("R002")).thenReturn(mockResponse);

        mockMvc.perform(get(RESERVATION_BASE + CommonConstants.GET_BY_ID.replace("{id}", "R002")).header("Authorization", MOCK_AUTH_HEADER)).andExpect(status().isOk()).andExpect(jsonPath("$.reservationList[0].vehicle.vehicleId").value("V002")).andExpect(jsonPath("$.reservationList[0].vehicle.make").value("Ford")).andExpect(jsonPath("$.reservationList[0].vehicle.color").value("Black"));
    }
}
