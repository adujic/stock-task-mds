package com.mds.task.stock_task_mds.businessservices;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mds.task.stock_task_mds.constants.ErrorMessageConstants;
import com.mds.task.stock_task_mds.entities.Company;
import com.mds.task.stock_task_mds.entities.StockPrice;
import com.mds.task.stock_task_mds.exceptions.DateBadRequestException;
import com.mds.task.stock_task_mds.exceptions.DateValidationException;
import com.mds.task.stock_task_mds.services.CompanyService;
import com.mds.task.stock_task_mds.services.StockPriceService;
import com.mds.task.stock_task_mds.transferobjects.CheckRequestDTO;
import com.mds.task.stock_task_mds.transferobjects.CheckResponseDTO;
import com.mds.task.stock_task_mds.transferobjects.CheckResponseItemDTO;
import com.mds.task.stock_task_mds.transferobjects.CompoundCheckResponseDTO;
import com.mds.task.stock_task_mds.utils.DateUtils;

@Service
public class ProfitService {

    private final StockPriceService stockPriceService;
    private final CompanyService companyService;

    public ProfitService(final StockPriceService stockPriceService, final CompanyService companyService) {
        this.stockPriceService = stockPriceService;
        this.companyService = companyService;
    }

    /**
     * Checks the profit for the given company code within the specified date range and compares it with previous 
     * and subsequent periods of the same duration.
     *
     * @param checkRequestDTO the request object containing the company code, start date, and end date.
     * @return a {@link CompoundCheckResponseDTO} containing the profit details for the current, previous, and later periods.
     * @throws DateBadRequestException if either the start date or end date is missing in the request.
     * @throws DateValidationException if the start date is not before the end date.
     */
    public CompoundCheckResponseDTO checkProfit(final CheckRequestDTO checkRequestDTO) {
        final LocalDate dateFrom;
        final LocalDate dateTo;
        final Company company;
        final long days;

        if (checkRequestDTO.getDateFrom() == null || checkRequestDTO.getDateTo() == null) {
            throw new DateBadRequestException(
                String.format(ErrorMessageConstants.DATE_BAD_EXCEPTION)
            );
        }

        dateFrom = checkRequestDTO.getDateFrom();
        dateTo = checkRequestDTO.getDateTo();

        if (!DateUtils.isDateBefore(dateFrom, dateTo)) {
            throw new DateValidationException(
                String.format(ErrorMessageConstants.DATE_RANGE_EXCEPTION)
            );
        }

        
        company = this.companyService.getCompanyByCode(checkRequestDTO.getCode());
        days = ChronoUnit.DAYS.between(dateFrom, dateTo);

        CheckResponseDTO currentPeriod = this.checkProfitForDates(company, dateFrom, dateTo);
        CheckResponseDTO previousPeriod = this.checkProfitForDates(company, dateFrom.minusDays(days), dateTo.minusDays(days)); 
        CheckResponseDTO laterPeriod = this.checkProfitForDates(company, dateFrom.plusDays(days), dateTo.plusDays(days));

        return new CompoundCheckResponseDTO(currentPeriod, previousPeriod, laterPeriod);        
    }

    /**
     * Load stock period for database for provided company and time range in order to calculate max profit.
     * 
     * @param company
     * @param dateFrom
     * @param dateTo
     * @return
     */
    private CheckResponseDTO checkProfitForDates(final Company company, final LocalDate dateFrom, final LocalDate dateTo) {
        List<StockPrice> period = this.stockPriceService.getPeriodStockPriceByCompany(company, dateFrom, dateTo);

        return calculate(period);
    }

    /**
     * Calculate best date to buy and sell stock for provided time period
     * 
     * @param period
     * @return
     */
    private CheckResponseDTO calculate(final List<StockPrice> period) {
        final CheckResponseDTO result = new CheckResponseDTO();
        BigDecimal maxProfit = BigDecimal.ZERO;
        StockPrice buyPrice = period.get(0);
        StockPrice selPrice = period.get(0);

        for (int i = 0; i < period.size(); i++) {
            StockPrice buyPriceTmp = period.get(i);

            for (int j = i + 1; j < period.size(); j++) {
                StockPrice sellPriceTmp = period.get(j);
                BigDecimal profit = sellPriceTmp.getHigh().subtract(buyPriceTmp.getLow());

                if (profit.compareTo(maxProfit) > 0) {
                    maxProfit = profit;
                    buyPrice = buyPriceTmp;
                    selPrice = sellPriceTmp;
                }
            }
        }

        result.setShouldBuy(new CheckResponseItemDTO(buyPrice.getDate(), buyPrice.getClose()));
        result.setShouldSell(new CheckResponseItemDTO(selPrice.getDate(), selPrice.getClose()));

        return result;
    }
}
