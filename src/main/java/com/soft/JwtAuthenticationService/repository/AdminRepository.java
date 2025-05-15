package com.soft.JwtAuthenticationService.repository;

import com.soft.JwtAuthenticationService.entities.Admin;
import com.soft.JwtAuthenticationService.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    List<Admin> findByFirstName(String firstName);

    List<Admin> findByLastName(String lastName);

    Admin findByEmail(String email);
}
