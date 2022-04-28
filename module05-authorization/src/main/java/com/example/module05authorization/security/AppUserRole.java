package com.example.module05authorization.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.module05authorization.security.AppUserPermission.*;

public enum AppUserRole {

    ADMIN(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE)),
    ADMIN_TRAINEE(Sets.newHashSet(ADMIN_READ)),
    MANAGER(Sets.newHashSet(MANAGER_READ, MANAGER_WRITE)),
    MANAGER_TRAINEE(Sets.newHashSet(MANAGER_READ));

    private final Set<AppUserPermission> appUserPermissions;

    AppUserRole(Set<AppUserPermission> appUserPermissions) {
        this.appUserPermissions = appUserPermissions;
    }

    public Set<AppUserPermission> getAppUserPermissions() {
        return appUserPermissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> permissions = this.appUserPermissions.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
