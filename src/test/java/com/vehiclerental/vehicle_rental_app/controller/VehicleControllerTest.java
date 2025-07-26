package com.vehiclerental.vehicle_rental_app.controller;

import com.vehiclerental.vehicle_rental_app.config.TestSecurityConfig;
import com.vehiclerental.vehicle_rental_app.constants.CommonConstants;
import com.vehiclerental.vehicle_rental_app.entities.Vehicle;
import com.vehiclerental.vehicle_rental_app.enums.VehicleStatus;
import com.vehiclerental.vehicle_rental_app.enums.VehicleType;
import com.vehiclerental.vehicle_rental_app.services.VehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VehicleController.class)
@Import({TestSecurityConfig.class, VehicleControllerTest.TestServiceConfig.class})
class VehicleControllerTest {

    private final String VEHICLE_BASE = CommonConstants.VEHICLE_BASE;
    private final String MOCK_AUTH_HEADER = "Bearer mock-token-123";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VehicleService vehicleService;

    @TestConfiguration
    public static class TestServiceConfig {
        @Bean
        public VehicleService vehicleService() {
            return Mockito.mock(VehicleService.class);
        }
    }

    @Test
    void getAllVehicles_shouldReturnOk() throws Exception {
        Vehicle vehicle1 = new Vehicle("V001", "Toyota", 2020, "Red", "VIN1234567890", VehicleType.SEDAN, VehicleStatus.AVAILABLE, "Sunroof, Bluetooth", 35000);
        Vehicle vehicle2 = new Vehicle("V002", "Ford", 2022, "Black", "VIN0987654321", VehicleType.SUV, VehicleStatus.RENTED, "Navigation, Heated Seats", 12000);
        List<Vehicle> mockList = List.of(vehicle1, vehicle2);

        Mockito.when(vehicleService.getAllVehicles()).thenReturn(mockList);

        mockMvc.perform(get(VEHICLE_BASE + CommonConstants.GET_ALL)
                        .header("Authorization", MOCK_AUTH_HEADER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }
}
