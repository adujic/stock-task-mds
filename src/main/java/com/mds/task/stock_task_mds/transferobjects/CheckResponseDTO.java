package com.mds.task.stock_task_mds.transferobjects;

public class CheckResponseDTO {
    private CheckResponseItemDTO shouldBuy;
    private CheckResponseItemDTO shouldSell;
    
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

    

}
