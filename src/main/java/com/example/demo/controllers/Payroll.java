package com.example.demo.controllers;

import com.example.demo.models.Deductions;
import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.services.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.List;

@Controller
public class Payroll {
    private final PayService payService;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public Payroll(PayService payService, EmployeeRepository employeeRepository) {
        this.payService = payService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/admin/payroll")
    public String payroll(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        List<Deductions> payrollDetailsList = employees.stream()
                        .map(employee -> payService.calculatePayroll(employee))
                                .toList();
        model.addAttribute("payrollDetails", payrollDetailsList);
        model.addAttribute("title", "Payroll");
        return "Payroll";
    }

}
