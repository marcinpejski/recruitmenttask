package com.marcinpejski.recruitmenttask.service.external;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import com.marcinpejski.recruitmenttask.exception.ExternalRequestException;
import com.marcinpejski.recruitmenttask.exception.InvalidResponseException;

public class RandomOrgServiceTest {

    private static RandomOrgService randomOrgService;
    private static RestTemplate template;

    @BeforeAll
    public static void init() {
        template = Mockito.mock(RestTemplate.class);
        var builder = Mockito.mock(RestTemplateBuilder.class);
        Mockito.when(builder.build()).thenReturn(template);
        randomOrgService = new RandomOrgService(builder);
    }

    @Test
    public void getSingleDecimalNumberAsyncReturnsValidNumber() throws ExecutionException, InterruptedException {
        var expectedResult = BigDecimal.valueOf(10.1);

        Mockito.when(template.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(expectedResult + "\n");
        var result = randomOrgService.getSingleDecimalNumberAsync();
        assertThat(result.get()).isEqualTo(expectedResult);
    }

    @Test
    public void getSingleDecimalNumberThrowsExceptionOrEmptyResponse() {
        Mockito.when(template.getForObject(Mockito.anyString(), Mockito.any())).thenReturn("");
        assertThrows(ExternalRequestException.class, () -> randomOrgService.getSingleDecimalNumberAsync());
    }

    @Test
    public void getSingleDecimalNumberThrowsExceptionOnInvalidNumber() {
        Mockito.when(template.getForObject(Mockito.anyString(), Mockito.any())).thenReturn("test");
        assertThrows(InvalidResponseException.class, () -> randomOrgService.getSingleDecimalNumberAsync());
    }
}
