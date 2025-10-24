package com.example.controller;

import com.example.model.WishlistAttraction;
import com.example.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

// Markerer denne klasse som en Spring MVC controller. Håndterer web-requests og returnerer HTML-sider
@Controller
public class WishlistController {
    private final WishlistService wishlistService;

    // Constructor injection. Spring indsætter automatisk et TouristService-objekt
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    // Viser alle attraktioner på en liste-side
    @GetMapping("/attractions")
    public String getAllAttractions(Model model) {
        List<WishlistAttraction> attractions = wishlistService.getAllAttractions();
        model.addAttribute("attractions", attractions); // gør data tilgængelig for Thymeleaf
        return "attractionList";
    }

    // Viser detaljer om én specifik attraktion
    @GetMapping("/attractions/{id}")
    public String getAttraction(@PathVariable UUID id, Model model) {
        WishlistAttraction attraction = wishlistService.getAttraction(id);
        model.addAttribute("attraction", attraction);
        return "singleAttraction";
    }

    // Viser formular til at oprette en ny attraktion
    @GetMapping("/attractions/new")
    public String newAttractionForm(Model model) {
        model.addAttribute("attraction", new WishlistAttraction()); // tomt objekt til formularbinding
        return "newAttraction";
    }

    // Håndterer POST fra formularen til at oprette en ny attraktion
    @PostMapping("/attractions")
    public String createAttraction(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String city,
            @RequestParam String tags) {

        // Deler tag-strengen op i en liste (kommasepareret)
        List<String> tagList = Arrays.asList(tags.split(",\\s*"));

        // Opretter et nyt objekt med brugerens input
        WishlistAttraction attraction =
                new WishlistAttraction(name, description, city, tagList);

        // Tilføjer den nye attraktion gennem service-laget
        wishlistService.addAttraction(attraction);

        // Sender brugeren tilbage til oversigten (redirect = ny GET-request)
        return "redirect:/attractions";
    }

    // Viser redigeringsformular for en eksisterende attraktion
    @GetMapping("/attractions/{id}/edit")
    public String editAttractionForm(@PathVariable UUID id, Model model) {
        WishlistAttraction attraction = wishlistService.getAttraction(id);
        model.addAttribute("attraction", attraction);
        return "editAttraction"; // viser editAttraction.html
    }

    // Opdaterer en eksisterende attraktion efter formularen er sendt
    @PostMapping("/attractions/{id}/edit")
    public String updateAttraction(
            @PathVariable UUID id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String city,
            @RequestParam String tags) {

        // Danner liste af tags
        List<String> tagList = Arrays.asList(tags.split(",\\s*"));

        // Opretter nyt objekt med opdaterede værdier
        WishlistAttraction attraction =
                new WishlistAttraction(name, description, city, tagList);

        // Sætter id manuelt via refleksion, da feltet måske er privat i modellen
        try {
            var field = WishlistAttraction.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(attraction, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Kalder service for at gemme ændringerne
        wishlistService.updateAttraction(attraction);

        // Redirecter tilbage til detaljesiden for den opdaterede attraktion
        return "redirect:/attractions/" + id;
    }

    // Sletter en attraktion baseret på dens id
    @PostMapping("/attractions/{id}/delete")
    public String deleteAttraction(@PathVariable UUID id) {
        wishlistService.deleteAttraction(id);
        return "redirect:/attractions"; // sender tilbage til listen
    }
}
