package com.neighbors.tohero.domain.domain.user.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.query.LetterRepository;
import com.neighbors.tohero.domain.query.UserRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class DeleteUser {

    private final UserRepository userRepository;
    private final LetterRepository letterRepository;

    public void signout(long userId){
        letterRepository.remainLetterWithoutUser(userId);
        userRepository.deleteUser(repo -> repo.deleteById(userId));
    }
}
