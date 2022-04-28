package com.example.module05authorization.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/test")
public class SameApiNameController {

    @PostMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_ADMIN_TRAINEE"})
    public String postMethod() {
        return "Post Method";
    }

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_ADMIN_TRAINEE"})
    public String getMethod() {
        return "Get Method";
    }
}
