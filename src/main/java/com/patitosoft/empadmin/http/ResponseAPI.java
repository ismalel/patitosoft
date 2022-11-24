package com.patitosoft.empadmin.http;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class ResponseAPI implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 7702134516418120340L;
    private Boolean success;
    private String message;
    @JsonIgnore
    private HttpStatus status;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public ResponseAPI(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseAPI(Boolean success, String message, HttpStatus status) {
        this.success = success;
        this.message = message;
        this.status = status;
    }
}
