package com.neighbors.tohero.infrastructure.query.impl;

import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.exception.user.UserException;
import com.neighbors.tohero.domain.domain.user.model.User;
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
        UserEntity existedUserEntity = userEntityRepository.findByEmail(user.getEmail());
        if (existedUserEntity != null) {
            return userMapper.toDomain(existedUserEntity);
        }
        UserEntity userEntity = userMapper.toEntity(user);
        userEntityRepository.save(userEntity);
        UserEntity createdUserEntity = userEntityRepository.findByEmail(user.getEmail());
        return userMapper.toDomain(createdUserEntity);
    }

    @Override
    public void updateUserName(long userId, String nickname) {
        UserEntity matchedUserEntity = userEntityRepository.findByUserId(userId)
                .orElseThrow(() -> new UserException(
                        BaseResponseStatus.BAD_REQUEST,
                        BaseResponseMessage.존재하지_않는_유저입니다.getMessage()
                ));

        matchedUserEntity.changeNickname(nickname);
        userEntityRepository.save(matchedUserEntity);
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
