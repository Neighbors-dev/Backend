package com.neighbors.tohero.infrastructure.mapper;

import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.infrastructure.entity.RecommendEntity;
import com.neighbors.tohero.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toNewEntity(User user){
        return UserEntity.returnNewObjectOf(
                user.getUserName(),
                user.getEmail(),
                user.getRole()
        );
    }

    public UserEntity toEntity(User user){
        if(user == null) return null;
        return UserEntity.from(user);
    }

    public User toDomain(UserEntity userEntity){
        return User.of(
                userEntity.getUserId(),
                userEntity.getNickName(),
                userEntity.getEmail(),
                userEntity.getRole(),
                userEntity.getRecommenders()
        );
    }
}
