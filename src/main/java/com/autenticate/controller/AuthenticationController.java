package com.autenticate.controller;

import com.autenticate.constants.Constants;
import com.autenticate.security.AuthenticationRequest;
import com.autenticate.security.AuthenticationResponse;
import com.autenticate.security.JWTUtil;
import com.autenticate.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
                                    @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));

        final var userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        return ResponseEntity.ok(
                new AuthenticationResponse(jwtUtil.generateToken(userDetails)));
    }

    @GetMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshAndGetAuthenticationToken(HttpServletRequest request) {

        final var token = request.getHeader(Constants.TOKEN_HEADER).substring(7);
        final var username = jwtUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            return ResponseEntity.ok(
                    new AuthenticationResponse(jwtUtil.refreshToken(token)));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
