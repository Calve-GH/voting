package com.github.calve.repository.datajpa;

import com.github.calve.model.Restaurant;
import com.github.calve.util.MenuItemMappring;
import com.github.calve.util.RestaurantMapping;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Override
    Optional<Restaurant> findById(Integer id);

    @EntityGraph(attributePaths = {"items"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=:id")
    Restaurant getByIdWithItems(@Param("id")Integer id);

    //    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") Integer id);

    //    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    Restaurant save(Restaurant restaurant);

    //    @Cacheable("restaurants")
    @Query("SELECT r FROM Restaurant r ORDER BY r.name DESC")
    List<Restaurant> getAll();

    //SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1
    //@Query("SELECT vl.date, vl.restaurant.id, COUNT(vl) FROM VoteLog vl group by vl.date, vl.restaurant.id")
/*    @Query("SELECT r, count(vl) as vote_count FROM Restaurant r LEFT OUTER JOIN " +
            "(SELECT vl.restaurant.id as rest_id, count(*) FROM VoteLog vl GROUP BY vl.date, vl.restaurant.id) x " +
            "ON r.id = x.rest_id")*/

    //@Query("SELECT r.id, count(vl.id) FROM Restaurant r LEFT JOIN VoteLog vl ON vl.restaurant.id=r.id GROUP BY vl.date, vl.restaurant.id")
    //@EntityGraph(attributePaths = {"items"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select new com.github.calve.util.RestaurantMapping(r, count(v.id)) from Restaurant r left join VoteLog v " +
            "on v.restaurant.id = r.id where r.id IN (SELECT mi.restaurant.id FROM MenuItem mi " +
            "WHERE mi.date =current_date GROUP BY mi.restaurant.id) group by r.id")
    List<RestaurantMapping> getAllWithSome();


    @Query("SELECT mi.restaurant.id FROM MenuItem mi WHERE mi.date =current_date GROUP BY mi.restaurant.id")
    List<Integer> getAllWithSome1();

/*    SELECT post.id, post.title, user.id AS uid, username, count(post_comments.comment_id) as comment_count
    FROM `post`
    LEFT JOIN post_user ON post.id = post_user.post_id
    LEFT JOIN user ON user.id = post_user.user_id
    LEFT JOIN post_comments ON post_comments.post_id = post.id GROUP BY post.id
    ORDER BY post_date DESC*/

}
