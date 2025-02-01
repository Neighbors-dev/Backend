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

import java.util.*;
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
            userEntity.setRecommendEntity(recommendEntity);
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
    public void reflectRecommendation(String writer, List<String> recommenderEmails, long userId) {
        List<RecommendEntity> recommendEntities = recommendEntityRepository.findAllByUserEmailIn(recommenderEmails);

        String userIdStr = String.valueOf(userId);
        recommendEntities.stream()
                        .filter(entity ->
                            Arrays.stream(entity.getRecommendedPeopleId().split(","))
                                    .noneMatch(id -> id.equals(userIdStr))
                        )
                        .forEach(entity -> {
                            entity.addRecommendedPeopleId(userId);
                            entity.addRecommendedPeopleName(writer);
                        });

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
    public User getUserAndUpdateRecommenders(Function<UserEntityRepository, Optional<UserEntity>> findUserFunction, String recommenderCode) {
        UserEntity userEntity = getUserEntity(findUserFunction);

        if(recommenderCode != null && !recommenderCode.isEmpty()){
            updateUserRecommenders(userEntity, recommenderCode);
        }

        return userMapper.toDomain(userEntity);
    }

    private void updateUserRecommenders(UserEntity userEntity, String recommenderEmailsDividedBySlash) {
        if(userEntity.getRecommenders().isEmpty()){
            userEntity.setRecommenders(recommenderEmailsDividedBySlash);
        }
        else{
            queueingUserRecommenders(userEntity, recommenderEmailsDividedBySlash);
        }

        //이전 사람 이름 저장
        String lastUserEmail = recommenderEmailsDividedBySlash.split("/")[recommenderEmailsDividedBySlash.split("/").length - 1];
        UserEntity lastUserEntity = getUserEntity(repo -> repo.findByEmail(lastUserEmail));

        boolean isNotExistId = false;
        isNotExistId = Arrays.stream(userEntity.getRecommendEntity().getRecommendedPeopleId().split(","))
                .noneMatch(id -> id.equals(String.valueOf(lastUserEntity.getUserId())));

        if(isNotExistId){
            userEntity.getRecommendEntity().addRecommendedPeopleName(lastUserEntity.getNickName());
            userEntity.getRecommendEntity().addRecommendedPeopleId(lastUserEntity.getUserId());
        }
        userEntityRepository.save(userEntity);
    }

    private void queueingUserRecommenders(UserEntity userEntity, String recommenderEmailsDividedBySlash) {
        List<String> existedRecommendersEmail = new ArrayList<>(Arrays.asList(userEntity.getRecommenders().split("/")));
        List<String> addedRecommendersEmail = Arrays.asList(recommenderEmailsDividedBySlash.split("/"));

        // 순서를 유지하며 중복 제거
        LinkedHashSet<String> uniqueEmails = new LinkedHashSet<>(existedRecommendersEmail);
        uniqueEmails.addAll(addedRecommendersEmail);

        // 마지막 5개만 유지
        List<String> lastFive = new ArrayList<>(uniqueEmails);
        int size = lastFive.size();
        List<String> resultEmails = lastFive.subList(Math.max(0, size - 5), size);

        // 추천인 리스트 업데이트
        userEntity.setRecommenders(String.join("/", resultEmails));
    }
}
