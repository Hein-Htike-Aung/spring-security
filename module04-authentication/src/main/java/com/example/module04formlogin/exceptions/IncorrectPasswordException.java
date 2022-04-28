package com.example.module04formlogin.exceptions;

import org.springframework.security.authentication.BadCredentialsException;

public class IncorrectPasswordException extends BadCredentialsException {
    public IncorrectPasswordException(String msg) {
        super(msg);
    }
}
