package com.example.module05authorization.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/manager")
public class ManagerController {

    @GetMapping("/get")
    @Secured({"ROLE_ADMIN", "ROLE_ADMIN_TRAINEE", "ROLE_MANAGER", "ROLE_MANAGER_TRAINEE"})
    public String getMethod() {
        return "Manager Get Method";
    }

    @PostMapping("/post")
    @Secured({"ROLE_ADMIN", "ROLE_ADMIN_TRAINEE", "ROLE_MANAGER", "ROLE_MANAGER_TRAINEE"})
    public String postMethod() {
        return "Manager Post Method";
    }

    @PutMapping("/put")
    @Secured({"ROLE_ADMIN", "ROLE_ADMIN_TRAINEE", "ROLE_MANAGER", "ROLE_MANAGER_TRAINEE"})
    public String putMethod() {
        return "Manager Put Method";
    }

    @DeleteMapping("/delete")
    @Secured({"ROLE_ADMIN", "ROLE_ADMIN_TRAINEE", "ROLE_MANAGER", "ROLE_MANAGER_TRAINEE"})
    public String deleteMethod() {
        return "Manager Delete Method";
    }
}
