package com.github.calve.web.controller;

import com.github.calve.model.Restaurant;
import com.github.calve.model.User;
import com.github.calve.model.VoteLog;
import com.github.calve.repository.datajpa.CrudRestaurantRepository;
import com.github.calve.repository.datajpa.CrudUserRepository;
import com.github.calve.repository.datajpa.CrudVoteLogRepository;
import com.github.calve.service.VoteService;
import com.github.calve.to.RestaurantTo;
import com.github.calve.util.exception.NotFoundException;
import com.github.calve.web.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    public static final String REST_URL = "/rest/vote/";

    private CrudRestaurantRepository restaurantRepository;
    private CrudUserRepository userRepository;
    private CrudVoteLogRepository voteLogRepository;
    private VoteService voteService;


    @Autowired
    public VoteRestController(CrudRestaurantRepository restaurantRepository, VoteService voteService,
                              CrudUserRepository userRepository, CrudVoteLogRepository voteLogRepository) {
        this.restaurantRepository = restaurantRepository;
        this.voteService = voteService;
        this.userRepository = userRepository;
        this.voteLogRepository = voteLogRepository;
    }

    @GetMapping
    public List<RestaurantTo> getVoteList() {
        return voteService.getVoteList();
    }

    @PostMapping("{restaurantId}/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void vote(@PathVariable Integer restaurantId) {
        voteService.vote(SecurityUtil.authUserId(), restaurantId);
    }

    @GetMapping("history/")
    public List<RestaurantTo> getHistoryList(@RequestParam(required = false) LocalDate date,
                                             @RequestParam(required = false) Integer restaurantId) {
        return voteService.getHistory(Optional.ofNullable(date), Optional.ofNullable(restaurantId));
    }
}
