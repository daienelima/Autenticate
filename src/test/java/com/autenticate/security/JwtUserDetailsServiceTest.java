package com.autenticate.security;

import com.autenticate.model.User;
import com.autenticate.model.UserTest;
import com.autenticate.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JwtUserDetailsServiceTest {

    @Mock
    private UserService userService;
    private JwtUserDetailsService jwtUserDetailsService;
    private User user ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        jwtUserDetailsService = new JwtUserDetailsService(userService);
        user = UserTest.createUserMocke();
    }

    @Test
    void loadUserByUsername() {
        Mockito.when(userService.findByUsername(Mockito.anyString())).thenReturn(user);
        var out = jwtUserDetailsService.loadUserByUsername("user");
        assertEquals(user.getUsername(), out.getUsername());
    }
}