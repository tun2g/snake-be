package com.example.tram.modules.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String username;
    private String fullName;
    private String imgUrl;
    private String email;
    private String address;
    private String accessToken;
    private String refreshToken;
}
