package com.soft.JwtAuthenticationService.controller;

import com.soft.JwtAuthenticationService.dto.ClientDto;
import com.soft.JwtAuthenticationService.entities.Role;
import com.soft.JwtAuthenticationService.entities.User;
import com.soft.JwtAuthenticationService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/authentication")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    UserService userService;

    @PostMapping("/generate")
    public Map<String, String> generateToken(@RequestBody ClientDto clientDto){
        //authentifier l'utilisateur pris en paramètre grace a authentication manager
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(clientDto.getUserName(), clientDto.getPassword()));
        if(authentication.isAuthenticated()){
            //generer la token grace au service et retourner
            Map<String, String> object = new HashMap<>();
            User user = userService.getUserByUserName(clientDto.getUserName());
            List<String> roles = new ArrayList<>();
            for(Role r : user.getRoles()){
                roles.add(r.getName());
            }
            object.put("token", userService.generateToken(clientDto.getUserName(), roles));
            return object;
        }else {
            throw new RuntimeException("username or password is incorrect");
        }
    }

    @GetMapping("/user")
    public User getUserByToken(@RequestParam("token") String token){
        return userService.getUserByToken(token);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam(name = "token") String token){
        userService.validateToken(token);
        return "this token is valide";
    }
}
