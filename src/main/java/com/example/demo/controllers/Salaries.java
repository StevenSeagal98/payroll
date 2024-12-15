package com.example.demo.controllers;
import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Salaries {

    private final EmployeeRepository employeeRepository;

    public Salaries(EmployeeRepository employeeRepository) { this.employeeRepository = employeeRepository; }

    @GetMapping("/admin/salaries")
    public String salaries(Model model) {
        Iterable<Employee> salaries = employeeRepository.findAll();
        model.addAttribute("title", "Salaries");
        model.addAttribute("salaries", salaries);
        return "Salaries";
    }
}
