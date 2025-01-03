package com.neighbors.tohero.infrastructure.query.impl;

import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.exception.user.UserException;
import com.neighbors.tohero.domain.login.model.User;
import com.neighbors.tohero.domain.query.UserRepository;
import com.neighbors.tohero.infrastructure.entity.UserEntity;
import com.neighbors.tohero.infrastructure.mapper.UserMapper;
import com.neighbors.tohero.infrastructure.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(User user) {
        checkExistUser(user);
        UserEntity userEntity = userMapper.toEntity(user);
        userEntityRepository.save(userEntity);
        UserEntity createdUserEntity = userEntityRepository.findByEmail(user.getEmail());
        return userMapper.toDomain(createdUserEntity);
    }

    private void checkExistUser(User user) {
        if(userEntityRepository.existsByEmail(user.getEmail())){
            throw new UserException(BaseResponseStatus.BAD_REQUEST, BaseResponseMessage.이미_존재하는_유저입니다.getMessage());
        }
    }
}
