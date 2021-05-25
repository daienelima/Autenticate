package com.autenticate.exception;

import com.autenticate.constants.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordInvalidExceptionTest {

    @Test
    void passwordInvalidException(){
        String msg;
        try {
            throw new PasswordInvalidException(Constants.PASSWORD_INVALID);
        }catch (Exception e){
            msg = e.getMessage();
        }
        assertEquals(Constants.PASSWORD_INVALID, msg);
    }

}