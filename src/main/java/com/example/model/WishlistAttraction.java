package com.example.model;

import java.util.List;
import java.util.UUID;

public class WishlistAttraction {
    private UUID id;
    private String name;
    private String description;
    private String city;
    private List<String> tags;
    private String imagePath;

    public WishlistAttraction() {
        this.id = UUID.randomUUID();
    }

    public WishlistAttraction(String name, String description, String city, List<String> tagList) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) { 
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
