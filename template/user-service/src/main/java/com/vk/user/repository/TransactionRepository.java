package com.vk.user.repository;

import com.vk.user.domain.Transaction;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transaction, Integer> {

    @Query("SELECT * FROM transactions WHERE user_id = :userId")
    Flux<Transaction> findAllByUserId(Integer userId);

}