package com.neighbors.tohero.domain.domain.user.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.query.UserOpinionRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CreateUserOpinion {

    private final UserOpinionRepository userOpinionRepository;

    public void createSignOutOpinion(String signOutCategory, String adviceForService){
        userOpinionRepository.createSignOutOpinion(signOutCategory, adviceForService);
    }
}
