package com.github.calve.web;

import com.github.calve.model.MenuItem;
import com.github.calve.repository.datajpa.CrudMenuItemRepository;
import com.github.calve.to.RestaurantTo;
import com.github.calve.util.exception.ErrorInfo;
import com.github.calve.web.controller.MenuItemsRestController;
import com.github.calve.web.json.JsonUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.github.calve.TestData.*;
import static com.github.calve.TestUtil.*;
import static com.github.calve.model.AbstractBaseEntity.START_SEQ;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuItemsRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuItemsRestController.REST_URL;

    @Autowired
    private CrudMenuItemRepository menuItemRepository;

    @Test
    void testGetAllMenuItemsByRestaurantSuccess() throws Exception {
        MenuItem mi1 = new MenuItem(START_SEQ + 14, LocalDate.now(), DISH_6, 5.0);
        MenuItem mi2 = new MenuItem(START_SEQ + 15, LocalDate.now(), DISH_2, 11.0);
        mockMvc.perform(get(REST_URL + "?restaurantId=100001")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, MenuItem.class), Arrays.asList(mi1, mi2)));
    }

    @Test
    @Disabled
    void testGetAllMenuItemsByRestaurantAndDateSuccess() throws Exception {
        LocalDate localDate = LocalDate.now().plusDays(1);

        mockMvc.perform(get(REST_URL + "?restaurantId=100000&date=" + formatter.format(localDate))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, MenuItem.class), MENU_ITEMS_2));
    }

    @Test
    void testGetAllWithoutParameterFalse() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testCreateNewDishSuccess() throws Exception {

        LocalDate localDate = LocalDate.now().plusDays(3);

        MENU_ITEM_1.setId(null);
        MENU_ITEM_2.setId(null);

        List<MenuItem> list = Arrays.asList(MENU_ITEM_1, MENU_ITEM_2);

        RestaurantTo restaurantTo = new RestaurantTo(localDate, list);

        mockMvc.perform(post(REST_URL + "?restaurantId=100001")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(restaurantTo))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void testGetMenuItemsAuthFail() throws Exception {
        mockMvc.perform(get(REST_URL + "?restaurantId=100001")
                .with(userHttpBasic(TEST_USER_1)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void testDeleteMenuItemsByDate() throws Exception {
        mockMvc.perform(delete(REST_URL + "?restaurantId=100000&date=" + formatter.format(LocalDate.now()))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void testFindByIdSuccess() throws Exception {
        mockMvc.perform(get(REST_URL + MENU_ITEM_1.getId() + "/")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, MenuItem.class), MENU_ITEM_1));
    }

    @Test
    void testFindByWrongIdFail() throws Exception {
        mockMvc.perform(get(REST_URL + "200000/")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testDeleteByIdSuccess() throws Exception {
        mockMvc.perform(delete(REST_URL + "100009/")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteByWrongIdFail() throws Exception {
        mockMvc.perform(delete(REST_URL + "200000/")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testUpdateByIdSuccess() throws Exception {

        MenuItem menuItem = new MenuItem(MENU_ITEM_1.getId(), LocalDate.now(), DISH_6, 33.3);        menuItem.setRestaurant(RESTAURANT_1);

        mockMvc.perform(put(REST_URL + "100009/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(menuItem))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());

        menuItemRepository.findAllByRestaurantId(100000).forEach(System.out::println);
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void testUpdateWithSameDishFail() throws Exception {

        MENU_ITEM_2.setDish(DISH_1);
        MENU_ITEM_2.setRestaurant(RESTAURANT_1);
        MENU_ITEM_2.setPrice(100.1);

        mockMvc.perform(put(REST_URL + MENU_ITEM_2.getId() + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(MENU_ITEM_2))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isConflict());
    }


}
