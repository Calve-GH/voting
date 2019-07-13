package com.github.calve;

import com.github.calve.model.*;
import com.github.calve.util.exception.ErrorInfo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.github.calve.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class TestData {

    public static final Restaurant RESTAURANT_1 = new Restaurant(START_SEQ, "Sweet bobaleh");
    public static final Restaurant RESTAURANT_2 = new Restaurant(START_SEQ + 1, "ITAKA");
    public static final Restaurant RESTAURANT_3 = new Restaurant(START_SEQ + 2, "Hunter Village");

    public static final List<Restaurant> RESTAURANT_LIST = Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);

    public static final Dish DISH_1 = new Dish(START_SEQ + 3, "Soup");
    public static final Dish DISH_2 = new Dish(START_SEQ + 4, "French fries");
    public static final Dish DISH_3 = new Dish(START_SEQ + 5, "Hamburger");
    public static final Dish DISH_4 = new Dish(START_SEQ + 6, "Tea");
    public static final Dish DISH_5 = new Dish(START_SEQ + 7, "Coffee");
    public static final Dish DISH_6 = new Dish(START_SEQ + 8, "Juice");

    public static final List<Dish> DISH_LIST = Arrays.asList(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6);

    public static final MenuItem MENU_ITEM_1 = new MenuItem(START_SEQ + 14, LocalDate.now(), DISH_6, 5.0);
    public static final MenuItem MENU_ITEM_2 = new MenuItem(START_SEQ + 15, LocalDate.now(), DISH_2, 11.0);

    public static final List<MenuItem> MENU_ITEMS_1 = Arrays.asList(MENU_ITEM_1, MENU_ITEM_2);

    public static final MenuItem MENU_ITEM_3 = new MenuItem(START_SEQ + 18, LocalDate.now().plusDays(1), DISH_3, 15.0);
    public static final MenuItem MENU_ITEM_4 = new MenuItem(START_SEQ + 19, LocalDate.now().plusDays(1), DISH_4, 2.0);
    public static final MenuItem MENU_ITEM_5 = new MenuItem(START_SEQ + 20, LocalDate.now().plusDays(1), DISH_5, 3.0);
    public static final MenuItem MENU_ITEM_NEW = new MenuItem(RESTAURANT_3, DISH_5, 3.0);

    public static final List<MenuItem> MENU_ITEMS_2 = Arrays.asList(MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5);

    public static final User TEST_ADMIN_1 =
            new User(START_SEQ + 24, "Ivanov", "ivanov@gmail.com", "1234567", true, Collections.singletonList(Role.ROLE_ADMIN));
    public static final User TEST_USER_1 =
            new User(START_SEQ + 25, "Petrov", "petrov@gmail.com", "9876543", true, Collections.singletonList(Role.ROLE_USER));
    public static final User TEST_USER_2 =
            new User(START_SEQ + 26, "Sidorov", "sidirov@mail.ru", "12351514", true, Collections.singletonList(Role.ROLE_USER));
    public static final User TEST_USER_3 =
            new User(START_SEQ + 27, "Davidov", "davidov009@gmail.com", "1234567", true, Collections.singletonList(Role.ROLE_USER));

    public static final VoteLog VOTE_LOG_1 = new VoteLog(TEST_ADMIN_1, RESTAURANT_1);   //32
    public static final VoteLog VOTE_LOG_2 = new VoteLog(TEST_USER_1, RESTAURANT_1);    //33
    public static final VoteLog VOTE_LOG_3 = new VoteLog(TEST_USER_2, RESTAURANT_2);    //34

    public static final ErrorInfo ERROR_INFO_MISSING_RESTAURANT_ID = new ErrorInfo("http://localhost/rest/admin/items/", "Required Integer parameter 'restaurantId' is not present");

    public static final Dish NEW_DISH_1 = new Dish("Sandwich");

    public static final Restaurant NEW_RESTAURANT_1 = new Restaurant("CoCo Bongo");

    public static final VoteLog VOTE_LOG_NEW = new VoteLog(TEST_USER_3, RESTAURANT_1);

    public static final Dish DISH_7 = new Dish(START_SEQ + 31, "Juice");

    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static <T> void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static <T> void assertMatch(Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("id").isEqualTo(expected);
    }
}