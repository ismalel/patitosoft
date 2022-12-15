package com.patitosoft.empadmin.controller;

import com.patitosoft.empadmin.exception.ResourceNotFoundException;
import com.patitosoft.empadmin.model.Birthday;
import com.patitosoft.empadmin.model.Employee;
import com.patitosoft.empadmin.model.UserEmployeeResponse;
import com.patitosoft.empadmin.service.EmployeeService;
import com.patitosoft.empadmin.util.ControllerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class UserController {


    final private EmployeeService employeeService;

    public UserController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(params = "firstName")
    public ResponseEntity<List<UserEmployeeResponse>> getEmployeesByFirstName(@RequestParam("firstName") String firstName) {
        List<Employee> employees = employeeService.findByFirstName(firstName).stream().filter(employee -> employee.getActive()).collect(Collectors.toList());
        List<UserEmployeeResponse> response = new ArrayList<>();
        for (Employee e : employees) {
            response.add(ControllerUtil.hideSensitiveData(e));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = "lastName")
    public ResponseEntity<List<UserEmployeeResponse>> getEmployeesByLastName(@RequestParam("lastName") String lastName) {
        List<Employee> employees = employeeService.findByLastName(lastName).stream().filter(employee -> employee.getActive()).collect(Collectors.toList());
        List<UserEmployeeResponse> response = new ArrayList<>();
        for (Employee e : employees) {
            response.add(ControllerUtil.hideSensitiveData(e));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = "position")
    public ResponseEntity<List<UserEmployeeResponse>> getEmployeesByPosition(@RequestParam("position") String position) {
        List<Employee> employees = employeeService.findByPosition(position).stream().filter(employee -> employee.getActive()).collect(Collectors.toList());
        List<UserEmployeeResponse> response = new ArrayList<>();
        for (Employee e : employees) {
            response.add(ControllerUtil.hideSensitiveData(e));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = "corporateEmail")
    public ResponseEntity<UserEmployeeResponse> getEmployeesByEmail(@RequestParam("corporateEmail") String corporateEmail) {
        Employee employee = employeeService.findByCorporateEmail(corporateEmail);
        if (employee == null || !employee.getActive()) {
            throw new ResourceNotFoundException("Employee", "id", employee);
        }
        UserEmployeeResponse userEmployeeResponse = ControllerUtil.hideSensitiveData(employee);
        return new ResponseEntity<>(userEmployeeResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEmployeeResponse> getEmployeesByEmail(@PathVariable("id") Long id) {
        Employee employee = employeeService.read(id);
        if (employee == null || !employee.getActive()) {
            throw new ResourceNotFoundException("Employee", "id", employee);
        }
        UserEmployeeResponse userEmployeeResponse = ControllerUtil.hideSensitiveData(employee);
        return new ResponseEntity<>(userEmployeeResponse, HttpStatus.OK);
    }

    @GetMapping("/birthdays")
    public ResponseEntity<Birthday> getEmployeesBirthdays() {
        Birthday birthday = employeeService.findByBirthDay();
        return new ResponseEntity<>(birthday, HttpStatus.OK);
    }
}
