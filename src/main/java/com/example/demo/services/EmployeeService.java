package com.example.demo.services;

import com.example.demo.models.Employee;
import com.example.demo.models.User;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Employee createEmployee(Employee employee) {
        System.out.println("Hit employee create service");
        Employee savedEmployee = employeeRepository.save(employee);

        System.out.println("Saved employee: " + savedEmployee);
        String password = employee.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Username: " + savedEmployee.getUsername());
        System.out.println("Pass: " + password);
        System.out.println("Encoded password: " + encodedPassword);
        User user = new User(
                employee.getUsername(),
                employee.getEmail(),
                encodedPassword,
                "USER"
        );
        userRepository.save(user);
        return savedEmployee;
    }

    @Transactional
    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }
}
