package org.example.controller;

import org.example.domain.Restaurant;
import org.example.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Integer restaurantId) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(restaurantId));
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody @Valid Restaurant restaurant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        Restaurant createdRestaurant = restaurantService.saveRestaurant(restaurant);
        return ResponseEntity.status(201).body(createdRestaurant);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Integer restaurantId, @RequestBody @Valid Restaurant restaurant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurantId, restaurant);
        if (updatedRestaurant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRestaurant);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Integer restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }
}
