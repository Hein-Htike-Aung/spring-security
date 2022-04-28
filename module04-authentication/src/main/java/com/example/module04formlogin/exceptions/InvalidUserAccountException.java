package com.example.module04formlogin.exceptions;

import org.springframework.security.authentication.BadCredentialsException;

public class InvalidUserAccountException extends BadCredentialsException {
    public InvalidUserAccountException(String msg) {
        super(msg);
    }
}
