package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "payroll")
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID payrollId;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
    private Employee employee;

    private double grossPay;

    private double netPay;

    private double stateTax;

    private double federalTaxEmployee;
    private double federalTaxEmployer;

    private double socialSecurityEmployee;
    private double socialSecurityEmployer;

    private double medicareEmployee;
    private double medicareEmployer;

    private double medicalDeduction;

    private double dependentStipend;

    private String payrollDate;

    public Payroll(Employee employee, double grossPay, double netPay, double stateTax, double federalTaxEmployee, double federalTaxEmployer, double socialSecurityEmployee, double socialSecurityEmployer, double medicareEmployee, double medicareEmployer, double medicalDeduction, double dependentStipend, String payrollDate) {
        this.employee = employee;
        this.grossPay = grossPay;
        this.netPay = netPay;
        this.stateTax = stateTax;
        this.federalTaxEmployee = federalTaxEmployee;
        this.federalTaxEmployer = federalTaxEmployer;
        this.socialSecurityEmployee = socialSecurityEmployee;
        this.socialSecurityEmployer = socialSecurityEmployer;
        this.medicareEmployee = medicareEmployee;
        this.medicareEmployer = medicareEmployer;
        this.medicalDeduction = medicalDeduction;
        this.dependentStipend = dependentStipend;
        this.payrollDate = payrollDate;
    }
}
