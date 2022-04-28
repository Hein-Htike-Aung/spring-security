package com.example.module05authorization.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.module05authorization.security.AppUserPermission.*;
import static com.example.module05authorization.security.AppUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        jsr250Enabled = true,
        securedEnabled = true
)
public class MethodLevelSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public MethodLevelSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        var user1 = User
                .withUsername("john")
                .password(this.passwordEncoder.encode("password"))
                .authorities(ADMIN.getAuthorities())
                .build();
        var user2 = User
                .withUsername("merry")
                .password(this.passwordEncoder.encode("password"))
                .authorities(ADMIN_TRAINEE.getAuthorities())
                .build();
        var user3 = User
                .withUsername("tom")
                .password(this.passwordEncoder.encode("password"))
                .authorities(MANAGER.getAuthorities())
                .build();
        var user4 = User
                .withUsername("lucas")
                .password(this.passwordEncoder.encode("password"))
                .authorities(MANAGER_TRAINEE.getAuthorities())
                .build();

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(user1);
        inMemoryUserDetailsManager.createUser(user2);
        inMemoryUserDetailsManager.createUser(user3);
        inMemoryUserDetailsManager.createUser(user4);

        auth.userDetailsService(inMemoryUserDetailsManager)
                .passwordEncoder(this.passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .csrf().disable()
                .authorizeRequests()

                .mvcMatchers("/", "/index", "/css/**", "/js/**").permitAll()

                .anyRequest().authenticated();
    }
}
