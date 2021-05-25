package com.autenticate.service.impl;

import com.autenticate.constants.Constants;
import com.autenticate.exception.RoleNotFoundException;
import com.autenticate.model.Role;
import com.autenticate.model.RoleName;
import com.autenticate.repository.RoleRepository;
import com.autenticate.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    @Override
    public Role findByName(RoleName name) {
        log.info("Searching for role by name");
        var role = this.roleRepository.findByName(name);
        if (role == null) {
            throw new RoleNotFoundException(Constants.ROLE_NOT_FOUND);
        }
        return role;
    }

    @Override
    public Optional<Role> findById(Long id) {
        log.info("Searching for role by id");
        var roles = roleRepository.findById(id);
        if (!roles.isPresent()) {
            throw new RoleNotFoundException(Constants.ROLE_NOT_FOUND);
        }
        return roles;
    }

    @Override
    public Role save(Role role) {
        log.info("Saving role");
        return this.roleRepository.save(role);
    }
}
