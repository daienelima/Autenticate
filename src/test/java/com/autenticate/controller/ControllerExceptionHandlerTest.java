package com.autenticate.controller;

import com.autenticate.constants.Constants;
import com.autenticate.exception.EmailNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler controllerExceptionHandler;

    @BeforeEach
    void setUp() {
        controllerExceptionHandler = new ControllerExceptionHandler();
    }

    @Test
    void handleUser() {
        var res = controllerExceptionHandler
                .handleUser(new EmailNotFoundException(Constants.EMAIL_INVALID));
        assertEquals(Constants.EMAIL_INVALID, res.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), res.getBody().getStatus());
        assertEquals(HttpStatus.BAD_REQUEST.toString(), res.getBody().getError());
    }
}