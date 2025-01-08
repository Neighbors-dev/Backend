package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.domain.domain.user.model.User;

public interface UserRepository {
    User createUser(User user);
    void updateUserName(long userId, String nickname);
}
