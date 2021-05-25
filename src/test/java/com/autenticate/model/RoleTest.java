package com.autenticate.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTest {

    @Test
    void create_model_test(){
        assertEquals(1, createRoleMock().getId().byteValue());
        assertEquals(RoleName.getByName("ROLE_USER"), createRoleMock().getName());
        assertEquals("user", createRoleMock().getUsers().get(0).getUsername());
    }

    public static Role createRoleMock(){
        List<User> users = new ArrayList<>();
        User user = UserTest.createUserMocke();
        users.add(user);
        return new Role(1L, RoleName.ROLE_USER, users);
    }
}
