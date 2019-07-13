package com.github.calve.util;

import com.github.calve.model.Restaurant;
import com.github.calve.to.RestaurantTo;
import com.github.calve.util.exception.IllegalRequestDataException;

import java.time.LocalDate;

public class RestaurantToValidator {

    private RestaurantToValidator() {
    }

    public static void validateMenuItemsOnSave(RestaurantTo restaurantTo, Integer savedMenuSize) {
        validateOnSaveDate(restaurantTo.getDate());
        if (restaurantTo.getItems().size() + savedMenuSize > 5)
            throw new IllegalRequestDataException(restaurantTo.getDate() + " To large menu list size in current date, must be in range[2,5]." +
                    " Please check already saved items");
    }

    public static void validateOnSaveDate(LocalDate date) {
        if(date.isBefore(LocalDate.now())) {
            throw new IllegalRequestDataException(date + " must be now or after");
        }
    }

    public static boolean validateMenuItemsRange(RestaurantTo restaurantTo) {
        Restaurant restaurant = restaurantTo.getRestaurant();
        return restaurant.getItems().size() >= 2 && restaurant.getItems().size() <= 5;
    }
}
