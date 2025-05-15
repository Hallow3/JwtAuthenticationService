package com.soft.JwtAuthenticationService.serviceImplement;

import com.soft.JwtAuthenticationService.entities.Admin;
import com.soft.JwtAuthenticationService.entities.Role;
import com.soft.JwtAuthenticationService.entities.User;
import com.soft.JwtAuthenticationService.repository.UserRepository;
import com.soft.JwtAuthenticationService.service.JwtService;
import com.soft.JwtAuthenticationService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),  mapRolesToAuthorities(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }



    @Override
    public String generateToken(String userName, List<String> roles) {
        return jwtService.generateToken(userName, roles);
    }

    @Override
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    @Override
    public User getUserByToken(String token) {
        String email = jwtService.extractUserName(token);
        User user = userRepository.findByEmail(email);
        if(user != null){
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setId(user.getId());
            admin.setLastConnexion(user.getLastConnexion());
            admin.setLastName(user.getLastName());
            admin.setFirstName(user.getFirstName());
            admin.setRoles(user.getRoles());
            return admin;
        }
        return null;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByEmail(userName);
    }

}
