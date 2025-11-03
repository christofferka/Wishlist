package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    // Primærnøgle for brugeren
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Brugernavn, e-mail og adgangskode
    private String username;
    private String email;
    private String password;

    // Tom konstruktør (kræves af JPA)
    public User() {}

    // Konstruktør til oprettelse af ny bruger
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}
