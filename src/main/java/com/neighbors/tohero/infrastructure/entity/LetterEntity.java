package com.neighbors.tohero.infrastructure.entity;

import com.neighbors.tohero.infrastructure.entity.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "`Letter`")
public class LetterEntity extends BaseEntity {
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
    private AddressEntity address;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private UserEntity user;


}
