package com.github.calve.web;

import com.github.calve.repository.datajpa.CrudVoteLogRepository;
import com.github.calve.web.controller.VoteRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.github.calve.TestData.*;
import static com.github.calve.TestUtil.userHttpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL;

    @Autowired
    private CrudVoteLogRepository voteLogRepository;

    @Test
    void testGetDailyVoteListSuccess() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testVoteForRestaurantSuccess() throws Exception {
        mockMvc.perform(post(REST_URL + "100002/")
                .with(userHttpBasic(TEST_USER_3)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void testVoteForRestaurantFail() throws Exception {
        mockMvc.perform(post(REST_URL + "1000021/")
                .with(userHttpBasic(TEST_USER_3)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void testReVoteForRestaurant() throws Exception {

        if (LocalTime.now().isBefore(LocalTime.of(11, 0, 0))) {
            mockMvc.perform(post(REST_URL + "100002/")
                    .with(userHttpBasic(TEST_USER_2)))
                    .andDo(print())
                    .andExpect(status().isCreated());
        } else {
            mockMvc.perform(post(REST_URL + "100002/")
                    .with(userHttpBasic(TEST_USER_2)))
                    .andDo(print())
                    .andExpect(status().isConflict());
        }
    }

    @Test
    void testGetDailyVoteListAuthFail() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void voteGetAllHistory() throws Exception {
        mockMvc.perform(get(REST_URL + "history/")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void voteGetHistoryByDateAndRestaurantId() throws Exception {
        LocalDate date = LocalDate.now();
        mockMvc.perform(get(REST_URL + "history/?date=" + formatter.format(date) + "&restaurantId=" + RESTAURANT_1.getId())
                .with(userHttpBasic(TEST_USER_1)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
