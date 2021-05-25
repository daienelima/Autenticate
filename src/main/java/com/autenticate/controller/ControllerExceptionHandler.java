package com.autenticate.controller;

import com.autenticate.dto.CustomErroDTO;
import com.autenticate.exception.EmailNotFoundException;
import com.autenticate.exception.RoleNotFoundException;
import com.autenticate.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {RoleNotFoundException.class, EmailNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<CustomErroDTO> handleUser(RuntimeException ex) {

        CustomErroDTO response = CustomErroDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .error(HttpStatus.BAD_REQUEST.toString())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
