package com.soft.JwtAuthenticationService.repository;

import com.soft.JwtAuthenticationService.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByName(String name);
}
