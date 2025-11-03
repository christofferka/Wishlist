package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Denne controller håndterer alt relateret til bruger-login, registrering og logout.
 * Den fungerer som forbindelsen mellem HTML-formularerne og UserService.
 */
@Controller
public class AuthController {

    private final UserService userService;

    // Constructor der injicerer UserService (for logik som login/registrering)
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Viser login-siden (GET-request).
     * Tilføjer et tomt User-objekt til formularen.
     */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login"; // Viser login.html
    }

    /**
     * Håndterer login-logikken (POST-request).
     * Tjekker brugerens email og adgangskode i databasen.
     * Hvis korrekt → gem i session og send til ønskelisten.
     */
    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session, Model model) {
        User existingUser = userService.loginUser(user.getEmail(), user.getPassword());
        if (existingUser != null) {
            session.setAttribute("currentUser", existingUser);
            return "redirect:/wishes/list"; // Send brugeren til sin ønskeliste
        }
        model.addAttribute("error", "Forkert email eller adgangskode");
        return "login";
    }

    /**
     * Viser registreringssiden (GET-request).
     */
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Viser register.html
    }

    /**
     * Håndterer oprettelsen af en ny bruger (POST-request).
     * Hvis email allerede findes → vis fejlbesked.
     * Ellers → opret ny bruger og send til login.
     */
    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        User newUser = userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
        if (newUser == null) {
            model.addAttribute("error", "Emailen findes allerede");
            return "register";
        }
        return "redirect:/login";
    }

    /**
     * Logger brugeren ud ved at fjerne session-data.
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Fjern nuværende login-session
        return "redirect:/login";
    }
}
