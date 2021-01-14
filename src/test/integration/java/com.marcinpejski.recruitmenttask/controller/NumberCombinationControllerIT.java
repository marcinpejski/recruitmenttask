package com.marcinpejski.recruitmenttask.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.marcinpejski.recruitmenttask.dto.NumberValueDTO;
import com.marcinpejski.recruitmenttask.service.external.RandomOrgService;

import util.JsonStringToPojoConverter;

class NumberCombinationControllerIT extends TestContext {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private RandomOrgService randomOrgService;

    @Test
    void getDecimalShouldReturnExpectedValue() {
        Mockito.when(randomOrgService.getSingleDecimalNumberAsync())
                .thenReturn(CompletableFuture.completedFuture(BigDecimal.ONE));

        var jsonString = this.restTemplate
                .getForObject("http://localhost:" + port + "/combination/numbers/v1/decimal", String.class);
        var pojoResult = JsonStringToPojoConverter.map(jsonString, NumberValueDTO.class);

        assertThat(pojoResult.getValue()).isEqualTo(BigDecimal.valueOf(99.99));
    }
}
