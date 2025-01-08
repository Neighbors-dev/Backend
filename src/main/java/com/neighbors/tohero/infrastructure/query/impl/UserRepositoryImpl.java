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

import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(User user) {
        try{
            return getUserByEmail(user.getEmail());
        }catch(UserException e){
            UserEntity userEntity = userMapper.toEntity(user);
            userEntityRepository.save(userEntity);

            UserEntity createdUserEntity = userEntityRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new UserException(
                            BaseResponseStatus.NO_RESULT,
                            BaseResponseMessage.존재하지_않는_유저입니다.getMessage()
                    ));;
            return userMapper.toDomain(createdUserEntity);
        }
    }

    @Override
    public User updateUserName(Function<UserEntityRepository, Optional<UserEntity>> findUserFunction, String nickname) {
        UserEntity matchedUserEntity = findUserFunction.apply(userEntityRepository)
                .orElseThrow(() -> new UserException(
                        BaseResponseStatus.BAD_REQUEST,
                        BaseResponseMessage.존재하지_않는_유저입니다.getMessage()
                ));

        matchedUserEntity.changeNickname(nickname);
        UserEntity userEntity = userEntityRepository.save(matchedUserEntity);
        return userMapper.toDomain(userEntity);
    }

    @Override
    public User getUserByEmail(String email) {
        UserEntity userEntity = userEntityRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(
                        BaseResponseStatus.NO_RESULT,
                        BaseResponseMessage.존재하지_않는_유저입니다.getMessage()
                ));

        return userMapper.toDomain(userEntity);
    }
}
