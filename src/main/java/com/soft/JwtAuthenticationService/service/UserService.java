package com.soft.JwtAuthenticationService.service;

import com.soft.JwtAuthenticationService.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {

    public String generateToken(String userName, List<String> roles);

    public void validateToken(String token);

    public User getUserByToken(String token);

    public User getUserByUserName(String userName);
}
