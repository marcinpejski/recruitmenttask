package com.marcinpejski.recruitmenttask.dao;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marcinpejski.recruitmenttask.entity.NumberValue;

public interface NumberRepository extends JpaRepository<NumberValue, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM numbers ORDER BY random() LIMIT 1")
    CompletableFuture<Optional<NumberValue>> findOneRandomAsync();
}
