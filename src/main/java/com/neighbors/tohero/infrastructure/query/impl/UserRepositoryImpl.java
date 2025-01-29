package com.neighbors.tohero.infrastructure.query.impl;

import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.exception.user.UserException;
import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.domain.query.UserRepository;
import com.neighbors.tohero.infrastructure.entity.RecommendEntity;
import com.neighbors.tohero.infrastructure.entity.UserEntity;
import com.neighbors.tohero.infrastructure.mapper.UserMapper;
import com.neighbors.tohero.infrastructure.repository.RecommendEntityRepository;
import com.neighbors.tohero.infrastructure.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityRepository userEntityRepository;
    private final RecommendEntityRepository recommendEntityRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(User user) {
        try{
            return getUser(repo -> repo.findByEmail(user.getEmail()));
        }catch(UserException e){
            UserEntity userEntity = userMapper.toNewEntity(user);
            RecommendEntity recommendEntity = new RecommendEntity(userEntity);
            userEntity.setRecommenders(recommendEntity);
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
    public User getUser(Function<UserEntityRepository, Optional<UserEntity>> findUserFunction) {
        UserEntity userEntity = getUserEntity(findUserFunction);

        return userMapper.toDomain(userEntity);
    }

    private UserEntity getUserEntity(Function<UserEntityRepository, Optional<UserEntity>> findUserFunction) {
        UserEntity userEntity = findUserFunction.apply(userEntityRepository)
                .orElseThrow(() -> new UserException(
                        BaseResponseStatus.NO_RESULT,
                        BaseResponseMessage.존재하지_않는_유저입니다.getMessage()
                ));
        return userEntity;
    }

    @Override
    public void deleteUser(Consumer<UserEntityRepository> findUserConsumer) {
        findUserConsumer.accept(userEntityRepository);
    }

    @Override
    public void reflectRecommendation(String writer, List<String> recommenderEmails) {
        List<RecommendEntity> recommendEntities = recommendEntityRepository.findAllByUserEmailIn(recommenderEmails);

        recommendEntities
                .forEach(recommendEntity -> recommendEntity.addRecommendedPeopleName(writer));

        recommendEntityRepository.saveAll(recommendEntities);
    }

    @Override
    public User getUserForSharing(Function<UserEntityRepository, Optional<UserEntity>> findUserFunction) {
        UserEntity matchedUserEntity = getUserEntity(findUserFunction);

        User user = userMapper.toDomain(matchedUserEntity);
        if(matchedUserEntity.isFirstSharing()){
            matchedUserEntity.setFirstSharing(false);
            userEntityRepository.save(matchedUserEntity);
        }

        return user;
    }

    @Override
    public void updateUserRecommenders(User user, String recommenderEmailsDividedBySlash) {
        if(user.getRecommenders().isEmpty()){
           user.setRecommenders(recommenderEmailsDividedBySlash);
        }
        else{
            queueingUserRecommenders(user, recommenderEmailsDividedBySlash);
        }
        userEntityRepository.save(userMapper.toEntity(user));
    }

    private void queueingUserRecommenders(User user, String recommenderEmailsDividedBySlash) {
        List<String> existedRecommendersEmail = Arrays.stream(user.getRecommenders().split("/")).toList();
        List<String> addedRecommendersEmail = Arrays.stream(recommenderEmailsDividedBySlash.split("/")).toList();

        String result = user.getRecommenders() + "/" + recommenderEmailsDividedBySlash;
        if(existedRecommendersEmail.size() + addedRecommendersEmail.size() > 5){
            existedRecommendersEmail.addAll(addedRecommendersEmail);

            int size = existedRecommendersEmail.size();
            List<String> lastFive = existedRecommendersEmail.subList(Math.max(0, size - 5), size);

            result = String.join("/", lastFive);
        }
        user.setRecommenders(result);
    }
}
