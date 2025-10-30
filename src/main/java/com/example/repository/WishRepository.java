package com.example.repository;

import com.example.model.User;
import com.example.model.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> findByUser(User user);
}
