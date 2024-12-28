package com.neighbors.santa.domain.model;

import lombok.Builder;

@Builder
public class User {
    private int userId;
    private String userName;
    private String email;
}
