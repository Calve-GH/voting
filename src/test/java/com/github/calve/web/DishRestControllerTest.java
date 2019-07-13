package com.github.calve.web;

import com.github.calve.model.Dish;
import com.github.calve.repository.datajpa.CrudDishRepository;
import com.github.calve.web.controller.DishRestController;
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

public class DishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishRestController.REST_URL;

    @Autowired
    private CrudDishRepository dishRepository;

    @Test
    void testGetAllDishesSuccess() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Dish.class), DISH_LIST));
    }

    @Test
    void testGetAllDishesAuthFail() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(TEST_USER_1)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testCreateNewDishSuccess() throws Exception {
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue("Coca Cola"))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void testCreateNewDishFail() throws Exception {
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue("Tea"))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void testFindByIdSuccess() throws Exception {
        mockMvc.perform(get(REST_URL + DISH_1.getId() + "/")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Dish.class), DISH_1));
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
        mockMvc.perform(delete(REST_URL + DISH_1.getId() + "/")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertFalse(dishRepository.findAll().contains(DISH_1));
    }

    @Test
    void testDeleteByWrongIdFail() throws Exception {
        mockMvc.perform(delete(REST_URL + "200000/")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateByIdSuccess() throws Exception {
        mockMvc.perform(put(REST_URL + DISH_1.getId() + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue("Cream Soup"))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void testUpdateWithSameNameFail() throws Exception {
        mockMvc.perform(put(REST_URL + DISH_1.getId() + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue("Tea"))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}
