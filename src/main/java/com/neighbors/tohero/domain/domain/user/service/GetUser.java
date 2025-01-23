package com.neighbors.tohero.domain.domain.user.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.domain.query.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class GetUser {
    private final UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.getUser(repo -> repo.findByEmail(email));
    }

    public User getUserById(long userId) {
        return userRepository.getUser(repo -> repo.findByUserId(userId));
    }

    public List<String> getNameOfWritersByUserId(long userId){
        return null;
    }
}
