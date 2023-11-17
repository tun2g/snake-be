package com.example.tram.modules.user;

import com.example.tram.config.redis.IRedisCacheService;
import com.example.tram.config.response.BadRequest;
import com.example.tram.config.response.ForBiden;
import com.example.tram.modules.role.Role;
import com.example.tram.modules.role.RoleRepository;
import com.example.tram.modules.user.interface_user.IUserService;
import com.example.tram.modules.user.interface_user.UserRepository;
import com.example.tram.modules.user.request.CreateUserRequest;
import com.example.tram.modules.user.request.LoginUserRequest;
import com.example.tram.modules.user.request.RefreshTokenRequest;
import com.example.tram.modules.user.response.AuthResponse;
import com.example.tram.modules.user.response.RefreshTokenResponse;
import com.example.tram.modules.user_role.UserRole;
import com.example.tram.modules.user_role.IUserRoleRepository;
import com.example.tram.security.jwt.JwtTokenManager;
import com.example.tram.security.util.ExceptionMessagesAccessor;
import com.example.tram.util.MessageConstants;
import com.example.tram.util.ProjectConstants;
import com.example.tram.util.RoleConstants;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Value("${spring.redis.key_auth}")
    private String KEY_AUTH;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IRedisCacheService redisCacheService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ExceptionMessagesAccessor exceptionMessagesAccessor;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse createNewUser(CreateUserRequest createUserRequest, HttpServletResponse response){

        if(userRepository.existsByUsername(createUserRequest.getUsername())){
            throw new BadRequest(
                    exceptionMessagesAccessor.getMessage(null,MessageConstants.USERNAME_ALREADY_EXISTS)
            );
        }

        if(userRepository.existsByEmail(createUserRequest.getEmail())){
            throw  new BadRequest(
                    exceptionMessagesAccessor.getMessage(null,MessageConstants.EMAIL_ALREADY_EXISTS)
            );
        }

        if(!createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())){
            throw new BadRequest(
                    exceptionMessagesAccessor.getMessage(null, MessageConstants.PASSWORD_NOT_MATCH)
            );
        }

        Role defaultRole = roleRepository.findByRoleName(RoleConstants.ROLE_USER);

        User user = User.builder()
                .address(createUserRequest.getAddress())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .username(createUserRequest.getUsername())
                .fullName(createUserRequest.getFullName())
                .address(createUserRequest.getAddress())
                .email(createUserRequest.getEmail())
                .imageUrl(null)
                .createdAt(null)
                .updatedAt(null)
                .deletedAt(null)
                .build();

        userRepository.saveAndFlush(user);

        UserRole userRole = userRoleRepository.save( UserRole.builder()
                .user(user)
                .role(defaultRole)
                .build());

        String accessToken = jwtTokenManager.generateToken(user.getUsername(), ProjectConstants.ACCESS_TOKEN_EXPIRED_TIME);
        String refreshToken = jwtTokenManager.generateToken(user.getUsername(),ProjectConstants.REFRESH_TOKEN_EXPIRED_TIME);

        Cookie cookie = new Cookie("refreshToken",refreshToken);

        response.addCookie(cookie);

        AuthResponse createUserResponse = AuthResponse.builder()
                .address(createUserRequest.getAddress())
                .email(createUserRequest.getEmail())
                .imgUrl(null)
                .fullName(createUserRequest.getFullName())
                .username(createUserRequest.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return createUserResponse;
    }


    @Override
    public AuthResponse loginUser(LoginUserRequest loginUserRequest,HttpServletResponse response){
        Optional<User> user= userRepository.findUserAndRoleToLogin(loginUserRequest.getUsername());

        if(user.isEmpty()){
            throw new BadRequest(
                    exceptionMessagesAccessor.getMessage(null,MessageConstants.LOGIN_FAILED)
            );
        }

        if(!passwordEncoder.matches(loginUserRequest.getPassword(),user.get().getPassword())){
            throw new BadRequest(
                    exceptionMessagesAccessor.getMessage(null,MessageConstants.LOGIN_FAILED)
            );
        }

        String accessToken = jwtTokenManager.generateToken(user.get().getUsername(), ProjectConstants.ACCESS_TOKEN_EXPIRED_TIME);
        String refreshToken = jwtTokenManager.generateToken(user.get().getUsername(),ProjectConstants.REFRESH_TOKEN_EXPIRED_TIME);

        Cookie cookie = new Cookie("refreshToken",refreshToken);

        response.addCookie(cookie);

        AuthResponse loginResponse = AuthResponse.builder()
                .address(user.get().getAddress())
                .email(user.get().getEmail())
                .imgUrl(user.get().getImageUrl())
                .fullName(user.get().getFullName())
                .username(user.get().getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return loginResponse;

    }

    @Override
    public RefreshTokenResponse requestRefreshToken(RefreshTokenRequest refreshTokenRequest){

        String username = jwtTokenManager.extractSubject(refreshTokenRequest.getRefreshToken());

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (!jwtTokenManager.verifyToken(refreshTokenRequest.getRefreshToken(), userDetails)) {
            throw new ForBiden("");
        }
        String keyRedis = KEY_AUTH + ":" + refreshTokenRequest.getRefreshToken();
        redisCacheService.del(keyRedis);

        String accessToken = jwtTokenManager.generateToken(username,ProjectConstants.ACCESS_TOKEN_EXPIRED_TIME);
        String refreshToken = jwtTokenManager.generateToken(username,ProjectConstants.REFRESH_TOKEN_EXPIRED_TIME);

        RefreshTokenResponse refreshTokenResponse = RefreshTokenResponse.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();

        return refreshTokenResponse;
    }
}
