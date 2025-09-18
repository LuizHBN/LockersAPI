package com.inertia.lockersapi.api.controller;

import com.inertia.lockersapi.api.controller.dto.RefreshTokenDTO;
import com.inertia.lockersapi.api.controller.dto.TokensDTO;
import com.inertia.lockersapi.api.controller.dto.request.DadosLoginDTO;
import com.inertia.lockersapi.api.infra.security.TokenService;
import com.inertia.lockersapi.domain.user.User;
import com.inertia.lockersapi.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
public class AuthController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;


    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @PostMapping("/api/login")
    public ResponseEntity<TokensDTO> login(@Valid @RequestBody DadosLoginDTO dadosLoginDTO){
        var autenticationToker = new UsernamePasswordAuthenticationToken(dadosLoginDTO.email(), dadosLoginDTO.password());
        var authentication = authenticationManager.authenticate(autenticationToker);

        String accessToken = tokenService.createToken((User)authentication.getPrincipal());
        String refreshToken = tokenService.createRefreshToken((User)authentication.getPrincipal());

        ZonedDateTime expiration = tokenService.getExpirationDateTime(accessToken);
        ZonedDateTime refreshExpiration = tokenService.getExpirationDateTime(refreshToken);
        String userId = ((User) authentication.getPrincipal()).getId().toString();

        return  ResponseEntity.ok(new TokensDTO(accessToken, refreshToken, expiration, refreshExpiration, userId));
    }
    @PostMapping("/api/refresh-token")
    public ResponseEntity<TokensDTO> refreshToken(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO){
        var refreshToken = refreshTokenDTO.refreshToken();
        UUID userId = UUID.fromString(tokenService.verifyToken(refreshToken));
        User user = userRepository.findById(userId).orElseThrow();

        String accessToken = tokenService.createToken(user);
        String newRefreshToken = tokenService.createRefreshToken(user);
        ZonedDateTime expirationDateTime = tokenService.getExpirationDateTime(accessToken);
        ZonedDateTime refreshExpiration = tokenService.getExpirationDateTime(newRefreshToken);

        return  ResponseEntity.ok(new TokensDTO(accessToken, newRefreshToken, expirationDateTime, refreshExpiration, userId.toString()));

    }

}
