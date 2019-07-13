package com.github.calve.service;

import com.github.calve.model.MenuItem;
import com.github.calve.model.Restaurant;
import com.github.calve.model.User;
import com.github.calve.model.VoteLog;
import com.github.calve.repository.datajpa.CrudMenuItemRepository;
import com.github.calve.repository.datajpa.CrudRestaurantRepository;
import com.github.calve.repository.datajpa.CrudUserRepository;
import com.github.calve.repository.datajpa.CrudVoteLogRepository;
import com.github.calve.to.RestaurantTo;
import com.github.calve.to.TupleTo;
import com.github.calve.util.RestaurantToValidator;
import com.github.calve.util.exception.NotFoundException;
import com.github.calve.web.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service("voteService")
public class VoteServiceImpl implements VoteService {


    private CrudRestaurantRepository restaurantRepository;

    private CrudMenuItemRepository menuItemRepository;

    private CrudVoteLogRepository voteLogRepository;

    private CrudUserRepository userRepository;

    @Autowired
    public VoteServiceImpl(CrudRestaurantRepository restaurantRepository, CrudMenuItemRepository menuItemRepository,
                           CrudVoteLogRepository voteLogRepository, CrudUserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.voteLogRepository = voteLogRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<RestaurantTo> getVoteList() {
        return restaurantRepository.getRestaurantToWithVotes()
                .parallelStream()
                .filter(RestaurantToValidator::validateMenuItemsRange)
                .collect(toList());
    }

    @Override
    public void vote(Integer userId, Integer restaurantId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (user.isPresent() && restaurant.isPresent()) {
            if (LocalTime.now().isBefore(LocalTime.of(11, 0, 0))) {
                voteLogRepository.delete(user.get().getId(), LocalDate.now());
            }
            voteLogRepository.save(new VoteLog(user.get(), restaurant.get()));
        } else {
            throw new NotFoundException("Restaurant with this id not found");
        }
    }

    @Override
    public List<RestaurantTo> getHistory(Optional<LocalDate> dateOptional, Optional<Integer> idOptional) {
        List<MenuItem> list;
        List<TupleTo> votes;
        if (dateOptional.isPresent() && idOptional.isPresent() && dateOptional.get().isBefore(LocalDate.now())) {
            list = menuItemRepository.findAllByDateAndRestaurantId(dateOptional.get(), idOptional.get());
            votes = voteLogRepository.countVoteLogsByDateAndRestaurant(dateOptional.get(), idOptional.get());
        } else if (dateOptional.isPresent() && dateOptional.get().isBefore(LocalDate.now())) {
            list = menuItemRepository.findAllByDate(dateOptional.get());
            votes = voteLogRepository.countVoteLogsByDate(dateOptional.get());
        } else if (idOptional.isPresent()) {
            list = menuItemRepository.findAllByRestaurantId(idOptional.get());
            votes = voteLogRepository.countVoteLogsByRestaurant(idOptional.get());
        } else {
            list = menuItemRepository.findAll();
            votes = voteLogRepository.countVoteLogs();
        }

        Map<TupleTo, List<MenuItem>> mapByDate = list.stream().collect(groupingBy(mi ->
                new TupleTo(mi.getDate(), mi.getRestaurant())));

        return votes.stream()
                .map(v -> new RestaurantTo(v.date, v.restaurant, mapByDate.get(v), v.getCounter())).collect(toList());
    }
}
