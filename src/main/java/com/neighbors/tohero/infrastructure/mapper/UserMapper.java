package com.neighbors.tohero.infrastructure.mapper;

import com.neighbors.tohero.domain.login.model.User;
import com.neighbors.tohero.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toEntity(User user){
        return UserEntity.of(user.getUserName(), user.getEmail(), user.getRole());
    }
}
