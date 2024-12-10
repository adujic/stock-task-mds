package com.mds.task.stock_task_mds.transferobjects;

import java.time.LocalDate;

public class CheckRequestDTO {
    private String code;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    
    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(final LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(final LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
