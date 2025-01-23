package com.neighbors.tohero.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Recommend`")
@NoArgsConstructor
public class RecommendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommend_id")
    private long recommendId;

    @Column(name = "recommendedPeopleName")
    @Getter
    private String recommendedPeopleName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    public RecommendEntity(UserEntity user){
        this.userEntity = user;
    }

    public void addRecommendedPeopleName(String recommendedPeopleName){
        //todo 만약 나중에 문제가 될 시, 닉네임 중복은 허용안되게 설정할 것
        if(this.recommendedPeopleName != null){
            this.recommendedPeopleName += "," +recommendedPeopleName;
            return;
        }
        this.recommendedPeopleName = recommendedPeopleName;
    }
}
