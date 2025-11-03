package com.example.repository;

import com.example.model.Wish;
import com.example.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for individuelle ønsker.
 * Indeholder forespørgsler relateret til ønsker i en ønskeseddel.
 */
@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    // Henter alle ønsker, der tilhører en bestemt ønskeseddel
    List<Wish> findByWishlist(Wishlist wishlist);

    // Henter kun de reserverede ønsker i en ønskeseddel (valgfrit brug)
    List<Wish> findByWishlistAndReservedTrue(Wishlist wishlist);
}
