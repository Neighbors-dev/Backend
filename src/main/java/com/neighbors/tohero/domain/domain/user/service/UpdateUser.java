package com.neighbors.tohero.domain.domain.user.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.domain.query.UserRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class UpdateUser {

    private final UserRepository userRepository;

    public User updateUserName(long userId, String nickname) {
        return userRepository.updateUserName(repo -> repo.findByUserId(userId), nickname);
    }

    public User updateUserName(String email, String nickname){
        return userRepository.updateUserName(repo -> repo.findByEmail(email), nickname);
    }
}
