package com.example.demo.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ElementCollection
    @CollectionTable(name = "menu_plats", joinColumns = @JoinColumn(name = "menu_id"))
    @Column(name = "plat")
    private List<String> plats = new ArrayList<>();

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<String> getPlats() {
        return plats;
    }

    public void setPlats(List<String> plats) {
        this.plats = plats;
    }

    public void addPlat(String plat) {
        this.plats.add(plat);
    }

    public void removePlat(String plat) {
        this.plats.remove(plat);
    }

    public void updatePlat(int index, String newPlat) {
        if (index >= 0 && index < plats.size()) {
            this.plats.set(index, newPlat);
        }
    }
}
