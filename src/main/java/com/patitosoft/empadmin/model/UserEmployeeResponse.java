package com.patitosoft.empadmin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class UserEmployeeResponse implements Serializable {

    private Long id;
    private String corporateEmail;
    private String firstName;
    private String lastName;
    private String gender;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ContactInformation contactInformation;

    public UserEmployeeResponse() {}

    public UserEmployeeResponse(Long id, String corporateEmail, String firstName, String lastName, String gender, ContactInformation contactInformation) {
        this.id = id;
        this.corporateEmail = corporateEmail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.contactInformation = contactInformation;
    }

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

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }
}
