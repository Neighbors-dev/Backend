package com.neighbors.tohero.domain.domain.user.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.domain.user.model.UserOpinion;
import com.neighbors.tohero.domain.query.UserOpinionRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CreateUserOpinion {

    private final UserOpinionRepository userOpinionRepository;

    public void createSignOutOpinion(String signOutCategory, String adviceForService, String email){

        UserOpinion userOpinion = UserOpinion.builder()
                .signOutCategory(signOutCategory)
                .adviceForService(adviceForService)
                .userEmail(email)
                .build();

        userOpinionRepository.createSignOutOpinion(userOpinion);
    }
}
