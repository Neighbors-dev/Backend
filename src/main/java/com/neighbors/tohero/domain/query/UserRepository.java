package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.infrastructure.entity.UserEntity;
import com.neighbors.tohero.infrastructure.repository.UserEntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface UserRepository {
    User createUser(User user);
    User updateUserName(Function<UserEntityRepository, Optional<UserEntity>> findUserFunction, String nickname);
    User getUser(Function<UserEntityRepository, Optional<UserEntity>> findUserFunction);
    void deleteUser(Consumer<UserEntityRepository> findUserConsumer);
    void reflectRecommendation(String writer, List<String> recommenderEmails);
    void reflectRecommendation(String writer, List<String> recommenderEmails, long userId);
    User getUserForSharing(Function<UserEntityRepository, Optional<UserEntity>> findUserFunction);
    User getUserAndUpdateRecommenders(Function<UserEntityRepository, Optional<UserEntity>> findUserFunction, String recommenderCode);
}
