package com.soft.JwtAuthenticationService.controller;

import com.soft.JwtAuthenticationService.dto.ClientDto;
import com.soft.JwtAuthenticationService.entities.Client;
import com.soft.JwtAuthenticationService.service.ClientService;
import com.soft.JwtAuthenticationService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/authentication/client")
public class ClientController {

    @Autowired
    ClientService clientService;


    @PostMapping("/register")
    public ResponseEntity<Client> register(@RequestBody Client client){
        System.out.println("trying registration");
        return new ResponseEntity<>(clientService.addClient(client),HttpStatus.OK);
    }

}
