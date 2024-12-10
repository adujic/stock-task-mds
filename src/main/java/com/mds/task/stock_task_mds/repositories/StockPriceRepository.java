package com.mds.task.stock_task_mds.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mds.task.stock_task_mds.entities.Company;
import com.mds.task.stock_task_mds.entities.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

    /**
     * Checks if an entity with the specified company ID exists.
     *
     * @param companyId the ID of the company to check for existence.
     * @return {@code true} if an entity with the given company ID exists, {@code false} otherwise.
     */
    public boolean existsByCompanyId(final Long companyId);

    /**
     * Retrieves a list of stock prices for a given company within a specified date range.
     *
     * @param company the company whose stock prices are to be retrieved.
     * @param startDate the start date of the range.
     * @param endDate the end date of the range.
     * @return a list of {@link StockPrice} objects that match the given company and date range.
     */
    List<StockPrice> findByCompanyAndDateBetween(Company company, LocalDate startDate, LocalDate endDate);
}
