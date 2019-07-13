package com.github.calve.repository.datajpa;

import com.github.calve.model.MenuItem;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TableGenerator;
import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuItemRepository extends JpaRepository<MenuItem, Integer> {
    @Transactional
    MenuItem save(MenuItem item);

    @Transactional
    void delete(MenuItem item);

    @Transactional
    @Modifying
    void deleteAllByDateAndRestaurantId(LocalDate date, Integer id);

    @Transactional
    @Override
    void deleteAll();

    @Cacheable(value = "menuItemsCache")
    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    List<MenuItem> findAllByDateAndRestaurantId(LocalDate date, Integer id);

    Integer countAllByDateAndRestaurantId(LocalDate date, Integer id);

    @Override
    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    List<MenuItem> findAll();

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    List<MenuItem> findAllByDate(LocalDate date);

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    List<MenuItem> findAllByRestaurantId(Integer id);


}
