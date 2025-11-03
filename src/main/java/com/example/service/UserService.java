package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Denne klasse håndterer al logik relateret til brugere.
 * Den står for registrering, login og kommunikation med databasen via UserRepository.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    // Dependency Injection: Spring indsætter automatisk repository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Opretter en ny bruger, hvis emailen ikke allerede findes.
     * Returnerer den gemte bruger eller null, hvis emailen er optaget.
     */
    public User registerUser(String username, String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            return null; // Email findes allerede
        }
        User user = new User(username, email, password);
        return userRepository.save(user); // Gemmer ny bruger i databasen
    }

    /**
     * Logger en bruger ind, hvis email og adgangskode matcher.
     * Returnerer brugeren ved succes, ellers null.
     */
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        // Simpel adgangskodekontrol (kan senere erstattes med hashed passwords)
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Login mislykkedes
    }
}
