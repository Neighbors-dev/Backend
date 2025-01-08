package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.infrastructure.entity.UserEntity;
import com.neighbors.tohero.infrastructure.repository.UserEntityRepository;

import java.util.Optional;
import java.util.function.Function;

public interface UserRepository {
    User createUser(User user);
    User updateUserName(Function<UserEntityRepository, Optional<UserEntity>> findUserFunction, String nickname);
    User getUser(Function<UserEntityRepository, Optional<UserEntity>> findUserFunction);
}
