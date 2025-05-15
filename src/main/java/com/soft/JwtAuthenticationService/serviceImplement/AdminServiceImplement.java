package com.soft.JwtAuthenticationService.serviceImplement;

import com.netflix.discovery.converters.Auto;
import com.soft.JwtAuthenticationService.entities.Admin;
import com.soft.JwtAuthenticationService.entities.Role;
import com.soft.JwtAuthenticationService.repository.AdminRepository;
import com.soft.JwtAuthenticationService.repository.RoleRepository;
import com.soft.JwtAuthenticationService.service.AdminService;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImplement implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Admin addAdmin(Admin admin) {
        Role role = roleRepository.findByName("ADMIN");
        List<Role> roles = new ArrayList<>();
        if(role != null){
            roles.add(role);
        }
        else
            throw new RuntimeException("unable to assign role");

        admin.setRoles(roles);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setActive(false);
        admin.setProfile(null);
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(int id) {
        return adminRepository.findById(id).get();
    }

    @Override
    public Admin getPartAdminById(int id) {
        Admin admin = adminRepository.findById(id).get();
        if(admin== null){
            throw new IllegalArgumentException("l'utilisateur n'existe pas ");
        }
        Admin admin1 = new Admin();
        admin1.setProfile(admin.getProfile());
        admin1.setPhone(admin.getPhone());
        admin1.setFirstName(admin.getFirstName());
        admin1.setLastName(admin.getLastName());
        admin1.setEmail(admin.getEmail());
        return admin1;
    }

    @Override
    public Admin update(Admin admin) {
        Admin admin1 = adminRepository.findByEmail(admin.getEmail());
        if(admin1== null){
            throw new IllegalArgumentException("l'utilisateur n'existe pas ");
        }
        Admin adminToUpdate  = adminRepository.findById(admin1.getId()).get();
        if(adminToUpdate == null){
            throw new IllegalArgumentException("l'utilisateur n'existe pas ");
        }
        adminToUpdate.setFirstName(admin.getFirstName());
        adminToUpdate.setLastName(admin.getLastName());
        adminToUpdate.setBirthDate(admin.getBirthDate());
        adminToUpdate.setPhone(admin.getPhone());
        if(admin.getProfile()!=null)
          adminToUpdate.setProfile(admin.getProfile());
        System.out.println(admin.getProfile());
        return adminRepository.save(adminToUpdate);
    }

    @Override
    public Admin getByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public Admin resetPassword(String email, String password) {
        Admin admin1 = adminRepository.findByEmail(email);
        if(admin1== null){
            throw new IllegalArgumentException("l'utilisateur n'existe pas ");
        }
        Admin adminToUpdate  = adminRepository.findById(admin1.getId()).get();
        if(adminToUpdate == null){
            throw new IllegalArgumentException("l'utilisateur n'existe pas ");
        }
        adminToUpdate.setPassword(passwordEncoder.encode(password));
        return adminRepository.save(adminToUpdate);
    }

    @Override
    public boolean activeAdminAccount(int id) {
        Admin admin = adminRepository.findById(id).get();
        admin.setActive(true);
        adminRepository.save(admin);
        return true;
    }
}
