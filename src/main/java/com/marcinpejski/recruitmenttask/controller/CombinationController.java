package com.marcinpejski.recruitmenttask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcinpejski.recruitmenttask.dto.NumberValueDTO;
import com.marcinpejski.recruitmenttask.workflow.combination.DecimalNumbersCombinator;

@RestController
@RequestMapping("/combination/v1")
public class CombinationController {

    private final DecimalNumbersCombinator decimalNumberSumCombination;

    public CombinationController(DecimalNumbersCombinator decimalNumberSumCombination) {
        this.decimalNumberSumCombination = decimalNumberSumCombination;
    }

    @GetMapping("/decimal")
    private NumberValueDTO combineDecimalNumbers() {
        var result = decimalNumberSumCombination.combineData();

        return new NumberValueDTO(result);
    }
}
