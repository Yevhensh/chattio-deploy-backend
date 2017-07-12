package com.chattio.dto;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public enum  RoleDto implements GrantedAuthority, Serializable {
    USER("USER"),
    ADMIN("ADMIN");
    private final String name;

    RoleDto(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }
}
