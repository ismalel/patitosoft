package com.patitosoft.empadmin.service.impl;

import com.patitosoft.empadmin.exception.BadRequestException;
import com.patitosoft.empadmin.exception.ResourceNotFoundException;
import com.patitosoft.empadmin.http.ResponseAPI;
import com.patitosoft.empadmin.model.Birthday;
import com.patitosoft.empadmin.model.Employee;
import com.patitosoft.empadmin.repository.EmployeeRepository;
import com.patitosoft.empadmin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl  implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        if (employeeRepository.existsByCorporateEmail(employee.getCorporateEmail())) {
            ResponseAPI response = new ResponseAPI(Boolean.FALSE, "Email is already taken");
            throw new BadRequestException(response);
        }

        return  employeeRepository.save(employee);
    }

    @Override
    public Employee read(Long id) {

        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> readAll() {

        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> readAllPaginated(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Employee> employeesPageResult = employeeRepository.findAll(paging);

        return employeesPageResult.toList();
    }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee currentEmployee = employeeRepository.getReferenceById(id);
        currentEmployee = employee;
        return employeeRepository.save(currentEmployee);
    }

    @Override
    public ResponseAPI delete(Employee employee) {
        Employee temp = employeeRepository.findByCorporateEmail(employee.getCorporateEmail()).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employee.getCorporateEmail()));

        employeeRepository.deleteById(temp.getId());

        return new ResponseAPI(Boolean.TRUE, "You successfully deleted user of:" + temp.getId());
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        return employeeRepository.findByFirstNameContains(firstName).orElseThrow(() -> new ResourceNotFoundException("Employee", "firstName", firstName));
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        return employeeRepository.findByLastNameContains(lastName).orElseThrow(() -> new ResourceNotFoundException("Employee", "lastName", lastName));
    }

    @Override
    public List<Employee> findByPosition(String position) {
        return employeeRepository.findByCurrentPosition(position).orElseThrow(() -> new ResourceNotFoundException("Employee", "position", position));
    }

    @Override
    public List<Employee> findByCountry(String country) {
        return employeeRepository.findAllByContactInformationAddressCountry(country).orElseThrow(() -> new ResourceNotFoundException("Employee", "country", country));
    }

    @Override
    public List<Employee> findByState(String state) {
        return employeeRepository.findAllByContactInformationAddressState(state).orElseThrow(() -> new ResourceNotFoundException("Employee", "state", state));
    }

    @Override
    public Birthday findByBirthDay() {

        List<Employee> todayList = employeeRepository.findBirthday().orElseThrow(() -> new ResourceNotFoundException("Employee", "date", ""));
        List<Employee> nextWeekList = new ArrayList<>();
        return new Birthday(todayList, nextWeekList);

    }

    @Override
    public Employee findByCorporateEmail(String corporateEmail) {
        return employeeRepository.findByCorporateEmail(corporateEmail).orElseThrow(() -> new ResourceNotFoundException("Employee", "corporateEmail", corporateEmail));

    }
}
