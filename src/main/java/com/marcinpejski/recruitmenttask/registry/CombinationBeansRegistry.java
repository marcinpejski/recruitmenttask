package com.marcinpejski.recruitmenttask.registry;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcinpejski.recruitmenttask.dao.NumberRepository;
import com.marcinpejski.recruitmenttask.service.external.RandomOrgService;
import com.marcinpejski.recruitmenttask.workflow.combination.DataOperator;
import com.marcinpejski.recruitmenttask.workflow.combination.DecimalNumSumOperator;
import com.marcinpejski.recruitmenttask.workflow.combination.DecimalNumbersCombinator;
import com.marcinpejski.recruitmenttask.workflow.numberproviders.DbNumberProvider;
import com.marcinpejski.recruitmenttask.workflow.numberproviders.DecimalNumberProvider;
import com.marcinpejski.recruitmenttask.workflow.numberproviders.RandomOrgNumberProvider;

@Configuration
public class CombinationBeansRegistry {

    @Bean
    public DecimalNumberProvider randomOrgDecimalNumberProvider(RandomOrgService randomOrgService) {
        return new RandomOrgNumberProvider(randomOrgService);
    }

    @Bean
    public DecimalNumberProvider dbDecimalNumberProvider(NumberRepository numberRepository) {
        return new DbNumberProvider(numberRepository);
    }

    @Bean
    @Qualifier("decimalDataNumSumOperator")
    public DataOperator<BigDecimal> decimalDataNumSumOperator() {
        return new DecimalNumSumOperator();
    }

    @Bean
    @Qualifier("decimalNumbersSumCombinator")
    public DecimalNumbersCombinator decimalNumbersSumCombinator(List<DecimalNumberProvider> decimalNumberProviders,
                                                                DataOperator<BigDecimal> decimalDataOperator) {
        return new DecimalNumbersCombinator(decimalNumberProviders, decimalDataOperator);
    }
}
