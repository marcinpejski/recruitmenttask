package com.marcinpejski.recruitmenttask.service.external;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.marcinpejski.recruitmenttask.exception.ExternalRequestException;
import com.marcinpejski.recruitmenttask.exception.InvalidResponseException;

@Service
public class RandomOrgService {

    @Value("${service.randomorg.url}")
    private String serviceUrl;

    private final RestTemplate restTemplate;

    public RandomOrgService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<BigDecimal> getSingleDecimalNumberAsync() {
        var url = serviceUrl + "/decimal-fractions/?num=1&col=1&base=10&format=plain&rnd=new&dec=2";
        var textResponse = restTemplate.getForObject(url, String.class);

        if (textResponse == null || "".equalsIgnoreCase(textResponse)) {
            throw new ExternalRequestException("Got empty response from random.org");
        }

        try {
            return CompletableFuture.completedFuture(RandomOrgResponseConverterUtil.convertToDecimal(textResponse));
        } catch (NumberFormatException e) {
            throw new InvalidResponseException("Could not parse response from random.org", e);
        }
    }

}
