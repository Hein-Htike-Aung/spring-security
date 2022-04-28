package com.example.module03authentication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class JdbcSecurityConfig {

    /*
     * JdbcUserDetailsManager can only be used when
     * Table names must be users and authorities
     * Table's field must be irreversible
     * */

    private final DataSource dataSource;

    public JdbcSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

    @Bean("jdbcUserDetailsManager")
    public UserDetailsService userDetailsService() {
        return jdbcUserDetailsManager();
    }

}
