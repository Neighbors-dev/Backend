package com.neighbors.tohero.domain.domain.user.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.query.UserRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class UpdateUser {

    private final UserRepository userRepository;

    public void updateUserName(long userId, String nickname) {
        userRepository.updateUserName(userId, nickname);
    }
}
