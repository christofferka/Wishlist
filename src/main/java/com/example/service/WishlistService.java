package com.example.service;

import com.example.model.User;
import com.example.model.Wish;
import com.example.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service-klassen håndterer al forretningslogik for ønsker.
 * Den fungerer som bindeled mellem controlleren og databasen.
 */
@Service
public class WishlistService {

    private final WishRepository wishRepository;

    // Dependency Injection af repository
    public WishlistService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    /**
     * Gemmer eller opdaterer et ønske.
     * Hvis ønsket ikke har et delings-ID, genereres et nyt.
     */
    public void saveWish(Wish wish) {
        if (wish.getShareId() == null || wish.getShareId().isEmpty()) {
            wish.setShareId(UUID.randomUUID().toString());
        }
        wishRepository.save(wish);
    }

    /**
     * Henter alle ønsker, der tilhører en specifik bruger.
     */
    public List<Wish> getWishesByUser(User user) {
        return wishRepository.findByUser(user);
    }

    /**
     * Finder et enkelt ønske via dets ID.
     */
    public Wish getWish(Long id) {
        return wishRepository.findById(id).orElse(null);
    }

    /**
     * Finder et ønske via delingslink (shareId).
     * Bruges, når nogen åbner et delt ønske.
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
