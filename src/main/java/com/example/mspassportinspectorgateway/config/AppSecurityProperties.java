package com.example.mspassportinspectorgateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.security")
public class AppSecurityProperties {

    private Jwt jwt = new Jwt();
    private List<ApiUser> apiUsers = new ArrayList<>();

    @Getter
    @Setter
    public static class Jwt {
        private String issuer;
        private String secret;
        private long accessTokenValidityMinutes;
    }

    @Getter
    @Setter
    public static class ApiUser {
        private String username;
        private String password;
        private UUID merchantId;
        private List<String> roles = new ArrayList<>();
    }
}
