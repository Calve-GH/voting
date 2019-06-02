package com.github.calve.web.controller;

import com.github.calve.model.Dish;
import com.github.calve.repository.datajpa.CrudDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {

    public static final String REST_URL = "/rest/admin/dishes";

    private CrudDishRepository dishRepository;

    @Autowired
    public DishRestController(CrudDishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @GetMapping
    public List<Dish> getDishes() {
        return null;//TODO
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createDish(@RequestBody String dishName) {
//TODO
    }

    @GetMapping("/{id}")
    public Dish getDishById(@PathVariable Integer id) {
        return null;//TODO
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDishById(@PathVariable Integer id) {
//TODO
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateDishById(@PathVariable Integer id, @RequestBody String dishName) {
//TODO
    }
}
