package com.example.controller;

import com.example.model.User;
import com.example.model.Wish;
import com.example.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishes")
public class WebWishlistController {

    private final WishlistService wishlistService;

    public WebWishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/list")
    public String showWishlist(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/login";
        model.addAttribute("wishes", wishlistService.getWishesByUser(currentUser));
        return "wishlist";
    }

    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") == null) return "redirect:/login";
        model.addAttribute("wish", new Wish());
        return "form";
    }

    @PostMapping("/add")
    public String addWish(@ModelAttribute Wish wish, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/login";
        wish.setUser(currentUser);
        wishlistService.saveWish(wish);
        return "redirect:/wishes/list";
    }

    // --- NYE/OPDATERDE METODER ---

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/login";

        Wish wish = wishlistService.getWish(id);
        // Sammenlign ID'er i stedet for equals på entity
        boolean mine = (wish != null &&
                wish.getUser() != null &&
                wish.getUser().getId() != null &&
                wish.getUser().getId().equals(currentUser.getId()));

        if (!mine) return "redirect:/wishes/list";

        model.addAttribute("wish", wish);
        return "edit";              // templates/edit.html
    }

    @PostMapping("/update/{id}")
    public String updateWish(@PathVariable Long id, @ModelAttribute Wish wish, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/login";

        wish.setId(id);             // sikkerhed: brug path id
        wish.setUser(currentUser);  // hold ejerskab på den loggede bruger
        wishlistService.saveWish(wish);
        return "redirect:/wishes/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteWish(@PathVariable Long id, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/login";

        Wish wish = wishlistService.getWish(id);
        if (wish != null &&
                wish.getUser() != null &&
                wish.getUser().getId() != null &&
                wish.getUser().getId().equals(currentUser.getId())) {
            wishlistService.deleteWish(id);
        }
        return "redirect:/wishes/list";
    }
}
