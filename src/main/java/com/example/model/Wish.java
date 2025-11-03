package com.example.model;

import jakarta.persistence.*;

/**
 * Et enkelt ønske, som tilhører en ønskeseddel.
 * Kan reserveres af en person via delingslink.
 */
@Entity
@Table(name = "wishes")
public class Wish {

    // Primærnøgle – genereres automatisk
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Selve ønskets indhold
    private String description;
    private String link;
    private double price;

    // Om ønsket er reserveret, og af hvem
    private boolean reserved = false;
    private String reservedBy;

    // Hvilken ønskeseddel dette ønske tilhører
    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;

    // Tom konstruktør (kræves af JPA)
    public Wish() {}

    // Konstruktør til oprettelse af nyt ønske
    public Wish(String description, String link, double price, Wishlist wishlist) {
        this.description = description;
        this.link = link;
        this.price = price;
        this.wishlist = wishlist;
    }

    // Getters
    public Long getId() { return id; }
    public String getDescription() { return description; }
    public String getLink() { return link; }
    public double getPrice() { return price; }
    public boolean isReserved() { return reserved; }
    public String getReservedBy() { return reservedBy; }
    public Wishlist getWishlist() { return wishlist; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setDescription(String description) { this.description = description; }
    public void setLink(String link) { this.link = link; }
    public void setPrice(double price) { this.price = price; }
    public void setReserved(boolean reserved) { this.reserved = reserved; }
    public void setReservedBy(String reservedBy) { this.reservedBy = reservedBy; }
    public void setWishlist(Wishlist wishlist) { this.wishlist = wishlist; }
}
