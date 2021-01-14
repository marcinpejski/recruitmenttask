package com.marcinpejski.recruitmenttask.workflow.combination;

import java.math.BigDecimal;
import java.util.List;

public class DecimalNumSumOperator implements DataOperator<BigDecimal> {

    @Override
    public final BigDecimal calculate(List<BigDecimal> numbers) {
        return numbers.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
