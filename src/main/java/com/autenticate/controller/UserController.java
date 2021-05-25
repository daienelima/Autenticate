package com.autenticate.controller;

import com.autenticate.constants.Constants;
import com.autenticate.model.User;
import com.autenticate.security.JWTUtil;
import com.autenticate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    private final JWTUtil jwtUtil;
    @Qualifier("jwtUserDetailsService")
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Autowired
    public UserController(JWTUtil jwtUtil, UserDetailsService userDetailsService, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAuthenticatedUser(HttpServletRequest request) {
        var username = jwtUtil.getUsernameFromToken(
                request.getHeader(Constants.TOKEN_HEADER).substring(7));
        var response = this.userDetailsService.loadUserByUsername(username);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user")
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.save(user));
    }

    @GetMapping("/enable/{username}")
    public ResponseEntity<?> enabledUser(@PathVariable String username) {
        this.userService.enableUser(username);
        return ResponseEntity.ok().build();
    }
}
