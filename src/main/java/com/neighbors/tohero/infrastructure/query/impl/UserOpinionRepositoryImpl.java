package com.neighbors.tohero.infrastructure.query.impl;

import com.neighbors.tohero.domain.domain.user.model.UserOpinion;
import com.neighbors.tohero.domain.query.UserOpinionRepository;
import com.neighbors.tohero.infrastructure.entity.UserOpinionEntity;
import com.neighbors.tohero.infrastructure.mapper.UserOpinionMapper;
import com.neighbors.tohero.infrastructure.repository.UserOpinionEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserOpinionRepositoryImpl implements UserOpinionRepository {

    private final UserOpinionMapper userOpinionMapper;
    private final UserOpinionEntityRepository userOpinionEntityRepository;

    @Override
    public void createSignOutOpinion(UserOpinion userOpinion) {
        UserOpinionEntity userOpinionEntity = userOpinionMapper.toEntity(userOpinion);
        userOpinionEntityRepository.save(userOpinionEntity);
    }
}
