package com.github.calve.web.controller;

import com.github.calve.model.Dish;
import com.github.calve.repository.datajpa.CrudDishRepository;
import com.github.calve.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {

    public static final String REST_URL = "/rest/admin/dishes/";

    private CrudDishRepository dishRepository;

    @Autowired
    public DishRestController(CrudDishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @GetMapping
    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createDish(@RequestBody String dishName) {
        dishRepository.save(new Dish(dishName));
    }

    @GetMapping("{id}/")
    public Dish findDishById(@PathVariable Integer id) {
        return dishRepository.findById(id).orElseThrow(() -> new NotFoundException("Dish with this id not found"));
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDishById(@PathVariable Integer id) {
        dishRepository.delete(id);
    }

    @PutMapping(value = "{id}/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateDishById(@PathVariable Integer id, @RequestBody String dishName) {
        dishRepository.save(new Dish(id, dishName));
    }
}
