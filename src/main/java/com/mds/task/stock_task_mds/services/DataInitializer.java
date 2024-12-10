package com.mds.task.stock_task_mds.services;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.mds.task.stock_task_mds.constants.LogMessageConstants;
import com.mds.task.stock_task_mds.entities.Company;
import com.mds.task.stock_task_mds.entities.StockPrice;
import com.mds.task.stock_task_mds.repositories.CompanyRepository;
import com.mds.task.stock_task_mds.repositories.StockPriceRepository;
import com.mds.task.stock_task_mds.utils.DateUtils;
import com.opencsv.CSVReader;

import jakarta.annotation.PostConstruct;

@Service
public class DataInitializer {
    private static final Logger LOG = LoggerFactory.getLogger(DataInitializer.class);

    private final CompanyRepository companyRepository;
    private final StockPriceRepository stockPriceRepository;

    public DataInitializer(final CompanyRepository companyRepository, final StockPriceRepository stockPriceRepository) {
        this.stockPriceRepository = stockPriceRepository;
        this.companyRepository = companyRepository;
    }

    @PostConstruct
    public void init() {
        LOG.info(LogMessageConstants.INITIALIZER_START_COMPANY);
        this.populateCompanies();
        LOG.info(LogMessageConstants.INITIALIZER_END_COMPANY);
        LOG.info(LogMessageConstants.INITIALIZER_START_STOCK);
        this.populateStockPrices();
        LOG.info(LogMessageConstants.INITIALIZER_END_STOCK);
    }

    /**
     * Populates the stock prices for each company.
     * This method retrieves all companies from the repository, checks if stock prices for each company already exist, 
     * and if not, reads the corresponding CSV file to populate the stock price data and save it to the repository.
     */
    private void populateStockPrices() {
        final List<Company> companies = this.companyRepository.findAll();

        companies.stream().forEach(company -> {
            if (!this.stockPriceRepository.existsByCompanyId(company.getId())) {
                try (CSVReader reader = new CSVReader( new InputStreamReader(new ClassPathResource(company.getName() + ".csv").getInputStream()))) {
                    reader.readNextSilently();
                    final List<String[]> entries = reader.readAll();
        
                    for (String[] entry : entries) {
                        final StockPrice stockPrice = this.mapToStockPrice(entry, company);

                        this.stockPriceRepository.save(stockPrice);
                    }
                } catch (Exception e) {
                    LOG.error(e.getLocalizedMessage(), e);
                }
            }
        });
    }

    /**
     * Populates the companies from a CSV file.
     * This method reads the "companies.csv" file located in the "src/main/resources" directory, 
     * which contains company information in the format: code, name, and date founded.
     * For each company, it checks if the company already exists in the repository by its code. 
     * If the company does not exist, it is saved to the repository.
     */
    private void populateCompanies() {
        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource("companies.csv").getInputStream()))) {
            reader.readNextSilently();
            List<String[]> entries = reader.readAll();

            for (String[] entry : entries) {
                final String code = entry[0];

                if (!this.companyRepository.existsByCode(code)) {
                    final Company company = this.mapToCompany(entry);
                    this.companyRepository.save(company);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
        }
    }

    /**
     * Maps an array of strings representing stock price data to a {@link StockPrice} object.
     *
     * @param entry an array of strings containing stock price data in the order: date, open, high, low, close, adjusted close, and volume.
     * @param company the {@link Company} associated with the stock price.
     * @return a {@link StockPrice} object populated with the provided data.
     */
    private StockPrice mapToStockPrice(final String[] entry, final Company company) {
        final StockPrice stockPrice = new StockPrice();

        stockPrice.setDate(DateUtils.localDateFromString(entry[0]));
        stockPrice.setOpen(new BigDecimal(entry[1]));
        stockPrice.setHigh(new BigDecimal(entry[2]));
        stockPrice.setLow(new BigDecimal(entry[3]));
        stockPrice.setClose(new BigDecimal(entry[4]));
        stockPrice.setAdjClose(new BigDecimal(entry[5]));
        stockPrice.setVolume(Long.parseLong(entry[6]));
        stockPrice.setCompany(company);

        return stockPrice;
    }

    /**
     * Maps an array of strings representing company data to a {@link Company} object.
     *
     * @param entry an array of strings containing company data in the order: code, name, and date founded.
     * @return a {@link Company} object populated with the provided data.
     */
    private Company mapToCompany(final String[] entry) {
        final Company company = new Company();
        
        company.setCode(entry[0]);
        company.setName(entry[1]);
        company.setDateFounded(DateUtils.localDateFromString(entry[2]));

        return company;
    }

}
