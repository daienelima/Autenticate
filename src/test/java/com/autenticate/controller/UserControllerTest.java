package com.autenticate.controller;

import com.autenticate.model.UserTest;
import com.autenticate.security.JWTUtil;
import com.autenticate.security.JwtUser;
import com.autenticate.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    private static String TOKENHEADER = "Authorization";
    private String token;
    @Mock
    private JWTUtil jwtUtil;
    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private UserService userService;
    private UserController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        token = "kdjaidoahhhhd!daD";
        controller = new UserController(jwtUtil,userDetailsService,userService);
    }

    @Test
    void get_authenticated_user_test(){
        var request = Mockito.mock(HttpServletRequest.class);
        var user = Mockito.mock(JwtUser.class);

        Mockito.when(request.getHeader(TOKENHEADER)).thenReturn(token);
        Mockito.when(jwtUtil.getUsernameFromToken(Mockito.anyString())).thenReturn("user");
        Mockito.when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(user);

        var response = controller.getAuthenticatedUser(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void create_new_user_test(){
        var user = UserTest.createUserMocke();
        Mockito.when(userService.save(Mockito.any())).thenReturn(user);

        var response = controller.createNewUser(user);
        assertSame(user, response.getBody());
    }

    @Test
    void enabledUser_test(){
        var user = UserTest.createUserMocke();
        Mockito.when(userService.findByUsername(Mockito.anyString())).thenReturn(user);

        var response = controller.enabledUser("user");
        assertSame(HttpStatus.OK, response.getStatusCode());
    }
}