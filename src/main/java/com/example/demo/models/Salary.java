package com.example.demo.models;

import com.example.demo.enums.MedicalCoverage;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "salaries")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID salaryId;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
    private Employee employee;

    private double baseSalary;

    @Enumerated(EnumType.STRING)
    private MedicalCoverage medicalCoverage;

    private int dependents;

    private String dateHired;
}
