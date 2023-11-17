package com.example.tram.modules.user.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotEmpty(message = "username is required")
    private String username;

    @NotEmpty(message = "password is required")
    private String password;

    @NotEmpty(message = "confirmPassword is required")
    private String confirmPassword;

    @NotEmpty(message = "email is required")
    private String email;

    @NotEmpty(message = "address is required")
    private String address;

    @NotEmpty(message = "fullName is required")
    private String fullName;

}
