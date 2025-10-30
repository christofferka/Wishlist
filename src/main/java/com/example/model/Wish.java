package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "wishes")
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String link;
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Wish() {}

    public Wish(String description, String link, double price, User user) {
        this.description = description;
        this.link = link;
        this.price = price;
        this.user = user;
    }

    public Long getId() { return id; }
    public String getDescription() { return description; }
    public String getLink() { return link; }
    public double getPrice() { return price; }
    public User getUser() { return user; }

    public void setId(Long id) { this.id = id; }
    public void setDescription(String description) { this.description = description; }
    public void setLink(String link) { this.link = link; }
    public void setPrice(double price) { this.price = price; }
    public void setUser(User user) { this.user = user; }
}
