package com.github.calve.repository.datajpa;

import com.github.calve.model.MenuItem;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuItemRepository extends JpaRepository<MenuItem, Integer> {
    @Transactional
    MenuItem save(MenuItem item);

    @Transactional
    void delete(MenuItem item);

    List<MenuItem> findAll();

    @Transactional
    @Override
    void deleteAll();

    List<MenuItem> findAllByDate(LocalDate date);

    @Cacheable(value = "menuItemsCache")
    List<MenuItem> findAllByDateAndRestaurantId(LocalDate date, Integer id);

    @Query("SELECT m.date, m.restaurant.id, count(m) as cnt FROM MenuItem m group by m.date, m.restaurant.id")
    List<?> findAllMenus();
}
