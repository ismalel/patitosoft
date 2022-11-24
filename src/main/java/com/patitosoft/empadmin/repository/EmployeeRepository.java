package com.patitosoft.empadmin.repository;

import com.patitosoft.empadmin.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByCorporateEmail(String corporateEmail);
    Boolean existsByCorporateEmail(String corporateEmail);
}
