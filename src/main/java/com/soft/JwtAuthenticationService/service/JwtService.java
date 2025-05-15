package com.soft.JwtAuthenticationService.service;

import java.security.Key;
import java.util.List;
import java.util.Map;

public interface JwtService {
    void validateToken(String token);

    String generateToken(String userName, List<String> roles);

    String createToken(Map<String, Object> claims, String userName);

    Key getSignKey();

    String extractUserName(String token);

    boolean isTokenExpired(String token);
}
