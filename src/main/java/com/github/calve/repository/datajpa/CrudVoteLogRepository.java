package com.github.calve.repository.datajpa;

import com.github.calve.model.VoteLog;
import com.github.calve.to.TupleTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteLogRepository extends JpaRepository<VoteLog, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM VoteLog v WHERE v.user.id=:id AND v.date=:date")
    int delete(@Param("id") int userId, @Param("date") LocalDate date);

    @Transactional
    @Override
    VoteLog save(VoteLog voteLog);

    List<VoteLog> findAll();

    @Transactional
    @Override
    void deleteAll();

    Long countVoteLogsByDateAndRestaurantId(LocalDate date, Integer id);

/*    @Query("select new com.github.calve.to.RestaurantTo(r, count(v.id)) from Restaurant r left join VoteLog v " +
            "on v.restaurant.id = r.id where r.id IN (SELECT mi.restaurant.id FROM MenuItem mi " +
            "WHERE mi.date =current_date GROUP BY mi.restaurant.id) AND v.date = current_date OR v.date=null group by r.id")*/
    //TODO BEFORE
    @Query("SELECT new com.github.calve.to.TupleTo(v.date, v.restaurant, count(v.id)) FROM VoteLog v WHERE v.date =:date AND v.restaurant.id =:restaurantId GROUP BY v.date, v.restaurant")
    List<TupleTo> countVoteLogsByDateAndRestaurant(@Param("date")LocalDate date, @Param("restaurantId")Integer restaurantId);

    @Query("SELECT new com.github.calve.to.TupleTo(v.date, v.restaurant, count(v.id)) FROM VoteLog v WHERE v.date =:date GROUP BY v.date, v.restaurant")
    List<TupleTo> countVoteLogsByDate(@Param("date")LocalDate date);

    @Query("SELECT new com.github.calve.to.TupleTo(v.date, v.restaurant, count(v.id)) FROM VoteLog v WHERE v.restaurant.id =:restaurantId AND v.date < CURRENT_DATE GROUP BY v.date, v.restaurant")
    List<TupleTo> countVoteLogsByRestaurant(@Param("restaurantId")Integer restaurantId);

    @Query("SELECT new com.github.calve.to.TupleTo(v.date, v.restaurant, count(v.id)) FROM VoteLog v WHERE v.date < CURRENT_DATE GROUP BY v.date, v.restaurant")
    List<TupleTo> countVoteLogs();

}
