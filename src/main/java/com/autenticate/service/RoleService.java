package com.autenticate.service;

import com.autenticate.model.Role;
import com.autenticate.model.RoleName;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> findAll();

    Role findByName(RoleName name);

    Optional<Role> findById(Long id);

    Role save(Role role);
}
