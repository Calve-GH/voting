package com.github.calve.repository;

import com.github.calve.model.MenuItem;
import com.github.calve.model.Restaurant;
import com.github.calve.repository.datajpa.CrudDishRepository;
import com.github.calve.repository.datajpa.CrudMenuItemRepository;
import com.github.calve.repository.datajpa.CrudRestaurantRepository;
import com.github.calve.repository.datajpa.CrudVoteLogRepository;
import com.github.calve.util.MenuItemMappring;
import com.github.calve.util.RestaurantMapping;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Test
    void firstTest() {
        menuItemRepository.findAllByDateAndRestaurantId(LocalDate.now(), 100000).forEach(System.out::println);
    }

    @Test
    void secondTest() {
        Optional<Restaurant> byId = restaurantRepository.findById(100000);
        for (MenuItem item : byId.get().getItems()) {
            log.info(item.toString());
        }
    }

    @Test
    void thirdTest() {
        Long aLong = voteLogRepository.countVoteLogsByDateAndRestaurantId(LocalDate.now(), 100001);
        log.info("Count of rows in db = {}", aLong.toString());
    }

    @Test
    void forthTest() {
        List<?> allMenus = menuItemRepository.findAllMenus();
        System.out.println(allMenus);
    }

    @Test
    void fifthTest() {
        //restaurantRepository.getAllWithSome().forEach(System.out::println);
        restaurantRepository.findAll();
        dishRepository.findAll();

        List<RestaurantMapping> allWithSome = restaurantRepository.getAllWithSome();

        for (RestaurantMapping em : allWithSome){
            System.out.println(em.getRestaurant().getId() + " " + em.getRestaurant().getName() + " " + em.getCount());
            em.getRestaurant().setItems(menuItemRepository.findAllByDateAndRestaurantId(LocalDate.now(), em.getRestaurant().getId()));
            System.out.println(em.getRestaurant().getItems());
        }

        for (RestaurantMapping em : allWithSome){
            System.out.println(em.getRestaurant().getId() + " " + em.getRestaurant().getName() + " " + em.getCount());
            em.getRestaurant().setItems(menuItemRepository.findAllByDateAndRestaurantId(LocalDate.now(), em.getRestaurant().getId()));
            System.out.println(em.getRestaurant().getItems());
        }


    }
    @Test
    void sixTest() {
        //restaurantRepository.getAllWithSome().forEach(System.out::println);
        List<Integer> allWithSome = restaurantRepository.getAllWithSome1();

        allWithSome.forEach(System.out::println);

    }

    @Test
    void seventhTest() {
        Restaurant byIdWithItems = restaurantRepository.getByIdWithItems(100000);
        System.out.println(byIdWithItems.toStringWithItems());
    }

}
