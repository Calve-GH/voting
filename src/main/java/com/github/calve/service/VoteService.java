package com.github.calve.service;

import com.github.calve.to.RestaurantTo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteService {

    List<RestaurantTo> getVoteList();

    void vote(Integer userId, Integer restaurantId);

    List<RestaurantTo> getHistory(Optional<LocalDate> dateOptional, Optional<Integer> idOptional);
}
