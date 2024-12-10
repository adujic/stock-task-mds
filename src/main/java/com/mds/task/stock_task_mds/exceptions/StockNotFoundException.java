package com.mds.task.stock_task_mds.exceptions;

public class StockNotFoundException extends RuntimeException{
    public StockNotFoundException(final String message) {
        super(message);
    }
}
