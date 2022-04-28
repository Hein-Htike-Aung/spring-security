package com.example.module02jwt.jwt;

import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.crypto.SecretKey;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.jwt") // @ConfigurationPropertiesScan in Main Class
public class JwtConfig {

    private String secretKey;

    private String tokenPrefix;

    private Integer tokenExpirationAfterDays;

    public JwtConfig() {
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
