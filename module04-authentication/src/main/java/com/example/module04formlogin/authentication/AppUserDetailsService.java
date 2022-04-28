package com.example.module04formlogin.authentication;

import com.example.module04formlogin.repository.AppUserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserDao appUserDao;

    public AppUserDetailsService(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.appUserDao.findFirstByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("username %s not found!", username)));
    }
}
