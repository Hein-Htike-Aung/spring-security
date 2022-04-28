package com.example.module02jwt.security;

import com.example.module02jwt.auth.AppUserService;
import com.example.module02jwt.jwt.JwtConfig;
import com.example.module02jwt.jwt.JwtTokenVerifier;
import com.example.module02jwt.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

import static com.example.module02jwt.security.AppUserRole.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final AppUserService appUserService;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public AppSecurityConfig(
            PasswordEncoder passwordEncoder,
            AppUserService appUserService,
            JwtConfig jwtConfig,
            SecretKey secretKey
    ) {
        this.passwordEncoder = passwordEncoder;
        this.appUserService = appUserService;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appUserService);

        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                /*
                * POST localhost:8080/login
                * body -> {
                *   "username": "...",
                *   "password": "..."
                * }
                * */
                // Make Auth Server Stateless
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtUsernameAndPasswordAuthenticationFilter.class)

                .authorizeRequests()
                .antMatchers("/", "/index", "/css/**", "/js/**").permitAll()
                .antMatchers("/api/**").hasRole(ADMIN.name())
                .anyRequest().authenticated();

    }
}
