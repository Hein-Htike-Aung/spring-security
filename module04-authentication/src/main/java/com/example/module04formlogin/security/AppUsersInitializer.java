package com.example.module04formlogin.security;

import com.example.module04formlogin.entity.AppUser;
import com.example.module04formlogin.entity.AppUserAuthority;
import com.example.module04formlogin.repository.AppUserDao;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Component
public class AppUsersInitializer {

    private final AppUserDao appUserDao;
    private final PasswordEncoder passwordEncoder;

    public AppUsersInitializer(AppUserDao appUserDao, PasswordEncoder passwordEncoder) {
        this.appUserDao = appUserDao;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void init(){

        AppUser user1 = new AppUser();
        user1.setUsername("karina");
        user1.setPassword(this.passwordEncoder.encode("password"));
        AppUserAuthority appUserAuthority = new AppUserAuthority();
        appUserAuthority.setAuthority("ROLE_ADMIN");
        user1.addAuthority(appUserAuthority);
        user1.setEnabled(true);
        user1.setAccountNonExpired(true);
        user1.setAccountNonLocked(true);
        user1.setCredentialsNonExpired(true);

        AppUser user2 = new AppUser();
        user2.setUsername("lucifer");
        user2.setPassword(this.passwordEncoder.encode("password"));
        AppUserAuthority appUserAuthority2 = new AppUserAuthority();
        appUserAuthority2.setAuthority("ROLE_ADMIN");
        user2.addAuthority(appUserAuthority2);
        user2.setEnabled(true);
        user2.setAccountNonExpired(true);
        user2.setAccountNonLocked(true);
        user2.setCredentialsNonExpired(true);

        AppUser user3 = new AppUser();
        user3.setUsername("tom");
        user3.setPassword(this.passwordEncoder.encode("password"));
        AppUserAuthority appUserAuthority3 = new AppUserAuthority();
        appUserAuthority3.setAuthority("ROLE_ADMIN");
        user3.addAuthority(appUserAuthority3);
        user3.setEnabled(false);
        user3.setAccountNonExpired(true);
        user3.setAccountNonLocked(true);
        user3.setCredentialsNonExpired(true);

        this.appUserDao.save(user1);
        this.appUserDao.save(user2);
        this.appUserDao.save(user3);
    }
}
