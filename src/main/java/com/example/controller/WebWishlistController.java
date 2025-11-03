package com.example.controller;

import com.example.model.User;
import com.example.model.Wish;
import com.example.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller-laget for håndtering af brugerens ønskeliste.
 * Her defineres alle web-ruter (URL’er) der styrer visning, tilføjelse,
 * redigering, sletning og deling af ønsker.
 */
@Controller
@RequestMapping("/wishes")
public class WebWishlistController {

    private final WishlistService wishlistService;

    // Constructor der injicerer service-laget
    public WebWishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    /**
     * Viser den nuværende brugers ønskeliste.
     * Kræver at brugeren er logget ind (tjek via session).
     */
    @GetMapping("/list")
    public String showWishlist(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/login";

        List<Wish> wishes = wishlistService.getWishesByUser(currentUser);
        model.addAttribute("wishes", wishes);
        return "wishlist";
    }

    /**
     * Viser formular til at tilføje et nyt ønske.
     */
    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") == null) return "redirect:/login";
        model.addAttribute("wish", new Wish());
        return "form";
    }

    /**
     * Gemmer et nyt ønske i databasen.
     */
    @PostMapping("/add")
    public String addWish(@ModelAttribute Wish wish, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/login";
        wish.setUser(currentUser);
        wishlistService.saveWish(wish);
        return "redirect:/wishes/list";
    }

    /**
     * Viser redigeringsformular for et eksisterende ønske.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/login";

        Wish wish = wishlistService.getWish(id);
        if (wish == null || !wish.getUser().getId().equals(currentUser.getId()))
            return "redirect:/wishes/list";

        model.addAttribute("wish", wish);
        return "edit";
    }

    /**
     * Opdaterer et eksisterende ønske efter redigering.
     */
    @PostMapping("/update/{id}")
    public String updateWish(@PathVariable Long id, @ModelAttribute Wish wish, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/login";

        wish.setId(id);
        wish.setUser(currentUser);
        wishlistService.saveWish(wish);
        return "redirect:/wishes/list";
    }

    /**
     * Sletter et ønske (kun hvis det tilhører den loggede bruger).
     */
    @GetMapping("/delete/{id}")
    public String deleteWish(@PathVariable Long id, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/login";

        Wish wish = wishlistService.getWish(id);
        if (wish != null && wish.getUser().getId().equals(currentUser.getId())) {
            wishlistService.deleteWish(id);
        }
        return "redirect:/wishes/list";
    }

    // ===========================
    // DELINGSFUNKTION
    // ===========================

    /**
     * Viser et enkelt ønske via et delingslink (uden login).
     * Finder ønsket via shareId, som er et unikt UUID.
     */
    @GetMapping("/share/{shareId}")
    public String viewSharedWishlist(@PathVariable String shareId, Model model) {
        return wishlistService.getWishByShareId(shareId)
                .map(wish -> {
                    model.addAttribute("wish", wish);
                    model.addAttribute("owner", wish.getUser().getUsername());
                    return "sharedWishlist"; // Viser delingssiden
                })
                .orElse("wishlistNotFound"); // Hvis ønsket ikke findes
    }
}
