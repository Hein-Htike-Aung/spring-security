package com.example.module02jwt.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.module02jwt.security.AppUserRole.*;

@Repository("fake")
public class FakeAppUserDaoService implements AppUserDao {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<AppUser> selectApplicationUserByUsername(String username) {

        return getAppUsers().stream()
                .filter(appUser -> appUser.getUsername().equals(username))
                .findFirst();
    }

    private List<AppUser> getAppUsers() {
        List<AppUser> appUsers = Lists.newArrayList(   // Guava
                new AppUser(
                        ADMIN.getGrantedAuthorities(),
                        "karina",
                        this.passwordEncoder.encode("password"),
                        true,
                        true,
                        true,
                        true
                ),
                new AppUser(
                        STUDENT.getGrantedAuthorities(),
                        "winter",
                        this.passwordEncoder.encode("password"),
                        true,
                        true,
                        true,
                        true
                ),
                new AppUser(
                        ADMINTRAINEE.getGrantedAuthorities(),
                        "tom",
                        this.passwordEncoder.encode("password"),
                        true,
                        true,
                        true,
                        true
                )
        );

        return appUsers;
    }

}
