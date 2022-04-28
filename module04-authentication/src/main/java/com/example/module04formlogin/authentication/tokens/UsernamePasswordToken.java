package com.example.module04formlogin.authentication.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/*
 * To Set in Provider class's support method
 * */
public class UsernamePasswordToken extends UsernamePasswordAuthenticationToken {

    public UsernamePasswordToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
