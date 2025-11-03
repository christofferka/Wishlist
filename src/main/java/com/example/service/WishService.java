package com.example.service;

import com.example.model.Wish;
import com.example.model.Wishlist;
import com.example.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    // Hent alle ønsker for en ønskeseddel
    public List<Wish> getWishesByWishlist(Wishlist wishlist) {
        return wishRepository.findByWishlist(wishlist);
    }

    // Hent enkelt ønske
    public Optional<Wish> getWishById(Long id) {
        return wishRepository.findById(id);
    }

    // Opret eller opdater ønske
    public Wish saveWish(Wish wish) {
        return wishRepository.save(wish);
    }

    // Slet ønske
    public void deleteWish(Long id) {
        wishRepository.deleteById(id);
    }

    // Reserver et ønske
    public void reserveWish(Long wishId, String name) {
        wishRepository.findById(wishId).ifPresent(wish -> {
            if (!wish.isReserved()) {
                wish.setReserved(true);
                wish.setReservedBy(name);
                wishRepository.saveAndFlush(wish);
            }
        });
    }

    // Fjern reservation
    public void cancelReservation(Long wishId) {
        wishRepository.findById(wishId).ifPresent(wish -> {
            wish.setReserved(false);
            wish.setReservedBy(null);
            wishRepository.saveAndFlush(wish);
        });
    }
}
