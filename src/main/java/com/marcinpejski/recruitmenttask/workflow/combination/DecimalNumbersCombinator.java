package com.marcinpejski.recruitmenttask.workflow.combination;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.lang.NonNull;

import com.marcinpejski.recruitmenttask.exception.AsyncTaskException;
import com.marcinpejski.recruitmenttask.workflow.numberproviders.DecimalNumberProvider;

public class DecimalNumbersCombinator extends DataCombinator<BigDecimal, DecimalNumberProvider> {

    public DecimalNumbersCombinator(@NonNull List<DecimalNumberProvider> decimalNumberProviders,
                                    @NonNull DataOperator<BigDecimal> combinationOperator) {
        super(decimalNumberProviders, combinationOperator);
    }

    public BigDecimal combineData() {
        var result = decimalNumberProviders.stream().map(DecimalNumberProvider::getDecimalNumber).reduce((c1, c2) -> c1
                .thenCombineAsync(c2, (a, b) -> combinationOperator.calculate(Arrays.asList(a, b))))
                .orElse(CompletableFuture.completedFuture(BigDecimal.ZERO));

        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new AsyncTaskException("Error while computing values", e);
        }
    }
}
