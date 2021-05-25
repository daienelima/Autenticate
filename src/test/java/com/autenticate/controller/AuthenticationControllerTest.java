package com.autenticate.controller;

import com.autenticate.security.AuthenticationRequest;
import com.autenticate.security.JWTUtil;
import com.autenticate.security.JwtUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationControllerTest {

    private static String TOKENHEADER = "Authorization";
    private String token;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JWTUtil jwtUtil;
    @Mock
    private UserDetailsService userDetailsService;
    private AuthenticationController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        token = "kdjaidoahhhhd!daD";
        controller = new AuthenticationController(authenticationManager,jwtUtil, userDetailsService);
    }

    @Test
    void create_authentication_token_test(){
        var request = AuthenticationRequest.builder()
                .username("user")
                .password("pass").build();
        var autenticate = Mockito.mock(Authentication.class);
        var userDatails = Mockito.mock(UserDetails.class);

        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(autenticate);
        Mockito.when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(userDatails);
        Mockito.when(jwtUtil.generateToken(userDatails)).thenReturn(token);

        var response = controller.createAuthenticationToken(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody().getToken());
    }

    @Test
    void refresh_and_get_authentication_token_ok_test(){
        var request = Mockito.mock(HttpServletRequest.class);
        var user = Mockito.mock(JwtUser.class);

        Mockito.when(request.getHeader(TOKENHEADER)).thenReturn(token);
        Mockito.when(jwtUtil.getUsernameFromToken(Mockito.anyString())).thenReturn("user");
        Mockito.when(jwtUtil.canTokenBeRefreshed(Mockito.anyString(),Mockito.any())).thenReturn(true);
        Mockito.when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(user);
        Mockito.when(jwtUtil.refreshToken(Mockito.anyString())).thenReturn(token);

        var response = controller.refreshAndGetAuthenticationToken(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody().getToken());
    }

    @Test
    void refresh_and_get_authentication_token_bad_request_test(){
        var request = Mockito.mock(HttpServletRequest.class);
        var user = Mockito.mock(JwtUser.class);

        Mockito.when(request.getHeader(TOKENHEADER)).thenReturn(token);
        Mockito.when(jwtUtil.getUsernameFromToken(Mockito.anyString())).thenReturn("user");
        Mockito.when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(user);

        var response = controller.refreshAndGetAuthenticationToken(request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}