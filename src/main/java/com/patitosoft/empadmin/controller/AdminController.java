package com.patitosoft.empadmin.controller;

import com.patitosoft.empadmin.exception.ResourceNotFoundException;
import com.patitosoft.empadmin.model.*;
import com.patitosoft.empadmin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/employees")
public class AdminController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.create(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeService.readAll().stream().filter(Employee::getActive).collect(Collectors.toList());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping(params = {"pageNo", "pageSize"})
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "1") int pageSize) {
        List<Employee> employees = employeeService.readAllPaginated(pageNo, pageSize).stream().filter(Employee::getActive).collect(Collectors.toList());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeesByEmail(@PathVariable("id") Long id) {
        Employee employee = employeeService.read(id);
        if (employee == null || !employee.getActive()) {
            throw new ResourceNotFoundException("Employee", "id", employee);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", params = {"position", "salary"})
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestParam(value = "position", required = true) String position, @RequestParam(value = "salary", required = false) String salary) {
        Employee currentEmployee = employeeService.read(id);
        if (currentEmployee == null || !currentEmployee.getActive()) {
            throw new ResourceNotFoundException("Employee", "id", currentEmployee);
        } else {
            currentEmployee.getPositionHistory().add(new PositionHistory(currentEmployee.getCurrentPosition()));
            currentEmployee.setCurrentPosition(position);
            currentEmployee.getSalaryHistory().add(new SalaryHistory(currentEmployee.getCurrentSalary()));
            currentEmployee.setCurrentSalary(salary);
        }
        currentEmployee = employeeService.update(id, currentEmployee);
       // ResponseEntity.status(HttpStatus.OK).body(currentEmployee);
       // ResponseEntity.ok(currentEmployee);
        return ResponseEntity.ok(currentEmployee);
    }

    @PutMapping(value = "/{id}", params = "position")
    public ResponseEntity<Employee> updateEmployeePosition(@PathVariable Long id, @RequestParam(value = "position", required = true) String position) {
        Employee currentEmployee = employeeService.read(id);
        if (currentEmployee == null || !currentEmployee.getActive()) {
            throw new ResourceNotFoundException("Employee", "id", currentEmployee);
        } else {
            currentEmployee.getPositionHistory().add(new PositionHistory(currentEmployee.getCurrentPosition()));
            currentEmployee.setCurrentPosition(position);
        }
        currentEmployee = employeeService.update(id, currentEmployee);
        return new ResponseEntity<>(currentEmployee, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", params = "salary")
    public ResponseEntity<Employee> updateEmployeeSalary(@PathVariable Long id, @RequestParam(value = "salary", required = true) String salary) {
        Employee currentEmployee = employeeService.read(id);
        if (currentEmployee == null || !currentEmployee.getActive()) {
            throw new ResourceNotFoundException("Employee", "id", currentEmployee);
        } else {
            currentEmployee.getSalaryHistory().add(new SalaryHistory(currentEmployee.getCurrentSalary()));
            currentEmployee.setCurrentSalary(salary);
        }
        currentEmployee = employeeService.update(id, currentEmployee);
        return new ResponseEntity<>(currentEmployee, HttpStatus.OK);
    }

    @GetMapping(params = "countPosition")
    public ResponseEntity<PositionCount> getTotalPositionCount(@RequestParam("countPosition") String position){
        List<Employee> employees = employeeService.findByPosition(position).stream().filter(Employee::getActive).collect(Collectors.toList());
        return new ResponseEntity<>(new PositionCount(employees.size(), employees), HttpStatus.OK);
    }
    @GetMapping("/positions")
    public ResponseEntity<List<ReportPositionCount>> getReportPositionCount() {
        List<Employee> employees = employeeService.readAll().stream().filter(Employee::getActive).collect(Collectors.toList());
        List<ReportPositionCount> report = new ArrayList<>();
        employees.stream()
                .collect(Collectors.groupingBy(t -> t.getCurrentPosition(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(e -> report.add(new ReportPositionCount(e.getValue(), e.getKey())));


        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping(params = "country")
    public ResponseEntity<List<Employee>> getEmployeesByCountry(@RequestParam("country") String country) {
        List<Employee> employees = employeeService.findByCountry(country).stream().filter(Employee::getActive).collect(Collectors.toList());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(params = "state")
    public ResponseEntity<List<Employee>> getEmployeesByState(@RequestParam("state") String state) {
        List<Employee> employees = employeeService.findByState(state).stream().filter(Employee::getActive).collect(Collectors.toList());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<Employee> softDeleteEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.read(id);
        if (employee == null || !employee.getActive()) {
            throw new ResourceNotFoundException("Employee", "id", employee);
        } else {
            employee.setActive(false);
        }
        employeeService.update(id, employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/genders")
    public ResponseEntity<ReportGender> getGenderPieChart() {
        List<Employee> employees = employeeService.readAll().stream().filter(Employee::getActive).collect(Collectors.toList());
        List<Employee> males = employees.stream().filter(employee -> employee.getGender().equals("male")).collect(Collectors.toList());
        List<Employee> females = employees.stream().filter(employee -> employee.getGender().equals("female")).collect(Collectors.toList());

        ReportGender reportGender = new ReportGender(males.size(), females.size());

        return new ResponseEntity<>(reportGender, HttpStatus.OK);
    }

    @GetMapping("/salaries")
    public ResponseEntity<List<ReportSalaryEmployee>> getSalaryPieChart() {
        List<Employee> employees = employeeService.readAll().stream().filter(Employee::getActive).collect(Collectors.toList());
        List<ReportSalaryEmployee> salaryEmployees = new ArrayList<>();

        employees.stream()
                .collect(Collectors.groupingBy(t -> t.getCurrentSalary(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(e -> salaryEmployees.add(new ReportSalaryEmployee(e.getValue(), e.getKey())));


        return new ResponseEntity<>(salaryEmployees, HttpStatus.OK);
    }

    @GetMapping(params = "firstName")
    public ResponseEntity<List<Employee>> getEmployeesByFirstName(@RequestParam("firstName") String firstName, @RequestParam("all") Boolean all) {
        List<Employee> employees = employeeService.findByFirstName(firstName);
        if (!all) {
            employees = employees.stream().filter(employee -> employee.getActive()).collect(Collectors.toList());
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(params = "lastName")
    public ResponseEntity<List<Employee>> getEmployeesByLastName(@RequestParam("lastName") String lastName, @RequestParam("all") Boolean all) {
        List<Employee> employees = employeeService.findByLastName(lastName);
        if (!all) {
            employees = employees.stream().filter(employee -> employee.getActive()).collect(Collectors.toList());
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(params = "position")
    public ResponseEntity<List<Employee>> getEmployeesByPosition(@RequestParam("position") String position, @RequestParam("all") Boolean all) {
        List<Employee> employees = employeeService.findByPosition(position);
        if (!all) {
            employees = employees.stream().filter(employee -> employee.getActive()).collect(Collectors.toList());
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
