package com.vehiclerental.vehicle_rental_app.controller;

import com.vehiclerental.vehicle_rental_app.config.TestSecurityConfig;
import com.vehiclerental.vehicle_rental_app.constants.CommonConstants;
import com.vehiclerental.vehicle_rental_app.entities.Vehicle;
import com.vehiclerental.vehicle_rental_app.enums.VehicleStatus;
import com.vehiclerental.vehicle_rental_app.enums.VehicleType;
import com.vehiclerental.vehicle_rental_app.model.VehicleListResponse;
import com.vehiclerental.vehicle_rental_app.services.VehicleService;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VehicleController.class)
@Import({TestSecurityConfig.class, VehicleControllerTest.TestServiceConfig.class})
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VehicleService vehicleService;

    @TestConfiguration
    public static class TestServiceConfig {
        @Bean
        @Primary
        public VehicleService vehicleService() {
            return Mockito.mock(VehicleService.class);
        }
    }

    private final String VEHICLE_BASE = CommonConstants.VEHICLE_BASE;
    private final String MOCK_AUTH_HEADER = "Bearer mock-token-123";

    @Test
    void getAllVehicles_shouldReturnOk() throws Exception {
        Vehicle vehicle1 = new Vehicle("V001", "Toyota", 2020, "Red", "Camry", "VIN1234567890", VehicleType.SEDAN, VehicleStatus.AVAILABLE, "Sunroof, Bluetooth", 35000);
        Vehicle vehicle2 = new Vehicle("V002", "Ford", 2022, "Black", "Mustang", "VIN0987654321", VehicleType.SUV, VehicleStatus.RENTED, "Navigation, Heated Seats", 12000);

        VehicleListResponse mockList = VehicleListResponse.builder().status(HttpStatus.OK).message("Success").vehicleList(List.of(vehicle1, vehicle2)).build();

        Mockito.when(vehicleService.getAllVehicles()).thenReturn(mockList);

        mockMvc.perform(get(VEHICLE_BASE + CommonConstants.GET_ALL)
                        .header("Authorization", MOCK_AUTH_HEADER))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.vehicleList.length()").value(2));
    }

    @Test
    void getVehicleById_shouldReturnOk() throws Exception {
        Vehicle vehicle = new Vehicle("V001", "Toyota", 2020, "Red", "Corolla", "VIN1234567890", VehicleType.SEDAN, VehicleStatus.AVAILABLE, "Sunroof, Bluetooth", 35000);

        VehicleListResponse mockResponse = VehicleListResponse.builder().status(HttpStatus.OK).message("Success").vehicleList(List.of(vehicle)).build();

        Mockito.when(vehicleService.getVehicleById("V001")).thenReturn(mockResponse);

        mockMvc.perform(get(VEHICLE_BASE + CommonConstants.GET_BY_ID.replace("{id}", "V001"))
                        .header("Authorization", MOCK_AUTH_HEADER))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.vehicleList[0].vehicleId").value("V001"))
                        .andExpect(jsonPath("$.vehicleList[0].make").value("Toyota"))
                        .andExpect(jsonPath("$.vehicleList[0].color").value("Red"));
    }
}
