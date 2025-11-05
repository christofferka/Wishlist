package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Dette er hovedklassen for Spring Boot applikation.
 * Den starter hele programmet og konfigurere alle komponenter automatisk.
 */
@SpringBootApplication
public class WishlistApplication {

    public static void main(String[] args) {
        // Starter Spring Boot-applikationen
        SpringApplication.run(WishlistApplication.class, args);
    }
}
