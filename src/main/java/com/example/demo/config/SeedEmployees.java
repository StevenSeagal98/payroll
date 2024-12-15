package com.example.demo.config;

import com.example.demo.enums.Gender;
import com.example.demo.enums.MedicalCoverage;
import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.HoursWorkedRepository;
import com.example.demo.services.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.models.HoursWorked;

@Configuration
public class SeedEmployees {
    private final HoursWorkedRepository hoursWorkedRepository;

    public SeedEmployees(HoursWorkedRepository hoursWorkedRepository) {
        this.hoursWorkedRepository = hoursWorkedRepository;
    }

    @Bean
    CommandLineRunner seedEmployeeData(EmployeeService employeeService) {
        return args -> {
            Employee employeeOne = new Employee(
                    "Nate",
                    "Orona",
                    "Software Developer",
                    "Engineering",
                    Gender.MALE,
                    "Active",
                    "Hourly",
                    "nateodev@gmail.com",
                    "1234 Broad St",
                    "",
                    "Valparaiso",
                    "IN",
                    "46383",
                    "",
                    55,
                    MedicalCoverage.FAMILY,
                    3,
                    LocalDate.of(2024, 3, 21)
            );
            Employee employeeTwo = new Employee(
                    "Jane",
                    "Doe",
                    "Analyst",
                    "Engineering",
                    Gender.FEMALE,
                    "Active",
                    "Salary",
                    "janedoe@gmail.com",
                    "2127 E Glendale Blvd",
                    "",
                    "Valparaiso",
                    "IN",
                    "47383",
                    "",
                    70000,
                    MedicalCoverage.FAMILY,
                    3,
                    LocalDate.of(2018, 11, 23)
            );
            Employee employeeThree = new Employee(
                    "Jim",
                    "Doe",
                    "Accountant",
                    "Accounting",
                    Gender.NONBINARY,
                    "Active",
                    "Hourly",
                    "jimdoe@gmail.com",
                    "5555 5th St.",
                    "",
                    "Chicago",
                    "IL",
                    "55555",
                    "",
                    45.25,
                    MedicalCoverage.FAMILY,
                    3,
                    LocalDate.of(2019, 10, 19)
            );
            Employee[] employees = {employeeOne, employeeTwo, employeeThree};

            LocalDate today = LocalDate.now();
            for(Employee employee : employees) {
                List<HoursWorked> hoursWorkedList = new ArrayList<>();
                employeeService.createEmployee(employee);
                for(int i = 1; i <= 7; i++) {
                    LocalDate date = today.minusDays(i);
                    HoursWorked hoursWorked = new HoursWorked();
                    hoursWorked.setEmployee(employee);
                    hoursWorked.setDate(date);
                    hoursWorked.setHours(8);
                    hoursWorked.setConfirmed(false);
                    hoursWorked.setIsPTO(employee.getPayType().equalsIgnoreCase("salary"));
                    hoursWorkedList.add(hoursWorked);
                }
                hoursWorkedRepository.saveAll(hoursWorkedList);
            }
        };
    }
}
