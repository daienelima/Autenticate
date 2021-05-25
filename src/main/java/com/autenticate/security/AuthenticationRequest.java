package com.autenticate.security;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class AuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;
    private final String username;
    private final String password;

}
