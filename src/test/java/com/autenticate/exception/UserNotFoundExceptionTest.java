package com.autenticate.exception;

import com.autenticate.constants.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNotFoundExceptionTest {

    @Test
    void userNotFoundException(){
        String msg;
        try {
            throw new UserNotFoundException(Constants.USER_NOT_FOUND);
        }catch (Exception e){
            msg = e.getMessage();
        }
        assertEquals(Constants.USER_NOT_FOUND, msg);
    }

}