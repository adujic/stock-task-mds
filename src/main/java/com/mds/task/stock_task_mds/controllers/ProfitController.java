package com.mds.task.stock_task_mds.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mds.task.stock_task_mds.businessservices.ProfitService;
import com.mds.task.stock_task_mds.transferobjects.CheckRequestDTO;
import com.mds.task.stock_task_mds.transferobjects.CompoundCheckResponseDTO;

@RestController
@RequestMapping("api/v1/profit")
public class ProfitController {

    private final ProfitService profitService;

    public ProfitController(final ProfitService profitService) {
        this.profitService = profitService;
    }

    @PostMapping("/check")
    public ResponseEntity<CompoundCheckResponseDTO> checkProfit(@RequestBody CheckRequestDTO checkRequestDTO) {
        return new ResponseEntity<>(this.profitService.checkProfit(checkRequestDTO), HttpStatus.OK);
    }

}
