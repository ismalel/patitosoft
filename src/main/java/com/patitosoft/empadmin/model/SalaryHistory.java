package com.patitosoft.empadmin.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "salary_history")
public class SalaryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "salary_history")
    private String salaryHistory;

    public SalaryHistory(String salaryHistory) {
        this.salaryHistory = salaryHistory;
    }

    public SalaryHistory() {

    }

    public String getSalaryHistory() {
        return salaryHistory;
    }

    public void setSalaryHistory(String salaryHistory) {
        this.salaryHistory = salaryHistory;
    }
}
