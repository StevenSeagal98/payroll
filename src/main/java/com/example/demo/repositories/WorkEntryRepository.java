package com.example.demo.repositories;

import com.example.demo.models.WorkEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkEntryRepository extends JpaRepository<WorkEntry, UUID> {
    List<WorkEntry> findByEmployee_EmployeeId(UUID employeeId);
}
