package com.afridevteam.gestionstock.controller;

import com.afridevteam.gestionstock.controller.api.AuthenticationApi;
import com.afridevteam.gestionstock.domain.auth.ExtendedUser;
import com.afridevteam.gestionstock.dto.auth.AuthenticationRequest;
import com.afridevteam.gestionstock.dto.auth.AuthenticationResponse;
import com.afridevteam.gestionstock.service.auth.ApplicationUserDetailsService;
import com.afridevteam.gestionstock.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements AuthenticationApi {
    private final AuthenticationManager authenticationManager;
    private final ApplicationUserDetailsService applicationUserDetailsService;
    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, ApplicationUserDetailsService applicationUserDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.applicationUserDetailsService = applicationUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(), authenticationRequest.getPassword())
        );
        final UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(authenticationRequest.getLogin());
        final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);
        return ResponseEntity.ok(AuthenticationResponse.builder().acccessToken(jwt).build());
    }
}
