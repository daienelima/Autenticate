package com.autenticate.security;

import com.autenticate.model.UserTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUserFactoryTest {

    @Test
    void create() {
        var user = UserTest.createUserMocke();
        var jwtUser = JwtUserFactory.create(user);
        assertEquals(user.getUsername(), jwtUser.getUsername());
    }

}