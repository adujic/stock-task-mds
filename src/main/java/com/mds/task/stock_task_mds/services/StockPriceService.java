package com.mds.task.stock_task_mds.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mds.task.stock_task_mds.constants.ErrorMessageConstants;
import com.mds.task.stock_task_mds.entities.Company;
import com.mds.task.stock_task_mds.entities.StockPrice;
import com.mds.task.stock_task_mds.exceptions.StockPriceNotFoundException;
import com.mds.task.stock_task_mds.repositories.StockPriceRepository;

@Service
public class StockPriceService {
    
    private final StockPriceRepository stockPriceRepository;

    public StockPriceService(final StockPriceRepository stockPriceRepository) {
        this.stockPriceRepository = stockPriceRepository;
    }

    /**
     * Retrieves a list of stock prices for a given company within a specified date range.
     *
     * @param company the {@link Company} whose stock prices are to be retrieved.
     * @param dateFrom the start date of the period.
     * @param dateTo the end date of the period.
     * @return a list of {@link StockPrice} objects for the specified company and date range.
     * @throws StockPriceNotFoundException if no stock prices are found for the given company and date range.
     */
    public List<StockPrice> getPeriodStockPriceByCompany(final Company company, final LocalDate dateFrom, final LocalDate dateTo) {
        List<StockPrice> result = this.stockPriceRepository.findByCompanyAndDateBetween(company, dateFrom, dateTo);

        if (result.isEmpty()) {
            throw new StockPriceNotFoundException(
                String.format(ErrorMessageConstants.STOCK_PRICE_NOT_FOUND, company.getCode(), dateFrom, dateTo)
            );
        }
        
        return result;
    }

}
