package com.neighbors.tohero.infrastructure.mapper;

import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.infrastructure.entity.RecommendEntity;
import com.neighbors.tohero.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<String> nameOfRecommendedWriter;
        if(userEntity.getRecommendEntity() == null){
            nameOfRecommendedWriter = new ArrayList<>();
        }
        else{
            nameOfRecommendedWriter = Arrays.stream(userEntity.getRecommendEntity().getRecommendedPeopleName().split(",")).toList();
        }
        return User.of(
                userEntity.getUserId(),
                userEntity.getNickName(),
                userEntity.getEmail(),
                userEntity.getRole(),
                userEntity.getRecommenders(),
                userEntity.isFirstSharing(),
                nameOfRecommendedWriter
        );
    }
}
