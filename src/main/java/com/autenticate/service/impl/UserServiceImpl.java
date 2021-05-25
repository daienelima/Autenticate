package com.autenticate.service.impl;

import com.autenticate.constants.Constants;
import com.autenticate.exception.EmailNotFoundException;
import com.autenticate.exception.UserNotFoundException;
import com.autenticate.model.User;
import com.autenticate.repository.UserRepository;
import com.autenticate.service.RoleService;
import com.autenticate.service.UserService;
import com.autenticate.util.Validate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    @Override
    public User findByUsername(String username) {
        log.info("Searching for user by {}", username);
        User user = this.userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UserNotFoundException(String.format(Constants.USER_NOT_FOUND + "'%s'", username));
    }

    @Override
    public User save(User user) {
        log.info("Saving user");
        if (this.userRepository.existsUsersByEmail(user.getEmail())) {
            throw new EmailNotFoundException(Constants.EMAIL_REGISTERED + user.getEmail());
        }
        if (!Validate.isEmail(user.getEmail())) {
            throw new EmailNotFoundException(String.format(Constants.EMAIL_INVALID + "'%s'", user.getEmail()));
        }
        user.getRoles().forEach(r -> this.roleService.findById(r.getId()));
        user.setPassword(Validate.encoderPassword(user.getPassword()));
        user.setLastPasswordResetDate(new Date());
        return this.userRepository.save(user);
    }

    @Override
    public void enableUser(String username) {
        log.info("activating user {} ", username);
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException(String.format(Constants.USER_NOT_FOUND + "'%s'", username));
        } else {
            user.setEnabled(true);
            this.userRepository.save(user);
        }
    }
}
