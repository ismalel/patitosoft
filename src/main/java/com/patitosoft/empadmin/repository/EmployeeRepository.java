package com.patitosoft.empadmin.repository;

import com.patitosoft.empadmin.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByCorporateEmail(String corporateEmail);
    Optional<List<Employee>> findByFirstNameContains(String name);
    Optional<List<Employee>> findByLastNameContains(String lastName);
    Optional<List<Employee>> findByCurrentPosition(String currentPosition);
    Boolean existsByCorporateEmail(String corporateEmail);
    @Query(value = "SELECT * FROM employee WHERE DAY(SYSDATE()) = DAY(contact_information.birthday) AND MONTH(SYSDATE()) = MONTH(contact_information.birthday)", nativeQuery = true)
    Optional<List<Employee>> findBirthday();
    Optional<List<Employee>> findAllByContactInformationBirthdayBetween(Date start, Date end);
    Optional<List<Employee>> findAllByContactInformationAddressCountry(String country);
    Optional<List<Employee>> findAllByContactInformationAddressState(String country);
}
