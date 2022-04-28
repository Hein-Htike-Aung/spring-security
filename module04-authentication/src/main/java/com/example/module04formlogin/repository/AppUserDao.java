package com.example.module04formlogin.repository;

import com.example.module04formlogin.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserDao extends JpaRepository<AppUser, Integer> {

    @Query("select u from AppUser u where u.username=?1")
    Optional<AppUser> findFirstByUsername(String username);

    @Query("select u from AppUser u where u.otp=?1")
    Optional<AppUser> findFirstByOtp(Integer opt);
}
