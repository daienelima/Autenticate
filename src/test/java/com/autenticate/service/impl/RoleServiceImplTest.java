package com.autenticate.service.impl;

import com.autenticate.model.Role;
import com.autenticate.model.RoleName;
import com.autenticate.model.RoleTest;
import com.autenticate.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private RoleServiceImpl service;
    private Role role;

    @BeforeEach
    void setUp() {
        role = RoleTest.createRoleMock();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        List<Role> roles = new ArrayList<>();
        roles.add(RoleTest.createRoleMock());
        Mockito.when(roleRepository.findAll()).thenReturn(roles);
        var out = service.findAll();
        assertNotNull(out);
        assertEquals(role.getName(), out.get(0).getName());
    }

    @Test
    void findByName() {
        Mockito.when(roleRepository.findByName(Mockito.any())).thenReturn(role);
        var out = service.findByName(RoleName.ROLE_USER);
        assertSame(role, out);
    }

    @Test
    void findById() {
        Mockito.when(roleRepository.findById(Mockito.any())).thenReturn(Optional.of(role));
        var out = service.findById(1L);
        assertSame(role, out.get());
    }

    @Test
    void save() {
        Mockito.when(roleRepository.save(Mockito.any())).thenReturn(role);
        var out = service.save(role);
        assertEquals(role.getName(), out.getName());
    }
}