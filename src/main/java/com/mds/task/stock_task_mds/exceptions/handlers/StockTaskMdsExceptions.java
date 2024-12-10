package com.mds.task.stock_task_mds.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mds.task.stock_task_mds.exceptions.CompanyWithCodeNotFoundException;
import com.mds.task.stock_task_mds.exceptions.DateBadRequestException;
import com.mds.task.stock_task_mds.exceptions.DateValidationException;
import com.mds.task.stock_task_mds.exceptions.StockNotFoundException;
import com.mds.task.stock_task_mds.exceptions.StockPriceNotFoundException;
import com.mds.task.stock_task_mds.exceptions.dto.HttpResponseDTO;

@RestControllerAdvice
public class StockTaskMdsExceptions {

    @ExceptionHandler({StockNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<HttpResponseDTO> handleStockNotFoundException(final StockNotFoundException e) {
        return new ResponseEntity<>(
                new HttpResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND), 
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({StockPriceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<HttpResponseDTO> handleStockPriceNotFoundException(final StockPriceNotFoundException e) {
        return new ResponseEntity<>(
                new HttpResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND), 
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({DateValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpResponseDTO> handleDateValidationException(final DateValidationException e) {
        return new ResponseEntity<>(
                new HttpResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST), 
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({CompanyWithCodeNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<HttpResponseDTO> handleCompanyWithCodeNotFoundException(final CompanyWithCodeNotFoundException e) {
        return new ResponseEntity<>(
                new HttpResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND), 
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({DateBadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpResponseDTO> handleDateBadRequestException(final DateBadRequestException e) {
        return new ResponseEntity<>(
                new HttpResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST), 
                HttpStatus.BAD_REQUEST
        );
    }
}
