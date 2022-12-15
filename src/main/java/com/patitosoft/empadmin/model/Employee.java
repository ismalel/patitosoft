package com.patitosoft.empadmin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "corporate_email", nullable = false)
    private String corporateEmail;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "gender", nullable = false)
    private String gender;
    @Column(name = "currentSalary")
    private String currentSalary;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "currentPosition")
    private String currentPosition;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_information_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ContactInformation contactInformation;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "position_history_id", referencedColumnName = "id")
    private List<PositionHistory> positionHistory;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "salary_history_id", referencedColumnName = "id")
    private List<SalaryHistory> salaryHistory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorporateEmail() {
        return corporateEmail;
    }

    public void setCorporateEmail(String corporateEmail) {
        this.corporateEmail = corporateEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(String currentSalary) {
        this.currentSalary = currentSalary;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public List<PositionHistory> getPositionHistory() {
        return positionHistory;
    }

    public void setPositionHistory(List<PositionHistory> positionHistory) {
        this.positionHistory = positionHistory;
    }

    public List<SalaryHistory> getSalaryHistory() {
        return salaryHistory;
    }

    public void setSalaryHistory(List<SalaryHistory> salaryHistory) {
        this.salaryHistory = salaryHistory;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", corporateEmail='" + corporateEmail + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", currentSalary='" + currentSalary + '\'' +
                ", active=" + active +
                ", currentPosition='" + currentPosition + '\'' +
                ", contactInformation=" + contactInformation +
                '}';
    }
}
