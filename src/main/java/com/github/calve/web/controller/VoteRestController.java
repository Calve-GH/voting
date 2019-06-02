package com.github.calve.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    public static final String REST_URL = "/rest/vote/";

    @GetMapping
    public List<?> getVoteList() {
        return null;//TODO
    }

    @PostMapping("{restaurantId}/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void vote(@PathVariable Integer restaurantId) {
        //TODO
    }

    @GetMapping("history/")
    public List<?> getHistoryList(@RequestParam(required = false) LocalDate date,
                                  @RequestParam(required = false) Integer restaurantId) {
        return null;//TODO
    }
}
//@PropertySource("classpath:restaurant-vote.properties")
/*  public static final String REST_URL = "/rest/profile";

    private static Boolean CHANGE_VOTING_ENABLE = true;
    @Value("${system.vote.change.disable.start}")
    private String voteDisableStart;
    @Value("${system.vote.change.disable.end}")
    private String getVoteDisableEnd;

    private CrudMenuRepository menuRepo;
    private CrudVoteLogRepository voteLogRepo;
    private CrudHistoryRepo historyRepo;
    private CrudRestaurantRepository restaurantRepo;
    private UserService userService;
    private SystemService systemRepository;

    @Autowired
    public VoteRestController(CrudMenuRepository menuRepo, CrudVoteLogRepository voteLogRepo,
                              CrudHistoryRepo historyRepo, SystemService systemRepository,
                              CrudRestaurantRepository restaurantRepo, UserService userService) {
        this.menuRepo = menuRepo;
        this.voteLogRepo = voteLogRepo;
        this.historyRepo = historyRepo;
        this.restaurantRepo = restaurantRepo;
        this.userService = userService;
        this.systemRepository = systemRepository;
    }

    @PostMapping("/vote")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    void vote(@RequestParam Integer id) {
        User user = SecurityUtil.get().getUser();
        Restaurant restaurant = restaurantRepo.findById(id).orElse(null);
        ValidationUtil.checkNotFound(restaurant, "current id");
        if (restaurant.getMenuExist()) {
            VoteLog voteLog = new VoteLog(user, restaurant);
            if (CHANGE_VOTING_ENABLE)
                voteLogRepo.delete(voteLog.getUser().getId(), voteLog.getDate());
            try {
                voteLogRepo.save(voteLog);
            } catch (Exception e) {
                Throwable t = getCause(e);
                if (t.getMessage().contains(VOTE_LOG_UNIQUE_USER_DATE_IDX))
                    throw new StoreEntityException(VOTE_LOG_UNIQUE_USER_DATE_IDX_MSG);
            }

        }
    }

    @GetMapping
    List<Menu> getVoteList() {
        return menuRepo.findAllByDate(LocalDate.now());
    }

    @GetMapping("/history")
    List<HistoryItem> getVoteHistory(@RequestParam(required = false) LocalDate date) {
        if (date != null)
            return historyRepo.findAllByDate(date);
        return historyRepo.findAll();
    }

    @GetMapping("/history/{id}")
    List<HistoryItem> getVoteHistoryByRestaurant(@PathVariable Integer id) {
        return historyRepo.findByRestaurantId(id);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody UserTo userTo) throws Exception {
        User created = createUser(userTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Transactional
    User createUser(@RequestBody UserTo userTo) throws Exception {
        User user = UserUtil.createNewFromTo(userTo);
        checkNew(user);
        return userService.create(user);
    }

    @Scheduled(cron = "${system.change.vote.time}")
    private void blockChangeVotingState() {
        CHANGE_VOTING_ENABLE = false;
    }

    @Scheduled(cron = "${system.restart.time}")
    private void systemRestart() {
        systemRepository.resetAndLogVoteSystem();
        CHANGE_VOTING_ENABLE = true;
    }

    @PostConstruct
    private void initMethod() {
        LocalTime currentServerTime = LocalTime.now();
        if (currentServerTime.isAfter(LocalTime.parse(voteDisableStart)) && currentServerTime.isBefore(
                LocalTime.parse(getVoteDisableEnd)
        )) {
            CHANGE_VOTING_ENABLE = false;
        }
    }*/
