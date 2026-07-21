package com.example.mspassportinspectorgateway.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class PassportInspectorFeignConfig {

    private static final String MERCHANT_ID_HEADER = "merchantId";
    private static final String INTERNAL_TOKEN_HEADER = "x-token";

    private final PassportInspectorClientProperties properties;

    @Bean
    public RequestInterceptor passportInspectorHeadersInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(MERCHANT_ID_HEADER, properties.getMerchantId());
            requestTemplate.header(INTERNAL_TOKEN_HEADER, properties.getToken());
        };
    }
}
