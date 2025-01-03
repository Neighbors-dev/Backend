package com.neighbors.tohero.infrastructure.entity;

import com.neighbors.tohero.common.enums.Role;
import com.neighbors.tohero.infrastructure.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "`User`")
@Getter
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

    public UserEntity() {
    }

    public UserEntity(String nickName, String email, Role role){
        this.nickName = nickName;
        this.email = email;
        this.role = role;
    }

    public static UserEntity of(String nickName, String email, Role role) {
        return new UserEntity(nickName, email, role);
    }
}
