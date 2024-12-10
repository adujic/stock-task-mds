package com.mds.task.stock_task_mds.transferobjects;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CheckResponseItemDTO {
    private LocalDate date;
    private BigDecimal closeValue;

    public CheckResponseItemDTO(final LocalDate date, final BigDecimal closeValue) {
        this.date = date;
        this.closeValue = closeValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public BigDecimal getCloseValue() {
        return closeValue;
    }

    public void setCloseValue(final BigDecimal closeValue) {
        this.closeValue = closeValue;
    }
    
    
}
