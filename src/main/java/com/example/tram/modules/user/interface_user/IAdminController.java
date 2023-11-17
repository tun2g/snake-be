package com.example.tram.modules.user.interface_user;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public interface IAdminController {

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> testAuth();
}
