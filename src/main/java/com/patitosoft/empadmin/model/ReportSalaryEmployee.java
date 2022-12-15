package com.patitosoft.empadmin.model;

public class ReportSalaryEmployee {

    private Long countEmployees;
    private String salary;

    public ReportSalaryEmployee() {}

    public ReportSalaryEmployee(Long countEmployees, String salary) {
        this.countEmployees = countEmployees;
        this.salary = salary;
    }

    public Long getCountEmployees() {
        return countEmployees;
    }

    public void setCountEmployees(Long countEmployees) {
        this.countEmployees = countEmployees;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
