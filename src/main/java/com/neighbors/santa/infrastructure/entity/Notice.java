package com.neighbors.santa.infrastructure.entity;

import com.neighbors.santa.infrastructure.entity.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(schema = "`Notice`")
public class Notice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private long noticeId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
}
