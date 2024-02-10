package com.vk.user.domain;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("users")
public class User {
    @Id
    private Long id;
    private String name;
    private Double balance;
    @Column("phoneNumber")
    private String phoneNumber;


    public void updateFieldsFromEntity(User updatedUserEntity , Long id ) {
        this.id =  id;
        this.name = updatedUserEntity.getName();
        this.balance = updatedUserEntity.getBalance();
        this.phoneNumber = updatedUserEntity.getPhoneNumber();
    }
}
