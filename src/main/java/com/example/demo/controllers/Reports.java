package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Reports {
    @GetMapping("/reports")
    public String reports(Model model) {
        model.addAttribute("title", "Reports");
        return "Reports";
    }
}
