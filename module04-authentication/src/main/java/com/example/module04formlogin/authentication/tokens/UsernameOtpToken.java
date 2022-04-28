package com.example.module04formlogin.authentication.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/*
* To Set in Provider class's support method
* */
public class UsernameOtpToken extends UsernamePasswordAuthenticationToken {

    public UsernameOtpToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
