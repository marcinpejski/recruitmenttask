package com.marcinpejski.recruitmenttask.workflow.numberproviders;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcinpejski.recruitmenttask.service.external.RandomOrgService;

public class RandomOrgNumberProvider implements DecimalNumberProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomOrgNumberProvider.class);

    private final RandomOrgService randomOrgService;

    public RandomOrgNumberProvider(RandomOrgService randomOrgService) {
        this.randomOrgService = randomOrgService;
    }

    @Override
    public CompletableFuture<BigDecimal> getDecimalNumber() {
        LOGGER.info("Fetching random number from random.org");
        return randomOrgService.getSingleDecimalNumberAsync();
    }
}
