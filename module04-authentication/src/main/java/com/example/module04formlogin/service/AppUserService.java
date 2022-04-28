package com.example.module04formlogin.service;

import com.example.module04formlogin.entity.AppUser;
import com.example.module04formlogin.repository.AppUserDao;
import com.example.module04formlogin.util.OptGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    private final Logger logger = LoggerFactory.getLogger(AppUserService.class);

    private final AppUserDao appUserDao;

    public AppUserService(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }


    public void renewOpt(String username) {

        AppUser user = this.appUserDao.findFirstByUsername(username).orElse(null);
        int code = OptGenerator.generateOpt();
        if (user != null) {

            if (user.getOtp() == null) {
                // new
                user.setOtp(code);
                this.appUserDao.save(user);
            } else {

                // renew
                user.setOtp(code);
            }

            logger.info(String.format("Otp code -> %s", code));
        }

    }
}
