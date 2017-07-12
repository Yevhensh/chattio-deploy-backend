package com.chattio.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    
    USER("USER"),
    ADMIN("ADMIN");
    private final String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }
}

