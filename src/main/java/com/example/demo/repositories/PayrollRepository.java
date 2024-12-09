package com.example.demo.repositories;

import org.springframework.stereotype.Repository;
import com.example.demo.models.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, UUID> {

}
