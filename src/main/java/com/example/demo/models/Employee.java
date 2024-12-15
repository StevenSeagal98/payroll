package com.example.demo.models;

import com.example.demo.enums.Gender;
import com.example.demo.enums.MedicalCoverage;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private UUID employeeId;
    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String department;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String status; // active or terminated

    @Column(nullable = false)
    private String payType; //salary or hourly

    private String email;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private double salary;
    private MedicalCoverage medicalCoverage;
    private int dependents;
    private LocalDate hireDate;

    @Column(nullable = true)
    private String pictureUrl;

    @Column(nullable = false)
    private String username;

    public Employee(String firstName, String lastName, String jobTitle, String department,
                    Gender gender, String status, String payType, String email,
                    String addressLine1, String addressLine2, String city, String state, String zip,
                    String pictureUrl, double salary, MedicalCoverage medicalCoverage, int dependents, LocalDate hireDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.department = department;
        this.gender = gender;
        this.status = "Active";
        this.payType = payType;
        this.email = email;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.pictureUrl = pictureUrl;
        this.salary = salary;
        this.medicalCoverage = medicalCoverage;
        this.dependents = dependents;
        this.hireDate = hireDate;
        this.username = this.getUsername();
    }

    public String getUsername() {
        int year = hireDate.getYear();
        return this.firstName.substring(0,1).toLowerCase() + this.lastName.toLowerCase() + year;
    }
    public String getPassword() {
        String emailStr = this.email.split("@")[0];
        int day = this.hireDate.getDayOfMonth();
        int month = this.hireDate.getMonthValue();
        return emailStr + day + month;
    }
}
