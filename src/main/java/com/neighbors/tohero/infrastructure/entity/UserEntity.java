package com.neighbors.tohero.infrastructure.entity;

import com.neighbors.tohero.common.enums.Role;
import com.neighbors.tohero.infrastructure.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "`User`")
@Builder
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

    public static UserEntity of(String nickName, String email, Role role) {
        return UserEntity.builder()
                .nickName(nickName)
                .email(email)
                .role(role)
                .build();
    }
}
