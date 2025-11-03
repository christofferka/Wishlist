package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository-laget for brugere.
 * Denne klasse håndterer al kommunikation mellem applikationen og "users"-tabellen i databasen.
 * Spring Data JPA genererer automatisk SQL baseret på metodenavne.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finder en bruger baseret på email.
     * Bruges ved login og registrering til at tjekke om en email allerede eksisterer.
     */
    User findByEmail(String email);
}
