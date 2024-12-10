package com.mds.task.stock_task_mds.businessservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mds.task.stock_task_mds.constants.ErrorMessageConstants;
import com.mds.task.stock_task_mds.exceptions.DateValidationException;
import com.mds.task.stock_task_mds.services.CompanyService;
import com.mds.task.stock_task_mds.services.StockPriceService;
import com.mds.task.stock_task_mds.transferobjects.CheckRequestDTO;

public class ProfitServiceUnitTest {
    @Mock
    private StockPriceService stockPriceService;

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private ProfitService profitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void checkProfit_InvalidDateRange() {
        LocalDate dateFrom = LocalDate.of(2023, 1, 10);
        LocalDate dateTo = LocalDate.of(2023, 1, 1);
        CheckRequestDTO request = new CheckRequestDTO();
        request.setCode("TEST");
        request.setDateFrom(dateFrom);
        request.setDateTo(dateTo);

        DateValidationException exception = assertThrows(DateValidationException.class, () -> {
            profitService.checkProfit(request);
        });

        assertEquals(ErrorMessageConstants.DATE_RANGE_EXCEPTION, exception.getMessage());
    }
    
}
