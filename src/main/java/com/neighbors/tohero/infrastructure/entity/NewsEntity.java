package com.neighbors.tohero.infrastructure.entity;

import com.neighbors.tohero.infrastructure.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "`News`")
@Getter
public class NewsEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    long newsId;

    @Column(name = "news_title")
    String newsTitle;

    @Column(name = "news_content")
    String newsContent;
}
