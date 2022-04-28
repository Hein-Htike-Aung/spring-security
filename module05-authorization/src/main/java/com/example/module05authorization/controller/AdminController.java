package com.example.module05authorization.controller;

import com.example.module05authorization.security.AppUserPermission;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('admin:read')")
    public String getMethod() {
        return "Admin Get Method";
    }

    @PostMapping("/post")
    @PreAuthorize("hasAuthority('admin:write')")
    public String postMethod() {
        return "Admin Post Method";
    }

    @PutMapping("/put")
    @PreAuthorize("hasAuthority('admin:write')")
    public String putMethod() {
        return "Admin Put Method";
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin:write')")
    public String deleteMethod() {
        return "Admin Delete Method";
    }
}
