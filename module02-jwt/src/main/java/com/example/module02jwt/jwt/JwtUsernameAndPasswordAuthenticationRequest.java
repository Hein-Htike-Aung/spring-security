package com.example.module02jwt.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Getter
@Setter
public class JwtUsernameAndPasswordAuthenticationRequest {

    private String username;

    private String password;

    public JwtUsernameAndPasswordAuthenticationRequest() {
    }
}
