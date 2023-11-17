package com.example.tram.modules.user.interface_user;


import com.example.tram.modules.user.request.CreateUserRequest;
import com.example.tram.modules.user.request.LoginUserRequest;
import com.example.tram.modules.user.request.RefreshTokenRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public interface IUserController {

    @PreAuthorize("permitAll")
    @PostMapping("/register")
    ResponseEntity<?> createUser(
            @Validated
            @RequestBody CreateUserRequest createUserRequest,
            HttpServletResponse response
    );

    @PreAuthorize("permitAll")
    @PostMapping("/login")
    ResponseEntity<?> loginUser(
            @Validated
            @RequestBody LoginUserRequest loginUserRequest,
            HttpServletResponse response
    );

    @PreAuthorize("permitAll")
    @PostMapping("/refresh-token")
    ResponseEntity<?> requestRefreshToken(
            @Validated
            @RequestBody RefreshTokenRequest refreshTokenRequest
    );

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<?> testAuth();

}
