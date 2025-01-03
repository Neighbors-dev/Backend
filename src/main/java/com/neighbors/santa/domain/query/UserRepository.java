package com.neighbors.santa.domain.query;

import com.neighbors.santa.domain.login.model.User;

public interface UserRepository {
    void createUser(User user);
}
