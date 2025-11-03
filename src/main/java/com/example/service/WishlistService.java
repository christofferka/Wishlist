package com.example.service;

import com.example.model.User;
import com.example.model.Wishlist;
import com.example.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    // Hent alle ønskesedler for en bruger
    public List<Wishlist> getWishlistsByUser(User user) {
        return wishlistRepository.findByUser(user);
    }

    // Hent ønskeseddel via ID
    public Optional<Wishlist> getWishlistById(Long id) {
        return wishlistRepository.findById(id);
    }

    // Hent ønskeseddel via delingslink
    public Optional<Wishlist> getWishlistByShareId(String shareId) {
        return wishlistRepository.findByShareId(shareId);
    }

    // Opret ny ønskeseddel
    public Wishlist createWishlist(String name, User user) {
        Wishlist wishlist = new Wishlist(name, user);
        return wishlistRepository.save(wishlist);
    }

    // Gem/Opdater ønskeseddel
    public Wishlist saveWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    // Slet ønskeseddel
    public void deleteWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }

    // Find eller opret standardliste (bruges ved første login)
    public Wishlist getOrCreateDefaultWishlist(User user) {
        List<Wishlist> lists = wishlistRepository.findByUser(user);
        if (!lists.isEmpty()) return lists.get(0);

        Wishlist defaultList = new Wishlist("Min ønskeseddel", user);
        return wishlistRepository.save(defaultList);
    }
}
