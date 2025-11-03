package com.example.model;

import jakarta.persistence.*;
import java.util.UUID;

/**
 * Denne klasse repræsenterer et ønske i databasen.
 * Hver instans svarer til en række i "wishes"-tabellen.
 */
@Entity
@Table(name = "wishes")
public class Wish {

    // Primærnøgle (genereres automatisk af databasen)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ønskets beskrivelse, link og pris
    private String description;
    private String link;
    private double price;

    /**
     * Unikt ID til deling.
     * Bruges når et ønske skal kunne deles med andre via et link.
     * UUID sikrer at hvert ID er globalt unikt.
     */
    @Column(unique = true, updatable = false)
    private String shareId = UUID.randomUUID().toString();

    @PrePersist
    public void generateShareId() {
        if (this.shareId == null || this.shareId.isEmpty()) {
            this.shareId = UUID.randomUUID().toString();
        }
    }

    /**
     * Relation til den bruger, som ejer ønsket.
     * "ManyToOne" betyder, at mange ønsker kan tilhøre én bruger.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Tom konstruktør (kræves af JPA)
    public Wish() {}

    // Konstruktør til oprettelse af nye ønsker
    public Wish(String description, String link, double price, User user) {
        this.description = description;
        this.link = link;
        this.price = price;
        this.user = user;
        this.shareId = UUID.randomUUID().toString(); // genererer nyt delings-ID
    }

    // Getters og Setters
    public Long getId() { return id; }
    public String getDescription() { return description; }
    public String getLink() { return link; }
    public double getPrice() { return price; }
    public User getUser() { return user; }
    public String getShareId() { return shareId; }

    public void setId(Long id) { this.id = id; }
    public void setDescription(String description) { this.description = description; }
    public void setLink(String link) { this.link = link; }
    public void setPrice(double price) { this.price = price; }
    public void setUser(User user) { this.user = user; }
}
