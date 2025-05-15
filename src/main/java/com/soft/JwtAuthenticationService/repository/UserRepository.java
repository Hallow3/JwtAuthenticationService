package com.soft.JwtAuthenticationService.repository;

import com.soft.JwtAuthenticationService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String username);
}
