package com.github.calve.web.controller;

import com.github.calve.model.MenuItem;
import com.github.calve.repository.datajpa.CrudMenuItemRepository;
import com.github.calve.to.RestaurantTo;
import com.github.calve.util.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.github.calve.util.RestaurantToValidator.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = MenuItemsRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuItemsRestController {

    public static final String REST_URL = "/rest/admin/items/";

    private CrudMenuItemRepository menuItemRepository;

    public MenuItemsRestController(CrudMenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @GetMapping
    public List<MenuItem> getAllRestaurantMenuItems(@RequestParam Integer restaurantId, @RequestParam(required = false) LocalDate date) {

        if (date == null) {
            return menuItemRepository.findAllByRestaurantId(restaurantId);
        }

        return menuItemRepository.findAllByDateAndRestaurantId(date, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void saveMenuItems(@RequestParam Integer restaurantId, @RequestBody RestaurantTo restaurantTo) {
        Integer savedMenuSize = menuItemRepository.countAllByDateAndRestaurantId(restaurantTo.getDate(), restaurantId);
        validateMenuItemsOnSave(restaurantTo, savedMenuSize);
        menuItemRepository.saveAll(restaurantTo.getItems());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMenuByDate(@RequestParam Integer restaurantId, @RequestParam LocalDate date) {
        menuItemRepository.deleteAllByDateAndRestaurantId(date, restaurantId);
    }

    @GetMapping("{id}/")
    public MenuItem getMenuItemById(@PathVariable Integer id) {
        return menuItemRepository.findById(id).orElseThrow(() -> new NotFoundException("Menu item with this id not found"));
    }

    @PutMapping("{id}/")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateMenuItemById(@PathVariable Integer id, @RequestBody MenuItem item) {
        item.setId(id);
        menuItemRepository.save(item);
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMenuItemById(@PathVariable Integer id) {
        menuItemRepository.deleteById(id);
    }

}