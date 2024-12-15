package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeDemographics {

    private final EmployeeRepository employeeRepository;

    public EmployeeDemographics(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/admin/employee-demos")
    public String employeeDemographics(Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("title", "Employee Demographics");
        model.addAttribute("employees", employees);
        return "EmployeeDemographics";
    }
}
