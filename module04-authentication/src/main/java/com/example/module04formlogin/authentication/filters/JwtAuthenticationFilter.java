package com.example.module04formlogin.authentication.filters;

import com.example.module04formlogin.authentication.tokens.UsernameOtpToken;
import com.example.module04formlogin.authentication.tokens.UsernamePasswordToken;
import com.example.module04formlogin.jwt.JwtConfigurationProperties;
import com.example.module04formlogin.jwt.AuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

/*
 * UsernamePasswordAuthenticationFilter only will work at localhost:8080/login
 * */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfigurationProperties jwtConfigurationProperties;
    private final SecretKey secretKey;

    public JwtAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JwtConfigurationProperties jwtConfigurationProperties,
            SecretKey secretKey
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtConfigurationProperties = jwtConfigurationProperties;
        this.secretKey = secretKey;
    }

    /*
     * Validate Credential
     * */
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {

        try {

            AuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), AuthenticationRequest.class);

            if (authenticationRequest.getPassword() != null) {

                // authentication with username & password
                return this.authenticationManager.authenticate(
                        new UsernamePasswordToken(
                                authenticationRequest.getUsername(),
                                authenticationRequest.getPassword()
                        )
                );

            } else {
                // authentication with username & otp
                return this.authenticationManager.authenticate(
                        new UsernameOtpToken(
                                authenticationRequest.getUsername(),
                                authenticationRequest.getOtp()
                        )
                );
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*
     * Create Jwt Token and send it to client
     * */
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {

        String jwtToken = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new java.util.Date())
                .setExpiration(Date.valueOf(LocalDate.now().plusDays(this.jwtConfigurationProperties.getTokenExpirationAfterDays())))
                .signWith(this.secretKey)
                .compact();


        response.addHeader(
                jwtConfigurationProperties.getAuthorizationHeader(),
                jwtConfigurationProperties.getTokenPrefix() + jwtToken
        );
    }

}
