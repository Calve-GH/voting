package com.github.calve.to;

import com.github.calve.model.MenuItem;
import com.github.calve.model.Restaurant;

import java.time.LocalDate;
import java.util.*;

public class RestaurantTo extends BaseTo { //TODO REFACTORING

    private LocalDate date;
    private Restaurant restaurant;
    private List<MenuItem> items;
    private Long count = 0L;

    public RestaurantTo() {
    }

    public RestaurantTo(Restaurant restaurant, Long count) {
        this.restaurant = restaurant;
        this.count = count;
    }

    public RestaurantTo(LocalDate date, List<MenuItem> items) {
        this.date = date;
        this.items = items;
    }

    public RestaurantTo(Integer id, LocalDate date, List<MenuItem> items) {
        super(id);
        this.date = date;
        this.items = items;
    }

    public RestaurantTo(LocalDate date, Restaurant restaurant, List<MenuItem> items) {
        this.date = date;
        this.restaurant = restaurant;
        this.items = items;
    }

    public RestaurantTo(LocalDate date, Restaurant restaurant, List<MenuItem> items, Long count) {
        this.date = date;
        this.restaurant = restaurant;
        this.items = items;
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RestaurantTo.class.getSimpleName() + "[", "]")
                .add("date=" + date)
                .add("restaurant=" + restaurant)
                .add("items=" + items)
                .add("count=" + count)
                .toString();
    }
}
