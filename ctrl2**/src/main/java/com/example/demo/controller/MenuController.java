package com.example.demo.controller;

import com.example.demo.entities.Menu;
import com.example.demo.entities.Restaurant;
import com.example.demo.repositories.MenuRepository;
import com.example.demo.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository; // Ajouter ce champ

    @GetMapping("/ajouter")
    public String showAddMenuForm(Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("restaurants", restaurantRepository.findAll()); // Ajoutez cette ligne pour passer les restaurants au modèle
        return "ajouter-menu";
    }

    /* Méthode pour ajouter un menu
    @PostMapping("/ajouter")
    public String addMenu(@ModelAttribute("menu") Menu menu) {
        menuRepository.save(menu);
        return "redirect:/menus";
    }*/

    @PostMapping("/ajouter")
    public String addMenu(@ModelAttribute("menu") Menu menu, @RequestParam("restaurant.id") Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        restaurant.ifPresent(menu::setRestaurant); // Associe le restaurant si trouvé
        menuRepository.save(menu);
        return "redirect:/menus";
    }

    // Méthode pour afficher le formulaire de modification d'un menu
    @GetMapping("/modifier/{id}")
    public String showEditMenuForm(@PathVariable Long id, Model model) {
        Optional<Menu> menu = menuRepository.findById(id);
        if (menu.isPresent()) {
            model.addAttribute("menu", menu.get());
            model.addAttribute("restaurants", restaurantRepository.findAll());
            return "modifier-menu";
        } else {
            return "redirect:/menus";
        }
    }


    // Méthode pour modifier un menu
    @PostMapping("/modifier")
    public String updateMenu(@ModelAttribute("menu") Menu menu, @RequestParam("restaurant.id") Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            menu.setRestaurant(restaurant.get());
        }
        menuRepository.save(menu);
        return "redirect:/menus";
    }


    /* Méthode pour supprimer un menu
    @GetMapping("/supprimer/{id}")
    public String deleteMenu(@PathVariable Long id) {
        menuRepository.deleteById(id);
        return "redirect:/menus";
    }*/

    @PostMapping("/supprimer")
    public String deleteMenu(@RequestParam Long id) {
        menuRepository.deleteById(id);
        return "redirect:/menus";
    }

    // Méthode pour afficher les détails d'un menu et les plats disponibles
    @GetMapping("/details/{id}")
    public String viewMenuDetails(@PathVariable Long id, Model model) {
        Optional<Menu> menu = menuRepository.findById(id);
        if (menu.isPresent()) {
            model.addAttribute("menu", menu.get());
            return "details-menu";
        } else {
            return "redirect:/menus"; // Redirige si le menu n'existe pas
        }
    }
    @GetMapping
    public String listMenus(Model model) {
        model.addAttribute("menus", menuRepository.findAll());
        return "list-menus"; // La vue Thymeleaf "list-menus.html" doit être créée
    }
    @PostMapping("/ajouter-plat")
    public String addPlat(@RequestParam Long menuId, @RequestParam String plat) {
        Optional<Menu> menuOpt = menuRepository.findById(menuId);
        if (menuOpt.isPresent()) {
            Menu menu = menuOpt.get();
            menu.addPlat(plat); // Adding the new dish
            menuRepository.save(menu); // Save the updated menu
        }
        return "redirect:/menus"; // Redirect to the menu list
    }
    @PostMapping("/supprimer-plat")
    public String deletePlat(@RequestParam Long menuId, @RequestParam String plat) {
        Optional<Menu> menuOpt = menuRepository.findById(menuId);
        if (menuOpt.isPresent()) {
            Menu menu = menuOpt.get();
            menu.removePlat(plat); // Remove the specified dish
            menuRepository.save(menu); // Save the updated menu
        }
        return "redirect:/menus"; // Redirect to the menu list
    }
    @PostMapping("/modifier-plat")
    public String updatePlat(@RequestParam Long menuId, @RequestParam int index, @RequestParam String newPlat) {
        Optional<Menu> menuOpt = menuRepository.findById(menuId);
        if (menuOpt.isPresent()) {
            Menu menu = menuOpt.get();
            menu.updatePlat(index, newPlat); // Update the dish at the specified index
            menuRepository.save(menu); // Save the updated menu
        }
        return "redirect:/menus"; // Redirect to the menu list
    }
}

