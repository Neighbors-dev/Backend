package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.domain.domain.user.model.UserOpinion;

public interface UserOpinionRepository {
    void createSignOutOpinion(UserOpinion userOpinion);
}
