package com.example.tram.modules.user.interface_user;

import com.example.tram.modules.user.request.CreateUserRequest;
import com.example.tram.modules.user.request.LoginUserRequest;
import com.example.tram.modules.user.request.RefreshTokenRequest;
import com.example.tram.modules.user.response.AuthResponse;
import com.example.tram.modules.user.response.RefreshTokenResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface IUserService {
    AuthResponse createNewUser(CreateUserRequest createUserRequest, HttpServletResponse response);

    AuthResponse loginUser(LoginUserRequest loginUserRequest, HttpServletResponse response);

    RefreshTokenResponse requestRefreshToken(RefreshTokenRequest refreshTokenRequest);
}
