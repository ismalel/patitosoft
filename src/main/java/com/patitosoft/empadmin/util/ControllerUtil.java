package com.patitosoft.empadmin.util;

import com.patitosoft.empadmin.model.Employee;
import com.patitosoft.empadmin.model.UserEmployeeResponse;

public class ControllerUtil {

    public static UserEmployeeResponse hideSensitiveData(Employee e) {
        return new UserEmployeeResponse(e.getId(), e.getCorporateEmail(), e.getFirstName(),
                e.getLastName(), e.getGender(), e.getContactInformation());
    }
}
