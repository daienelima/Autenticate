package com.autenticate.model;

import java.util.Optional;
import java.util.stream.Stream;

public enum RoleName {

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String name;

    RoleName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static RoleName getByName(String name) {
        Optional<RoleName> opt = Stream.of(RoleName.values())
                .filter(i -> i.getName().equalsIgnoreCase(name))
                .findFirst();
        return opt.get();
    }
}
