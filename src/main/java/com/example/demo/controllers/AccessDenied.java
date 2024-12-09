package com.example.demo.controllers;
import com.example.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDenied {
    @GetMapping("/access-denied")
    public String accessDeniedPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        String role = auth.getAuthorities().toString();

        model.addAttribute("role", role);
        model.addAttribute("principal", principal);
        model.addAttribute("requiredRole", role);
        return "AccessDenied";
    }
}
