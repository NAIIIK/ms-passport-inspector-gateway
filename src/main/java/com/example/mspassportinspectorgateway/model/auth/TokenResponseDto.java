package com.example.mspassportinspectorgateway.model.auth;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class TokenResponseDto {
    private String accessToken;
    private String tokenType;
    private long expiresIn;
    private UUID merchantId;
    private List<String> roles;
}
