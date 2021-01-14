package com.marcinpejski.recruitmenttask.workflow.combination;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class DecimalNumSumOperatorTest {

    @Test
    public void combineDataSumsUpNumbersCollection() {
        var operator = new DecimalNumSumOperator();
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.valueOf(2));

        var result = operator.calculate(numbers);
        assertThat(result).isEqualTo(BigDecimal.valueOf(13));
    }
}
