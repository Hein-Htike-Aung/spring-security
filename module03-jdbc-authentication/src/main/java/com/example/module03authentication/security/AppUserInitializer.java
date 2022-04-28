package com.example.module03authentication.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Collections;

@Component
public class AppUserInitializer {

    private final JdbcUserDetailsManager jdbcUserDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public AppUserInitializer(
            JdbcUserDetailsManager jdbcUserDetailsManager,
            PasswordEncoder passwordEncoder
    ) {
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void init() {

        this.jdbcUserDetailsManager.createUser(
                new User(
                        "john",
                        passwordEncoder.encode("password"),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
                )
        );
        this.jdbcUserDetailsManager.createUser(
                new User(
                        "lucifer",
                        passwordEncoder.encode("password"),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT"))
                )
        );
    }
}
