package com.mds.task.stock_task_mds.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mds.task.stock_task_mds.StockTaskMdsApplication;
import com.mds.task.stock_task_mds.businessservices.ProfitService;
import com.mds.task.stock_task_mds.transferobjects.CheckRequestDTO;
import com.mds.task.stock_task_mds.transferobjects.CompoundCheckResponseDTO;

@SpringBootTest(classes = StockTaskMdsApplication.class)
@AutoConfigureMockMvc
public class ProfitControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper; 
    @Autowired
    private ProfitService profitService;

    private static final String ENDPOINT = "/api/v1/profit/check";

    @Test
    void checkProfitIntegrationTest() throws Exception {
        CheckRequestDTO request = new CheckRequestDTO();
        request.setCode("AMZN");
        request.setDateFrom(LocalDate.of(1999, 5, 5));
        request.setDateTo(LocalDate.of(1999, 5, 15));

        CompoundCheckResponseDTO cResponseDTO = this.profitService.checkProfit(request);

        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.providedDates.shouldBuy.date").value("1999-05-07"))
                .andExpect(jsonPath("$.providedDates.shouldBuy.closeValue").value(68.19))
                .andExpect(jsonPath("$.providedDates.shouldSell.date").value("1999-05-11"))
                .andExpect(jsonPath("$.providedDates.shouldSell.closeValue").value(74.19))
                .andExpect(jsonPath("$.beforeProvidedDates.shouldBuy.date").value("1999-04-26"))
                .andExpect(jsonPath("$.beforeProvidedDates.shouldBuy.closeValue").value(103.59))
                .andExpect(jsonPath("$.beforeProvidedDates.shouldSell.date").value("1999-04-27"))
                .andExpect(jsonPath("$.beforeProvidedDates.shouldSell.closeValue").value(102.94))
                .andExpect(jsonPath("$.afterProvidedDates.shouldBuy.date").value("1999-05-17"))
                .andExpect(jsonPath("$.afterProvidedDates.shouldBuy.closeValue").value(68.81))
                .andExpect(jsonPath("$.afterProvidedDates.shouldSell.date").value("1999-05-19"))
                .andExpect(jsonPath("$.afterProvidedDates.shouldSell.closeValue").value(69.78));
    }

    @Test
    void checkProfitIntegrationTest_NotFound() throws Exception {
        CheckRequestDTO request = new CheckRequestDTO();
        request.setCode("ABCD");
        request.setDateFrom(LocalDate.of(1999, 5, 5));
        request.setDateTo(LocalDate.of(1999, 5, 15));

        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void checkProfitIntegrationTest_BadReques() throws Exception {
        CheckRequestDTO request = new CheckRequestDTO();
        request.setCode("ABCD");
        request.setDateFrom(LocalDate.of(1999, 5, 5));

        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void checkProfitIntegrationTest_BadRequesDateRange() throws Exception {
        CheckRequestDTO request = new CheckRequestDTO();
        request.setCode("ABCD");
        request.setDateFrom(LocalDate.of(1999, 5, 5));
        request.setDateFrom(LocalDate.of(1998, 5, 5));

        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
