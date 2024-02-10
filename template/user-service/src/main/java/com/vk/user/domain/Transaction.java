package com.vk.user.domain;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@Table("transactions")
public class Transaction {
    @Id
    private Integer id;
    private Integer userId;
    private Double amount;
    private LocalDateTime transactionDate;
}
