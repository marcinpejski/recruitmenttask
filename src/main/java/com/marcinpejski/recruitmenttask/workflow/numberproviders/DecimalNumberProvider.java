package com.marcinpejski.recruitmenttask.workflow.numberproviders;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface DecimalNumberProvider {
    CompletableFuture<BigDecimal> getDecimalNumber();
}
