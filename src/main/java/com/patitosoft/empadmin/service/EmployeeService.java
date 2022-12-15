package com.patitosoft.empadmin.service;

import com.patitosoft.empadmin.model.Birthday;
import com.patitosoft.empadmin.model.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeService extends GenericService<Employee> {

    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String lastName);
    List<Employee> findByPosition(String position);
    List<Employee> findByCountry(String country);
    List<Employee> findByState(String state);
    Birthday findByBirthDay();
    Employee findByCorporateEmail(String corporateEmail);

}
