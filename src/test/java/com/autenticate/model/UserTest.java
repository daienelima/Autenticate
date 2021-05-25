package com.autenticate.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    void create_user_model_test(){
        assertEquals(1, createUserMocke().getId().byteValue());
        assertEquals("user", createUserMocke().getUsername());
        assertEquals("pass", createUserMocke().getPassword());
        assertEquals("useruser", createUserMocke().getFirstname());
        assertEquals("user", createUserMocke().getLastname());
        assertEquals("user@gmail.com", createUserMocke().getEmail());
        assertEquals(true, createUserMocke().getEnabled());
        assertEquals(new Date(02/05/2021), createUserMocke().getLastPasswordResetDate());
        assertEquals(RoleName.getByName("ROLE_USER"),createUserMocke().getRoles().get(0).getName());
    }

    public static User createUserMocke(){
        Date date = new Date(02/05/2021);
        List<Role> roles = new ArrayList<>();
        List<User> users = new ArrayList<>();
        roles.add(new Role(1L, RoleName.ROLE_USER, users));

        return new User(1L, "user", "pass", "useruser",
                "user", "user@gmail.com", true, date, roles);
    }
}
