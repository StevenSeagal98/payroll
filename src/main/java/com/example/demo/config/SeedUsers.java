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
            if (userRepository.count() == 0) {
                userRepository.save(new User("admin", "admin@example.com", passwordEncoder.encode("adminpassword"), "ADMIN"));
                userRepository.save(new User("user1", "user1@example.com", passwordEncoder.encode("user1password"), "USER"));
                userRepository.save(new User("user2", "user2@example.com", passwordEncoder.encode("user2password"), "USER"));
                userRepository.save(new User("user3", "user3@example.com", passwordEncoder.encode("user3password"), "USER"));
                userRepository.save(new User("user4", "user4@example.com", passwordEncoder.encode("user4password"), "USER"));

                System.out.println("Seeded 5 users into the database.");
            }
        };
    }
}
