package com.example.module04formlogin.security;

import com.example.module04formlogin.authentication.UsernameOtpAuthenticationProvider;
import com.example.module04formlogin.authentication.UsernamePasswordAuthenticationProvider;
import com.example.module04formlogin.authentication.AppUserDetailsService;
import com.example.module04formlogin.jwt.JwtConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import com.example.module04formlogin.authentication.filters.*;

import javax.crypto.SecretKey;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
    private final UsernameOtpAuthenticationProvider usernameOtpAuthenticationProvider;
    private final AppUserDetailsService appUserDetailsService;
    private final JwtConfigurationProperties jwtConfigurationProperties;
    private final SecretKey secretKey;

    public SecurityConfig(UsernamePasswordAuthenticationProvider appUserAuthenticationProvider, UsernameOtpAuthenticationProvider usernameOtpAuthenticationProvider, AppUserDetailsService appUserDetailsService, JwtConfigurationProperties jwtConfigurationProperties, SecretKey secretKey) {
        this.usernamePasswordAuthenticationProvider = appUserAuthenticationProvider;
        this.usernameOtpAuthenticationProvider = usernameOtpAuthenticationProvider;
        this.appUserDetailsService = appUserDetailsService;
        this.jwtConfigurationProperties = jwtConfigurationProperties;
        this.secretKey = secretKey;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernamePasswordAuthenticationProvider);
        auth.authenticationProvider(usernameOtpAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*
         * POST localhost:8080/login
         * body -> {
         *   "username": "...",
         *   "password": "..."
         * }
         * And then access api with authorization header using jwt token
         * * body -> {
         *   "username": "...",
         *   "otp": "..."
         * }
         * */
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .addFilter(new JwtAuthenticationFilter(authenticationManager(), this.jwtConfigurationProperties, this.secretKey))
                .addFilterAfter(new JwtTokenVerifier(this.jwtConfigurationProperties, this.secretKey), JwtAuthenticationFilter.class);

//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/index", "/css/**", "/js/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//
//                .formLogin()
//                .loginPage("/login").permitAll()  // SessionId will expire in 30 min of inactivity
//                .defaultSuccessUrl("/home", true)
//                .and()
//
//                .rememberMe() // remember Me cookie default 2 weeks
//                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//                .key("secured")
//                .userDetailsService(this.appUserDetailsService)
//                .and()
//
//                .logout()
//                .logoutUrl("/logout")
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID", "remember-me")
//                .logoutSuccessUrl("/login");

    }


}
