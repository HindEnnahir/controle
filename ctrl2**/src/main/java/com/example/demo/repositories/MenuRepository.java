package com.example.demo.repositories;

import com.example.demo.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    // Aucun code additionnel n'est nécessaire pour les opérations CRUD de base
}