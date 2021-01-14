package com.marcinpejski.recruitmenttask.service.external;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.text.ParseException;

import org.junit.jupiter.api.Test;

public class RandomOrgResponseConverterUtilTest {

    @Test
    public void convertToDecimalReturnsValidResult() throws ParseException {
        var expectedValue = BigDecimal.valueOf(55.2);
        var res = RandomOrgResponseConverterUtil.convertToDecimal(expectedValue + "\n");

        assertThat(res).isEqualTo(expectedValue);
    }

    @Test
    public void convertToDecimalThrowsExceptionWhenInputIsNotAValidNumber() {
        assertThrows(NumberFormatException.class, () -> RandomOrgResponseConverterUtil.convertToDecimal("test"));
    }
}
