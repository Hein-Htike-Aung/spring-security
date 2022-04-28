package com.example.module05authorization.security;

public enum AppUserPermission {

    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    MANAGER_READ("manager:read"),
    MANAGER_WRITE("manager:write");

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
