package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Håndterer database-operationer for User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find bruger ud fra e-mail (bruges til login)
    User findByEmail(String email);
}
