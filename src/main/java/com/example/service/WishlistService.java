package com.example.service;

import com.example.model.WishlistAttraction;
import com.example.repository.WishlistRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<WishlistAttraction> getAllAttractions() {
        return wishlistRepository.getAttractions();
    }

    public WishlistAttraction getAttraction(UUID id) {
        return wishlistRepository.getAttraction(id);
    }

    public void addAttraction(WishlistAttraction attraction) {
        wishlistRepository.addAttraction(attraction);
    }

    public void updateAttraction(WishlistAttraction attraction) {
        wishlistRepository.updateAttraction(attraction);
    }

    public void deleteAttraction(UUID id) {
        wishlistRepository.deleteAttraction(id);
    }
}
