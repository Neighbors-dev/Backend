package com.neighbors.tohero.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "`Tidings`")
public class TidingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tidings_id")
    private long tidingsId;

    @Column(name = "tidings_title")
    private String tidingsTitle;

    @Column(name = "tidings_content")
    private String tidingsContent;
}
