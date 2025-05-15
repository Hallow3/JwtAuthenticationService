package com.soft.JwtAuthenticationService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
public class Admin extends User{

    @Column(name="PROFILE", nullable = true)
    private String profile;

    @Column(name="ISACTIVE", nullable = false)
    private boolean isActive;

}
