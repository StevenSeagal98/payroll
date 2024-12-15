package com.example.demo.controllers;
import com.example.demo.models.Deductions;
import com.example.demo.models.Employee;
import com.example.demo.models.HoursWorked;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.HoursWorkedRepository;
import com.example.demo.services.PayService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.UUID;

@Controller
public class EmployeeTimeEntry {

    private final HoursWorkedRepository hoursWorkedRepository;
    private final EmployeeRepository employeeRepository;
    private final PayService payService;

    public EmployeeTimeEntry(HoursWorkedRepository hoursWorkedRepository, EmployeeRepository employeeRepository, PayService payService) {
        this.hoursWorkedRepository = hoursWorkedRepository;
        this.employeeRepository = employeeRepository;
        this.payService = payService;
    }

    @GetMapping("/employee/time-entry")
    public String employeeTimeEntry(@ModelAttribute HoursWorked hoursWorked, Model model) {
        System.out.println("Hit GET for time entry");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        String username = auth.getName();
        Employee employee = employeeRepository.findByUsername(username);
        Deductions payrollDetails = payService.calculatePayroll(employee);
        Iterable<HoursWorked> hoursWorkedList = hoursWorkedRepository.findAllByEmployee(employee);
        model.addAttribute("hoursWorkedList", hoursWorkedList);
        model.addAttribute("hoursWorked", hoursWorked);
        model.addAttribute("employee", employee);
        model.addAttribute("title", "Time Entry");
        model.addAttribute("deductions", payrollDetails);
        System.out.println("Deductions: " + payrollDetails);
        return "EmployeeTimeEntry";
    }

    @PostMapping("/employee/time-entry")
    public String employeeTimeEntrySubmit(
            @RequestParam Long hours,
            @RequestParam LocalDate date,
            @RequestParam UUID employeeId,
            @RequestParam Boolean isPTO
    ) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Employee ID"));
        HoursWorked hoursWorked = new HoursWorked();
        hoursWorked.setEmployee(employee);
        hoursWorked.setHours(hours);
        hoursWorked.setDate(date);
        hoursWorked.setIsPTO(isPTO);
        hoursWorked.setConfirmed(false);
        // Create new record
        hoursWorkedRepository.save(hoursWorked);
        return "redirect:/employee/time-entry";
    }

    @PostMapping("/employee/time-entry/edit")
    public String employeeTimeEntryEdit(
            @RequestParam Long hours,
            @RequestParam LocalDate date,
            @RequestParam UUID employeeId,
            @RequestParam Boolean isPTO,
            @RequestParam Long id
    ) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Employee ID"));
        HoursWorked hoursWorked = new HoursWorked();
        hoursWorked.setId(id);
        hoursWorked.setIsPTO(isPTO);
        hoursWorked.setEmployee(employee);
        hoursWorked.setDate(date);
        hoursWorked.setHours(hours);
        hoursWorked.setConfirmed(false);
        hoursWorkedRepository.save(hoursWorked);
        return "redirect:/employee/time-entry";
    }

    @PostMapping("/employee/time-entry/delete")
    public String employeeTimeEntryDelete(
            @RequestParam Long id,
            @RequestParam Boolean confirmed
    ) {
        System.out.println("Hit DELETE for time entry: " + id);
        if(confirmed) {
            hoursWorkedRepository.deleteById(id);
        }
        return "redirect:/employee/time-entry?error=confirmed";
    }
}
