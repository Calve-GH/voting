package com.github.calve.web;

import com.github.calve.model.Restaurant;
import com.github.calve.repository.datajpa.CrudRestaurantRepository;
import com.github.calve.web.controller.RestaurantRestController;
import com.github.calve.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.github.calve.TestData.*;
import static com.github.calve.TestUtil.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL;

    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    @Test
    void testGetAllRestaurantsSuccess() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Restaurant.class), RESTAURANT_LIST));
    }

    @Test
    void testGetAllRestaurantsAuthFail() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(TEST_USER_1)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testCreateNewRestaurantSuccess() throws Exception {
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue("Coco Bongo"))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void testCreateNewRestaurantFail() throws Exception {
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue("Hunter Village"))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void testFindByIdSuccess() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_1.getId() + "/")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Restaurant.class), RESTAURANT_1));
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
        mockMvc.perform(delete(REST_URL + RESTAURANT_1.getId() + "/")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertFalse(restaurantRepository.findAll().contains(RESTAURANT_1));
        restaurantRepository.findAll().forEach(System.out::println);
    }

    @Test
    void testDeleteByWrongIdFail() throws Exception {
        mockMvc.perform(delete(REST_URL + "200000/")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());
        restaurantRepository.findAll().forEach(System.out::println);
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void testUpdateByIdSuccess() throws Exception {
        mockMvc.perform(put(REST_URL + RESTAURANT_1.getId() + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue("RESTAURANT 1"))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void testUpdateWithSameNameFail() throws Exception {
        mockMvc.perform(put(REST_URL + RESTAURANT_1.getId() + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue("Hunter Village"))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}
