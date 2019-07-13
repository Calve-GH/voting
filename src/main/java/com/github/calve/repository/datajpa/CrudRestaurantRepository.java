package com.github.calve.repository.datajpa;

import com.github.calve.model.Restaurant;
import com.github.calve.to.RestaurantTo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @EntityGraph(attributePaths = {"items"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=:id")
    Restaurant getByIdWithItems(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") Integer id);

    @Query("select new com.github.calve.to.RestaurantTo(r, count(v.id)) from Restaurant r left join VoteLog v " +
            "on v.restaurant.id = r.id where r.id IN (SELECT mi.restaurant.id FROM MenuItem mi " +
            "WHERE mi.date =current_date GROUP BY mi.restaurant.id) AND v.date = current_date OR v.date=null group by r.id")
    List<RestaurantTo> getRestaurantToWithVotes();

    //  @Query("SELECT r FROM Restaurant r left join (SELECT v.restaurant.id, count(v.id) as cnt FROM VoteLog v WHERE v.date=current_date GROUP BY v.restaurant.id)")
    //List<?> getAllWithSome1();
}
