package com.example.tram.modules.user;

import com.example.tram.config.response.ForBiden;
import com.example.tram.config.response.Response;
import com.example.tram.modules.user.interface_user.IUserController;
import com.example.tram.modules.user.interface_user.IUserService;
import com.example.tram.modules.user.request.CreateUserRequest;
import com.example.tram.modules.user.request.LoginUserRequest;
import com.example.tram.modules.user.request.RefreshTokenRequest;
import com.example.tram.modules.user.response.AuthResponse;
import com.example.tram.modules.user.response.RefreshTokenResponse;
import com.example.tram.security.util.GeneralMessagesAccessor;
import com.example.tram.util.MessageConstants;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserControllerImpl implements IUserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private GeneralMessagesAccessor generalMessagesAccessor;

    @Override
    public ResponseEntity<?> createUser(
            CreateUserRequest createUserRequest,
            HttpServletResponse response
    ){
        AuthResponse createUserResponse = userService.createNewUser(createUserRequest,response);

        return Response.CreatedResponse(
                createUserResponse,
                generalMessagesAccessor
                        .getMessage(null, MessageConstants.REGISTRATION_SUCCESSFUL, createUserResponse.getUsername())
        );
    }

    @Override
    public ResponseEntity<?> loginUser(
            LoginUserRequest loginUserRequest,
            HttpServletResponse response
    ){

        AuthResponse authResponse = userService.loginUser(loginUserRequest,response);

        return Response.SuccessResponse(
                authResponse,
                generalMessagesAccessor
                        .getMessage(null,MessageConstants.LOGIN_SUCCESSFUL,authResponse.getUsername())
        );

    }

    @Override
    public ResponseEntity<?> requestRefreshToken(
            RefreshTokenRequest refreshTokenRequest
    ){
        try{
            RefreshTokenResponse refreshTokenResponse = userService.requestRefreshToken(refreshTokenRequest);

            return Response.SuccessResponse(
                    refreshTokenResponse,
                    generalMessagesAccessor
                            .getMessage(null,MessageConstants.REQUEST_TOKEN_SUCCESSFUL)
            );
        }
        catch (Exception error){
            throw new ForBiden("");
        }
    }

    @Override
    public ResponseEntity<?> testAuth(){
        return Response.SuccessResponse(null,"Oke");
    }
}
