package com.vk.user.util;

import com.vk.user.domain.Transaction;
import com.vk.user.dto.TransactionRequestDto;
import com.vk.user.dto.TransactionResponseDto;
import com.vk.user.dto.TransactionStatus;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class TransactionMapper {
    public static Transaction toEntity(TransactionRequestDto dto) {
        Transaction entity = Transaction
                .builder()
                .userId(dto.getUserId())
                .amount(dto.getAmount())
                .transactionDate(LocalDateTime.now())
                .build();

        log.info("Mapped TransactionRequestDto to TransactionEntity: {}", entity);

        return entity;
    }

    public static TransactionResponseDto toDto(TransactionRequestDto dto, TransactionStatus status) {
        TransactionResponseDto result = TransactionResponseDto
                .builder()
                .amount(dto.getAmount())
                .userId(dto.getUserId())
                .status(status)
                .build();

        log.info("Mapped TransactionRequestDto to TransactionResponseDto: {}", result);

        return result;
    }

    public static TransactionResponseDto toDto(Transaction entity, TransactionStatus status) {
        TransactionResponseDto result = TransactionResponseDto
                .builder()
                .amount(entity.getAmount())
                .userId(entity.getUserId())
                .status(status)
                .build();

        log.info("Mapped TransactionEntity to TransactionResponseDto: {}", result);

        return result;
    }

}
