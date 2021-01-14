package com.marcinpejski.recruitmenttask.workflow.combination;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.springframework.lang.NonNull;

public abstract class DataCombinator<T, R> {

    protected final List<R> decimalNumberProviders;
    protected final DataOperator<T> combinationOperator;

    protected DataCombinator(@NonNull List<R> decimalNumberProviders, @NonNull DataOperator<T> combinationOperator) {
        this.decimalNumberProviders = decimalNumberProviders;
        this.combinationOperator = combinationOperator;
    }

    public abstract T combineData() throws InterruptedException, ExecutionException, TimeoutException;
}
