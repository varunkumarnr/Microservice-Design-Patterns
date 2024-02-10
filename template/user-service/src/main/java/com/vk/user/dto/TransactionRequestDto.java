package com.vk.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequestDto {
    private Integer userId;
    private Double amount;
}
