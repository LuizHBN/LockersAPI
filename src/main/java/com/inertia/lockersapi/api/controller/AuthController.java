package com.inertia.lockersapi.api.controller;

import com.inertia.lockersapi.api.controller.dto.request.DadosLoginDTO;
import com.inertia.lockersapi.api.infra.security.TokenService;
import com.inertia.lockersapi.domain.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody DadosLoginDTO dadosLoginDTO){
        var autenticationToker = new UsernamePasswordAuthenticationToken(dadosLoginDTO.email(), dadosLoginDTO.password());
        var authentication = authenticationManager.authenticate(autenticationToker);
        String accessToken = tokenService.createToken((User)authentication.getPrincipal());
        return ResponseEntity.ok(accessToken);
    }
}
