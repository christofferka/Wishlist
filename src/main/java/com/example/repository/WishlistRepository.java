package com.example.repository;

import com.example.model.User;
import com.example.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for ønskesedler.
 * Indeholder metoder til at hente, gemme og dele ønskesedler.
 */
@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    // Finder alle ønskesedler, der tilhører en bestemt bruger
    List<Wishlist> findByUser(User user);

    // Finder en ønskeseddel via et unikt delings-ID
    Optional<Wishlist> findByShareId(String shareId);

    // Finder ønskeseddel ud fra navn (kan være nyttig senere)
    Optional<Wishlist> findByNameAndUser(String name, User user);
}
