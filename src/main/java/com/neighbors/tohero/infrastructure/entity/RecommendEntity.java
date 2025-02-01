package com.neighbors.tohero.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Recommend`")
@NoArgsConstructor
@Getter
public class RecommendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommend_id")
    private long recommendId;

    @Column(name = "recommendedPeopleName")
    private String recommendedPeopleName;

    @Column(name = "recommendedPeopleId")
    private String recommendedPeopleId = null;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = true)
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

    public void addRecommendedPeopleId(long recommendedPeopleId){
        if(this.recommendedPeopleId != null){
            this.recommendedPeopleId += "," +recommendedPeopleId;
            return;
        }
        this.recommendedPeopleId = String.valueOf(recommendedPeopleId);
    }

    public String getRecommendedPeopleName(){
        if(recommendedPeopleName == null){
            return "";
        }
        return recommendedPeopleName;
    }

    public String getRecommendedPeopleId(){
        if(recommendedPeopleId == null){
            return "";
        }
        return recommendedPeopleId;
    }
}
