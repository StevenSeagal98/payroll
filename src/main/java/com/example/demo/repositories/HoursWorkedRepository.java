package com.example.demo.repositories;

import com.example.demo.models.Employee;
import com.example.demo.models.HoursWorked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HoursWorkedRepository extends JpaRepository<HoursWorked, Long> {
    Iterable<HoursWorked> findAllByEmployee(Employee employee);

    List<HoursWorked> findAllByEmployeeAndDateBetween(Employee employee, LocalDate startOfWeek, LocalDate today);
}
