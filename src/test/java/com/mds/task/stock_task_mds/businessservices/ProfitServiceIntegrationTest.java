package com.mds.task.stock_task_mds.businessservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mds.task.stock_task_mds.transferobjects.CheckRequestDTO;
import com.mds.task.stock_task_mds.transferobjects.CompoundCheckResponseDTO;

@SpringBootTest
public class ProfitServiceIntegrationTest {

    @Autowired
    ProfitService profitService;

    @Test
    void checkProfit() {
        CheckRequestDTO request = new CheckRequestDTO();
        request.setCode("AMZN");
        request.setDateFrom(LocalDate.of(1999, 5, 5));
        request.setDateTo(LocalDate.of(1999, 5, 15));

        CompoundCheckResponseDTO response = profitService.checkProfit(request);

        assertNotNull(response);
        assertEquals(LocalDate.of(1999, 5, 7), response.getProvidedDates().getShouldBuy().getDate());
        assertEquals(LocalDate.of(1999 , 5, 11), response.getProvidedDates().getShouldSell().getDate());

    }

}
