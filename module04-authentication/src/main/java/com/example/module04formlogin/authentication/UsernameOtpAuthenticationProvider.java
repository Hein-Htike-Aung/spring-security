package com.example.module04formlogin.authentication;

import com.example.module04formlogin.authentication.tokens.UsernameOtpToken;
import com.example.module04formlogin.authentication.tokens.UsernamePasswordToken;
import com.example.module04formlogin.entity.AppUser;
import com.example.module04formlogin.repository.AppUserDao;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsernameOtpAuthenticationProvider implements AuthenticationProvider {

    private final AppUserDao appUserDao;

    public UsernameOtpAuthenticationProvider(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String otp = authentication.getCredentials().toString();

        AppUser user = this.appUserDao.findFirstByOtp(Integer.parseInt(otp)).orElse(null);

        if(user != null){

            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getOtp(), user.getAuthorities());


        }else {
            throw new BadCredentialsException("Opt doesn't match");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {

        return UsernameOtpToken.class.isAssignableFrom(authentication);
    }
}
