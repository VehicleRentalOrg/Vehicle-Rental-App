package com.vehiclerental.vehicle_rental_app.config;

import com.vehiclerental.vehicle_rental_app.entities.*;
import com.vehiclerental.vehicle_rental_app.enums.*;
import com.vehiclerental.vehicle_rental_app.repositories.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final VehicleRepository vehicleRepository;
    private final ReservationRepository reservationRepository;

    @Value("${app.data.seed}")
    private boolean seedData;

    private final Faker faker = new Faker();
    private final Random random = new Random();

    @Autowired
    public DataSeeder(CustomerRepository customerRepository, EmployeeRepository employeeRepository, VehicleRepository vehicleRepository, ReservationRepository reservationRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.vehicleRepository = vehicleRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (!seedData) {
            System.out.println("Seeding is disabled. Skipping...");
            return;
        }

        System.out.println("Seeding data...");
        seedCustomers();
        seedEmployees();
        seedVehicles();
        seedReservations();
        System.out.println("Data seeding complete.");
    }

    private void seedCustomers() {
        if (customerRepository.count() == 0) {
            Set<String> emails = new HashSet<>();
            Set<String> phones = new HashSet<>();
            List<Customer> customers = new ArrayList<>();

            while (customers.size() < 100) {
                String email = faker.internet().emailAddress();
                String phone = faker.phoneNumber().cellPhone();

                if (!emails.add(email) || !phones.add(phone)) continue;

                LocalDate dob = faker.date().birthday(18, 75)
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                customers.add(new Customer(
                        null,
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.address().streetAddress(),
                        faker.address().city(),
                        faker.address().zipCode(),
                        dob,
                        email,
                        phone
                ));
            }
            customerRepository.saveAll(customers);
        }
    }

    private void seedEmployees() {
        if (employeeRepository.count() == 0) {
            Set<String> emails = new HashSet<>();
            Set<String> phones = new HashSet<>();
            List<Employee> employees = new ArrayList<>();

            while (employees.size() < 100) {
                String email = faker.internet().emailAddress();
                String phone = faker.phoneNumber().cellPhone();

                if (!emails.add(email) || !phones.add(phone)) continue;

                LocalDate dob = faker.date().birthday(22, 65)
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                LocalDate startDate = LocalDate.now().minusYears(random.nextInt(10));
                LocalDate endDate = random.nextInt(5) == 0 ? startDate.plusYears(random.nextInt(2) + 1) : null;

                employees.add(new Employee(
                        null,
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.address().streetAddress(),
                        faker.address().city(),
                        faker.address().state(),
                        faker.address().zipCode(),
                        dob,
                        email,
                        phone,
                        EmpRole.values()[random.nextInt(EmpRole.values().length)],
                        startDate,
                        endDate
                ));
            }
            employeeRepository.saveAll(employees);
        }
    }

    private void seedVehicles() {
        if (vehicleRepository.count() == 0) {
            Set<String> vins = new HashSet<>();
            List<Vehicle> vehicles = new ArrayList<>();

            while (vehicles.size() < 100) {
                String vin = faker.vehicle().vin();
                if (!vins.add(vin)) continue;

                vehicles.add(new Vehicle(
                        null,
                        faker.vehicle().make(),
                        2000 + random.nextInt(25),
                        faker.vehicle().model(),
                        faker.color().name(),
                        vin,
                        VehicleType.values()[random.nextInt(VehicleType.values().length)],
                        VehicleStatus.values()[random.nextInt(VehicleStatus.values().length)],
                        faker.lorem().sentence(),
                        10000 + random.nextInt(150000)
                ));
            }
            vehicleRepository.saveAll(vehicles);
        }
    }

    private void seedReservations() {
        if (reservationRepository.count() == 0) {
            List<Customer> customers = customerRepository.findAll();
            List<Employee> employees = employeeRepository.findAll();
            List<Vehicle> vehicles = vehicleRepository.findAll();

            List<Reservation> reservations = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                Customer customer = customers.get(random.nextInt(customers.size()));
                Employee employee = employees.get(random.nextInt(employees.size()));
                Vehicle vehicle = vehicles.get(random.nextInt(vehicles.size()));

                LocalDateTime start = LocalDateTime.now().plusDays(random.nextInt(15));
                LocalDateTime end = start.plusDays(1 + random.nextInt(5));

                reservations.add(new Reservation(
                        null,
                        start,
                        end,
                        faker.address().cityName(),
                        faker.address().cityName(),
                        vehicle,
                        employee,
                        customer,
                        ReservationType.values()[random.nextInt(ReservationType.values().length)],
                        ReservationStatus.values()[random.nextInt(ReservationStatus.values().length)],
                        random.nextBoolean()
                ));
            }

            reservationRepository.saveAll(reservations);
        }
    }
}

