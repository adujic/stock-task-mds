package com.mds.task.stock_task_mds.transferobjects;

public class CompoundCheckResponseDTO {
    private CheckResponseDTO providedDates;
    private CheckResponseDTO beforeProvidedDates;
    private CheckResponseDTO afterProvidedDates;

    

    public CompoundCheckResponseDTO() {
    }

    public CompoundCheckResponseDTO(final CheckResponseDTO providedDates, final CheckResponseDTO beforeProvidedDates,
            final CheckResponseDTO afterProvidedDates) {
        this.providedDates = providedDates;
        this.beforeProvidedDates = beforeProvidedDates;
        this.afterProvidedDates = afterProvidedDates;
    }

    public CheckResponseDTO getProvidedDates() {
        return providedDates;
    }

    public void setProvidedDates(final CheckResponseDTO providedDates) {
        this.providedDates = providedDates;
    }

    public CheckResponseDTO getBeforeProvidedDates() {
        return beforeProvidedDates;
    }

    public void setBeforeProvidedDates(final CheckResponseDTO beforeProvidedDates) {
        this.beforeProvidedDates = beforeProvidedDates;
    }

    public CheckResponseDTO getAfterProvidedDates() {
        return afterProvidedDates;
    }

    public void setAfterProvidedDates(final CheckResponseDTO afterProvidedDates) {
        this.afterProvidedDates = afterProvidedDates;
    }
}
