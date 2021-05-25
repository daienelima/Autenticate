//package com.autenticate.dataloaders;
//
//import com.autenticate.model.Role;
//import com.autenticate.model.RoleName;
//import com.autenticate.model.User;
//import com.autenticate.service.RoleService;
//import com.autenticate.service.UserService;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Collections;
//import java.util.List;
//
//@Component
//public class UsersLoader implements ApplicationListener<ContextRefreshedEvent> {
//
//    private final RoleService roleService;
//    private final UserService userService;
//
//    public UsersLoader(RoleService roleService, UserService userService) {
//        this.roleService = roleService;
//        this.userService = userService;
//    }
//
//    @Override
//    @Transactional
//    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
//        List<Role> adminRoles = Collections.singletonList(createRoleIfNotFound(RoleName.ROLE_ADMIN));
//        List<Role> userRoles = Collections.singletonList(createRoleIfNotFound(RoleName.ROLE_USER));
//        createUserIfNotFound("admin", "admin",
//                "admin", "admin", "admin@admin.com",
//                true, adminRoles);
//        createUserIfNotFound("user", "admin",
//                "user", "user", "enabled@user.com",
//                true, userRoles);
//        createUserIfNotFound("disabled", "admin",
//                "user", "user", "disabled@user.com",
//                false, userRoles);
//    }
//
//    @Transactional
//    Role createRoleIfNotFound(RoleName name) {
//        Role role = new Role();
//        role.setName(name);
//        return roleService.save(role);
//    }
//
//    @Transactional
//    void createUserIfNotFound(String userName, String password, String firstName,
//                              String lastName, String email, boolean enabled, List<Role> roles) {
//        User user = new User();
//        user.setUsername(userName);
//        user.setPassword(password);
//        user.setFirstname(firstName);
//        user.setLastname(lastName);
//        user.setEmail(email);
//        user.setEnabled(enabled);
//        user.setRoles(roles);
//        userService.save(user);
//    }
//}
//
