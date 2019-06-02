package com.github.calve.web.controller;

import com.github.calve.model.MenuItem;
import com.github.calve.to.MenuTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = MenuItemsRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuItemsRestController {

    public static final String REST_URL = "/rest/admin/items";

    @GetMapping
    public List<MenuItem> getAllRestaurantMenuItems(@RequestParam Integer restaurantId, @RequestParam(required = false) LocalDate date) {
        return null;//TODO
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void saveMenuItems(@RequestParam Integer restaurantId, @RequestBody MenuTo menu) {
        //TODO
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMenuByDate(@RequestParam Integer restaurantId, @RequestParam LocalDate date) {
        //TODO
    }

    @GetMapping("/{id}")
    public MenuItem getMenuItemById(@PathVariable Integer id) {
        return null;        //TODO
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateMenuItemById(@PathVariable Integer id, @RequestBody MenuItem item) {        //TODO id path needed?!
        //TODO
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMenuItemById(@PathVariable Integer id) {
        //TODO
    }

}