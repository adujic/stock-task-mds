package com.mds.task.stock_task_mds.exceptions;

public class StockPriceNotFoundException extends RuntimeException{
    public StockPriceNotFoundException(final String message){
        super(message);
    }
}
