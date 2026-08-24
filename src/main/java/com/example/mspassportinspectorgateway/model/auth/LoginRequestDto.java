package com.example.mspassportinspectorgateway.model.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
