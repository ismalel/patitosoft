package com.patitosoft.empadmin.exception;

import com.patitosoft.empadmin.http.ResponseAPI;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private ResponseAPI responseAPI;

    public BadRequestException(ResponseAPI responseAPI) {
        super();
        this.responseAPI = responseAPI;
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseAPI getResponseAPI() {
        return responseAPI;
    }

    public void setResponseAPI(ResponseAPI responseAPI) {
        this.responseAPI = responseAPI;
    }
}
