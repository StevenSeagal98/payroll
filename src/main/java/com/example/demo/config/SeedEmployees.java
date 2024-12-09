package com.example.demo.config;

import com.example.demo.enums.Gender;
import com.example.demo.models.Employee;
import com.example.demo.models.Payroll;
import com.example.demo.models.WorkEntry;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.PayrollRepository;
import com.example.demo.repositories.WorkEntryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class SeedEmployees {

    @Bean
    CommandLineRunner seedEmployeeData(
            EmployeeRepository employeeRepository,
            WorkEntryRepository workEntryRepository,
            PayrollRepository payrollRepository
    ) {
        return args -> {
            // Create and save employees
            Employee employeeOne = new Employee(
                    "John",
                    "Doe",
                    "Software Engineer",
                    "Engineering",
                    Gender.MALE,
                    "Active",
                    "Salary",
                    "johndoe@gmail.com",
                    "2124 E Glendale Blvd",
                    "",
                    "Valparaiso",
                    "IN",
                    "46383",
                    ""
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
                    ""
            );
            Employee employeeThree = new Employee(
                    "Jim",
                    "Doe",
                    "Accountant",
                    "Accounting",
                    Gender.NONBINARY,
                    "Active",
                    "Salary",
                    "jimdoe@gmail.com",
                    "5555 5th St.",
                    "",
                    "Chicago",
                    "IL",
                    "55555",
                    ""
            );
            employeeRepository.saveAll(List.of(employeeOne, employeeTwo, employeeThree));

            Employee[] employees = {employeeOne, employeeTwo, employeeThree};

            // Create and save work entries for each employee
            for (Employee emp : employees) {
                WorkEntry weOne = new WorkEntry(
                        emp,
                        LocalDate.of(2024, 11, 15),
                        9.0,
                        false,
                        false
                );
                WorkEntry weTwo = new WorkEntry(
                        emp,
                        LocalDate.of(2024, 11, 20),
                        10.0,
                        true,
                        true
                );
                WorkEntry weThree = new WorkEntry(
                        emp,
                        LocalDate.of(2024, 11, 25),
                        8.5,
                        true,
                        false
                );
                workEntryRepository.saveAll(List.of(weOne, weTwo, weThree));
            }

            // Create and save payroll entries for each employee
            Payroll payrollOne = new Payroll(
                    employeeOne,
                    1000.0,
                    800.0,
                    50.0,
                    100.0,
                    100.0,
                    62.0,
                    62.0,
                    29.0,
                    29.0,
                    50.0,
                    0.0,
                    "2024-11-15"
            );
            Payroll payrollTwo = new Payroll(
                    employeeTwo,
                    1200.0,
                    900.0,
                    60.0,
                    120.0,
                    120.0,
                    74.4,
                    74.4,
                    34.8,
                    34.8,
                    60.0,
                    50.0,
                    "2024-11-20"
            );
            Payroll payrollThree = new Payroll(
                    employeeThree,
                    1500.0,
                    1100.0,
                    75.0,
                    150.0,
                    150.0,
                    93.0,
                    93.0,
                    43.5,
                    43.5,
                    75.0,
                    100.0,
                    "2024-11-25"
            );
            payrollRepository.saveAll(List.of(payrollOne, payrollTwo, payrollThree));
        };
    }
}
