package com.example.mspassportinspectorgateway.service;

import com.example.mspassportinspectorgateway.config.AppSecurityProperties;
import com.example.mspassportinspectorgateway.model.auth.LoginRequestDto;
import com.example.mspassportinspectorgateway.model.auth.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AppSecurityProperties properties;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    public TokenResponseDto login(LoginRequestDto request) {
        AppSecurityProperties.ApiUser user = properties
                .getApiUsers()
                .stream()
                .filter(candidate -> candidate.getUsername().equals(request.getUsername()))
                .findFirst()
                .orElseThrow(() -> new BadCredentialsException("Invalid username"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String accessToken = jwtTokenService.generateAccessToken(user);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .expiresIn(properties.getJwt()
                        .getAccessTokenValidityMinutes() * 60)
                .merchantId(user.getMerchantId())
                .roles(user.getRoles())
                .build();
    }
}
