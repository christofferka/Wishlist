package com.example.service;

import com.example.model.User;
import com.example.model.Wish;
import com.example.repository.WishRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishlistService {

    private final WishRepository wishRepository;

    public WishlistService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public void saveWish(Wish wish) {
        wishRepository.save(wish);
    }

    public List<Wish> getWishesByUser(User user) {
        return wishRepository.findByUser(user);
    }

    public Wish getWish(Long id) {
        return wishRepository.findById(id).orElse(null);
    }

    public void deleteWish(Long id) {
        wishRepository.deleteById(id);
    }
}
