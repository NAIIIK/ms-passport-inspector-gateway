package com.example.mspassportinspectorgateway.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@RequiredArgsConstructor
public class PassportInspectorFeignConfig {

    private static final String MERCHANT_ID_HEADER = "merchantId";

    @Bean
    public RequestInterceptor passportInspectorHeadersInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof JwtAuthenticationToken jwtAuthentication) {
                String tokenValue = jwtAuthentication.getToken().getTokenValue();
                String merchantId = jwtAuthentication.getToken().getClaimAsString("merchantId");

                requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenValue);
                requestTemplate.header(MERCHANT_ID_HEADER, merchantId);
            }
        };
    }
}
