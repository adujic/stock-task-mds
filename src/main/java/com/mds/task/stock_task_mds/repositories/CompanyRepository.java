package com.mds.task.stock_task_mds.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mds.task.stock_task_mds.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    
    /**
    * Checks if company entity with the specified code exists.
    *
    * @param code the code to check for existence.
    * @return {@code true} if an entity with the given code exists, {@code false} otherwise.
    */
    public boolean existsByCode(final String code);

    /**
    * Retriev company entity with the specified code.
    *
    * @param code the code to check for existence.
    * @return {@link Company} data
    */
    public Company findCompanyByCode(final String code);
}
