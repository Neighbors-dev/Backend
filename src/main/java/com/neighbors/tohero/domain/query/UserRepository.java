package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.domain.login.model.User;

public interface UserRepository {
    void createUser(User user);
}
