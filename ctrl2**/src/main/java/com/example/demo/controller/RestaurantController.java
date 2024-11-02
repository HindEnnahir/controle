package com.example.demo.controller;
import com.example.demo.entities.Restaurant;
import com.example.demo.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Méthode pour afficher la liste des restaurants avec leurs menus
    @GetMapping
    public String listRestaurantsWithMenus(Model model) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        model.addAttribute("restaurants", restaurants);
        return "liste-restaurants";
    }

    // Méthode pour afficher le formulaire d'ajout d'un restaurant
    @GetMapping("/ajouter")
    public String showAddRestaurantForm(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        return "ajouter-restaurant";
    }

    // Méthode pour ajouter un restaurant
    @PostMapping("/ajouter")
    public String addRestaurant(@ModelAttribute("restaurant") Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return "redirect:/restaurants";
    }

    // Méthode pour afficher le formulaire de modification d'un restaurant
    @GetMapping("/modifier/{id}")
    public String showEditRestaurantForm(@PathVariable Long id, Model model) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            model.addAttribute("restaurant", restaurant.get());
            return "modifier-restaurant";
        } else {
            return "redirect:/restaurants"; // Redirige si le restaurant n'existe pas
        }
    }

    // Méthode pour modifier un restaurant
    @PostMapping("/modifier")
    public String updateRestaurant(@ModelAttribute("restaurant") Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return "redirect:/restaurants";
    }

    /* Méthode pour supprimer un restaurant
    @GetMapping("/supprimer/{id}")
    public String deleteRestaurant(@PathVariable Long id) {
        restaurantRepository.deleteById(id);
        return "redirect:/restaurants";
    }*/

    /*@PostMapping("/supprimer")
    public String deleteRestaurant(@RequestParam Long id) {
        restaurantRepository.deleteById(id);
        return "redirect:/restaurants";
    }*/
    @PostMapping("/supprimer/{id}")
    public String deleteRestaurant(@PathVariable Long id) {
        restaurantRepository.deleteById(id);
        return "redirect:/restaurants";
    }
}


