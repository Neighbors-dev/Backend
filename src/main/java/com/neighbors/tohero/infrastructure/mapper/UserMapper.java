package com.neighbors.tohero.infrastructure.mapper;

import com.neighbors.tohero.domain.domain.login.model.User;
import com.neighbors.tohero.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toEntity(User user){
        return UserEntity.of(
                user.getUserName(),
                user.getEmail(),
                user.getRole()
        );
    }

    public User toDomain(UserEntity userEntity){
        return User.of(
                userEntity.getUserId(),
                userEntity.getNickName(),
                userEntity.getEmail(),
                userEntity.getRole()
        );
    }
}
