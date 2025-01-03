package com.neighbors.santa.infrastructure.entity;

import com.neighbors.santa.infrastructure.entity.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "`Letter`")
public class Letter extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_id")
    private long letterId;

    @Column(name = "letter_content", nullable = false)
    private String letterContent;

    @Column(name = "is_opened", nullable = false)
    private boolean isOpened;

    @Column(name = "from_user_name", nullable = false)
    private String fromUserName;

    @Column(name = "target_name", nullable = true)
    private String targetName;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;


}
