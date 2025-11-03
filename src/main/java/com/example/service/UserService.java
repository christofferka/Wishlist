package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Håndterer logik for registrering og login af brugere.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    // Dependency injection af repository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Registrerer en ny bruger, hvis e-mail ikke allerede findes
    public User registerUser(String username, String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            return null; // e-mail er allerede i brug
        }
        User user = new User(username, email, password);
        return userRepository.save(user);
    }

    // Tjekker login med e-mail og password
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
