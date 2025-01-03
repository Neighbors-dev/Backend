package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.domain.login.model.User;

public interface UserRepository {
    User createUser(User user);
}
