package com.example.tram.security.user_detail;

import com.example.tram.config.response.BadRequest;
import com.example.tram.modules.user.User;
import com.example.tram.modules.user.interface_user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<User> user = userRepository.findUserAndRoleToLogin(username);
        return user.map(UserDetailsImpl::new)
                .orElseThrow(() -> new BadRequest("User not found: " + username));
    }


}
