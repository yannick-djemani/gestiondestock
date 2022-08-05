package com.afridevteam.gestionstock.dto.auth;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
public class AuthenticationRequest {
    @NotEmpty
    @NotBlank
    private String login;

    @NotEmpty
    private String password;
}
