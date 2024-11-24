package com.csm.service;

import com.csm.exception.BadRequestException;
import com.csm.model.*;
import com.csm.model.request.RegisterRequest;
import com.csm.model.request.UserCredentials;
import com.csm.model.response.OAuth2TokenResponse;
import com.csm.repository.UserRepository;
import com.csm.repository.entity.UserEntity;
import com.csm.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService customUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public OAuth2TokenResponse authenticate(UserCredentials userCredentials) {
        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword())
        );

        // Load user details
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userCredentials.getUsername());

        // Generate JWT token
        String accessToken = JwtUtil.generateToken(userDetails);
        return new OAuth2TokenResponse(
                accessToken,
                "Bearer",
                JwtUtil.TOKEN_EXPIRE_TIME / 1000,
                null
        );
    }

    public void register(RegisterRequest registerRequest) throws Exception {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(registerRequest.getUsername());
        if (optionalUserEntity.isPresent()) {
            throw new BadRequestException(ResponseStatusEnum.USERNAME_ALREADY_EXISTS);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setFullname(registerRequest.getFullname());
        userEntity.setPhone(registerRequest.getPhone());
        userEntity.setRole(UserRoleEnum.ROLE_USER);
        userEntity.setAddress(registerRequest.getAddress());
        userRepository.save(userEntity);
    }
}
