package com.neighbors.tohero.domain.login.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.login.model.User;
import com.neighbors.tohero.domain.query.UserRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CreateUser {

    private final UserRepository userRepository;

    public void createUser(User user) {
        userRepository.createUser(user);
    }
}
