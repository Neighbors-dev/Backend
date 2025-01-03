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

    @Column(name = "notice_title")
    private String noticeTitle;

    @Column(name = "notice_content")
    private String noticeContent;
}
