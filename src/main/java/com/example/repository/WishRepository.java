package com.example.repository;

import com.example.model.User;
import com.example.model.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository-laget står for forbindelsen til databasen.
 * Her håndteres alle forespørgsler (queries) relateret til Wish-objekter.
 * Spring Data JPA genererer automatisk SQL ud fra metodernes navne.
 */
@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    /**
     * Finder alle ønsker, der tilhører en bestemt bruger.
     * Bruges til at vise "Mine Ønsker" for den loggede bruger.
     */
    List<Wish> findByUser(User user);

    /**
     * Finder et enkelt ønske via et unikt delings-ID (shareId).
     * Bruges, når en ønskeliste deles med andre gennem et link.
     */
    Optional<Wish> findByShareId(String shareId);
}
