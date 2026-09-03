package com.example.mspassportinspectorgateway.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@RequiredArgsConstructor
public class PassportInspectorFeignConfig {

    private static final String MERCHANT_ID_HEADER = "merchantId";
    private static final String TRACE_ID_HEADER = "X-Trace-Id";
    private static final String TRACE_ID_MDC_KEY = "traceId";


    @Bean
    public RequestInterceptor passportInspectorHeadersInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof JwtAuthenticationToken jwtAuthentication) {
                String tokenValue = jwtAuthentication.getToken().getTokenValue();
                String merchantId = jwtAuthentication.getToken().getClaimAsString(MERCHANT_ID_HEADER);

                requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenValue);
                requestTemplate.header(MERCHANT_ID_HEADER, merchantId);
            }

            String traceId = MDC.get(TRACE_ID_MDC_KEY);

            if (traceId != null && !traceId.isBlank()) {
                requestTemplate.header(TRACE_ID_HEADER, traceId);
            }
        };
    }
}
