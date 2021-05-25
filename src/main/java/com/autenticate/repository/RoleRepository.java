package com.autenticate.repository;

import com.autenticate.model.Role;
import com.autenticate.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleName name);

    Optional<Role> findById(Long id);
}
