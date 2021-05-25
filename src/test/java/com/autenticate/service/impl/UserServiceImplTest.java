package com.autenticate.service.impl;

import com.autenticate.model.User;
import com.autenticate.model.UserTest;
import com.autenticate.repository.UserRepository;
import com.autenticate.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleService roleService;
    @InjectMocks
    private UserServiceImpl userService;
    private User user;

    @BeforeEach
    void setUp() {
        user = UserTest.createUserMocke();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByUsername(){
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);
        var out = userService.findByUsername("user");
        assertEquals(user.getUsername() ,out.getUsername());
    }

    @Test
    void save_(){
        Mockito.when(userRepository.existsUsersByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(roleService.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        var out = userService.save(user);
        assertEquals(user.getId(), out.getId());
    }

    @Test
    void enableUser(){
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);
        userService.enableUser("user");
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername("user");
    }
}