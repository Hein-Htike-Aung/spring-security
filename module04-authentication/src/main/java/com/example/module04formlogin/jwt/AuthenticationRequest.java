package com.example.module04formlogin.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    private String username;
    private String password;
    private String otp;

    public AuthenticationRequest() {
    }
}
