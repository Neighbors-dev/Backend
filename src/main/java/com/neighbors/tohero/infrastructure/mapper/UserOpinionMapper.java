package com.neighbors.tohero.infrastructure.mapper;

import com.neighbors.tohero.domain.domain.user.model.UserOpinion;
import com.neighbors.tohero.infrastructure.entity.UserOpinionEntity;
import org.springframework.stereotype.Component;

@Component
public class UserOpinionMapper {

    public UserOpinionEntity toEntity(UserOpinion userOpinion) {
        return UserOpinionEntity.of(userOpinion.getSignOutCategory(), userOpinion.getAdviceForService(), userOpinion.getUserEmail());
    }
}
