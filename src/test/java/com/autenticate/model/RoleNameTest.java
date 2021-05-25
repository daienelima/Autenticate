package com.autenticate.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleNameTest {

    @Test
    void getByName() {
        assertEquals(RoleName.ROLE_USER, RoleName.getByName("ROLE_USER"));
        assertEquals(RoleName.ROLE_ADMIN, RoleName.getByName("ROLE_ADMIN"));
    }
}