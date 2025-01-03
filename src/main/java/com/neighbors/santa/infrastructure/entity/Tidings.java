package com.neighbors.santa.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "`Tidings`")
public class Tidings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tidings_id")
    private long tidingsId;

    @Column(name = "tidings_title")
    private String tidingsTitle;

    @Column(name = "tidings_content")
    private String tidingsContent;
}
