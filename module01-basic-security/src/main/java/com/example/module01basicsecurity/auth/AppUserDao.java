package com.example.module01basicsecurity.auth;

import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AppUserDao {

    Optional<AppUser> selectApplicationUserByUsername(String username);
}
