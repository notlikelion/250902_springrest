package com.example.springrest.service;

import com.example.springrest.domain.User;
import com.example.springrest.dto.UserDto;
import com.example.springrest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // 의존성 주입
@Transactional(readOnly = true) // 읽기 조회
public class UserService {
    private final UserRepository userRepository;

    // CRUD
    // Create : insert, add, [post]
    // Read   : select, [get]
    // Update : update, [patch, put]
    // Delete : [delete], remove

    // CREATE
    @Transactional // 쓰기 조회 락
    public UserDto.Response createUser(UserDto.CreateRequest dto) {
        User savedUser = userRepository.save(dto.toEntity());
        // PK -> auto increment, audit(생성, 수정일...)
        return UserDto.Response.fromEntity(savedUser);
    }

    // READ

}
