package com.example.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class Dashboard {
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();
        if(Objects.equals(role, "ROLE_ADMIN") || Objects.equals(role, "ADMIN")) {
            return "redirect:/admin/employees";
        } else {
            return "redirect:/employee/time-entry";
        }
    }
}

