package com.marcinpejski.recruitmenttask.workflow.numberproviders;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcinpejski.recruitmenttask.dao.NumberRepository;
import com.marcinpejski.recruitmenttask.entity.NumberValue;

public class DbNumberProvider implements DecimalNumberProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbNumberProvider.class);

    private final NumberRepository numberRepository;

    public DbNumberProvider(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    @Override
    public CompletableFuture<BigDecimal> getDecimalNumber() {
        LOGGER.info("Fetching random number from database");
        return this.numberRepository.findOneRandomAsync()
                .thenApply(nvOpt -> nvOpt.map(NumberValue::getValue).orElse(BigDecimal.ZERO));
    }
}
