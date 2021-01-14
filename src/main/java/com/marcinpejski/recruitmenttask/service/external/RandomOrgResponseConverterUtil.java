package com.marcinpejski.recruitmenttask.service.external;

import java.math.BigDecimal;

import org.springframework.lang.NonNull;

public class RandomOrgResponseConverterUtil {

    public static BigDecimal convertToDecimal(@NonNull String response) throws NumberFormatException {
        var strippedResponse = response.replace("\n", "");
        return new BigDecimal(strippedResponse);
    }

}
