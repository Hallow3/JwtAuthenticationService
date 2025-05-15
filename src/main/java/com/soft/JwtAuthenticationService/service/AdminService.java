package com.soft.JwtAuthenticationService.service;

import com.soft.JwtAuthenticationService.entities.Admin;

public interface AdminService {

    public Admin addAdmin(Admin admin);

    public Admin getAdminById(int id);
    public Admin getPartAdminById(int id);

    public Admin update(Admin admin);

    public Admin getByEmail(String email);

    public Admin resetPassword(String email, String password);

    public boolean activeAdminAccount(int id);
}
