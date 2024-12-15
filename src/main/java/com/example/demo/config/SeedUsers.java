package com.example.demo.config;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SeedUsers {

    @Bean
    public CommandLineRunner seedDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            userRepository.save(new User("HR0001", "admin@example.com", passwordEncoder.encode("sU0fq1!1W_O8"), "ADMIN"));
        };
    }
}
