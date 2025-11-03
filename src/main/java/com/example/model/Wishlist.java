package com.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * En ønskeseddel, som tilhører én bruger og indeholder mange ønsker.
 * Har et unikt delingslink via shareId.
 */
@Entity
@Table(name = "wishlists")
public class Wishlist {

    // Primærnøgle for ønskesedlen
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Navn på ønskesedlen (fx "Jul 2025", "Fødselsdag")
    private String name;

    // Unikt delings-ID til hele ønskesedlen
    @Column(unique = true, updatable = false)
    private String shareId;

    // Ejeren af ønskesedlen
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Alle ønsker, der hører til denne ønskeseddel
    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wish> wishes = new ArrayList<>();

    // Sikrer at shareId er sat, inden ønskesedlen gemmes første gang
    @PrePersist
    public void ensureShareId() {
        if (this.shareId == null || this.shareId.isEmpty()) {
            this.shareId = UUID.randomUUID().toString();
        }
    }

    public Wishlist() {
    }

    public Wishlist(String name, User user) {
        this.name = name;
        this.user = user;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getShareId() { return shareId; }
    public User getUser() { return user; }
    public List<Wish> getWishes() { return wishes; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setShareId(String shareId) { this.shareId = shareId; }
    public void setUser(User user) { this.user = user; }
    public void setWishes(List<Wish> wishes) { this.wishes = wishes; }
}
