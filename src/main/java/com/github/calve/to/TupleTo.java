package com.github.calve.to;

import com.github.calve.model.Restaurant;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class TupleTo {
    public final LocalDate date;
    public final Restaurant restaurant;
    private final Long counter;

    public TupleTo(LocalDate date, Restaurant restaurant) {
        this.date = date;
        this.restaurant = restaurant;
        this.counter = 0L;
    }

    public TupleTo(LocalDate obj1, Restaurant obj2, Long counter) {
        this.date = obj1;
        this.restaurant = obj2;
        this.counter = counter;
    }

    public Long getCounter() {
        return counter;
    }

    public LocalDate getDate() {
        return date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TupleTo.class.getSimpleName() + "[", "]")
                .add("date=" + date)
                .add("restaurant=" + restaurant)
                .add("counter=" + counter)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TupleTo tupleTo = (TupleTo) o;
        return date.equals(tupleTo.date) &&
                restaurant.equals(tupleTo.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, restaurant);
    }
}
