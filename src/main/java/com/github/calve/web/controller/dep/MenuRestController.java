package com.github.calve.web.controller.dep;

//@RestController
//@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {

   /* public static final String REST_URL = "/rest/admin";

    private CrudMenuRepository menuRepo;
    private CrudDishRepository dishRepo;
    private CrudRestaurantRepository restaurantRepo;

    @Autowired
    public MenuRestController(CrudMenuRepository menuRepo, CrudDishRepository dishRepo,
                              CrudRestaurantRepository restaurantRepo) {
        this.menuRepo = menuRepo;
        this.dishRepo = dishRepo;
        this.restaurantRepo = restaurantRepo;
    }

    @GetMapping("/menu")
    public List<Menu> getMenus(@RequestParam(required = false) LocalDate date,
                               @RequestParam(required = false) Integer restaurantId) {

        if (date == null && restaurantId == null) {
            return menuRepo.findAll();
        }
        if (date == null) {
            return menuRepo.findAllByRestaurantId(restaurantId);
        }
        if (restaurantId == null) {
            return menuRepo.findAllByDate(date);
        }
        return Arrays.asList(menuRepo.findByDateAndRestaurantId(date, restaurantId));
    }

    @PostMapping(value = "/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Menu> saveMenu(@Valid @RequestBody MenuTo menuTo) {
        Menu newMenu = createNewFromTo(menuTo);
        checkNew(newMenu);
        try {
            newMenu = menuRepo.save(newMenu);
        } catch (Exception e) {
            Throwable t = getCause(e);
            if (t.getMessage().contains(MENU_UNIQUE_RESTAURANT_DATE_IDX))
                throw new StoreEntityException(MENU_UNIQUE_RESTAURANT_DATE_IDX_MSG);
        }
        URI newMenuUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(REST_URL + "/menu/"
                + newMenu.getId()).buildAndExpand().toUri();
        return ResponseEntity.created(newMenuUri).body(newMenu);
    }

    @PutMapping(value = "/menu/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateMenu(@Valid @RequestBody MenuTo menuTo, @PathVariable int id) {
        assureIdConsistent(menuTo, id);
        try {
            menuRepo.save(createFromTo(menuTo));
        } catch (Exception e) {
            Throwable t = getCause(e);
            if (t.getMessage().contains(MENU_UNIQUE_RESTAURANT_DATE_IDX))
                throw new StoreEntityException(MENU_UNIQUE_RESTAURANT_DATE_IDX_MSG);
        }
    }

    @DeleteMapping("/menu/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenu(@PathVariable Integer id) {
        checkNotFoundWithId(menuRepo.delete(id) != 0, id);
    }

    @GetMapping("/dish")
    public List<Dish> getDishes() {
        return dishRepo.findAll();
    }

    @PostMapping("/dish")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void saveDish(@RequestBody Dish dish) {
        try {
            dishRepo.save(dish);
        } catch (Exception e) {
            Throwable t = getCause(e);
            if (t.getMessage().contains(DISH_UNIQUE_NAME_IDX))
                throw new StoreEntityException(DISH_UNIQUE_NAME_IDX_MSG);
        }
    }

    @DeleteMapping("/dish/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable Integer id) {
        checkNotFoundWithId(dishRepo.delete(id) != 0, id);
    }

    @GetMapping("/restaurant")
    public List<Restaurant> getRestaurants() {
        return restaurantRepo.findAll();
    }

    @PostMapping("/restaurant")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void saveRestaurant(@RequestBody Restaurant restaurant) {
        try {
            restaurantRepo.save(restaurant);
        } catch (Exception e) {
            Throwable t = getCause(e);
            if (t.getMessage().contains(RESTAURANT_UNIQUE_NAME_IDX))
                throw new StoreEntityException(RESTAURANT_UNIQUE_NAME_IDX_MSG);
        }
    }

    @DeleteMapping("/restaurant/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable Integer id) {
        checkNotFoundWithId(restaurantRepo.delete(id) != 0, id);
    }*/
}
