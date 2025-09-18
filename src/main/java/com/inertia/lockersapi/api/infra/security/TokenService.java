package com.inertia.lockersapi.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.inertia.lockersapi.domain.user.User;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;

@Service
public class TokenService {
    private static final String SECRET = "12345678";
    private static final String ISSUER = "InertiaLocker";

    public String createToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(expires(30))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new JWTCreationException("Not possible to create JWT", exception);
        }
    }

    public String createRefreshToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getId().toString())
                    .withExpiresAt(expires(120))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new JWTCreationException("Not possible to create JWT", exception);
        }
    }
    public String verifyToken(String token) {
        DecodedJWT decodedJWT;

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Not possible to verify token", exception);
        }
    }

    public ZonedDateTime getExpirationDateTime (String token) {
        try {
            DecodedJWT decodedJWT = decode(token);

            Date expirationDate = decodedJWT.getExpiresAt();
            Instant expirationInstant = expirationDate.toInstant();

            return expirationInstant.atZone(ZoneId.of("America/Sao_Paulo"));
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Not possible to verify token", exception);
        }
    }

    private Instant expires(Integer minutes) {
        return LocalDateTime.now().plusMinutes(minutes).toInstant(ZoneOffset.of("-03:00"));
    }


    private DecodedJWT decode (String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token);
    }

}
