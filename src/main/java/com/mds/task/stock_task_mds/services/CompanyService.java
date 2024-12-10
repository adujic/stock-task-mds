package com.mds.task.stock_task_mds.services;

import org.springframework.stereotype.Service;

import com.mds.task.stock_task_mds.constants.ErrorMessageConstants;
import com.mds.task.stock_task_mds.entities.Company;
import com.mds.task.stock_task_mds.exceptions.CompanyWithCodeNotFoundException;
import com.mds.task.stock_task_mds.repositories.CompanyRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(final CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * Retrieves a {@link Company} by its unique code.
     * 
     * @param code the unique code of the company to retrieve.
     * @return the {@link Company} with the specified code.
     * @throws CompanyWithCodeNotFoundException if no company with the given code is found.
     */
    public Company getCompanyByCode(final String code) {
        final Company company = this.companyRepository.findCompanyByCode(code);

        if (company == null) {
            throw new CompanyWithCodeNotFoundException(
                    String.format(ErrorMessageConstants.COMPANY_CODE_NOT_FOUND, code)
                );
        }

        return company;
    }

}
