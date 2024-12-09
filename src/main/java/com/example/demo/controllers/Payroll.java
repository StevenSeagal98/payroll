package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class Payroll {
    @GetMapping("/payroll")
    public String payroll(Model model) {
        model.addAttribute("title", "Payroll");
        return "Payroll";
    }
}
