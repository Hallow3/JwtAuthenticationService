package com.soft.JwtAuthenticationService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @Column(name = "FIRSTNAME" , nullable = false)
    private String firstName;


    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "LASTCONNEXION" , nullable = false)
    private Date lastConnexion;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "BIRTHDATE")
    private Date birthDate;

    @Column(name = "TOKEN")
    private String token;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "userrole",
            joinColumns = @JoinColumn(
                    name = "IDUSER", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "IDROLE", referencedColumnName = "id"
            )
    )
    private List<Role> roles;

}
