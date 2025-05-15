package com.soft.JwtAuthenticationService.serviceImplement;

import com.soft.JwtAuthenticationService.entities.Client;
import com.soft.JwtAuthenticationService.entities.Role;
import com.soft.JwtAuthenticationService.repository.ClientRepository;
import com.soft.JwtAuthenticationService.repository.RoleRepository;
import com.soft.JwtAuthenticationService.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Client addClient(Client client) {
        System.out.println("starting registration");
        Role role = roleRepository.findByName("CLIENT");
        List<Role> roles = new ArrayList<>();
        if(role != null){
            System.out.println("role to assign: "+role.getName());
            roles.add(role);
        }
        else
            throw new RuntimeException("unable to assign role");

        client.setRoles(roles);
        client.setLastConnexion(new Date());
        client.setOrderNumber(0);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }
}
