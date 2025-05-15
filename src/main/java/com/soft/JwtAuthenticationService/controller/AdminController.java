package com.soft.JwtAuthenticationService.controller;

import com.soft.JwtAuthenticationService.entities.Admin;
import com.soft.JwtAuthenticationService.entities.Client;
import com.soft.JwtAuthenticationService.service.AdminService;
import com.soft.JwtAuthenticationService.service.ClientService;
import com.soft.JwtAuthenticationService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/authentication/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<Admin> register(@RequestBody Admin admin){
        return new ResponseEntity<>(adminService.addAdmin(admin), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Admin> findAdminById(@PathVariable(name = "id") int adminId){
        return new ResponseEntity<>(adminService.getAdminById(adminId), HttpStatus.OK);
    }

    @GetMapping("/profil")
    ResponseEntity<Admin> findAdminByEmail(@RequestParam(name = "email") String adminEmail){
        try{
            return new ResponseEntity<>(adminService.getByEmail(adminEmail), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(adminService.getByEmail(adminEmail), HttpStatus.valueOf("erreur monsieur"));
        }
    }

    @GetMapping("/profil/{id}")
    ResponseEntity<Admin> findPartAdminById(@PathVariable(name = "id") int adminId){
            return new ResponseEntity<>(adminService.getPartAdminById(adminId), HttpStatus.OK);
    }

    @PostMapping("/profil")
    ResponseEntity<Admin> updateAdminProfil(@RequestBody Admin admin){
        return new ResponseEntity<>(adminService.update(admin), HttpStatus.OK);
    }

    @PutMapping("/profil")
    ResponseEntity<Admin> updateAdminPassword(@RequestBody Admin admin){
        return new ResponseEntity<>(adminService.resetPassword(admin.getEmail(),admin.getPassword()), HttpStatus.OK);
    }

}
