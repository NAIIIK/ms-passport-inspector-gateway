package com.example.mspassportinspectorgateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "passport-inspector.client")
public class PassportInspectorClientProperties {

    private String url;
    private String merchantId;
    private String token;
}
