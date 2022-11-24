package com.patitosoft.empadmin.exception;

import com.patitosoft.empadmin.http.ResponseAPI;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private transient ResponseAPI responseAPI;
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException( String resourceName, String fieldName, Object fieldValue) {
        super();
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResponseAPI getResponseAPI() {
        return responseAPI;
    }

    public void setResponseAPI(ResponseAPI responseAPI) {
        this.responseAPI = responseAPI;
    }

    private void setResponseAPI() {
        String message = resourceName + " not found with " + fieldName + ":" + fieldValue;
        responseAPI = new ResponseAPI(Boolean.FALSE, message);
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
}
