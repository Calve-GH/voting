package com.github.calve.web.controller;

import com.github.calve.model.Restaurant;
import com.github.calve.repository.datajpa.CrudRestaurantRepository;
import com.github.calve.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    public static final String REST_URL = "/rest/admin/restaurants/";

    private CrudRestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantRestController(CrudRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createRestaurant(@RequestBody String restaurantName) {
        restaurantRepository.save(new Restaurant(restaurantName));
    }

    @GetMapping("{id}/")
    public Restaurant getRestaurantById(@PathVariable Integer id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant with this id not found"));
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRestaurantById(@PathVariable Integer id) {
        restaurantRepository.delete(id);
    }

    @PutMapping(value = "{id}/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateRestaurantById(@PathVariable Integer id, @RequestBody String restaurantName) {
        restaurantRepository.save(new Restaurant(id, restaurantName));
    }
}
