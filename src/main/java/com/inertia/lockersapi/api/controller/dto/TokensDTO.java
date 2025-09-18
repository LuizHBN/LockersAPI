package com.inertia.lockersapi.api.controller.dto;



import java.time.ZonedDateTime;


public record TokensDTO(String accessToken,
                        String refreshToken,
                        ZonedDateTime expiration,
                        ZonedDateTime refreshExpiration,
                        String userId) {
}
