package com.mds.task.stock_task_mds.exceptions;

public class CompanyWithCodeNotFoundException extends RuntimeException{
    public CompanyWithCodeNotFoundException(final String message) {
        super(message);
    }
}
