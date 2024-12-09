package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "work_entry")
public class WorkEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID workEntryId;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
    private Employee employee;

    private LocalDate date;

    private double hoursWorked;

    private boolean isOvertime;

    private boolean isWeekend;

    public WorkEntry(Employee employee, LocalDate date, double hoursWorked, boolean isOvertime, boolean isWeekend) {
        this.employee = employee;
        this.date = date;
        this.hoursWorked = hoursWorked;
        this.isOvertime = isOvertime;
        this.isWeekend = isWeekend;
    }
}
