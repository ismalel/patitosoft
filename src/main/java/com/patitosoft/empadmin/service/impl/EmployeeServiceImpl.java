package com.patitosoft.empadmin.service.impl;

import com.patitosoft.empadmin.exception.BadRequestException;
import com.patitosoft.empadmin.exception.ResourceNotFoundException;
import com.patitosoft.empadmin.http.ResponseAPI;
import com.patitosoft.empadmin.model.Employee;
import com.patitosoft.empadmin.repository.EmployeeRepository;
import com.patitosoft.empadmin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return employeeRepository.getReferenceById(id);
    }

    @Override
    public List<Employee> readAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Long id, Employee employee) {
        return null;
    }

    @Override
    public ResponseAPI delete(Employee employee) {
        Employee temp = employeeRepository.findByCorporateEmail(employee.getCorporateEmail()).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employee.getCorporateEmail()));

        employeeRepository.deleteById(temp.getId());

        return new ResponseAPI(Boolean.TRUE, "You successfully deleted user of:" + temp.getId());
    }
}
