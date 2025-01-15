package com.neighbors.tohero.domain.domain.user.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserOpinion {
    private int opinionId;
    private String signOutCategory;
    private String adviceForService;
    private String userEmail;
}
