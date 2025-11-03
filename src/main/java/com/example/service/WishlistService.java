package com.example.service;

import com.example.model.User;
import com.example.model.Wish;
import com.example.repository.WishRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service-klassen håndterer al forretningslogik for ønsker (Wish).
 * Den kommunikerer med WishRepository for at hente, gemme og slette data fra databasen.
 */
@Service
public class WishlistService {

    private final WishRepository wishRepository;

    // Dependency Injection af repository
    public WishlistService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    /**
     * Gemmer eller opdaterer et ønske i databasen.
     * Hvis ønsket allerede findes (samme ID), bliver det opdateret.
     */
    public void saveWish(Wish wish) {
        wishRepository.save(wish);
    }

    /**
     * Henter alle ønsker, der tilhører en specifik bruger.
     * Bruges på brugerens "Min ønskeliste"-side.
     */
    public List<Wish> getWishesByUser(User user) {
        return wishRepository.findByUser(user);
    }

    /**
     * Finder et enkelt ønske via dets unikke ID.
     * Returnerer null, hvis ønsket ikke eksisterer.
     */
    public Wish getWish(Long id) {
        return wishRepository.findById(id).orElse(null);
    }

    /**
     * Finder et ønske via et delingslink (shareId).
     * Bruges, når en anden åbner et delt ønske.
     */
    public Optional<Wish> getWishByShareId(String shareId) {
        return wishRepository.findByShareId(shareId);
    }

    /**
     * Sletter et ønske fra databasen baseret på ID.
     */
    public void deleteWish(Long id) {
        wishRepository.deleteById(id);
    }
}
