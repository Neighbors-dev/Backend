package com.neighbors.santa.infrastructure.entity;

import com.neighbors.santa.common.enums.Role;
import com.neighbors.santa.infrastructure.entity.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "`User`")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "nickname", nullable = false)
    private String nickName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role", nullable = false)
    private Role role;
}
