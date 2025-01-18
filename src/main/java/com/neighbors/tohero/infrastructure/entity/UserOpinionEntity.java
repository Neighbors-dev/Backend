package com.neighbors.tohero.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "`UserOpinion`")
public class UserOpinionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_opinion_id")
    long userOpinionId;

    @Column(name = "signout_category")
    String signOutCategory;

    @Column(name = "advice_for_service")
    String adviceForService;

    @Column(name = "user_email")
    String userEmail;

    private UserOpinionEntity(String signOutCategory, String adviceForService, String email){
        this.signOutCategory = signOutCategory;
        this.adviceForService = adviceForService;
        this.userEmail = email;
    }

    public static UserOpinionEntity of(String signOutCategory, String adviceForService, String email) {
        return new UserOpinionEntity(signOutCategory, adviceForService, email);
    }
}
