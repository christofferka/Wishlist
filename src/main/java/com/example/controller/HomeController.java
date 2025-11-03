package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Denne controller håndterer forsiden (landing page) af applikationen.
 * Den sender brugeren til index.html, som ligger i templates-mappen.
 */
@Controller
public class HomeController {

    /**
     * Viser forsiden, når man går ind på roden af hjemmesiden (/).
     * Returnerer navnet på Thymeleaf-skabelonen: index.html.
     */
    @GetMapping("/")
    public String home() {
        return "index"; // Indlæser templates/index.html
    }
}
