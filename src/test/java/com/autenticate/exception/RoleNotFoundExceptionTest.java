package com.autenticate.exception;

import com.autenticate.constants.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleNotFoundExceptionTest {

    @Test
    void roleNotFoundException(){
        String msg;
        try {
            throw new RoleNotFoundException(Constants.ROLE_NOT_FOUND);
        }catch (Exception e){
            msg = e.getMessage();
        }
        assertEquals(Constants.ROLE_NOT_FOUND, msg);
    }

}