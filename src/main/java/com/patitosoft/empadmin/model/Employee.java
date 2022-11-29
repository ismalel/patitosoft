package com.patitosoft.empadmin.model;

import javax.persistence.*;

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
    @Column(name = "salary")
    private String salary;
    @Column(name = "active")
    private Boolean active;
    @OneToOne(mappedBy = "position", cascade = CascadeType.ALL)
    private Position position;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private ContactInformation contactInformation;

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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", corporateEmail='" + corporateEmail + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", salary='" + salary + '\'' +
                ", active=" + active +
                ", position=" + position +
                '}';
    }
}
