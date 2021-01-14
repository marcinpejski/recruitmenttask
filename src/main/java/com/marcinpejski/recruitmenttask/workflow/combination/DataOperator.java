package com.marcinpejski.recruitmenttask.workflow.combination;

import java.util.List;

@FunctionalInterface
public interface DataOperator<T> {
    T calculate(List<T> data);
}
