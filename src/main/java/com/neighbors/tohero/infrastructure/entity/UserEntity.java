package com.neighbors.tohero.infrastructure.entity;

import com.neighbors.tohero.common.enums.Role;
import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.infrastructure.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "`User`")
@Getter
@AllArgsConstructor
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

    @Setter
    @Column(name = "isFirstSharing", nullable = false)
    private boolean isFirstSharing = true;

    @Setter
    @Column(name = "recommenders", nullable = true)
    private String recommenders;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "recommend_id", nullable = true)
    private RecommendEntity recommendEntity;

    public UserEntity() {
    }

    public UserEntity(String nickName, String email, Role role){
        this.nickName = nickName;
        this.email = email;
        this.role = role;
    }

    public void changeNickname(final String nickName){
        this.nickName = nickName;
    }

    public static UserEntity returnNewObjectOf(String nickName, String email, Role role) {
        return new UserEntity(nickName, email, role);
    }

    public static UserEntity from(User user) {
        return new UserEntity(user.getUserId(), user.getUserName(), user.getEmail(), user.getRole(), user.isFirstSharing(), user.getRecommenders(), null);
    }

    public String getRecommenders() {
        if(recommenders == null) return "";
        return recommenders;
    }

    public void setRecommenders(RecommendEntity recommendEntity) {
        this.recommendEntity = recommendEntity;
    }
}
