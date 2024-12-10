package com.mds.task.stock_task_mds.transferobjects;

import java.math.BigDecimal;

public class CheckResponseDTO {
    private CheckResponseItemDTO shouldBuy;
    private CheckResponseItemDTO shouldSell;
    private BigDecimal profit;
    
    public CheckResponseItemDTO getShouldBuy() {
        return shouldBuy;
    }

    public void setShouldBuy(final CheckResponseItemDTO shouldBuy) {
        this.shouldBuy = shouldBuy;
    }

    public CheckResponseItemDTO getShouldSell() {
        return shouldSell;
    }

    public void setShouldSell(final CheckResponseItemDTO shouldSell) {
        this.shouldSell = shouldSell;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }
}
