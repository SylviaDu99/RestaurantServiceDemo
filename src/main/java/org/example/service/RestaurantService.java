package org.example.service;

import org.example.domain.Restaurant;
import org.example.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant getRestaurantById(Integer restaurantId) {
        return restaurantRepository.findById(restaurantId).orElse(null);
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Integer restaurantId, Restaurant restaurant) {
        Restaurant existingRestaurant = getRestaurantById(restaurantId);
        if (existingRestaurant != null) {
            existingRestaurant.setName(restaurant.getName());
            existingRestaurant.setAddress(restaurant.getAddress());
            existingRestaurant.setContact(restaurant.getContact());
            existingRestaurant.setDescription(restaurant.getDescription());
            return restaurantRepository.save(existingRestaurant);
        }
        return null;
    }

    public void deleteRestaurant(Integer restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }
}
