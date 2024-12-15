package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/admin/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public String employeeScreen(Model model) {
        model.addAttribute("title", "Employees");
        model.addAttribute("employees", employeeRepository.findAll());
        return "AdminEmployees";
    }

    @PostMapping
    public String createEmployee(@ModelAttribute Employee emp, Model model) {
        Employee savedEmployee = employeeService.createEmployee(emp);
        model.addAttribute("createdEmployee", savedEmployee);
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("title", "Employees");
        return "AdminEmployees";
    }

    @GetMapping("/edit/{id}")
    public String updateEmployeeGet(@PathVariable String id, @ModelAttribute Employee emp, Model model) {
        Employee employee = employeeRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + id));
        model.addAttribute("title", "Edit Employee");
        model.addAttribute("employee", employee);
        return "EditEmployee";
    }

    @PostMapping("/edit")
    public String updateEmployee(@ModelAttribute Employee emp) {
        UUID id = emp.getEmployeeId();
        System.out.println("Employee: " + emp);
        System.out.println("Post Edit Id: " + id);
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + id));
        // Update fields
        existingEmployee.setFirstName(emp.getFirstName());
        existingEmployee.setJobTitle(emp.getJobTitle());
        existingEmployee.setLastName(emp.getLastName());
        existingEmployee.setEmail(emp.getEmail());
        existingEmployee.setCity(emp.getCity());
        existingEmployee.setState(emp.getState());
        existingEmployee.setZip(emp.getZip());
        existingEmployee.setDependents(emp.getDependents());
        existingEmployee.setDepartment(emp.getDepartment());
        existingEmployee.setSalary(emp.getSalary());
        existingEmployee.setAddressLine1(emp.getAddressLine1());
        existingEmployee.setAddressLine2(emp.getAddressLine2());

        // Save updated employee
        employeeRepository.save(existingEmployee);
        return "redirect:/admin/employees";
    }

    @PostMapping("/delete")
    public String deleteEmployee(@RequestParam String id, RedirectAttributes redirectAttributes) {
        try {
            employeeService.deleteEmployee(UUID.fromString(id));
            redirectAttributes.addFlashAttribute("successMessage", "Employee deleted");
        } catch(Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Server Error: Employee Deletion Unsuccessful");
        }
        return "redirect:/admin/employees";
    }
}
