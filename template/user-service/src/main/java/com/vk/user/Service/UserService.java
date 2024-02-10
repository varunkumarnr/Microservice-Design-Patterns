package com.vk.user.Service;

import com.vk.user.domain.User;
import com.vk.user.dto.UserDto;
import com.vk.user.repository.UserRepository;
import com.vk.user.util.UserMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<UserDto> findAll() {
        return userRepository.findAll()
                .map(UserMapper::mapToDto);
    }

    public Mono<UserDto> findById(Long userId) {
        return userRepository.findById(userId)
                .map(UserMapper::mapToDto);
    }
    public Mono<UserDto> saveUser(Mono<UserDto> userDtoMono) {
        return userDtoMono
                .map(UserMapper::toEntity)
                .flatMap(userRepository::save)
                .map(UserMapper::mapToDto);
    }

    public Mono<UserDto> updateUser(Mono<UserDto> userDtoMono, Long userId) {
        return userDtoMono
                .flatMap(updatedUserDto -> userRepository.findById(userId)
                        .flatMap(existingUser -> {
                            User updatedUserEntity = UserMapper.toEntity(updatedUserDto);
                            existingUser.updateFieldsFromEntity(updatedUserEntity, userId);
                            return userRepository.save(existingUser);
                        })
                        .switchIfEmpty(Mono.error(new Exception("User not found"))))
                .map(UserMapper::mapToDto);
    }
}
