package com.example.demo.repositories;


import com.example.demo.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // Aucune méthode additionnelle n'est nécessaire pour les opérations CRUD de base
}