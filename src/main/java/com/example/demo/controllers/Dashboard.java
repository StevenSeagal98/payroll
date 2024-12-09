package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Dashboard {
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("title", "Test title");
        return "Dashboard";
    }
}

