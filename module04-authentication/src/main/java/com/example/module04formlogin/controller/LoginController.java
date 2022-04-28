package com.example.module04formlogin.controller;

import com.example.module04formlogin.exceptions.IncorrectPasswordException;
import com.example.module04formlogin.exceptions.InvalidUserAccountException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.CredentialNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(
            HttpServletRequest request,
            Model model
    ) {

        Exception exception = (Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

        if (exception instanceof UsernameNotFoundException) {

            model.addAttribute("invalidUsername", exception.getMessage());
        } else if (exception instanceof IncorrectPasswordException) {

            model.addAttribute("invalidPassword", exception.getMessage());
        } else if (exception instanceof InvalidUserAccountException) {

            model.addAttribute("invalidAccount", exception.getMessage());
        } else if (exception instanceof BadCredentialsException) {

            model.addAttribute("invalid", exception.getMessage());
        }

        return "/login";
    }

    @GetMapping("/login/google")
    public ModelAndView loginWithGoogle(
            Authentication authentication
    ){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        return new ModelAndView("/home", "user", oAuth2User.getAttribute("given_name"));
    }
}
