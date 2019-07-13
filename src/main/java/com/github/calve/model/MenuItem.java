package com.github.calve.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.StringJoiner;

@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "menu_item")
public class MenuItem extends AbstractBaseEntity {

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DATE_PATTERN)
//    @JsonIgnore
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties(value = {"name", "items"}, allowSetters = true)
//    @JsonIgnore
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Dish dish;

    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    public MenuItem(Integer id, Restaurant restaurant, Dish dish, Double price) {
        super(id);
        this.restaurant = restaurant;
        this.dish = dish;
        this.price = price;
    }

    public MenuItem(Integer id, LocalDate date, Dish dish, Double price) {
        super(id);
        this.date = date;
        this.dish = dish;
        this.price = price;
    }

    public MenuItem(Dish dish, Double price) {
        this.dish = dish;
        this.price = price;
    }

    public MenuItem(Restaurant restaurant, Dish dish, Double price) {
        this.restaurant = restaurant;
        this.dish = dish;
        this.price = price;
    }

    public MenuItem() {
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

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MenuItem.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("date=" + date)
                .add("restaurantId=" + restaurant.id)
                .add("dish=" + dish)
                .add("price=" + price)
                .toString();
    }
}
