package com.autenticate.service;

import com.autenticate.model.User;

public interface UserService {

    User findByUsername(String username);

    User save(User user);

    void enableUser(String username);
}