package com.example.module02jwt.auth;

import java.util.Optional;

public interface AppUserDao {

    Optional<AppUser> selectApplicationUserByUsername(String username);
}
