package com.example.mspassportinspectorgateway;

import com.example.mspassportinspectorgateway.config.PassportInspectorClientProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(PassportInspectorClientProperties.class)
public class MsPassportInspectorGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPassportInspectorGatewayApplication.class, args);
    }

}
