package com.example.module04formlogin.authentication;

import com.example.module04formlogin.authentication.tokens.UsernamePasswordToken;
import com.example.module04formlogin.exceptions.IncorrectPasswordException;
import com.example.module04formlogin.exceptions.InvalidUserAccountException;
import com.example.module04formlogin.service.AppUserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final AppUserDetailsService appUserDetailsService;
    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    public UsernamePasswordAuthenticationProvider(AppUserDetailsService appUserDetailsService, AppUserService appUserService, PasswordEncoder passwordEncoder) {
        this.appUserDetailsService = appUserDetailsService;
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = this.appUserDetailsService.loadUserByUsername(username);

        if (user != null) {

            if (this.passwordEncoder.matches(password, user.getPassword())) {

                if (user.isEnabled() && user.isAccountNonExpired() && user.isAccountNonLocked() && user.isCredentialsNonExpired()) {

                    // Renew Otp
                    this.appUserService.renewOpt(user.getUsername());

                    return new UsernamePasswordAuthenticationToken(user.getUsername(), password, user.getAuthorities());

                } else {
                    throw new InvalidUserAccountException("Please contact to admin!");
                }

            } else {
                throw new IncorrectPasswordException("username and password doesn't match!");
            }

        } else {
            throw new BadCredentialsException("There is something wrong with authentication!");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordToken.class.isAssignableFrom(authentication);
    }
}
