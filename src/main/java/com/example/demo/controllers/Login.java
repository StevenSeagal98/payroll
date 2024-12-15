package com.example.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login {
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "Login";
    }
}
