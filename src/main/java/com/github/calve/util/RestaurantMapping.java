package com.github.calve.util;

import com.github.calve.model.Restaurant;

public class RestaurantMapping {
    private Restaurant restaurant;
    private Long count;

    public RestaurantMapping(Restaurant restaurant, Long count) {
        this.restaurant = restaurant;
        this.count = count;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RestaurantMapping{");
        sb.append("restaurant=").append(restaurant);
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}
