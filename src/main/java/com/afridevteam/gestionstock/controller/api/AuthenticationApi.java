package com.afridevteam.gestionstock.controller.api;


import com.afridevteam.gestionstock.dto.auth.AuthenticationRequest;
import com.afridevteam.gestionstock.dto.auth.AuthenticationResponse;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static com.afridevteam.gestionstock.utils.Constants.AUTHENTICATION_ENDPOINT;

@Api("Authentication")
public interface AuthenticationApi {
    @PostMapping(AUTHENTICATION_ENDPOINT)
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest);
}
