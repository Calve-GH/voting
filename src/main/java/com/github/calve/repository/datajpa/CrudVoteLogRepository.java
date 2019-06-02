package com.github.calve.repository.datajpa;

import com.github.calve.model.User;
import com.github.calve.model.VoteLog;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudVoteLogRepository extends JpaRepository<VoteLog, Integer> {

    Optional<VoteLog> findByUserId(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM VoteLog v WHERE v.user.id=:id AND v.date=:date")
    int delete(@Param("id") int userId, @Param("date") LocalDate date);

    @Transactional
    @Override
    VoteLog save(VoteLog voteLog);

    VoteLog findByDateAndUser(LocalDate date, User user);

    List<VoteLog> findAll();

    @EntityGraph(attributePaths = {"user", "restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v FROM VoteLog v ORDER BY v.date DESC")
    List<VoteLog> findAllWithReferences();

    @Transactional
    @Override
    void deleteAll();

    @Transactional
    void deleteAllByDateBefore(LocalDate date);

    //@Query("SELECT vl.date, vl.restaurant.id, COUNT(vl) FROM VoteLog vl group by vl.date, vl.restaurant.id")
    Long countVoteLogsByDateAndRestaurantId(LocalDate date, Integer id);




/*    The following query counts for every letter the number of countries with names that
    start with that letter and the number of different currencies that are used by these countries:

    SELECT SUBSTRING(c.name, 1, 1), COUNT(c), COUNT(DISTINCT c.currency)
    FROM Country c
    GROUP BY SUBSTRING(c.name, 1, 1);*/

}
