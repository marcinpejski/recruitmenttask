package com.marcinpejski.recruitmenttask.workflow.combination;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;

import com.marcinpejski.recruitmenttask.exception.AsyncTaskException;
import com.marcinpejski.recruitmenttask.workflow.numberproviders.DecimalNumberProvider;

public class DecimalNumbersCombinatorTest {

    private DecimalNumbersCombinator dataCombinator;

    @Test
    public void combineDataSumReturnsValidResultBasedOnInputData() {
        var val1 = BigDecimal.valueOf(12.5);
        var val2 = BigDecimal.valueOf(0.25);

        var prov1 = new DecimalNumberProvider() {
            @Override
            public CompletableFuture<BigDecimal> getDecimalNumber() {
                return CompletableFuture.completedFuture(val1);
            }
        };
        var prov2 = new DecimalNumberProvider() {
            @Override
            public CompletableFuture<BigDecimal> getDecimalNumber() {
                return CompletableFuture.completedFuture(val2);
            }
        };

        var operator = new DecimalNumSumOperator();
        dataCombinator = new DecimalNumbersCombinator(Arrays.asList(prov1, prov2), operator);

        var expectedResult = val1.add(val2);
        assertThat(dataCombinator.combineData()).isEqualTo(expectedResult);
    }

    @Test
    public void combineDataSumThrowsExceptionWhenOneProviderFails() {
        var prov1 = new DecimalNumberProvider() {
            @Override
            public CompletableFuture<BigDecimal> getDecimalNumber() {
                return CompletableFuture.completedFuture(BigDecimal.ONE);
            }
        };

        var prov2 = new DecimalNumberProvider() {
            @Override
            public CompletableFuture<BigDecimal> getDecimalNumber() {
                return CompletableFuture.failedFuture(new NullPointerException("ex"));
            }
        };

        var operator = new DecimalNumSumOperator();
        dataCombinator = new DecimalNumbersCombinator(Arrays.asList(prov1, prov2), operator);

        assertThrows(AsyncTaskException.class, () -> {
            dataCombinator.combineData();
        });
    }
}
