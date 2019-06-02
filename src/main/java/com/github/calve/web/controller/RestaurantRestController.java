package com.github.calve.web.controller;

import com.github.calve.model.MenuItem;
import com.github.calve.model.Restaurant;
import com.github.calve.repository.datajpa.CrudRestaurantRepository;
import com.github.calve.to.MenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    public static final String REST_URL = "/rest/admin/restaurants";

    private CrudRestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantRestController(CrudRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return null;//TODO
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createRestaurant(@RequestBody String restaurantName) {
//TODO
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Integer id) {
        return null;//TODO
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRestaurantById(@PathVariable Integer id) {
//TODO
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateRestaurantById(@PathVariable Integer id, @RequestBody String restaurantName) {
//TODO
    }
}
