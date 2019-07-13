package com.github.calve.repository;

import com.github.calve.model.Dish;
import com.github.calve.model.MenuItem;
import com.github.calve.model.Restaurant;
import com.github.calve.model.VoteLog;
import com.github.calve.repository.datajpa.CrudDishRepository;
import com.github.calve.repository.datajpa.CrudMenuItemRepository;
import com.github.calve.repository.datajpa.CrudRestaurantRepository;
import com.github.calve.repository.datajpa.CrudVoteLogRepository;
import com.github.calve.service.VoteService;
import com.github.calve.to.RestaurantTo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.github.calve.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitWebConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Transactional
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DataJpaRepositoryTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CrudMenuItemRepository menuItemRepository;
    @Autowired
    private CrudRestaurantRepository restaurantRepository;
    @Autowired
    private CrudVoteLogRepository voteLogRepository;
    @Autowired
    private CrudDishRepository dishRepository;
    @Autowired
    private VoteService voteService;

    @Test
    void findAllDishes() {
        assertMatch(dishRepository.findAll().size(), 6);
    }

    @Test
    void findAllRestaurants() {
        assertEquals(restaurantRepository.findAll(), Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3));
    }

    @Test
    void findAllVoteLogs() {
        assertMatch(voteLogRepository.findAll().size(), 3);
    }

    @Test
    void saveDish() {
        Dish save = dishRepository.save(NEW_DISH_1);
        NEW_DISH_1.setId(save.getId());
        assertEquals(save, NEW_DISH_1);
    }

    @Test
    void saveRestaurant() {
        Restaurant save = restaurantRepository.save(NEW_RESTAURANT_1);
        NEW_RESTAURANT_1.setId(save.getId());
        assertEquals(save, NEW_RESTAURANT_1);

    }

    @Test
    void saveVoteLog() {
        VoteLog save = voteLogRepository.save(VOTE_LOG_NEW);
        VOTE_LOG_NEW.setId(save.getId());
        assertEquals(save, VOTE_LOG_NEW);
    }

    @Test
    void saveMenuItem() {
        MENU_ITEM_NEW.setDate(LocalDate.now());
        MenuItem save = menuItemRepository.save(MENU_ITEM_NEW);
        MENU_ITEM_NEW.setId(save.getId());
        assertEquals(save, MENU_ITEM_NEW);
    }

    @Test
    void findDishById() {
        assertEquals(dishRepository.findById(DISH_1.getId()).orElse(null), DISH_1);
    }

    @Test
    void findRestaurantById() {
        assertEquals(restaurantRepository.findById(RESTAURANT_1.getId()).orElse(null), RESTAURANT_1);
    }

    @Test
    void findVoteLogById() {
        assertEquals(voteLogRepository.findById(100028).get().getUser(), TEST_ADMIN_1);//TODO
    }

    @Test
    void findMenuItemById() {
        assertEquals(menuItemRepository.findById(MENU_ITEM_1.getId()).orElse(null), MENU_ITEM_1);
    }

    @Test
    void deleteDish() {
        dishRepository.delete(DISH_1);
        assertEquals(dishRepository.findAll().size(), 5);
    }

    @Test
    void deleteRestaurant() {
        restaurantRepository.delete(RESTAURANT_1);
        assertEquals(restaurantRepository.findAll().size(), 2);
    }

    @Test
    void deleteVoteLogByIdAndDate() {
        voteLogRepository.delete(100024, LocalDate.now());
        assertEquals(voteLogRepository.findAll().size(), 2);
    }

    @Test
    void deleteMenuItemById() {
        menuItemRepository.deleteById(100009);
        assertEquals(menuItemRepository.findAllByDateAndRestaurantId(LocalDate.now(), RESTAURANT_1.getId()).size(), 4);
    }

    @Test
    void findMenuItemsByRestaurantId() {
        assertEquals(menuItemRepository.findAllByRestaurantId(RESTAURANT_1.getId()).size(), 11);
    }

    @Test
    void findMenuItemsByDateAndRestaurantId() {
        assertEquals(menuItemRepository.findAllByDateAndRestaurantId(LocalDate.now(), RESTAURANT_2.getId()).size(), 2);
    }

    @Test
    void countAllByDateAndRestaurantId() {
        assertEquals(menuItemRepository.countAllByDateAndRestaurantId(LocalDate.now(), RESTAURANT_2.getId()), 2);
    }

    @Test
    void saveMenuItems() {
        MENU_ITEM_3.setRestaurant(RESTAURANT_2);
        MENU_ITEM_4.setRestaurant(RESTAURANT_2);
        MENU_ITEM_5.setRestaurant(RESTAURANT_2);
        List<MenuItem> menuItems = menuItemRepository.saveAll(MENU_ITEMS_2);
        assertEquals(menuItemRepository.findAllByDateAndRestaurantId(LocalDate.now().plusDays(1), RESTAURANT_2.getId()), menuItems);
    }

    @Test
    @Disabled
    @Transactional(propagation = Propagation.NEVER)
    void deleteMenuItemsByDateAndRestaurantId() {
        menuItemRepository.deleteAllByDateAndRestaurantId(LocalDate.now(), RESTAURANT_1.getId());
        assertEquals(menuItemRepository.findAllByDateAndRestaurantId(LocalDate.now(), RESTAURANT_1.getId()).size(), 0);
    }

    @Test
    void test() {
        LocalDate date = LocalDate.now();
        Integer restaurantId = 100000;
        List<RestaurantTo> history = voteService.getHistory(Optional.of(date), Optional.of(restaurantId));
        System.out.println(history);
    }

    @Test
    void test1() {
        LocalDate date = LocalDate.now();
        List<RestaurantTo> history = voteService.getHistory(Optional.of(date), Optional.empty());
        System.out.println(history);
    }

    @Test
    void test3() {
        LocalDate date = LocalDate.now();
        Integer restaurantId = 100001;
        List<RestaurantTo> history = voteService.getHistory(Optional.empty(), Optional.of(restaurantId));
        System.out.println(history);
    }

    @Test
    void test4() {
        LocalDate date = LocalDate.now();
        Integer restaurantId = 100000;
        List<RestaurantTo> history = voteService.getHistory(Optional.empty(), Optional.empty());
        System.out.println(history);
    }
}
