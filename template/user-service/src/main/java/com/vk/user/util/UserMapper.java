package com.vk.user.util;

import com.vk.user.domain.User;
import com.vk.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class UserMapper {

    public static UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .balance(user.getBalance())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
    public static User toEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .balance(dto.getBalance())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }
}
