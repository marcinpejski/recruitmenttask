package com.marcinpejski.recruitmenttask.dto;

import java.math.BigDecimal;

public class NumberValueDTO {

    private BigDecimal value;

    public NumberValueDTO() {
    }

    public NumberValueDTO(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
