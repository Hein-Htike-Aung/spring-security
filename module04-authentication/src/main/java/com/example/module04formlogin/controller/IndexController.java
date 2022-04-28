package com.example.module04formlogin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "/index";
    }

    @GetMapping("/home")
    public ModelAndView loginSuccessfulUrl(
            Authentication authentication
    ) {

        return new ModelAndView("/home", "user", authentication.getName());
    }

}
