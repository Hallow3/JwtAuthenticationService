package com.soft.JwtAuthenticationService.repository;

import com.soft.JwtAuthenticationService.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByFirstName(String firstName);

    List<Client> findByLastName(String lastName);

    Client findByEmail(String email);
}
