package com.vk.user.Service;

import com.vk.user.dto.TransactionRequestDto;
import com.vk.user.dto.TransactionResponseDto;
import com.vk.user.dto.TransactionStatus;
import com.vk.user.repository.TransactionRepository;
import com.vk.user.repository.UserRepository;
import com.vk.user.util.TransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Mono<TransactionResponseDto> saveTransaction(final TransactionRequestDto requestDto) {
        Mono<Boolean> updateUserBalance = userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount());

        return updateUserBalance
                .filter(Boolean::booleanValue)
                .map(b -> TransactionMapper.toEntity(requestDto))
                .flatMap(transactionRepository::save)
                .doOnNext(t -> log.info("Transaction saved {}", t))
                .doOnError(e -> log.error("Error saving transaction", e))
                .map(t -> TransactionMapper.toDto(requestDto, TransactionStatus.APPROVED))
                .defaultIfEmpty(TransactionMapper.toDto(requestDto, TransactionStatus.DECLINED));
    }

    public Flux<TransactionResponseDto> findAllByUserId(final Integer userId) {
        return transactionRepository.findAllByUserId(userId)
                .map(t -> TransactionMapper.toDto(t, TransactionStatus.APPROVED))
                .doOnNext(t -> log.info("Transaction found {}", t))
                .doOnError(e -> log.error("Error getting transactions", e));
    }

}
