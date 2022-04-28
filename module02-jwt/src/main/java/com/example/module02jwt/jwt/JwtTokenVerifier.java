package com.example.module02jwt.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtTokenVerifier(JwtConfig jwtConfig, SecretKey secretKey) {
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    /*
     * Verify Client's JWT Token
     * */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(this.jwtConfig.getAuthorizationHeader());

        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(this.jwtConfig.getTokenPrefix())) {

            // Rejected
            filterChain.doFilter(request, response);
            return;
        }

        // remove Bearer and Get Actual token
        String token = authorizationHeader.replace(this.jwtConfig.getTokenPrefix(), "");
        try {

            // Get jwt Decoded token (HEADER, PAYLOAD, VERIFY SIGNATURE)
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(this.secretKey)
                    .build()
                    .parseClaimsJws(token);

            // Get Body (PAYLOAD:DATA)
            Claims body = claimsJws.getBody();

            // get username from Body
            String username = body.getSubject();

            // get Authorities from Body
            var authorities = (List<Map<String, String>>) body.get("authorities");

            // Change Authorities Format into Set<SimpleGrantedAuthority>
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());

            // create authentication using username and simpleGrantedAuthorities
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities
            );

            // Set Authentication into SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            // Token must be modified, invalid or expired
            throw new IllegalStateException(String.format("Token %s cannot be truest", token));
        }

        filterChain.doFilter(request, response);
    }
}
